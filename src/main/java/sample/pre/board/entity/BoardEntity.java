package sample.pre.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;
import sample.pre.article.entity.ArticleEntity;

@Entity
@Table(name = "BOARDS")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {

	// TODO: hibernate이 엔티티 객체 만들때, db의 전략을 써서 만든다?
	//       좀 더 찾아보자. (DDL에서 이미 strategy가 정해지는 것 아닌가 해서. 왜 쓰는 건지 궁금)
	//       더 생각해보면, validator는 object mapper에서 호출된다. 그니까 맞지 않나...
	@Id
	@Setter(AccessLevel.NONE) // remove setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;

	// TODO: 카테고리는 enum과 string 중에 어떤 방식이 나는지 검토하기
	@NotBlank
	@Column(nullable = false)
	private String category;

	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
	private List<ArticleEntity> articles = new ArrayList<>();

	@Builder
	protected BoardEntity(String category) {
		this.category = category;
	}

	public void addArticle(ArticleEntity article) {
		Assert.notNull(article, "Article must not be null");
		this.articles.add(article);
	}

	public void removeArticle(ArticleEntity article) {
		Assert.notNull(article, "Article must not be null");
		this.articles.remove(article);
	}
}