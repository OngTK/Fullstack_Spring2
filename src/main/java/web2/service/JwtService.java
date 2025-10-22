package web2.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    // [1.2] SHA-256 알고리즘을 사용한 비밀키 / 32글자
    // 같은 데이터에 대해 서로 다른 토큰이 발생하도록 하는 알고리즘 추가 필요
    private final String secret = "12345678901234567890123456789012";  // 난수의 경우 까먹을 경우가 있으므로 키를 직접 생성하는 것을 권장
    private final Key secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));      // StandardCharsets.UTF_8 : 한글의 경우 필수

    /**
     * [1] 토큰 생성
     * @since 2025.10.22
     */
    public String createToken(String data){
        // [1-1] 토큰 생성 : Jwt 라이브러리 사용
        String Token = Jwts.builder()
                .claim("key", data)             // .claim("key", value) : 토큰에 넣을 데이터를 대입
                .setIssuedAt(new Date())           // .setIssuedAt( 토큰 발급 날짜/시간)
                .setExpiration( new Date(System.currentTimeMillis() + 1000 * 30 )  )
                // .sexExpiration( 토큰 만료 시간 ) 
                // 단위 milli-sec // 1000 = 1sec / 1000 * 60 = 60초 = 1분 

                //[1-3] 알고리즘을 이용한 토큰 HS256 서명
                .signWith( secretKey, SignatureAlgorithm.HS256 )
                .compact();
        System.out.println("Token = " + Token);
        return Token;
    } // func end

    /**
     * [2] 토큰 검증
     * @since 2025.10.22
     */
    public boolean checkToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)   // 서명 검증을 위한 비밀키 대입
                    .build()                    // 비밀키 확인
                    .parseClaimsJws(token);     // 검증할 토큰 대입
            return true;
        } catch (JwtException e){
            System.out.println("토큰없음");
            return false;
        }
    } // func end

    /**
     * [3] 토큰 값 추출 · payload
     * @since 2025.10.22
     */
    public String payloadToken(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();                  // 검증 후 클레임(내용물) 반환
            System.out.println("claims = " + claims);
            String value = claims.get("key").toString();
            return value;
        } catch (Exception e) {
            return null;
        }
    } // func end


} // class end
