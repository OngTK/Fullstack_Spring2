package example.day05_250909._02_WebCrawling;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Selenium Crawling
 * @since 2025.09.09
 * */

@RestController
@RequestMapping("/task/day05")
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    // [1] 다음 날씨 크롤링
    @GetMapping("/crawling1")
    public Map<String, String> task1(){
        System.out.println("CrawlingController.task1");

        return crawlingService.task1();
    } // func end

    // [2] CGV 관람평
    // 특징 : 무한스크롤
    @GetMapping("/crawling2")
    public List<String> task2(){
        System.out.println("CrawlingController.task2");

        return crawlingService.task2();
    } // func end


} // class end
