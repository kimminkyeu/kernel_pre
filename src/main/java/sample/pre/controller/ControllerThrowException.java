package sample.pre.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.pre.model.UserDtoXXX;

// without response body
@Controller
@RequestMapping("/test")
public class ControllerThrowException {

	@GetMapping("/get1")
	public ResponseEntity<?> getTest1() {
		List.of("hello").get(1); // throws index out of bound
		final UserDtoXXX userDto = UserDtoXXX.builder()
											 .userName("1")
											 .mailAddress("2")
											 .phoneNumber("3")
											 .isKorean(true)
											 .build();
		return ResponseEntity.status(HttpStatus.CREATED).body(userDto.toString());
	}

	@GetMapping("/get2")
	public ResponseEntity<?> getTest2() {
		throw new RuntimeException("Hello, Runtime Exception !!!");
	}
}
