package sample.pre.board.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.pre.board.dto.BoardCreateRequestDto;
import sample.pre.board.service.BoardService;

/**
 * NOTE: 약간 하면서 느낀점.
 * Exception Try Catch 방식의 프로그래밍은 안전성이 가장 중요해서 인데,
 * C++ 방식에선 잘 안쓰는 이유가 코드가 복잡해지고 성능도 않좋아서임.
 * 그런데 자바는 Relection, AOP 등 언어 단의 막강 기능이 이어서
 *  약간 방식이
 *  (1) 모든 코드는 성공을 가정하고 짠다.
 *  (2) 성공이 아니라면, 모두 예외로 던진다.
 *  (3) AOP를 통해 예외를 한곧에서 중앙 집중 관리한다.
 *   --- 그래서 약간 Assert 처럼, 모든 곳에 성공 조건을 나눔에 따라
 *   --- 코드가 더 보기 쉽고 쓰는 사람도 쉽게 보는 것 아닐까...
 */

@RestController
@RequestMapping("/api/boards")
public class BoardController {

	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@PostMapping("")
	public ResponseEntity<?> createBoard(
		@Valid @RequestBody BoardCreateRequestDto boardRequest
	) {
		boardService.createBoard(boardRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(boardRequest);
	}

	// Using Optional<> instead of (required = false)
	// https://stackoverflow.com/questions/22373696/requestparam-in-spring-mvc-handling-optional-parameters
	@GetMapping("")
	public ResponseEntity<?> getBoard(
		@RequestParam(value = "board_category") Optional<String> category
	) {
		if (category.isPresent()) {
			return ResponseEntity.ok(boardService.getBoardByCategory(category.get()));
		}
		return ResponseEntity.status(HttpStatus.FOUND)
							 .body(boardService.getAllBoards());
	}
}
