package practice.Task05_250918.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.Task05_250918.model.dto.MemberDto;
import practice.Task05_250918.service.MemberService;

import java.util.List;

/**
 * @since 2025.09.18
 * TASK5 : 기존 TASK4.jsx 이어 useEffect/axios를 활용해서
 * spring+mybatis 서버 와 통신하여 TASK5 완성(등록/전체조회/삭제)하시오.
 *
 */

@RestController
@RequestMapping("/task05/member")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:5173")
public class MemberController {

    private final MemberService memberService;

    // [1] create
    @PostMapping
    public ResponseEntity<Integer> createMember(@RequestBody MemberDto memberDto) {
        System.out.println("MemberController.createMember");
        System.out.println("memberDto = " + memberDto);

        int result = memberService.createMember(memberDto);
        return ResponseEntity.ok().body(result);
    } // func end

    // [2] read
    @GetMapping("/read")
    public ResponseEntity<MemberDto> readMember(@RequestParam int mno) {
        System.out.println("MemberController.readMember");
        System.out.println("mno = " + mno);

        MemberDto result = memberService.readMember(mno);
        return ResponseEntity.ok().body(result);
    } // func end

    // [3] readAll
    @GetMapping
    public ResponseEntity<List<MemberDto>> readAllMember() {
        System.out.println("MemberController.readAllMember");

        List<MemberDto> result = memberService.readAllMember();
        return ResponseEntity.ok().body(result);
    } // func end

    // [4] update
    @PutMapping
    public ResponseEntity<Integer> updateMember(@RequestBody MemberDto memberDto) {
        System.out.println("MemberController.updateMember");
        System.out.println("memberDto = " + memberDto);

        int result = memberService.updateMember(memberDto);
        return ResponseEntity.ok().body(result);
    } // func end

    // [5] delete
    @DeleteMapping
    public ResponseEntity<Integer> deleteMember(@RequestParam int mno) {
        System.out.println("MemberController.deleteMember");
        System.out.println("mno = " + mno);

        int result = memberService.deleteMember(mno);
        return ResponseEntity.ok().body(result);
    } // func end

} // class end
