package example.day04_250908._02_WebCrawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {

    // [1] 뉴스 제목 크롤링
    public List<String> task1() {
        System.out.println("CrawlingService.task1");

        // [1.1] 크롤링할 웹페이지 주소
        String URL = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";
        List<String> titleList = new ArrayList<>();
        try {
            // [1.2] Jsoup 을 이용한 HTML 가져오기
            // 주의 import org.jsoup.nodes.Document;
            // HTML을 통째로 가져오기
            Document document = Jsoup.connect(URL).get();
//             System.out.println("Document : " + document);

            // [1.3] HTML 식별자 확인
            // 식별자 안에 내용만 가져오기
            Elements aList = document.select(".titles > a");
            System.out.println("aList : " + aList);

            // [1.4] 가져온 마크업을 반복하여, 텍스트만 추출
            for (Element a : aList) {
                String title = a.text();    // == js innerHTML : 마크업 내의 내용을 가져옴
                if(title.isBlank()) continue;
                titleList.add(title);
            }
            return titleList;
        } catch (IOException e) {
            System.out.println("CrawlingService.task1 " + e);
        }
        return null;
    } // func end


} // class end