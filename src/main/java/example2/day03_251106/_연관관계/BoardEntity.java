package example2.day03_251106._연관관계;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="eboard")
@Data
@Builder
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;            // 게시물 번호
    private String btitle;      // 게시물 제목
    private String bcontent;    // 게시물 내용

    // 단방향 연결 ===============================
    // 하위 엔티티가 상위 엔티티를 참조
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // M:1, 다수가 하나에 참조
    // cascade = CascadeType.ALL : 상위 PK 삭제되면 KF도 모두 삭제됨
    // FetchType.EAGER  :
    // FetchType.LAZY   :
    @JoinColumn(name = "cno")   // FK 필드명 설정
    private CategoryEntity categoryEntity;

    /*
     * PK - FK 제약조건 옵션
     *
     * [ CASCADE ]
     *      cascade = CascadeType.ALL       : 부모가 삭제/수정되면 자식도 함께 삭제/수정          (권장)
     *      cascade = CascadeType.PERSIST   : 부모가 저장되면 자식도 저장
     *      cascade = CascadeType.MERGE     : 부모가 수정되면 자식도 수정
     *      cascade = CascadeType.REMOVE    : 부모가 삭제되면 자식도 삭제
     *      cascade = CascadeType.REFRESH   : 부모가 재호출(갱신)되면 자식도 재호출(갱신)
     *      cascade = CascadeType.DETACH    : 부모가 영속 해제되면 자식도 영속 해제
     * 
     * [ 영속성 ]
     *      자바 데이터(객체)오아 DB 데이터(테이블/레코드)를 매핑(연결)
     *      - 영속 중이라면 자바 객체 수정시 DB 데이터도 수정
     *      - 영속 중이 아니라면 자바 객체 수정해도 DB 데이터는 변경되지 않음
     *
     * [ Fetch ]
     *      부모[PK]와 자식[FK] 관계에서 엔티티를 조회하는 방법
     *      fetch = FetchType.EAGER         : 해당 엔티티를 조회하면 참조 엔티티도 즉시 조회, Default
     *                                      : 초기 로딩이 느림, 불필요한 엔티티 정보가 있을 경우 기능 저하
     *      fetch = FetchType.LAZY          : 해당 엔티티를 조회하면 참조 엔티티를 조회하지 않음
     *                                      : 초기 로딩이 빠름, 사용할 엔티티 정보를 적절하게 사용하면 성능 최적화
     *                                      : 엔티티.get@@@ 시 참조 엔티티를 조회
     *
     *   * 한 번 select 된 결과는 MyBatis와 JPA는 자동 1차 캐싱(기록)
     */

    // ReplyEntity 양방형 연결
    @OneToMany(mappedBy = "boardEntity")
    @ToStringExclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

} // class end
