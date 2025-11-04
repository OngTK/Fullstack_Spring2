package example2.practice.practice02_251104;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // auto_increment
    private int movieId;            // 영화번호

    @Column(nullable = false, length = 100)
    private String title;           // 영화제목

    private String director;        // 영화감독

    private String releaseDate;     // 개봉일

    private double rating;             // 평점

    // [1] Entity > DTO
    public MovieDto toDto(){
        return MovieDto.builder()
                .movieId(this.movieId)
                .title(this.title)
                .director(this.director)
                .releaseDate(this.releaseDate)
                .rating(this.rating)
                .createdAt(String.valueOf(this.getCreatedAt()))
                .updatedAt(String.valueOf(this.getUpdatedAt()))
                .build();
    } // func end

} // class end
