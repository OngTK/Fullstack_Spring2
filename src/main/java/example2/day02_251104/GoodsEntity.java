package example2.day02_251104;

import jakarta.persistence.*;
import lombok.*;

@Entity                     // Entity 삽입
@Table(name="goods")        // 테이블명 정의, 생략 시 클래스 명으로 table 명이 정의
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
// 생성시일과 수정시일을 BaseTime으로 부터 상속받음
public class GoodsEntity extends BaseTime {

    @Id                     // PK 선언
    @GeneratedValue( strategy = GenerationType.IDENTITY )    // auto_increment 주입, Oracle에서는 지원하지 않음
    private int gno;        // 제품 번호

    // @Column(속성명=값, 속성명=값, ...)
    @Column( nullable = false, length = 100)         // null 불가, 글자수 100 지정
    private String gname;   // 제품명
    
    @Column(nullable = true)            // null 가능 = default
    private int gprice;     // 제품 가격

    @Column( columnDefinition = "varchar(100) default '제품설명' not null")
    // SQL을 직접 선언
    private String gdesc;   // 제품 설명


    // Entity >> Dto 변환 작업 필요! ======================================
    // Service > Controller 이동 시, Entity를 Dto로 변환
    public GoodsDto toDto(){
        return GoodsDto.builder()
                .gno(this.gno)
                .gname(this.gname)
                .gprice(this.gprice)
                .gdesc(this.gdesc)
                .create_date(String.valueOf(this.getCreateDate()))
                .update_date(String.valueOf(this.getUpdateDate()))
                .build();
    }

} // class end
