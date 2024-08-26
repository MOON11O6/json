package com.example.practice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "json")
public class PracticeEntity {
    @Id
    @Column(name = "user_id")
    private long user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "post_count")
    private int post_count;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    @Override
    public String toString() {
        return "PracticeEntity{user_id=" + user_id + ", username='" + username + "', post_count=" + post_count + "}";
    }
}