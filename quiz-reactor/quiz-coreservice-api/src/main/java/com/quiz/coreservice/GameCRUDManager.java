package com.quiz.coreservice;

import java.util.List;

public interface GameCRUDManager<I, O> {

	List<O> findAll();
	O findById(String id);
	Boolean deleteById(String id);
	O create(I entity);
	O update(I entity);
}
