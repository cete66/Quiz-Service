package com.quiz.web.rs;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.quiz.coreservice.GameCRUDManager;
import com.quiz.rs.GameCRUDController;

public abstract class GameCRUDAbstractController<I, O> implements GameCRUDController<I, O>{

	private final GameCRUDManager<I, O> manager;

	@Autowired
	public GameCRUDAbstractController(
			final GameCRUDManager<I, O> manager) {
		this.manager = manager;
	}
	
	@Override
	public ResponseEntity<List<O>> findAll() {
		return ResponseEntity.ok(this.manager.findAll());
	}
	
	@Override
	public ResponseEntity<O> findById(String id) {
		return ResponseEntity.ok(this.manager.findById(id));
	}
	
	@Override
	public ResponseEntity<Boolean> deleteById(String id) {
		return ResponseEntity.ok(this.manager.deleteById(id));
	}
	
	@Override
	public ResponseEntity<O> create(I entity) {
		return ResponseEntity.ok(this.manager.create(entity));
	}
	
	@Override
	public ResponseEntity<O> update(I entity) {
		return ResponseEntity.ok(this.manager.update(entity));
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({	InvalidParameterException.class,
						NullArgumentException.class})
	public Map<String, String> handleValidationExceptions(
	  Exception ex) {
	    Map<String, String> errors = new HashMap<>();
	    errors.put("Cause", ex.getMessage());
	    return errors;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public Map<String, String> handleSpringValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}
