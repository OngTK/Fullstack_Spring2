package example2.practice.practice02_251104;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {

    private int movieId;            // 영화번호
    private String title;           // 영화제목
    private String director;        // 영화감독
    private String releaseDate;     // 개봉일
    private double rating;             // 평점
    private String createdAt;       // 등록일
    private String updatedAt;       // 수정일

    // [1] DTO > Entity
    public MovieEntity toEntity(){
        return MovieEntity.builder()
                .movieId(this.movieId)
                .title(this.title)
                .director(this.director)
                .releaseDate(this.releaseDate)
                .rating(this.rating)
                .build();
    } // func end

} // class end
