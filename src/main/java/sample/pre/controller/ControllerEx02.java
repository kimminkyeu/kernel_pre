package sample.pre.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.pre.model.UserDto;

@Slf4j
@RestController
@RequestMapping("/api2")
public class ControllerEx02 {

	@PostMapping("/users")
	public ResponseEntity<?> registerUser(
		@RequestBody @Valid final UserDto userDto
	) {
		log.info("userRegisterDto: {}", userDto);
		final String message = "user created";

		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}
}
