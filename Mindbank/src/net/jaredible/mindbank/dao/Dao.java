package net.jaredible.mindbank.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

	Optional<T> get(long id);

	List<T> getAll();

	boolean save(T t);

	int update(T t, String[] params);

	boolean delete(T t);

}
