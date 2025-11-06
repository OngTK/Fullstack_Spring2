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
@Table(name = "eCourse")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;

    // Enrolle과 양방향 연결
    @OneToMany( mappedBy = "courseEntity")
    @ToStringExclude
    @Builder.Default
    private List<EnrollEntity> enrollEntityList = new ArrayList<>();
} // class end
