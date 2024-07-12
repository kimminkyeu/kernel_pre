package sample.pre.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @deprecated 예전 학습 코드입니다. 더 이상 사용하지 않습니다.
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter // No Setter
@Builder
public class UserDtoXXX {

	private String userName;

	private String phoneNumber;

	private String mailAddress;

	private Boolean isKorean;

	@Override
	public String toString() {
		return "UserDto{" +
			"userName='" + userName + '\'' +
			", phoneNumber='" + phoneNumber + '\'' +
			", mailAddress='" + mailAddress + '\'' +
			", isKorean=" + isKorean +
			'}';
	}
}
