package com.example.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticeRepository extends JpaRepository<PracticeEntity, Long> {
    List<PracticeEntity> findAllByUserIdIn(List<Long> userIds);
}
