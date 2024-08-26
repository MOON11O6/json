package com.example.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//    //post-count 값 총합
//    public int calculateSum(){
//        List<PracticeEntity> entities = practiceRepository.findAll();
//        return entities.stream().mapToInt(PracticeEntity::getPost_count).sum();
//
//    }


}
