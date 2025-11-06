package example2.day03_251106._연관관계;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ecategory")
@Data
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;        // 카테고리 번호
    private String cname;   // 카테고리 명

    // 양방향 연결 ===============================
    // 상위 엔티티가 하위 엔티티를 참조
    @OneToMany( mappedBy = "categoryEntity")          // 1:M 하나의 PK가 다수의 하위 FK를 가짐
    // JPA 양방향은 사용하고, DB에서는 양방향을 쓰지 않는다.
    @ToStringExclude // 순환참지 방지
    @Builder.Default // 빌더 패턴 사용 시 초기 값 사용
    private List<BoardEntity> boardEntityList = new ArrayList<>();

} // class end
