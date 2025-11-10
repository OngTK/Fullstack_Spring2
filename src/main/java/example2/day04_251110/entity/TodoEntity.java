package example2.day04_251110.entity;

import example2.day04_251110.dto.TodoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity extends BaseTime{

    // [1] 설계 ====================================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;             // PK
    @Column( nullable = false, length = 100 )   // Column 속성
    private String title;
    @Column( columnDefinition = "longtext" )    // Column을 sql 문으로 정의
    private String content;
    @Column(columnDefinition = "boolean default false")
    private boolean done;       // 수행여부


    // [2] 참조 관계 ===============================================

    // [3] toDto 메소드 ============================================
    public TodoDto toDto(){
        return TodoDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .done(this.done)
                .createdAt(String.valueOf(this.getCreateDate()))
                .updatedAt(String.valueOf(this.getUpdateDate()))
                .build();
    } // func end
} // class end
