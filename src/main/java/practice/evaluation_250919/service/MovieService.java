package practice.evaluation_250919.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.evaluation_250919.model.dto.MovieDto;
import practice.evaluation_250919.model.mapper.MovieMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieMapper movieMapper;


    // [1] create
    public int createMovie(MovieDto movieDto){
        return movieMapper.create(movieDto);
    } // func end

    // [2] read - 생략


    // [3] readAll
    public List<MovieDto> readAllMovie(){
        return movieMapper.readAll();
    } // func end

    // [4] update - 생략


    // [5] delete
    public int deleteMovie(int mno, String mPassword){
        return movieMapper.delete(mno, mPassword);
    } // func end

} // class end
