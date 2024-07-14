package sample.pre.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import sample.pre.board.entity.BoardEntity;
import sample.pre.comment.entity.CommentEntity;
import sample.pre.common.TimeTrackableEntity;

// TODO: Hibernate Validator는 Entity가 아닌 DTO에서 쓰는 게 맞지 않을까?
//       DTO에서 검증이 됬다면, 그 데이터는 Entity에서 안전함을 보장하지 않을까...
//       만약 위 판단 대로라면, 모든 Validation은 DTO에서만 하고, Entity에선 빼자.

@Entity
@Table(name = "ARTICLES")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity extends TimeTrackableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "article_id")
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String body; // = Nullable Column

	@Column(name = "writer_name", nullable = false)
	private String writerName;

	@Column(name = "writer_email", nullable = false)
	private String writerEmail;

	// TODO: password를 이렇게 쌩으로 저장해도 될지...?
	@Column(nullable = false)
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "board_id", nullable = false)
	private BoardEntity board;

	@OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
	private List<CommentEntity> comments = new ArrayList<>();

	@Builder
	protected ArticleEntity(
		@NonNull String writerName,
		@NonNull String writerEmail,
		@NonNull String password,
		@NonNull String title,
		@NonNull String body,
		@NonNull BoardEntity board
	) {
		this.writerName = writerName;
		this.writerEmail = writerEmail;
		this.password = password;
		this.title = title;
		this.body = body;
		this.board = board;
	}
}