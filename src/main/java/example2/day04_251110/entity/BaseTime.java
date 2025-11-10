package example2.day04_251110.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 엔티티의 생성/수정 날짜(시간)을 자동으로 주입
@Getter
@MappedSuperclass                               // 엔티티에 대한 상속 선언
@EntityListeners(AuditingEntityListener.class)  // 본 클래스를 Auditing 대상으로 선언
public class BaseTime {

    // LocaDateTime -> sql : datetime(6)

    @CreatedDate                      // 현재 시간을 생성일자로 함
    private LocalDateTime createDate; // 생성 날짜·시간

    @LastModifiedDate                 // 엔티티의 변화 시검의 날짜, 시간을 자동으로 주입
    private LocalDateTime updateDate; // 수정 날짜·시간

} // class end
