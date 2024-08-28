package com.example.practice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class PracticeController {

    public final PracticeRepository practiceRepository;

    @Autowired
    private PracticeService practiceService;

    //update라는 url로 요청이 올경우 practiceService의 update메서드를 실행 후 리턴
    @GetMapping("/update")
    public String update() {
        practiceService.update();
        return "redirect:/";
    }

//    //db에 있는 데이터를 html로 출력
//    @GetMapping("/sum")
//    public String readjson(Model model) {
//    List<PracticeEntity> result = practiceRepository.findAll();
//    model.addAttribute("print", result);
//        return "sum";
//    }

    //db에서 url에 id를 입력하여 해당 데이터 출력하기
    @GetMapping("/sum/{user_id}")
    public String detail(@PathVariable("user_id") Long user_id, Model model){
        Optional<PracticeEntity> result = practiceRepository.findById(user_id);
        if (result.isPresent()) {
            model.addAttribute("print", result.get()); // 엔티티 존재 시
        } else {
            model.addAttribute("print", null); // 엔티티가 없을 시
        }
        return "sum";
    }

    //INPUT으로 받은 값을 DB에서 찾아 해당 데이터 출력
    @GetMapping("/sum")
    public String detail2(@RequestParam(value = "sum", required = false) Long user_id, Model model) {
        if (user_id == null) {
            model.addAttribute("print", "Please provide an ID.");
            return "sum";
        }
        Optional<PracticeEntity> result = practiceRepository.findById(user_id);
        if (result.isPresent()) {
            model.addAttribute("print", result.get());
        } else {
            model.addAttribute("print", "No data found for this ID.");
        }
        return "sum";
    }

    // 검색 페이지를 보여주는 메소드
    @GetMapping("/search")
    public String showSearchPage() {
        return "search"; // Thymeleaf 템플릿 파일 이름 (search.html)
    }

    // 검색 결과를 Thymeleaf 템플릿으로 전달하는 메소드
    @GetMapping("/search2")
    public String getEntitiesByUserIds(@RequestParam(required = false) String ids, Model model) {
        if (ids == null || ids.trim().isEmpty()) {
            // ids 파라미터가 없거나 빈 문자열인 경우 처리
            model.addAttribute("error", "Please provide at least one User ID.");
            return "searchResults"; // 에러 메시지를 포함한 결과 페이지
        }
        List<Long> userIdList;
        try {
            userIdList = Stream.of(ids.split(","))
                    .map(String::trim) // 공백 제거
                    .map(Long::parseLong) // Long으로 변환
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            // ID 변환 중 에러 발생 시 처리
            model.addAttribute("error", "Invalid User ID format. Please enter valid numbers.");
            return "searchResults"; // 에러 메시지를 포함한 결과 페이지
        }
        //입력받은 id값의 데이터들중 post_count의 합을 구함
        List<PracticeEntity> entities = practiceService.getEntitiesByUserIds(userIdList);
        int totalPostCount = entities.stream().mapToInt(PracticeEntity::getPostCount).sum();

        // Thymeleaf 템플릿에 데이터를 전달
        model.addAttribute("entities", entities);
        model.addAttribute("totalPostCount", totalPostCount);
        return "searchResults"; // Thymeleaf 템플릿 파일 이름 (searchResults.html)
    }
}