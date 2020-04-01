package com.quiz.coreservice;

import java.util.List;

public interface GameCRUDService<E, R> {

	List<R> findAll();
	R findById(String id);
	Integer deleteById(String id);
	R create(E entity);
	R update(E entity);
}
