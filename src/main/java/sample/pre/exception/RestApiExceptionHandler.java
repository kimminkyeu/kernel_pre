package sample.pre.exception;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 */
@Slf4j
@RestControllerAdvice
@Order(1)
public class RestApiExceptionHandler {
	/**
	 * Response Error Message Body Format, RFC 7807 - "Problem Detail"
	 * 이 객체를 상속해서 팀과 포맷을 정해도 좋을듯.
	 */
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public ProblemDetail handleIndexOutOfBoundsException(Exception e) {
		log.error("handleIndexOutOfBoundsException", e);
		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "에러 메시지입니다.");
	}

	// TODO: 딱 Controller @Validation에서 걸린 경우만 체크하는게 나을까? 이건 좀 범용적인 Exception인거 같기도 하다.
	// 	     아 이 경우에 package나 controller class를 설정하는 건 가보다.
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

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