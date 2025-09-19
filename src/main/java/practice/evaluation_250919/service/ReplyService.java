package practice.evaluation_250919.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.evaluation_250919.model.dto.ReplyDto;
import practice.evaluation_250919.model.mapper.ReplyMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    // [1] create
    public int createReply(ReplyDto replyDto){
        return replyMapper.create(replyDto);
    } // func end

    // [2] read
    public List<ReplyDto> readReply(int mno){
        return replyMapper.read(mno);
    }

    // [3] readAll
    public List<ReplyDto> readAllReply(){
        return replyMapper.readAll();
    } // func end

    // [4] update - 생략

    // [5] delete
    public int deleteReply(int rno, String rPassword){
        return replyMapper.delete(rno, rPassword);
    }


} // class end
