package example2.practice.practice01_251103;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookEntity {

    @Id     // pk 선언
    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;

} // class end
