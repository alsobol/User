package test.user.dao;

import java.util.List;

public interface BaseDao<T> {

	List<T> getAll();

	T getById(Integer id);

	void delete(Integer id);

	void create(T t);

	void update(T t);

}
