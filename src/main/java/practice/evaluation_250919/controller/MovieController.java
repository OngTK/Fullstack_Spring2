package practice.evaluation_250919.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.evaluation_250919.model.dto.MovieDto;
import practice.evaluation_250919.service.MovieService;

import java.util.List;

/**
 * @author OngTK
 * <p> 영화 등록·삭제·조회
 * @since 2025.09.19
 */

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:5173")
public class MovieController {

    private final MovieService movieService;

    /**
     * 1. 영화 등록
     * 익명으로 영화 추천 가능
     * 영화 제목, 감독, 장르, 간단한 소개 입력
     * 등록 시 삭제를 위한 비밀번호 설정
     * */
    // [1] create
    @PostMapping
    public ResponseEntity<Integer> createMovie(@RequestBody MovieDto movieDto){
        System.out.println("MovieController.createMovie");
        System.out.println("movieDto = " + movieDto);

        int result = movieService.createMovie(movieDto);
        return ResponseEntity.ok().body(result);
    } // func end

    // [2] read - 생략

    /**
     * 3. 영화 목록 조회
     * 등록된 추천 영화 리스트 조회 가능
     *
     */
    // [3] readAll
    @GetMapping
    public ResponseEntity<List<MovieDto>> readAllMovie(){
        System.out.println("MovieController.readAllMovie");

        List<MovieDto> result = movieService.readAllMovie();
        return ResponseEntity.ok().body(result);
    } // func end

    // [4] update - 생략

    /**
     * 2. 영화 삭제 (비밀번호 기반)
     *  사용자가 등록한 비밀번호를 입력해야 삭제 가능
     */
    // [5] delete
    @DeleteMapping
    public ResponseEntity<Integer> deleteMovie(@RequestParam int mno, @RequestParam String mPassword){
        System.out.println("MovieController.deleteMovie");
        System.out.println("mno = " + mno + ", mPassword = " + mPassword);

        int result = movieService.deleteMovie(mno, mPassword);
        return ResponseEntity.ok().body(result);
    }


}  // class end
