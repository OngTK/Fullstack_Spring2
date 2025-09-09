package example.day05_250909._02_WebCrawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

/**
 * Selenium Crawling
 * @since 2025.09.09
 * */

@Service
public class CrawlingService {

    // [1] 다음 날씨 크롤링
    public Map<String, String> task1(){
        // [1.1] 크롬 설치
        WebDriverManager.chromedriver().setup();
        // [1.2] 크롬 옵션 객체
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new","--diable-gpu"); // 크롬을 사용하지만, 화면에 브라우저 창을 띄우지 않음
        // [1.3] 크롬 옵션을 가진 웹 드라이버·셀레니움 객체 생성
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        // [1.4] 크롤링 할 웹 주소
        String url = "https://weather.daum.net/";
        // [1.5] 크롤링할 웹 주소 가져오기
        webDriver.get(url);
        // [1.6] 셀레니움으로 크롤링 하기 전 대기시키기
        // 동적 페이지의 경우 화면이 출력될 때까지 잠시 시간이 필요
        WebDriverWait wait = new WebDriverWait(webDriver , Duration.ofSeconds(3) ); // 5초 대기 후 크롤링
        // [1.7] 대기 후 크롤링할 HTML-CSS 분석
        // (1) 지역 , .info_location .tit_location
        WebElement location
                = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector( ".info_location .tit_location" )));
        System.out.println("location = " + location);

        // (2) 온도 , .wrap_weather .num_deg
        WebElement temp =
                wait.until(ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".wrap_weather .num_deg" ) ));
        System.out.println("temp = " + temp);

        // (3) 상태 , .wrap_weather .txt_sub
        WebElement status =
                wait.until(ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".wrap_weather .txt_sub" ) ));
        System.out.println("status = " + status);


        return null;
    } // func end

} // class end
