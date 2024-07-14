package sample.pre.article.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.pre.comment.entity.CommentEntity;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArticleContentResponseDto {

	private String writerName;
	private String writerEmail;

	private String title;
	private String body;

	private String boardCategory;

	// NOTE: LocalDate으로 하는게 맞을지 체크
	private LocalDateTime createAt;
	private LocalDateTime updatedAt;

	// TODO: CommentResponseDto로 변경
	private List<CommentEntity> comments;
}