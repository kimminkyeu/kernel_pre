package sample.pre.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sample.pre.validation.ValidPhoneNumber;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterDto {

	@Size(min = 4, max = 15) @NotBlank // TODO: Size가 이미 있는데 왜 필요한지 체크
	private final String nickName;

	@PastOrPresent // TODO: Date, LocalDate, LocateDateTime 알아 보기
	private final LocalDate birthDate;

	@Email @NotBlank
	private final String email;

	@ValidPhoneNumber
	private final String phoneNumber;
}
