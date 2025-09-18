package example.day07_250916.controller;

import example.day07_250916.model.dto.BoardDto;
import example.day07_250916.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@CrossOrigin(value="http://localhost:5173") // CORS 서로 다른 서버간의 요청·응답을 허용하는 어노테이션
public class BoardController {

    public final BoardService boardService;

    // [1] boardWrite()
    @PostMapping
    public ResponseEntity<Boolean> boardWrite(@RequestBody BoardDto boardDto) {
        System.out.println("BoardController.boardWrite");
        System.out.println("boardDto = " + boardDto);

        boolean result = boardService.boardWrite(boardDto);
        return ResponseEntity.status(200).body(result);
    } // func end

    // [2] boardPrint()
    @GetMapping
    public ResponseEntity<List<BoardDto>> boardPrint() {
        System.out.println("BoardController.boardPrint");

        List<BoardDto> list = boardService.boardPrint();
        return ResponseEntity.ok().body(list);
    } // func end

    // [3] boardFind()
    @GetMapping("/find")
    public ResponseEntity<BoardDto> boardFind(int bno){
        System.out.println("BoardController.boardFind");
        System.out.println("bno = " + bno);

        BoardDto boardDto = boardService.boardFind(bno);
        return ResponseEntity.status(200).body(boardDto);
    };

    // [4] boardDelete()
    @DeleteMapping
    public ResponseEntity<Boolean> boardDelete(int bno) {
        System.out.println("bno = " + bno);
        System.out.println("BoardController.boardDelete");

        boolean result = boardService.boardDelete(bno);
        return ResponseEntity.status(200).body(result);
    } //func end

    // [5] boardUpdate()
    @PutMapping
    public ResponseEntity<Boolean> boardUpdate(@RequestBody  BoardDto boardDto) {
        System.out.println("boardDto = " + boardDto);
        System.out.println("BoardController.boardUpdate");

        boolean result = boardService.boardUpdate(boardDto);
        return ResponseEntity.status(200).body(result);
    } // func end
} // class end
