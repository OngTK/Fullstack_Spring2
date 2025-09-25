package example.day09_250925;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransService {

    private final TransMapper transMapper;

    // [1] 목적 : 배두훈과 강형호를 insert -> commit
    // if 하나라도 실패라면 rollback
    @Transactional              // 함수명 또는 클래스위에 Transactional 을 삽입
    public boolean trans1(){
        // Tranaction 원리
        //      AOP가 before로 commit을 준비
        
        
        // [1.1] 배두훈 insert1
        transMapper.trans1("배두훈");      // 결과 : Insert 성공
        // [1.2] 강형호 insert2
        transMapper.trans2("강형호");      // 결과 : Insert 실패 > Exception 발생

        
        //      AOP가 after 처리 후 모두 성공시 commit
        //      Exception 발생시 rollback >> 앞전 실행된 SQL을 취소처리
        
        return true;
    }

    // [2] 계좌이체
    @Transactional
    public boolean transfer(Map<String, String > transInfo){

        String fromname = String.valueOf(transInfo.get("fromname"));
        int money = Integer.parseInt(transInfo.get("money"));
        String toname = String.valueOf(transInfo.get("toname"));

        // [2.1] 조민규 -100,000
        transMapper.withdraw(fromname, money);

        // 고의적 예외 발생
        if ( true ){ // 조민규가 10만원이 없거나, 내부적으로 로직/조건 문제가 있을 때,
            throw new RuntimeException("강제예외");
        }
        // [2.1] 이 실행되었지만
        // [2.2] 가 실행되기 전 고의적 예외 발생으로 인해
        // [2.1] 도 원래상태로 rollback

        // [2.2] 고우림 +100,000
        transMapper.deposit(toname, money);

        return false;
    }


} // class end
