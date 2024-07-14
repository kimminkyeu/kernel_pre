package sample.pre.article.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.pre.article.dto.ArticleCreateRequestDto;
import sample.pre.article.dto.ArticleContentRequestDto;
import sample.pre.article.dto.ArticleContentResponseDto;
import sample.pre.article.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	private final ArticleService articleService;

	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@PostMapping("")
	public ResponseEntity<?> createArticle(
		@Valid @RequestBody ArticleCreateRequestDto articleRequest
	) {
		articleService.createArticle(articleRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(articleRequest);
	}

	@PostMapping("/{articleId}")
	public ResponseEntity<ArticleContentResponseDto> getArticleContentById(
		@PathVariable Long articleId,
		@Valid @RequestBody ArticleContentRequestDto articleRequest
	) {
		return ResponseEntity.ok(
			articleService.getArticleContentById(articleId, articleRequest.getPassword())
		);
	}

	@GetMapping("")
	public ResponseEntity<?> getArticlesWithPagination(
		@PageableDefault() @Valid Pageable pageable
	) {
		return ResponseEntity.ok( articleService.all(pageable) );
	}
}