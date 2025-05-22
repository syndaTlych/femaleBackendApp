package com.example.female.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.sql.Timestamp;
import java.util.List;

@Entity

@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long commentId;
    //private Date created;
    private Timestamp created;
    private String body;
    private String attribute;

    @ManyToOne
    private Post post;

    @OneToMany(mappedBy = "comment",cascade=CascadeType.ALL)
    private List<Likes> likes ;


}
