package practice.evaluation_250919.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.evaluation_250919.service.ReplyService;

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


    // [2] read - 생략


    /**
     * 6. 영화별 토론 전체 조회
     * 특정 영화에 작성된 모든 토론 글을 조회 가능
     * */
    // [3] readAll


    // [4] update - 생략

    /**
     * 5. 토론 글 삭제 (비밀번호 기반)
     * 사용자가 등록한 비밀번호 입력 후 삭제 가능
     * */
    // [5] delete




} // class end
