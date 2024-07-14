package sample.pre.board.mapper;

import sample.pre.article.mapper.ArticleMapper;
import sample.pre.board.dto.BoardGetResponseDto;
import sample.pre.board.dto.BoardCreateRequestDto;
import sample.pre.board.entity.BoardEntity;

public class BoardMapper {

	public static BoardEntity toEntity(BoardCreateRequestDto dto) {
		return BoardEntity.builder()
						  .category( dto.getCategory() )
						  .build();
	}

	public static BoardGetResponseDto toDto(BoardEntity entity) {
		return BoardGetResponseDto.builder()
								  .boardCategory( entity.getCategory() )
								  .articlesCovers(
									  entity.getArticles().stream()
											.map(ArticleMapper::toArticleCoverDto)
											.toList()
								  )
								  .build();
	}
}
