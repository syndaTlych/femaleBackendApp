package com.example.female.entities;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long postId;
    private String title;
    private String description;



    @Temporal(TemporalType.DATE)
    private Date created;
    @ManyToOne
    private UserEntity user;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments;


}
