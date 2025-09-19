package practice.evaluation_250919.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReplyDto {

    // 멤버변수
    private int rNo;
    private String rContent;
    private String rPassword;
    private int mNo;

} // class end
