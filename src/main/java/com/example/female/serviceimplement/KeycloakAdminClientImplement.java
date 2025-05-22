package com.example.female.serviceimplement;

import com.example.female.services.KeycloakAdminClientInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class KeycloakAdminClientImplement implements KeycloakAdminClientInterface {

    private final String serverUrl = "http://localhost:8080";
    private final String realm = "your-realm"; // remplace par le vrai realm
    private final String adminUsername = "admin";
    private final String adminPassword = "admin";
    private final String clientId = "admin-cli";

    private final RestTemplate restTemplate = new RestTemplate();

    private String getAdminAccessToken() {
        String tokenUrl = serverUrl + "/realms/master/protocol/openid-connect/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "username=" + adminUsername +
                "&password=" + adminPassword +
                "&grant_type=password" +
                "&client_id=" + clientId;

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, Map.class);

        return (String) response.getBody().get("access_token");
    }

    @Override
    public void createUser(String username, String password) {
        String token = getAdminAccessToken();

        String url = serverUrl + "/admin/realms/" + realm + "/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("enabled", true);
        user.put("email", username + "@example.com");

        Map<String, Object> credential = new HashMap<>();
        credential.put("type", "password");
        credential.put("value", password);
        credential.put("temporary", false);
        user.put("credentials", Collections.singletonList(credential));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Erreur création utilisateur : " + response.getBody());
        }
    }
}
