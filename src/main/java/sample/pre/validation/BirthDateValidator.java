package sample.pre.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import java.time.LocalDate;
import java.util.regex.Pattern;

// https://www.baeldung.com/spring-mvc-custom-validator
public class BirthDateValidator implements ConstraintValidator<ValidBirthDate, String> {

	private static final String REGEX = "^(\\d{4})-(\\d{2})-(\\d{2})$";
	private static final Pattern PATTERN = Pattern.compile(REGEX);

	@Override
	public void initialize(ValidBirthDate constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String birthDate, ConstraintValidatorContext constraintValidatorContext) {
		if (!PATTERN.matcher(birthDate).matches()) {
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(
				"올바르지 않은 날짜 형식입니다. (Format: YYYY-MM-DD)"
			).addConstraintViolation();
			return false;
		}
		try {
			if (LocalDate.parse(birthDate).isAfter(LocalDate.now())) {
				throw new ValidationException();
			}
		} catch (Exception e) {
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(
				"날짜는 반드시 현재보다 과거여야 합니다."
			).addConstraintViolation();
			return false;
		}
		return true;
	}
}
