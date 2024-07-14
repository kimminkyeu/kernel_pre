package sample.pre.article.service;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sample.pre.article.dto.ArticleCreateRequestDto;
import sample.pre.article.dto.ArticleContentResponseDto;
import sample.pre.article.entity.ArticleEntity;
import sample.pre.article.mapper.ArticleMapper;
import sample.pre.article.repository.ArticleRepository;
import sample.pre.board.repository.BoardRepository;
import sample.pre.common.PaginatedListDto;
import sample.pre.common.Pagination;
import sample.pre.exception.ApiErrorCode;
import sample.pre.exception.ApiException;

@Service
public class ArticleService {
	private final ArticleRepository articleRepository;

	// NOTE: 여기서 repository를 써야 할까 service를 써야 할까...
	private final BoardRepository boardRepository;

	public ArticleService(
		ArticleRepository articleRepository,
		BoardRepository boardRepository
	) {
		this.articleRepository = articleRepository;
		this.boardRepository = boardRepository;
	}

	public PaginatedListDto<ArticleEntity> all(Pageable pageable) {
		var list = articleRepository.findAll(pageable);
		var pagination = Pagination.builder()
								   .page( list.getNumber() )
								   .size( list.getSize() )
								   .currentElements( list.getNumberOfElements() )
								   .totalElements( list.getTotalElements() )
								   .totalPage( list.getTotalPages() )
								   .build();

		return PaginatedListDto.<ArticleEntity>builder()
						   .pagination( pagination )
						   .contentList( list.getContent() )
						   .build();
	}

	// NOTE: 서비스에서 RequestDTO를 받는게 맞을까?
	//       내 생각엔 RequestDTO는 controller에서만 사용하는게 맞을 것 같은데...?
	public void createArticle(ArticleCreateRequestDto articleCreateRequestDto) {
		boardRepository.findByCategory(articleCreateRequestDto.getBoardCategory())
					   .map(board -> articleRepository.save(
						   ArticleMapper.toEntity(articleCreateRequestDto, board)
					   )).orElseThrow(() -> new ApiException(
						   ApiErrorCode.INVALID_BOARD_CATEGORY,
						   "존재하지 않는 게시판에는 글을 생성할 수 없습니다."
					   ));
	}

	// NOTE: 여기도 마찬가지. 서비스에서 ResponseDTO를 반환하는게 맞을까...? (컨트롤러에서 안하고?)
	public ArticleContentResponseDto getArticleContentById(
		@NotNull Long articleId,
		@NotEmpty String password
	) {
		ArticleEntity articleEntity = articleRepository.findById(articleId).map(
			article -> {
				if (false == article.getPassword().equals(password)) {
					throw new ApiException(
						ApiErrorCode.UNAUTHORIZED_VIEWER,
						"암호가 유효하지 않습니다."
					);
				}
				return article;
			}).orElseThrow(
				() -> new ApiException(ApiErrorCode.ARTICLE_NOT_FOUND, "게시글이 존재하지 않습니다.")
		);

		return ArticleMapper.toArticleContentDto(articleEntity);
	}

	// NOTE: Entity가 그대로 노출되서 문제가 될 것 (오직 개발 테스트용)
	public List<ArticleEntity> getAllArticles() {
		return articleRepository.findAll();
	}
}
