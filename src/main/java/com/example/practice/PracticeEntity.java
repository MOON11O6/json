package com.example.practice;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "json") // 데이터베이스의 테이블 이름
public class PracticeEntity {

    @Id
    @Column(name = "user_id") // 데이터베이스의 컬럼 이름
    @JsonProperty("user_id") // JSON의 필드 이름
    private long userId;

    @Column(name = "username") // 데이터베이스의 컬럼 이름
    @JsonProperty("username") // JSON의 필드 이름
    private String username;

    @Column(name = "post_count") // 데이터베이스의 컬럼 이름
    @JsonProperty("post_count") // JSON의 필드 이름
    private int postCount;

    // 기본 생성자
    public PracticeEntity() {}

    // Getter 및 Setter
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    // toString() 메소드
    @Override
    public String toString() {
        return "PracticeEntity{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", postCount=" + postCount +
                '}';
    }

    // hashCode() 메소드
    @Override
    public int hashCode() {
        return Objects.hash(userId, username, postCount);
    }

    // equals() 메소드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PracticeEntity that = (PracticeEntity) o;
        return userId == that.userId &&
                postCount == that.postCount &&
                Objects.equals(username, that.username);
    }
}