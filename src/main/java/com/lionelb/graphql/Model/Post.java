package com.lionelb.graphql.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private Date date;
    private String title;
    private File body;

    public Post (){

    }

    public Post(Date date, String title, File body) {
        this.date = date;
        this.title = title;
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public File getBody() {
        return body;
    }

    public void setBody(File body) {
        this.body = body;
    }
}
