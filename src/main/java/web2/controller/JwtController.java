package web2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web2.service.JwtService;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;
    
    /**
     * [1] 토큰 생성
     * @since 2025.10.22
     */
    @GetMapping("/create")
    public ResponseEntity<?> createToken(@RequestParam String data){
        String token = jwtService.createTokenSample(data);
        return ResponseEntity.ok(token);
    } // func end

    /**
     * [2] 토큰 검증
     * @since 2025.10.22
     */
    @GetMapping("/check")
    public ResponseEntity<?> checkToken(@RequestParam String token){
        System.out.println("token = " + token);
        boolean result = jwtService.checkTokenSample(token);
        return ResponseEntity.ok(result);
    } // func end

    /**
     * [3] 토큰 값 추출
     * @since 2025.10.22
     */
    @GetMapping("/payload")
    public ResponseEntity<?> payloadToken(@RequestParam String token){
        System.out.println("token = " + token);

        String value = jwtService.payloadTokenSample(token);
        return ResponseEntity.ok(value);
    };

} // class end
