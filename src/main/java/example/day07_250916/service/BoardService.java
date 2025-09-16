package example.day07_250916.service;

import example.day07_250916.model.dto.BoardDto;
import example.day07_250916.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    // [1] boardWrite()
    public boolean boardWrite(BoardDto boardDto) {
        System.out.println("BoardService.boardWrite");
        System.out.println("boardDto = " + boardDto);

        boolean result = boardMapper.create(boardDto);
        return result;
    } // func end

    // [2] boardPrint()
    public List<BoardDto> boardPrint() {
        System.out.println("BoardService.boardPrint");

        List<BoardDto> list = boardMapper.readAll();
        return list;
    }

    // [3] boardFind()
    public BoardDto boardFind(int bno){
        System.out.println("BoardService.boardFind");
        System.out.println("bno = " + bno);

        BoardDto boardDto = boardMapper.read(bno);
        return boardDto;
    } // func end


    // [4] boardDelete()
    public boolean boardDelete(int bno) {
        System.out.println("bno = " + bno);
        System.out.println("BoardService.boardDelete");

        boolean result = boardMapper.delete(bno);
        return result;
    }

    // [5] boardUpdate()
    public boolean boardUpdate(BoardDto boardDto) {
        System.out.println("BoardService.boardUpdate");
        System.out.println("boardDto = " + boardDto);

        boolean result = boardMapper.update(boardDto);
        return result;
    } // func end
} // class end
