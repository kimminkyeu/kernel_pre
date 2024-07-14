package sample.pre.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sample.pre.common.ApiErrorResponse;
import sample.pre.exception.ApiException;

@RestControllerAdvice
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

	private static final String UnidentifiedServerError = "Unidentified Server Error";

	@ExceptionHandler(Exception.class)
	public ApiErrorResponse handleException(Exception e) {
		log.error("handleException", e);
		return ApiErrorResponse.builder()
							   .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
							   .mainReason(UnidentifiedServerError)
						    // .details() --> add additional hints (Ex. Stack Trace Hints...)
							   .build();
	}

	@ExceptionHandler(ApiException.class)
	public ApiErrorResponse handleApiException(ApiException e) {
		log.error("handleApiException", e);
		return e.toApiErrorResponse();
	}
}
