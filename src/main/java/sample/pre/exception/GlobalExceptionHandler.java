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
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleDefaultException(Exception e) throws Exception {
		log.error("handleDefaultException", e);
		return ResponseEntity.status(HttpStatus.OK).body(e.getMessage()); // 그냥 hello만 출력
	}
}
