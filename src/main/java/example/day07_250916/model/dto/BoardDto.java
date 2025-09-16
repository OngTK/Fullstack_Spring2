package example.day07_250916.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardDto {
    private int bno;
    private String bwriter;
    private String bcontent;
} // class end
