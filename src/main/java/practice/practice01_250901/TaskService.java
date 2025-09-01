package practice.practice01_250901;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskService {

    final private TaskDao taskDao;

    // [1] 매 30초마다 모든 제품의 재고는 3개씩 감소
    @Scheduled(cron = "0/30 * * * * *")
    public void decreaseQTY() {
        System.out.println("TaskService.decreaseQTY");
        taskDao.decreaseQTY();
    } // func end

    // [2] 매 1분마다 모든 제품 정보를 조회/출력
    @Scheduled(cron = "0 0/1 * * * *")
    public void readProduct() {
        System.out.println("TaskService.readProduct");

        List<Map<String, String>> list  = taskDao.readProduct();

        for(Map<String, String> map : list ){
            System.out.printf("상품명 : %s , 재고 : %s \t", map.get("product_name"), map.get("stock_quantity"));
        }
    } // func end

    // [3] 매 5분마다 재고가 10 이하인 상품의 재고를 +20개 추가
    @Scheduled(cron = "0 0/5 * * * * ")
    public void increaseQTY() {
        System.out.println("TaskService.increaseQTY");
        taskDao.increaseQTY();
    }

} // class end
