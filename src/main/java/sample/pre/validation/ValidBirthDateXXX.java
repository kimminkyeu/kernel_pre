package sample.pre.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @deprecated
 * 일단 아래 방식이 더 보기 어렵고, 일단 작동을 안하는 문제
 * 왜 안되냐면, 이 데이터는 LocalDate 타입으로 먼저 받아온 뒤에 해당 타입을 Validation하기 때문.
 * 그래서 애초에 받는 타입이 String이여야 한다...
 */
//@ReportAsSingleViolation
//@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
//@PastOrPresent
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ConstraintComposition(CompositionType.AND)
public @interface ValidBirthDateXXX {

	// TODO: 아래 메시지는 여기서 설정하는게 맞을까? 왜냐면 Regex 규칙에 따라 메시지가 바뀌어야 하잖아.
	String message() default "날짜가 유효하지 않습니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}