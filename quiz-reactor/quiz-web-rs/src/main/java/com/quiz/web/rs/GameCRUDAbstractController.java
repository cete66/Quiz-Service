package com.quiz.web.rs;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.quiz.coreservice.GameCRUDAbstractManager;

public abstract class GameCRUDAbstractController<I, O, SI, SO, ID, T>{

	private final GameCRUDAbstractManager<I, O, SI, SO, ID, T> manager;

	@Autowired
	public GameCRUDAbstractController(
			final GameCRUDAbstractManager<I, O, SI, SO, ID, T> manager) {
		this.manager = manager;
	}
	
	protected GameCRUDAbstractManager<I, O, SI, SO, ID, T> getManager() {
		return manager;
	}
	
	public abstract ResponseEntity<List<O>> findAll();
	
	public abstract ResponseEntity<O> findById(@Valid @NotBlank ID id);
	
	public abstract ResponseEntity<Boolean> deleteById(@Valid @NotBlank ID id);
	
	public abstract ResponseEntity<O> create(@Valid I entity);
	
	public abstract ResponseEntity<O> update(@Valid I entity);
	
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
