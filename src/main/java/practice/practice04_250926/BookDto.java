package practice.practice04_250926;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDto {

    private int id;
    private String title;
    private int stock;

} // class end
