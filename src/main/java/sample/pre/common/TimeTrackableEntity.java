package sample.pre.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * NOTE: 장점: hibernate ddl 자동 생성을 이용한다면 상속만 해도 시간 칼럼이 자동 포함된다.
 *       단점: (ddl-auto=none) 인 경우 create_at, updated_at을 ddl에 직접 명시해야 한다.
 *            --> ddl 작성시 실수할 수도 있겠다...
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeTrackableEntity {

	@CreatedDate
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}