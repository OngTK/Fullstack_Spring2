package example2.practice.practice03_251106;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "eEnroll")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollId;
    private String status;

    // [1] course 단방향
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private CourseEntity courseEntity;

    // [2] student 단방향
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private StudentEntity studentEntity;


} // class end
