package tk.monkeycode.blogapi.service;

import java.util.List;

public interface CRUDService<T, S> {

	T create(T obj);
	T update(T obj);
	List<T> findAll();
	T findById(S id);
	void delete(S id);
	
}
