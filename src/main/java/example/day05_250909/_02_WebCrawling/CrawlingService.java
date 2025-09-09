package example.day05_250909._02_WebCrawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Selenium Crawling
 *
 * @since 2025.09.09
 *
 */

@Service
public class CrawlingService {

    // [1] 다음 날씨 크롤링
    public Map<String, String> task1() {
        // [1.1] 크롬 설치
        WebDriverManager.chromedriver().setup();
        // [1.2] 크롬 옵션 객체
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new", "--diable-gpu"); // 크롬을 사용하지만, 화면에 브라우저 창을 띄우지 않음
        // [1.3] 크롬 옵션을 가진 웹 드라이버·셀레니움 객체 생성
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        // [1.4] 크롤링 할 웹 주소
        String url = "https://weather.daum.net/";
        // [1.5] 크롤링할 웹 주소 가져오기
        webDriver.get(url);
        // [1.6] 셀레니움으로 크롤링 하기 전 대기시키기
        // 동적 페이지의 경우 화면이 출력될 때까지 잠시 시간이 필요
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3)); // 5초 대기 후 크롤링
        // [1.7] 대기 후 크롤링할 HTML-CSS 분석
        // (1) 지역 , .info_location .tit_location
        WebElement location
                = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".info_location .tit_location")));
        System.out.println("location = " + location);

        // (2) 온도 , .wrap_weather .num_deg
        WebElement temp =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".wrap_weather .num_deg")));
        System.out.println("temp = " + temp);

        // (3) 상태 , .wrap_weather .txt_sub
        WebElement status =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".wrap_weather .txt_sub")));
        System.out.println("status = " + status);

        // [1.8] 크롤링한 요소를 추출하여 맵에 반환
        Map<String, String> map = new HashMap();
        map.put("위치", location.getText());
        map.put("온도", temp.getText());
        map.put("상태", status.getText());

        // [1.9] 셀레니움 수동 종료
        webDriver.quit();

        // [1.10] 결과 반환
        return map;
    } // func end

    // [2] CGV 관람평
    // 특징 : 무한스크롤
    public List<String> task2() {
        // [2.1] chrome 설치
        WebDriverManager.chromedriver().setup();
        // [2.2] Chrome 옵션
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new", "--disable-gpu");
        // [2.3] 셀레니움·웹드라이버 객체 생성
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        // [2.4] 크롤링할 웹주소
        String url = "https://cgv.co.kr/cnm/cgvChart/movieChart/89833";
        // [2.5] 셀레니움 객체에 웹주소 삽입
        webDriver.get(url);

        // [2.7] 가져온 리뷰들을 리스트에 담기
        List<String> list = new ArrayList<>();

        // 5번 반복
        for (int i = 1; i <= 5; i++) {

            // 1개 Element element = webDriver.findElement();
            // N개 List<WebElement> elements = webDriver.findElements()
            // [2.6] HTML CSS 가져오기
            List<WebElement> webElements = webDriver.findElements(By.cssSelector(".reveiwCard_txt__RrTgu"));

            // [2.7] 가져온 리뷰들을 리스트에 담기
            int startCount = list.size();
            for (WebElement element : webElements) {
                String review = element.getText();
                System.out.println("review = " + review);
                // 중복 방지
                if (list.contains(review)) {
                    continue;
                }
                list.add(review);
            }
            int endCount = list.size();
            if (startCount == endCount) break;

            // 비어있으면 밖에 for(5번) 반복문을 종료

            //  ============================ 자바에서 JS 사용 ============================
            // 스크롤을 내리는 동작 = event
            // [2.8] 셀레니움 객체를 JS 조작 객체로 변환
            JavascriptExecutor js = (JavascriptExecutor) webDriver;

            // [2.9]현재 body에서 scroll의 위치를 높이만큼, 즉 최 하단으로 이동시킴
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("CrawlingService.task2 " + e);
            }
        }


        return list;
    } // func end

} // class end
