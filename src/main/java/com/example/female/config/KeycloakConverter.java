package com.example.female.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.*;

public class KeycloakConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final String REALM_ROLES_CLAIM_NAME = "realm_access";
    private static final String ROLES_CLAIM_NAME = "roles";
    private static final String CLIENT_ROLES_CLAIM_NAME = "resource_access";
    private static final String ROLES_PREFIX = "ROLE_";
    private static final String GROUPS_PREFIX = "GROUP_";

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt.getClaims());
        return new JwtAuthenticationToken(jwt, authorities);
    }

    private Collection<GrantedAuthority> extractAuthorities(Map<String, Object> claims) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.addAll(extractRealmAuthorities(claims));
        authorities.addAll(extractClientAuthorities(claims));
        authorities.addAll(extractGroups(claims));

        return authorities;
    }

    private Collection<GrantedAuthority> extractRealmAuthorities(Map<String, Object> claims) {
        if (!claims.containsKey(REALM_ROLES_CLAIM_NAME)) {
            return Collections.emptyList();
        }

        Map<String, Object> realmAccess = (Map<String, Object>) claims.get(REALM_ROLES_CLAIM_NAME);
        if (realmAccess == null || !realmAccess.containsKey(ROLES_CLAIM_NAME)) {
            return Collections.emptyList();
        }

        List<String> roles = (List<String>) realmAccess.get(ROLES_CLAIM_NAME);
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(ROLES_PREFIX + role.toUpperCase()));
        }

        return authorities;
    }

    private Collection<GrantedAuthority> extractClientAuthorities(Map<String, Object> claims) {
        if (!claims.containsKey(CLIENT_ROLES_CLAIM_NAME)) {
            return Collections.emptyList();
        }

        Map<String, Object> resourceAccess = (Map<String, Object>) claims.get(CLIENT_ROLES_CLAIM_NAME);
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Map.Entry<String, Object> entry : resourceAccess.entrySet()) {
            Map<String, Object> clientData = (Map<String, Object>) entry.getValue();
            if (clientData.containsKey(ROLES_CLAIM_NAME)) {
                List<String> roles = (List<String>) clientData.get(ROLES_CLAIM_NAME);
                for (String role : roles) {
                    authorities.add(new SimpleGrantedAuthority(ROLES_PREFIX + role.toUpperCase()));
                }
            }
        }

        return authorities;
    }

    private Collection<GrantedAuthority> extractGroups(Map<String, Object> claims) {
        // Implémentation personnalisée si tu veux gérer des groupes Keycloak
        return Collections.emptyList(); // ou une vraie logique
    }
}
