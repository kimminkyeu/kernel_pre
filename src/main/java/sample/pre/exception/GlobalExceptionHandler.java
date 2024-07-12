package sample.pre.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE) // default value, always last
// TODO: 예외 처리도 좀 더 세밀히 해서, exception 쪽에서 error code를 설정 할 수 있게 하자.
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleDefaultException(Exception e) {
		log.error("handleDefaultException", e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
