package example.day12_250930;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/axios")
@Log4j2
public class AxiosController {

    // [1] axios 통신
    @GetMapping
    public int axios1 (){
        return 10; // 임의 값
    } // func end

    // [2] 로그인
    @PostMapping
    public boolean axios2 (@RequestBody Map<String, String> map, HttpSession session) {

        // [2.1] loginId 정보를 session에 등록
        String id = map.get("id");
        session.setAttribute("loginId", id);

        return true;
    } // func end

    // [3] 내 정보 조회
    @GetMapping("/info")
    public boolean axios3 (HttpSession session){
        Object object = session.getAttribute("loginId");
        // 비로그인 상태
        if(object == null) return false;
        // 로그인 상태
        return true;
    } // func end
    
    // [4] form 정보 받기
    @PostMapping("/form")
    public boolean axios4 (@RequestParam Map<String, String> map){
        System.out.println("AxiosController.axios4");
        System.out.println("map = " + map);

        return true;
    } // func end
    
    // [5] 첨부파일 form 정보 받기
    @PostMapping("/formdata")
    public boolean axios5 (@RequestParam MultipartFile file){
        System.out.println("AxiosController.axios5");
        System.out.println("file = " + file);

        return true;
    } // func end

    
} // class end
//
//@Repository
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//class AxiosDto{
//    String id;
//    String password;
//}