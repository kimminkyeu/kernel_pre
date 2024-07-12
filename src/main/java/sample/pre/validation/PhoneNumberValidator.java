package sample.pre.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

// https://www.baeldung.com/spring-mvc-custom-validator
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

	private static final String REGEX = "^(010)-(\\d{4})-(\\d{4})$";
	private static final Pattern PATTERN = Pattern.compile(REGEX);

	@Override
	public void initialize(ValidPhoneNumber constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
		return (PATTERN.matcher(phoneNumber).matches());
	}
}
