package example2.day03_251106._자바참조;

import lombok.Data;

@Data
public class Board {
    private int bno;            // 게시물 번호
    private String btitle;      // 게시물 제목
    private String bcontent;    // 게시물 내용

    private Category category;  // FK : 카테고리 정보를 담음
    // 장점 : 별도의 JOIN 이 필요 없음!

} // class end
