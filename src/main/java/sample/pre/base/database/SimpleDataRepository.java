package sample.pre.base.database;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import sample.pre.base.entity.Entity;

public abstract class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {

	private final List<T> dataList = new ArrayList<T>();

	private static long index = 0;

	private final Comparator<T> comparator = new Comparator<T>() {
		@Override
		public int compare(T o1, T o2) {
			return Long.compare(o1.getId(), o2.getId());
		}
	};

	@Override
	public T save(T data) {
		if (Objects.isNull(data)) {
			throw new RuntimeException("Data is null");
		}
		Optional<T> prev = dataList.stream().filter(x -> x.getId().equals(data.getId())).findFirst();

		if (prev.isPresent()) {
			dataList.remove(prev.get());
			dataList.add(data);
		} else {
			data.setId(index++);
			dataList.add(data);
		}
		return data;
	}

	@Override
	public Optional<T> findById(ID id) {
		return dataList.stream().filter(x -> x.getId().equals(id)).findFirst();
	}

	@Override
	public List<T> findAll() {
		return dataList.stream().sorted(comparator).toList();
	}

	@Override
	public void delete(ID id) {
		// TODO: 여기서 이미 존재하는 엔티티를 삭제하려고 하면, 터뜨리는게 맞지 않을까...?
		dataList.removeIf(x -> x.getId().equals(id));
	}
}
