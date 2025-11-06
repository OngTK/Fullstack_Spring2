package example2.day03_251106._자바참조;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.List;

@Data
public class Category {

    // [1] 멤버변수
    private int cno;        // 카테고리 번호
    private String cname;   // 카테고리 명

    @ToStringExclude // toString 함수를 제외함
    List<Board> boardList = new ArrayList<>(); // FK List

    // [2] 생성자


    // [3] 메소드


} // class end
