package test.user.dao;

public interface UnitOfWork<T> {

	String INSERT = "INSERT";
	String DELETE = "DELETE";
	String UPDATE = "UPDATE";

	void registerDeleted(Integer id);

	void registerNew(T t);

	void registerUpdate(T t);

	void commit();
}
