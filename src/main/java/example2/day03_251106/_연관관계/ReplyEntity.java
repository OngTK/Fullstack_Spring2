package example2.day03_251106._연관관계;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="ereply")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyEntity {

    /* 구조
     * 카테고리
     *      └ 게시물
     *           └ 댓글
     */

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int rno;
    private String rcontent;

    // 단반향 연결
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;
    
} // class end

