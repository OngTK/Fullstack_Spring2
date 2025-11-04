package example2.practice.practice02_251104;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    /**
     * [1] 영화등록
     * <p>
     * 새로운 영화 정보를 입력받아 DB에 저장
     */
    public MovieDto post(MovieDto movieDto){
        // [1.1] dto > entity + 저장
        MovieEntity save = movieRepository.save(movieDto.toEntity());
        // [1.2] 저장 여부 확인
        if(save.getMovieId() >= 0) return save.toDto();
        return movieDto;
    } // func end

    /**
     * [2] 영화 전체 조회
     * <p>
     * 모든 영화 목록을 조회
     */
    public List<MovieDto> getAll(){
        // [2.1] 엔티티 전체 조회
        List<MovieEntity> entityList = movieRepository.findAll();
        // [2.2] 엔티티 > DTO LIST 처리 후 반환
        return entityList.stream().map( MovieEntity :: toDto).toList();
    } // func end

    /**
     * [3] 영화 개별 조회
     * <p>
     * 영화번호(movieId)를 기준으로 특정 영화 상세 정보 조회
     */
    public MovieDto get(int movieID){
        // [3.1] PK 기준으로 엔티티 여부 확인
        if(movieRepository.existsById(movieID)){
            // [3.2] PK 번호로 엔티티 추출
            Optional<MovieEntity> optional = movieRepository.findById(movieID);

            // [3.3] optional 존재여부 파악 및 꺼내기
            if(optional.isPresent()){
                return optional.get().toDto();
            }
        }
        return null;
    } // func end

    /**
     * [4] 특정 영화 수정
     * <p>
     * 영화번호(movieId)를 기준으로 해당 영화 정보 수정
     */
    public MovieDto put(MovieDto movieDto){
        // [4.1] PK 존재여부 확인
        if(!movieRepository.existsById(movieDto.getMovieId())){
            return movieDto;
        }
        // [4.2] PK로 엔티티 추출 + 확인
        Optional<MovieEntity> optional = movieRepository.findById(movieDto.getMovieId());
        if(optional.isPresent()) {

            // [4.3] 엔티티 확인 및 수정
            MovieEntity entity = optional.get();
            entity.setTitle(movieDto.getTitle());
            entity.setDirector(movieDto.getDirector());
            entity.setRating(movieDto.getRating());
            entity.setReleaseDate(movieDto.getReleaseDate());

            return entity.toDto();
        }
        return movieDto;
    } // func end

    /**
     * [5]특정 영화 삭제
     * <p>
     * 영화번호(movieId)를 기준으로 해당 영화 삭제
     */
    public boolean delete(int movieId){
        if(movieRepository.existsById(movieId)){
            movieRepository.deleteById(movieId);
            return true;
        }
        return false;
    } // func end

} // class end
