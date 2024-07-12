package sample.pre.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {BirthDateValidator.class})
public @interface ValidBirthDate {

	// TODO: 아래 메시지는 여기서 설정하는게 맞을까? 왜냐면 Regex 규칙에 따라 메시지가 바뀌어야 하잖아.
	// 일단 2가지 케이스, 즉 날짜 문제와 미래 문제를 각각 validator에서 처리하기로.
	String message() default "invalid date";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
