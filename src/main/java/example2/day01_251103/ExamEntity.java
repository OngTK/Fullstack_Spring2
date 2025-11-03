package example2.day01_251103;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExamEntity {

    // DB에서 사용할 테이블의 속성을 일치

    @Id         // PK 선언
    private int col1;
    private String col2;
    private double col3;

} // class end
