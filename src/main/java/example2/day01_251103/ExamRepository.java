package example2.day01_251103;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * T : 테이블 - 조작할 테이블 = Entity
 * <p>
 * ID : PK의 타입 - col1 > Integer
 */
@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {

} // interface end
