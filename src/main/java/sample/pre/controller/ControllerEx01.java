package sample.pre.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
import sample.pre.model.UserSearchQueryParam;


/**
 * NOTE: Input 데이터를 객체로 변환하는 규칙
 * [ mvc의 인자 초기화 우선순위 ]
 * (1순위) NoArgsConstructor --> 1순위 호출
 * (2순위) AllArgsConstructor --> NoArgs가 미정의 상태일 경우에만 호출
 * (3순위) setter --> 정의 되어 있을 경우, 앞단 생성자의 세팅 여부 관계 없이 무조건 호출
 *
 * Ex. NoArgsConstructor가 없는 경우
 *     call AllArgsConstructor --> call each setter
 */

@RestController // NOTE: 그냥 Controller랑 뭐가 다른지?
@Slf4j
@RequestMapping("/api")
public class ControllerEx01 {

	@GetMapping("/add/{number1}/{number2}")
	public int getWithPathVariable(
		@PathVariable final Integer number1, // required
		@PathVariable final Integer number2  // required
	) {
		/** TODO: 입력 값 검증은 어떻게?
		 * "add/1/k"를 입력하면
		 * k --> int 변환 실패 --> MethodArgumentTypeMismatchException 발생
		 * --> TODO: 이걸 던지는 곳은 어디고, 어디서 잡는 건지 break point 찍어보기.
		 */
		return (number1 + number2);
	}

	// 아래 처럼 쿼리 파라미터를 객체로 빼는게 좋은 건지 모르겠다.
	// 필터링 조건이 객체로 뺄 많큼 많은게 애초에 잘못된 것 아닐지...
	@GetMapping(path = "/users")
	public List<?/*User*/> getUserList(UserSearchQueryParam searchCondition) {
		log.info("search : age={} & country={}", searchCondition.getAge(), searchCondition.getCountry());
		// retrieve users...
		return new ArrayList<>(/*...*/);
	}

	/**
	 * @param userId : 차량을 보유한 사용자
	 * @param modelColor : 검색 조건 1. 모델 색상
	 * @param modelYear : 검색 조건 2. 모델 연식
	 * @return List<Car> : 사용자가 보유한 모든 차량 리스트
	 */
	@GetMapping(path = "/cars/{userId}")
	public List<?/*Car*/> getCarListByUserId(
		@PathVariable String userId,
		@RequestParam(name = "model_color", required = false) String modelColor,
		@RequestParam(name = "model_year", required = false) String modelYear
	) {
		// 차량 정보 탐색 후 반환
		return new ArrayList<>(/*...*/);
	}

	/**
	 * NOTE: 반환 타입에 따른 response content-type
	 * String = plain_text
	 * Custom Object = application/json
	 */
	@PostMapping("/users")
	public UserDto postMethod(@RequestBody final UserDto user) {
		log.info("users in post: {}", user);
		return user;
	}

	@PutMapping("/users")
	public UserDto putMethod(@RequestBody final UserDto user) {
		log.info("users in put: {}", user);
		return user;
	}

	@DeleteMapping(path = "/users/{userId}")
	public void deleteMethod(@PathVariable final String userId) {
		log.info("user-Id: {}", userId);
	}
}