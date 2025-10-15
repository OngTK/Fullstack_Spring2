package practice.practice04_251014;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RentalDto {

    private int id;
    private int book_id;
    private String member;
    private String rent_date;
    private String return_date;

    private String title;
}// class end
