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

import com.quiz.coreservice.QuizManager;
import com.quiz.coreservice.domain.QuizRequest;
import com.quiz.coreservice.domain.QuizResponse;
import com.quiz.coreservice.manager.QuizManagerImpl;
import com.quiz.coreservice.repository.entities.Quiz;
import com.quiz.request.QuizWebRequest;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.QuizWebResponse;

@RestController("quizController")
@RequestMapping(path = "/game/quiz")
@Validated
public class QuizController{

	private final QuizManager quizManager;
	private final QuizManagerImpl manager;
	
	@Autowired
	public QuizController(
			@Qualifier("quizManagerImpl")
			final QuizManagerImpl manager,
			@Qualifier("quizManagerImpl")
			final QuizManager quizManager) {
		this.quizManager = quizManager;
		this.manager = manager;
	}


	@GetMapping(path = "/findAll",
				consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<List<QuizWebResponse>> findAll() {
		return ResponseEntity.ok(manager.findAll());
	}
	

	@GetMapping(path = "/findById/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<QuizWebResponse> findById(@PathVariable("id") @Valid @NotBlank final String id) {
		return ResponseEntity.ok(manager.findById(id));
	}
	

	@DeleteMapping(path = "/deleteById/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") @Valid @NotBlank final String id) {
		return ResponseEntity.ok(manager.deleteById(id));
	}
	

	@PostMapping(path = "/create",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<QuizWebResponse> create(@RequestBody @Valid final QuizWebRequest entity) {
		return ResponseEntity.ok(manager.create(entity));
	}
	

	@PutMapping(path = "/update",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<QuizWebResponse> update(@RequestBody @Valid final QuizWebRequest entity) {
		return ResponseEntity.ok(manager.update(entity, entity.getId()));
	}
	
	@GetMapping(path = "/allQuiz",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<AllQuizWebResponse> allQuiz() {
		return ResponseEntity.ok(this.quizManager.generate(manager.findAll()));
	}
}
