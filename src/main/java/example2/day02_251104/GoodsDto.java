package example2.day02_251104;

import lombok.*;

// entity는 service에서만 사용하고
// controller, Mybatis 등 에서는 DTO를 사용
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GoodsDto {

    private int gno;
    private String gname;
    private int gprice;
    private String gdesc;
    private String create_date;
    private String update_date;

    // Dto >> Entity 변환 작업 필요! ======================================
    // Controller > Service 이동 시, Dto를 Entity로 변환

    public GoodsEntity toEntity(){
        // 날짜는 자동으로 처리하므로 필요 X
        return GoodsEntity.builder()
                .gno(this.gno)
                .gname(this.gname)
                .gprice(this.gprice)
                .gdesc(this.gdesc)
                .build();
    }
} // class end
