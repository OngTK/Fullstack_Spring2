package example.day04_250908._02_WebCrawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.crypto.ExemptionMechanism;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            // System.out.println("Document : " + document);

            // [1.3] HTML 식별자 확인
            // 식별자 안에 내용만 가져오기
            Elements aList = document.select(".titles > a");
            System.out.println("aList : " + aList);

            // [1.4] 가져온 마크업을 반복하여, 텍스트만 추출
            for (Element a : aList) {
                String title = a.text();    // == js innerHTML : 마크업 내의 내용을 가져옴
                if (title.isBlank()) continue;
                titleList.add(title);
            }
            return titleList;
        } catch (IOException e) {
            System.out.println("CrawlingService.task1 " + e);
        }
        return null;
    } // func end

    // [2] yes24 책 정보 크롤링
    public List<Map<String, String>> task2() {
        List<Map<String, String>> bookList = new ArrayList<>();

        try {
            for (int page = 1; page <= 3; page++) {

                String url = "https://www.yes24.com/product/category/daybestseller?categoryNumber=001&pageNumber=" + page + "&pageSize=24&type=day";
                Document document = Jsoup.connect(url).get();
                Elements titles = document.select(".info_name > .gd_name");
//            Elements subTitles = document.select(".info_name > .gd_nameE");
                Elements authors = document.select(".info_pubGrp > .info_auth > a:nth-child(1)");
                Elements prices = document.select(".info_price > .txt_num > .yes_b");
                Elements pictures = document.select(".img_bdr > .lazy");

//            System.out.println(titles);
//            System.out.println(subTitles);
//            System.out.println(authors);
//            System.out.println(prices);

                for (int i = 0; i < titles.size(); i++) {
                    String title = titles.get(i).text();
//                String subtitle = subTitles.get(i).text();
                    String author = authors.get(i).text();
                    String price = prices.get(i).text();
                    String picture = pictures.get(i).attr("data-original");

                    Map<String, String> book = new HashMap<>();
                    book.put("title", title);
//                book.put("subtitle", subtitle);
                    book.put("author", author);
                    book.put("price", price);
                    book.put("picture", picture);

                    bookList.add(book);
                }
            }
        } catch (Exception e) {
            System.out.println("CrawlingService.task2 " + e);
        }
        return bookList;
    } // func end

    // [3] 다음 날씨 정보 크롤링
    public Map<String, String> task3(){
        Map<String, String> map = new HashMap<>();
        try{
            String url = "https://weather.daum.net/";
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select(".info_weather .num_deg");
            // .info_weather 는 JS에서 동적으로 데이터를 삽입하므로 Jsoup으로 크롤링되지 않음

            System.out.println("elements = " + elements);

        } catch (Exception e) {
            System.out.println("CrawlingService.task3 " + e);
        }
        return map;
    } //
    // 동적페이지이므로 Jsoup으로 크롤링이 되지 않음!!!!!!!!!!!!!!!!!!!!!!!

} // class end