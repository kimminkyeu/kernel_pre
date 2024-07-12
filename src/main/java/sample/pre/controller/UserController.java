package sample.pre.controller;


import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.pre.model.UserDto;
import sample.pre.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	UserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping("")
	public List<UserDto> findUsers(
		// TODO: Integer로 받는게 나은데, 그러면, 타입 오류시 예외 처리는 어떻게 할지?
		@RequestParam(name = "score_min", required = false) Integer scoreMin
	) {
		if (scoreMin != null) {
			return userService.getUsers(user -> (scoreMin <= user.getScore()));
		}
		return userService.getUsers();
	}

	@PostMapping("")
	public ResponseEntity<?> createUser(
		@RequestBody @Valid final UserDto user
	) {
		userService.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(
		@PathVariable final Long userId, // TODO: /k 가 들어올 경우 예외 처리
		@RequestBody @Valid final UserDto newUserData
	) {
		userService.updateUser(userId, newUserData);
		return ResponseEntity.status(HttpStatus.OK).body("User updated");
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(
		@PathVariable final Long userId
	) {
		userService.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body("User deleted");
	}
}
