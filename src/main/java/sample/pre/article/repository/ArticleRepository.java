package sample.pre.article.repository;

import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;
import sample.pre.article.entity.ArticleEntity;
import sample.pre.board.entity.BoardEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
	// NOTE: 일단 쿼리문을 직접 작성하고, 원하는 결과를 도출하는 것을 연습해야겠다.
	//	List<ArticleEntity> findAllByBoard(BoardEntity board);
	//	List<ArticleEntity> findAllOrderByCreatedAt();
}
