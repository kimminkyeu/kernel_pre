package sample.pre.exception.handler;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sample.pre.common.ApiErrorResponse;

@Slf4j
@RestControllerAdvice
@Order(1)
public class ApiExceptionHandler {

	@ExceptionHandler( MethodArgumentNotValidException.class )
	public ApiErrorResponse handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e) {

		log.error("handleMethodArgumentNotValidException", e);

		List<String> validationErrorDetails = e.getBindingResult()
											   .getFieldErrors()
											   .stream()
											   .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
											   .toList();

		return ApiErrorResponse.builder()
							   .httpStatus(HttpStatus.BAD_REQUEST)
							   .mainReason("입력 검증에 실패 하였습니다.")
							   .details(validationErrorDetails)
							   .build();
	}
}