package practice.evaluation_250919.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieDto {

    // 멤버변수
    private int mNo;
    private String mName;
    private String mDirector;
    private String mGenre;
    private String mIntro;
    private String mPassword;

} // class end
