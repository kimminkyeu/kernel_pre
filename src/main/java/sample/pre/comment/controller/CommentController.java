package sample.pre.comment.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.pre.comment.dto.CommentCreateRequestDto;
import sample.pre.comment.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	private final CommentService commentService;

	CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("")
	public ResponseEntity<?> createComment(
		@Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto
	) {
		commentService.createComment(commentCreateRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(commentCreateRequestDto);
	}
}
