package com.example.female.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user_group")

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Long idGroup;
    @Column(nullable = false)
    private String name;

    private String description;
    @ManyToMany
    @JoinTable(name ="group_users", joinColumns = @JoinColumn(name = "group_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> members = new HashSet<>();
}


