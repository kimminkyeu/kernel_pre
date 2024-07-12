package sample.pre.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import sample.pre.base.database.SimpleDataRepository;
import sample.pre.model.UserEntity;

@Repository
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {

	public List<UserEntity> findAllByScoreGreaterThan(int score) {
		return this.findAll().stream()
			.filter(userEntity -> (score <= userEntity.getScore()))
			.toList();
	}
}