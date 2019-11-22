package net.jaredible.mindbank.dao;

import java.util.List;

public abstract interface Dao<T> {

	T get(long id);

	List<T> getAll();

	int save(T t);

	int update(T t, int[] params);

	int delete(long id);

}
