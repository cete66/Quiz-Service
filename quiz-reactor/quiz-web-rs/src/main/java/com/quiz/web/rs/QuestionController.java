package com.quiz.web.rs;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.coreservice.GameCRUDAbstractManager;
import com.quiz.coreservice.domain.QuestionRequest;
import com.quiz.coreservice.domain.QuestionResponse;
import com.quiz.coreservice.manager.QuestionManagerImpl;
import com.quiz.coreservice.repository.entities.Question;
import com.quiz.request.QuestionWebRequest;
import com.quiz.response.QuestionWebResponse;

@RestController("questionController")
@RequestMapping(path = "/game/question")
@Validated
public class QuestionController{

	private final QuestionManagerImpl manager;
	
	@Autowired
	public QuestionController(
			@Qualifier("questionManagerImpl")
			final QuestionManagerImpl manager) {
		this.manager = manager;
	}
	

	@GetMapping(path = "/findAll",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<List<QuestionWebResponse>> findAll() {
		return ResponseEntity.ok(manager.findAll());
	}
	

	@GetMapping(path = "/findById/{id}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<QuestionWebResponse> findById(@PathVariable("id") @NotBlank final String id) {
		return ResponseEntity.ok(manager.findById(id));
	}
	

	@DeleteMapping(path = "/deleteById/{id}", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") @NotBlank final String id) {
		return ResponseEntity.ok(manager.deleteById(id));
	}
	

	@PostMapping(path = "/create",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<QuestionWebResponse> create(@RequestBody @Valid final QuestionWebRequest entity) {
		return ResponseEntity.ok(manager.create(entity));
	}
	

	@PutMapping(path = "/update",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<QuestionWebResponse> update(@RequestBody @Valid final QuestionWebRequest entity) {
		return ResponseEntity.ok(manager.update(entity, entity.getId()));
	}

}
