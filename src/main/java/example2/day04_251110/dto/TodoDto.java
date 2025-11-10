package example2.day04_251110.dto;

import example2.day04_251110.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {
    // DTO
    // 데이터베이스 데이터 이동 객체
    // 기능 구현에 필요한 목적에 따른 이동할 데이터 구성

    // 실무시 DTO 구성
    // 테이블과 유사하게 vs 기능별로 구성

    private int id;             //  RUD
    private String title;       // CRU
    private String content;     // CRU
    private boolean done;       // CRU
    private String createdAt;   //  R
    private String updatedAt;   //  R

    // toEntity ================================================
    public TodoEntity toEntity(){
        return TodoEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .done(this.done)
                .build();
    } // func end

} // class end
