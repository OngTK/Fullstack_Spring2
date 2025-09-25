package example.day09_250925;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TransMapper {

    // [1] Insert
    // 정상 Insert
    @Insert("insert into trans(name) values ( #{name} )")
    boolean trans1(String name);

    // 오타가 포함된 비정상 Insert
    @Insert("insert into trans(namess) values ( #{name} )")
    boolean trans2(String name);

    // [2.1] 입금처리
    @Update("update trans set money = money + #{money} where name = #{name}")
    boolean deposit(String name, int money);

    // [2.2] 출금
    @Update("update trans set money = money - #{money} where name = #{name} and money >= #{money}")
    boolean withdraw(String name, int money);

} // class end
