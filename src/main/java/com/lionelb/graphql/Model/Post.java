package com.lionelb.graphql.Model;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String date;
    private String title;


    @Column(name = "body", columnDefinition="TEXT")
    private String body;

    public Post (){

    }

    public Post(String date, String title, String body) {
        this.date = date;
        this.title = title;
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
