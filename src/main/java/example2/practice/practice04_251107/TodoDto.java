package example2.practice.practice04_251107;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TodoDto {

    private int id;
    private String title;
    private String content;

    private String createAt;
    private String updateAt;

    // toEntity
    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .build();
    } // func end

} // class end
