package example2.day01_251103;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    // [1] Create
    public ExamEntity post(ExamEntity examEntity){
        // [1.1] Repository의 저장 메소드
        // .save(T)
        ExamEntity saveEntity = examRepository.save(examEntity);

        // [1.2] 저장 성공 시, 해당 엔티티를 반환함
        return saveEntity;
    } // func end

    // [2] ReadAll
    public List<ExamEntity> get(){
        // [2] Repository의 모든 에니티 객체를 반환
        // .findAll()
        List<ExamEntity> list = examRepository.findAll();
        return list;
    } // func end

    // [3] Update
    // [3.1] 특정한 엔티티 수정
    public ExamEntity put(ExamEntity examEntity){
        // [3.1.1] 수정할 Entity를 매개변수로 받음
        // 반드시 PK 포함
        // .save(T) : Entity PK가 없으면 **생성** / PK가 있으면 **수정**
        // **권장하지 않음**
        ExamEntity updatedEntity = examRepository.save(examEntity);
        return updatedEntity;
    } // func end

    // [3.2] 특정한 엔티티 수정
    @Transactional // update 시에는 Transactional 필수
    public ExamEntity put2(ExamEntity examEntity){
        // [3.2.1] 수정할 엔티티를 조회
        // Optional< > : nullPointException을 감싼 클래스
        // null 값에 대한 유효성 기능을 제공
        Optional<ExamEntity> optional = examRepository.findById(examEntity.getCol1());

        // [3.2.2] 엔티티 존재 여부 확인
        if( optional.isPresent() ){
            ExamEntity entity = optional.get();
            // [3.2.3] setter을 이용하여 entity 수정
            // Entity 를 setter 로 수정하면 자동으로 DB가 변경처리 됨
            // 따라서 .save(T)를 할 필요가 없음
            entity.setCol2(examEntity.getCol2());
            entity.setCol3(examEntity.getCol3());
            

            return entity;
        }
        return examEntity;
    } // func end
    
    // [4] Delete
    // .delete(T) / .deleteById(ID)
    public boolean delete(int col1){
        examRepository.deleteById(col1);
        return true;
    } // func end

} // class end
