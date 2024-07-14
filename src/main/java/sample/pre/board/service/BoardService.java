package sample.pre.board.service;

import java.util.List;
import org.springframework.stereotype.Service;
import sample.pre.board.dto.BoardCreateRequestDto;
import sample.pre.board.dto.BoardGetResponseDto;
import sample.pre.board.mapper.BoardMapper;
import sample.pre.board.repository.BoardRepository;
import sample.pre.exception.ApiErrorCode;
import sample.pre.exception.ApiException;

@Service
public class BoardService {

	private final BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public void createBoard(BoardCreateRequestDto boardRequest) {
		if (boardRepository.findByCategory(boardRequest.getCategory()).isPresent()) {
			throw new ApiException(
				ApiErrorCode.INVALID_BOARD_CATEGORY,
				String.format("%s 카테고리의 게시판이 이미 존재합니다.", boardRequest.getCategory())
			);
		}
		boardRepository.save( BoardMapper.toEntity(boardRequest) );
	}

	public List<BoardGetResponseDto> getAllBoards() {
		return this.boardRepository.findAll().stream()
								   .map(BoardMapper::toDto)
								   .toList();
	}

	public BoardGetResponseDto getBoardByCategory(String category) {
		return boardRepository.findByCategory(category)
							  .map(BoardMapper::toDto)
							  .orElseThrow(() -> new ApiException(
								  ApiErrorCode.BOARD_NOT_FOUND,
								  "존재하지 않는 게시판입니다."
							  ));
	}
}
