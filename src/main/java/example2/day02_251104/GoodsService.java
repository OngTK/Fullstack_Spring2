package example2.day02_251104;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    // [1] 저장
    public GoodsDto post(GoodsDto goodsDto){
        // [1.1] DTO > Entity
        GoodsEntity entity = goodsDto.toEntity();
        // [1.2] Entity 저장·영속화
        GoodsEntity saveEntity = goodsRepository.save(entity);
        // [1.3] IF PK가 생성되었으면, DTO로 변환하여 반환
        if( saveEntity.getGno() >= 0) return saveEntity.toDto();
        // [1.4] gno가 없으면, 인입된 매개변수 그대로 반환
        return goodsDto;
    } // func end

    // [2] 전체 조회
    public List<GoodsDto> getAll(){
        // [2.1] findAll로 모든 엔티티 조회
        List<GoodsEntity> list = goodsRepository.findAll();
//        // [2.2] Entity > DTO
//        List<GoodsDto> result = new ArrayList<>();
//        for(GoodsEntity entity : list){
//            result.add(entity.toDto());
//        }
//        return result;

        // 스트림 활용
        return list.stream().map( GoodsEntity :: toDto).collect(Collectors.toList());
    } // func end

    // [3] 개별 조회
    public GoodsDto get(int gno){
        // [3.1] findById로 gno에 해당하는 엔티티 검색
        Optional<GoodsEntity> optional = goodsRepository.findById(gno);
        // [3.2] 엔티티가 null인지 확인
        if(optional.isPresent()){
            return optional.get().toDto();
        }
        return null;
    } // func end

    // [4] 개별 삭제
    public boolean delete(int gno){
        // [4.1] gno 존재여부 확인
        if(goodsRepository.existsById(gno)){
            // [4.2] entity 삭제
            goodsRepository.deleteById(gno);
            return true;
        }
        return false;
    } // func end

    // [5] 개별 수정
    public GoodsDto put(GoodsDto goodsDto){
        // [5.1] dto gno로 엔티티 가져오기
        Optional<GoodsEntity> optional = goodsRepository.findById(goodsDto.getGno());
        // [5.2] Optional null 여부 확인
        if(optional.isPresent()){
            GoodsEntity goodsEntity = optional.get();
            goodsEntity.setGname(goodsDto.getGname());
            goodsEntity.setGprice(goodsDto.getGprice());
            goodsEntity.setGdesc(goodsDto.getGdesc());

            // [5.3] 엔티티 > DTO
            return goodsEntity.toDto();
        }
        // [5.4] 실패시
        return goodsDto;
    } // func end

} // class end
