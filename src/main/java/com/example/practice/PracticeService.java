package com.example.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PracticeService {

    //외부에서 관리되는 객체를 가져옴 (IOC)
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PracticeRepository practiceRepository;

    //DB에 JSON 데이터 저장
    @Transactional
    public void update() {
        try {
            //ClassPathResource는 src/main/resources파일을 먼저 읽음
            //json파일을 읽게함
            ClassPathResource resource = new ClassPathResource("user-data.json");

            List<PracticeEntity> entities = objectMapper.readValue(resource.getInputStream(),
                    new TypeReference<List<PracticeEntity>>() {});

            practiceRepository.saveAll(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<PracticeEntity> getEntitiesByUserIds(List<Long> userIds) {
        return practiceRepository.findAllByUserIdIn(userIds);
    }
    public List<PracticeEntity> getAllUsers() {
        return practiceRepository.findAll();
    }
}

