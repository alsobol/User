package test.user.dao;

public interface UnitOfWork<T> {

	void registerDeleted(Integer id);

	void registerNew(T t);

	void registerModified(T t);

	void commit();
}
