package example2.day02_251104;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goods")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    // [1] 저장
    @PostMapping
    public ResponseEntity<?> post(@RequestBody GoodsDto goodsDto){
        return ResponseEntity.ok(goodsService.post(goodsDto));
    } // func end

    // [2] 전체 조회
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(goodsService.getAll());
    } // func end

    // [3] 개별 조회
    @GetMapping
    public ResponseEntity<?> get(@RequestParam int gno){
        return ResponseEntity.ok(goodsService.get(gno));
    } // func end

    // [4] 개별 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int gno){
        return ResponseEntity.ok(goodsService.delete(gno));
    } // func end

    // [5] 개별 수정
    @PutMapping
    public ResponseEntity<?> put(@RequestBody GoodsDto goodsDto){
        return ResponseEntity.ok(goodsService.put(goodsDto));
    } // func end

} // class end
