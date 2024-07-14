package sample.pre.article.mapper;

import sample.pre.article.dto.ArticleCoverDto;
import sample.pre.article.dto.ArticleCreateRequestDto;
import sample.pre.article.dto.ArticleContentResponseDto;
import sample.pre.article.entity.ArticleEntity;
import sample.pre.board.entity.BoardEntity;


/**
 * NOTE: ArticleMapper 엔티티는 BoardEntity를 받아야 한다. 그래야 OneToMany 관계 형성이 된다.
 *       Mapper에서 service를 주입받을 수도 있지만, 객체 관계 상 파라미터로 받는게 맞다고 판단된다.
 *       일단 좀 더 고민이 필요하다.
 *       이 부분 때문에 강의에서는 FK로 안했나 보다...
 * @Refernce <a href="https://stackoverflow.com/questions/57010906/in-service-oriented-architecture-is-it-a-good-practise-that-a-mapper-calls-a-se">...</a>
 */
//public class ArticleMapper implements EntityDtoConvertable<ArticleEntity, ArticleCreateRequestDto> {
public class ArticleMapper {
	public static ArticleEntity toEntity(ArticleCreateRequestDto dto, BoardEntity boardEntity) {
		return ArticleEntity.builder()
							.writerName( dto.getWriterName() )
							.writerEmail( dto.getWriterEmail() )
							.password( dto.getPassword() )
							.title( dto.getTitle() )
							.body( dto.getBody() )
							.password( dto.getPassword() )
							.board( boardEntity )
							.build();
	}

	public static ArticleContentResponseDto toArticleContentDto(ArticleEntity articleEntity) {
		return ArticleContentResponseDto.builder()
										.boardCategory( articleEntity.getBoard().getCategory() )
										.body( articleEntity.getBody() )
										.title( articleEntity.getTitle() )
										.writerEmail( articleEntity.getWriterEmail() )
										.writerName( articleEntity.getWriterName() )
										.createAt( articleEntity.getCreatedAt() )
										.updatedAt( articleEntity.getUpdatedAt() )
										.comments( articleEntity.getComments() )
										.build();
	}

	public static ArticleCoverDto toArticleCoverDto(ArticleEntity articleEntity) {
		return ArticleCoverDto.builder()
							  .boardCategory( articleEntity.getBoard().getCategory() )
							  .writerName( articleEntity.getWriterName() )
							  .title( articleEntity.getTitle() )
							  .createdAt( articleEntity.getCreatedAt() )
							  .commentCount( articleEntity.getComments().size() )
							  .build();
	}
}
