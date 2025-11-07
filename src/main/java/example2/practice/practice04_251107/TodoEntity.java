package example2.practice.practice04_251107;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    // toDto
    public TodoDto toDto() {
        return TodoDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .createAt(String.valueOf(this.getCreatedAt()))
                .updateAt(String.valueOf(this.getUpdatedAt()))
                .build();
    } // func end

} // class end
