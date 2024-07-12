package sample.pre.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class UserDto {

	private String userName;

	private String phoneNumber;

	private String mailAddress;

	private Boolean isKorean;

	protected UserDto(String userName, String phoneNumber, String mailAddress, Boolean isKorean) {
		System.out.println("All ARGS constructor called");
		System.out.println(" - Inside constructor... " + userName + " " + phoneNumber + " " + mailAddress);
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.mailAddress = mailAddress;
		this.isKorean = isKorean;
	}

	public void setMailAddress(String mailAddress) {
		System.out.println("mailAddress setter called");
		this.mailAddress = mailAddress;
	}

	public void setPhoneNumber(String phoneNumber) {
		System.out.println("phoneNumber setter called");
		this.phoneNumber = phoneNumber;
	}

	public void setUserName(String userName) {
		System.out.println("userName setter called");
		this.userName = userName;
	}

	public void setIsKorean(Boolean isKorean) {
		System.out.println("userName setter called");
		this.isKorean = isKorean;
	}

	public String getUserName() {
		System.out.println("userName getter called");
		return userName;
	}

	public String getPhoneNumber() {
		System.out.println("phoneNumber getter called");
		return phoneNumber;
	}

	public String getMailAddress() {
		System.out.println("mainAddress getter called");
		return mailAddress;
	}

	public Boolean getIsKorean() {
		System.out.println("isKorean getter called");
		return isKorean;
	}

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
