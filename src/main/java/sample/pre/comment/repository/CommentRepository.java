package sample.pre.comment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.pre.comment.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	// select * from comments as c where c.article_id = articleId order by id desc
	List<CommentEntity> findAllByArticleIdOrderByIdAsc(Long articleId);
}
