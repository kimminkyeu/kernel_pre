package sample.pre.service;

import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.pre.model.UserEntity;
import sample.pre.model.UserDto;
import sample.pre.repository.UserRepository;
import sample.pre.utility.UserMapper;

@Slf4j
@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void registerUser(final UserDto userDto) {
		UserEntity created = userRepository.save( UserMapper.mapDtoToEntity(userDto) );
		// TODO: 이미 사용자가 존재하는 경우, UserAlreadyExistsException 발생
		log.info("User id{} created: {}", created.getId(), created);
	}

	// TODO: <? super T> Generic에 대해 더 알아보기
	public List<UserDto> getUsers(final Predicate<? super UserDto> filter) {
		return userRepository.findAll().stream()
							 .map(UserMapper::mapEntityToDto)
							 .filter(filter)
							 .toList();
	}

	public List<UserDto> getUsers() {
		return userRepository.findAll().stream()
					  .map(UserMapper::mapEntityToDto)
					  .toList();
	}

	public void updateUser(final Long userId, final UserDto newUserDto) {
		userRepository.findById(userId)
					  .ifPresent(userEntity -> {
						  UserEntity entity = UserMapper.mapDtoToEntityWithGivenId(newUserDto, userId);
						  userRepository.save(entity);
					  });
	}

	public void deleteUser(final Long userId) {
		// TODO: 이미 존재하는 사용자라면...?
		if (userRepository.findById(userId).isEmpty()) {
			// TODO: 어떻게 예외 처리 하는게 맞을지...?
			throw new RuntimeException("User does not exist");
		}
		userRepository.delete(userId);
	}
}
