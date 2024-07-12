package sample.pre.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sample.pre.validation.ValidBirthDate;
import sample.pre.validation.ValidPhoneNumber;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {

	@NotBlank // TODO: Size가 이미 있는데 왜 필요한지 체크
	@Size(min = 4, max = 15)
	private final String nickName;

	@ValidBirthDate // TODO: Date, LocalDate, LocateDateTime 알아 보기
	private final String birthDate; // TODO: 이걸 LocalDate으로 받으면 포맷팅 Exception이 분리된다...
	// NOTE: LocalDate으로 받고, 변환 이전에 String으로 검사하는게 좋을까...?

	@NotBlank
	@Email
	private final String email;

	@ValidPhoneNumber
	private final String phoneNumber;

	@NotNull
	@Min(value = 0) @Max(value = 100) // NOTE: @Size는 배열 길이...
	private final Integer score;
}
