package example2.practice.practice02_251104;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * [1] 영화등록
     * <p>
     * 새로운 영화 정보를 입력받아 DB에 저장
     */
    @PostMapping
    public ResponseEntity<?> post(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.post(movieDto));
    } // func end

    /**
     * [2] 영화 전체 조회
     * <p>
     * 모든 영화 목록을 조회
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(movieService.getAll());
    } // func end

    /**
     * [3] 영화 개별 조회
     * <p>
     * 영화번호(movieId)를 기준으로 특정 영화 상세 정보 조회
     */
    @GetMapping
    public ResponseEntity<?> get(@RequestParam int movieId){
        return ResponseEntity.ok(movieService.get(movieId));
    } // func end

    /**
     * [4] 특정 영화 수정
     * <p>
     * 영화번호(movieId)를 기준으로 해당 영화 정보 수정
     */
    @PutMapping
    public ResponseEntity<?> put(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.put(movieDto));
    } // func end

    /**
     * [5]특정 영화 삭제
     * <p>
     * 영화번호(movieId)를 기준으로 해당 영화 삭제
     */
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int movieId){
        return ResponseEntity.ok(movieService.delete(movieId));
    } // func end

} // class end
