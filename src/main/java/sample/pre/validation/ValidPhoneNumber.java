package sample.pre.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface ValidPhoneNumber {

	// TODO: 아래 메시지는 여기서 설정하는게 맞을까? 왜냐면 Regex 규칙에 따라 메시지가 바뀌어야 하잖아.
	String message() default "휴대폰 번호 양식은 010-XXXX-XXXX 이여야 합니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
