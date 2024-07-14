package sample.pre.comment.service;

import java.util.List;
import org.springframework.stereotype.Service;
import sample.pre.article.repository.ArticleRepository;
import sample.pre.comment.dto.CommentCreateRequestDto;
import sample.pre.comment.entity.CommentEntity;
import sample.pre.comment.mapper.CommentMapper;
import sample.pre.comment.repository.CommentRepository;
import sample.pre.exception.ApiErrorCode;
import sample.pre.exception.ApiException;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final ArticleRepository articleRepository;

	public CommentService(
		CommentRepository commentRepository,
		ArticleRepository articleRepository
	) {
		this.commentRepository = commentRepository;
		this.articleRepository = articleRepository;
	}

	public void createComment(CommentCreateRequestDto commentCreateRequestDto) {
		articleRepository.findById(commentCreateRequestDto.getArticleId())
						 .map(article -> commentRepository.save(
							 CommentMapper.toEntity(commentCreateRequestDto, article)
						 )).orElseThrow(() -> new ApiException(
							 ApiErrorCode.ARTICLE_NOT_FOUND,
							 "존재하지 않는 게시글입니다."
						 ));
	}

	public List<CommentEntity> findAllByArticleId(Long articleId) {
		return commentRepository.findAllByArticleIdOrderByIdAsc(articleId);
	}
}
