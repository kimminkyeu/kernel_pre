package sample.pre.article.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArticleCreateRequestDto {

	@NotBlank
	private String writerName;

	@NotBlank
	private String writerEmail;

	@NotBlank
	private String title;

	@NotBlank
	private String body;

	@NotBlank
	private String password;

	@NotBlank
	private String boardCategory;
}