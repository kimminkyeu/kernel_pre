package sample.pre.utility;

import java.time.LocalDate;
import sample.pre.model.UserDto;
import sample.pre.model.UserEntity;

// TODO: 인터페이스로 분리할 것.
//  (Ex. class UserMapper extends Mapper<UserDto, UserEntity>)
//   목표: 데이터가 추가됬을 때, 따로 수정하지 않아도 알아서 매핑 할 수 없을지...

public class UserMapper {

	public static UserDto mapEntityToDto(UserEntity entity) {
		return UserDto.builder()
					  .email( entity.getEmail() )
					  .birthDate( entity.getBirthDate().toString() )
					  .nickName( entity.getNickName() )
					  .phoneNumber( entity.getPhoneNumber() )
					  .score( entity.getScore() )
					  .build();
	}

	public static UserEntity mapDtoToEntity(UserDto userDto) {
		return UserEntity.builder()
						 .birthDate( LocalDate.parse(userDto.getBirthDate()) )
						 .nickName( userDto.getNickName() )
						 .email( userDto.getEmail() )
						 .phoneNumber( userDto.getPhoneNumber() )
						 .score( userDto.getScore() )
						 .build();
	}

	public static UserEntity mapDtoToEntityWithGivenId(UserDto userDto, Long id) {
		UserEntity userEntity = mapDtoToEntity(userDto);
		userEntity.setId(id);
		return userEntity;
	}
}
