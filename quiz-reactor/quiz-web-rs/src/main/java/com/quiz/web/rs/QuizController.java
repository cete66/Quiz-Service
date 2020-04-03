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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.coreservice.GameCRUDManager;
import com.quiz.coreservice.QuizManager;
import com.quiz.request.QuestionWebRequest;
import com.quiz.request.QuizWebRequest;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.QuestionWebResponse;
import com.quiz.response.QuizWebResponse;
import com.quiz.rs.GameCRUDController;

@RestController("quizController")
@RequestMapping(path = "/game/quiz")
@Validated
public class QuizController extends GameCRUDAbstractController<QuizWebRequest, QuizWebResponse> implements GameCRUDController<QuizWebRequest, QuizWebResponse>{

	private final QuizManager quizManager;
	
	@Autowired
	public QuizController(
			@Qualifier("quizManagerImpl")
			final GameCRUDManager<QuizWebRequest, QuizWebResponse> manager,
			@Qualifier("quizManagerImpl")
			final QuizManager quizManager) {
		super(manager);
		this.quizManager = quizManager;
	}

	@Override
	@GetMapping(path = "/findAll",
				consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, /** for compatibility with all browsers*/
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<List<QuizWebResponse>> findAll() {
		return super.findAll();
	}
	
	@Override
	@GetMapping(path = "/findById/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, /** for compatibility with all browsers*/
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<QuizWebResponse> findById(@PathParam("id") @Valid @NotBlank final String id) {
		return super.findById(id);
	}
	
	@Override
	@DeleteMapping(path = "/deleteById/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, /** for compatibility with all browsers*/
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<Boolean> deleteById(@PathParam("id") @Valid @NotBlank final String id) {
		return super.deleteById(id);
	}
	
	@Override
	@PostMapping(path = "/create",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, /** for compatibility with all browsers*/
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<QuizWebResponse> create(@RequestBody @Valid final QuizWebRequest entity) {
		return super.create(entity);
	}
	
	@Override
	@PutMapping(path = "/update",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, /** for compatibility with all browsers*/
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<QuizWebResponse> update(@RequestBody @Valid final QuizWebRequest entity) {
		return super.update(entity);
	}
	
	@GetMapping(path = "/allQuiz",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, /** for compatibility with all browsers*/
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<AllQuizWebResponse> allQuiz() {
		return ResponseEntity.ok(this.quizManager.generate(super.findAll().getBody()));
	}
}
