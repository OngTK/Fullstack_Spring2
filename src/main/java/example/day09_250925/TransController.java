package example.day09_250925;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/day09/trans")
public class TransController {

    private final TransService transService;

    // [1]
    @PostMapping
    public boolean trans1(){
        return transService.trans1();
    }

    // [2] 계좌이체
    @PostMapping("/transfer")
    public boolean transfer(@RequestBody Map<String, String> transInfo){
        // 조민규가 고우림에게 10만원을 보냄
        return  transService.transfer(transInfo);
    }

} // class end
