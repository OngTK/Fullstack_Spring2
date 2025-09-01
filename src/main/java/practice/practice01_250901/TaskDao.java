package practice.practice01_250901;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskDao {

    // [ Dao 연결 ]
    private String db_url = "jdbc:mysql://localhost:3306/springweb2";
    private String db_user = "root";
    private String db_password = "1234";
    public Connection conn;

    public TaskDao() {
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
            System.out.println("Dao.connect"); // 콘솔 확인용
        } catch (Exception e) {
            System.out.println(e);
        }
    }   // func end

    // [1] 매 30초마다 모든 제품의 재고는 3개씩 감소
    public void decreaseQTY() {
        try {
            String sql = "update products set stock_quantity = stock_quantity - 3 where stock_quantity > 2";
            PreparedStatement ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("TaskDao.decreaseQTY " + e);
        }
    } // func end

    // [2] 매 1분마다 모든 제품 정보를 조회/출력
    public List<Map<String, String>> readProduct() {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            String sql = "select * from products";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("product_name", rs.getString("product_name"));
                map.put("stock_quantity", rs.getString("stock_quantity"));
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            System.out.println("TaskDao.readProduct " + e);
        }
        return null;
    } // func

    // [3] 매 5분마다 재고가 10 이하인 상품의 재고를 +20개 추가
    public void increaseQTY() {
        try {
            String sql = "update products set stock_quantity = stock_quantity + 20 where stock_quantity <= 10";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("TaskDao.increaseQTY " + e);
        }
    } // func end

} // class end
