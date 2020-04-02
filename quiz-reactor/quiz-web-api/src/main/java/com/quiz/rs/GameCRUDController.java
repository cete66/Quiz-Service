package com.quiz.rs;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface GameCRUDController<I, O> {

	ResponseEntity<List<O>> findAll();
	ResponseEntity<O> findById(String id);
	ResponseEntity<Boolean> deleteById(String id);
	ResponseEntity<O>create(I entity);
	ResponseEntity<O> update(I entity);
}
