package sample.pre.model;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.pre.base.entity.Entity;

// TODO: https://projectlombok.org/features/EqualsAndHashCode --> callSuper 학습 진행
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter // no setter
@Builder
public class UserEntity extends Entity {
	private String nickName;
	private LocalDate birthDate;
	private String email;
	private String phoneNumber;
	private Integer score;
}