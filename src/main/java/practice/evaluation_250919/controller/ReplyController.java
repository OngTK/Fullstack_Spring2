package practice.evaluation_250919.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.evaluation_250919.model.dto.ReplyDto;
import practice.evaluation_250919.service.ReplyService;

import java.util.List;

/**
 * @since 2025.09.19
 * @author OngTK
 * <p> 토론 글 작성·삭제(비밀번호 기반)·영화별 토론 전체 조회
 * */

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:5173")
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 4. 토론 글 작성
     * 익명으로 해당 영화에 대한 토론 글 작성 가능
     * 비밀번호 설정하여 삭제 가능
     * */
    // [1] create
    @PostMapping
    public ResponseEntity<Integer> createReply(@RequestBody ReplyDto replyDto){
        System.out.println("ReplyController.createReply");
        System.out.println("replyDto = " + replyDto);

        int result = replyService.createReply(replyDto);
        return ResponseEntity.ok().body(result);
    } // func end

    /**
     * 6. 영화별 토론 전체 조회
     * 특정 영화에 작성된 모든 토론 글을 조회 가능
     * */

    // [2] read
    @GetMapping("/read")
    public ResponseEntity<List<ReplyDto>> readReply(@RequestParam int mno){
        System.out.println("ReplyController.readReply");
        System.out.println("mno = " + mno);

        List<ReplyDto> result = replyService.readReply(mno);
        return ResponseEntity.ok().body(result);
    } // func end



    // [3] readAll
    @GetMapping
    public ResponseEntity<List<ReplyDto>> readAllReply(){
        System.out.println("ReplyController.readAllReply");

        List<ReplyDto> result = replyService.readAllReply();
        return ResponseEntity.ok().body(result);
    } // func end

    // [4] update - 생략

    /**
     * 5. 토론 글 삭제 (비밀번호 기반)
     * 사용자가 등록한 비밀번호 입력 후 삭제 가능
     * */
    // [5] delete
    @DeleteMapping
    public ResponseEntity<Integer> deleteReply(@RequestParam int rno, @RequestParam String rPassword){
        System.out.println("ReplyController.deleteReply");
        System.out.println("rno = " + rno + ", rPassword = " + rPassword);

        int result = replyService.deleteReply(rno, rPassword);
        return ResponseEntity.ok().body(result);
    } // func end

} // class end
