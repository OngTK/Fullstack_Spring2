package example2.practice.practice03_251106;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "eStudent")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String studentName;

    // Enroll 양방향 연결
    @OneToMany(mappedBy = "studentEntity")
    @ToStringExclude
    @Builder.Default
    private List<EnrollEntity> enrollEntityList = new ArrayList<>();

} // class end
