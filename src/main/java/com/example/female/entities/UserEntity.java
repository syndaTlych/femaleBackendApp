package com.example.female.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
//@Data
@Table(name ="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column( length =10, nullable=true)
    @Size(max = 10, message = "le nom doit pas passer 10 characters")
    private String fullName;

    private String username;
    @Column(length = 100, nullable=false, unique = true )
    private String email;
    private String password;
    private String confirmPassword;
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();





    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List <Post> posts;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <Comment> comments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "likeId",referencedColumnName = "likeId")
    private Likes like;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List <CycleInfo> menstrualCycles ;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PregnancyTracker> pregnancyTrackers;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Notification> notifications;
    @ManyToMany(mappedBy = "members")
    private Set<Group> groups = new HashSet<>();


}



