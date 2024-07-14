package sample.pre.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import sample.pre.article.entity.ArticleEntity;
import sample.pre.common.TimeTrackableEntity;

// TODO: Hibernate Validator는 Entity가 아닌 DTO에서 쓰는 게 맞지 않을까?
//       DTO에서 검증이 됬다면, 그 데이터는 Entity에서 안전함을 보장하지 않을까...
//       만약 위 판단 대로라면, 모든 Validation은 DTO에서만 하고, Entity에선 빼자.

@Entity
@Table(name = "COMMENTS")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends TimeTrackableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "comment_id")
	private Long id;

	@Column(name = "writer_name", nullable = false)
	private String writerName;

	@Column(nullable = false)
	private String body;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id", nullable = false)
	@JsonIgnore // 순환 객체 변환 제거
	private ArticleEntity article;

	@Builder
	protected CommentEntity(
		@NonNull String body,
		@NonNull String writerName,
		@NonNull ArticleEntity article
	) {
		this.body = body;
		this.writerName = writerName;
		this.article = article;
	}
}
