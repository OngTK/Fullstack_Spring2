package practice.Task05_250918.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.Task05_250918.model.dto.MemberDto;
import practice.Task05_250918.model.mapper.MemberMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    // [1] create
    public int createMember(MemberDto memberDto) {
        return memberMapper.create(memberDto);
    } // func end

    // [2] read
    public MemberDto readMember(int mno) {
        return memberMapper.read(mno);
    } // func end

    // [3] readAll
    public List<MemberDto> readAllMember() {
        return memberMapper.readAll();
    } // func end

    // [4] update
    public int updateMember(MemberDto memberDto) {
        return memberMapper.update(memberDto);
    } // func end

    // [5] delete
    public int deleteMember(int mno){
        return memberMapper.delete(mno);
    } // func end
} // class end
