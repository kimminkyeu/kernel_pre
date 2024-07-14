package sample.pre.comment.mapper;

import sample.pre.article.entity.ArticleEntity;
import sample.pre.comment.dto.CommentCreateRequestDto;
import sample.pre.comment.entity.CommentEntity;

public class CommentMapper {
	public static CommentEntity toEntity(CommentCreateRequestDto dto, ArticleEntity article) {
		return CommentEntity.builder()
							.writerName( dto.getWriterName() )
							.body( dto.getBody() )
							.article( article )
							.build();
	}
}
