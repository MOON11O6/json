package com.example.practice;

public class PracticeDto {

    private long user_id;

    private String username;

    private int post_count;

    public long getUser_id() {
        return user_id;
    }

    ////
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    ////
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    ////
    public int getPost_count(){
        return post_count;
    }
    public void setPost_count(int post_count){
        this.post_count = post_count;
    }
}
