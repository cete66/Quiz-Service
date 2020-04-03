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
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.manager.OptionManagerImpl;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.request.OptionWebRequest;
import com.quiz.response.OptionWebResponse;

@RestController("optionController")
@RequestMapping(path = "/game/option")
@Validated
public class OptionController {

	private final OptionManagerImpl manager;
	
	@Autowired
	public OptionController(
			@Qualifier("optionManagerImpl")
			final OptionManagerImpl manager) {
		this.manager = manager;
	}

	@GetMapping(path = "/findAll",
				consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<List<OptionWebResponse>> findAll() {
		return ResponseEntity.ok(manager.findAll());
	}
	

	@GetMapping(path = "/findById/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<OptionWebResponse> findById(@PathVariable("id") @NotBlank final String id) {
		return  ResponseEntity.ok(manager.findById(id));
	}
	

	@DeleteMapping(path = "/deleteById/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") @NotBlank final String id) {
		return  ResponseEntity.ok(manager.deleteById(id));
	}
	

	@PostMapping(path = "/create",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<OptionWebResponse> create(@RequestBody @Valid final OptionWebRequest entity) {
		return  ResponseEntity.ok(manager.create(entity));
	}
	

	@PutMapping(path = "/update",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<OptionWebResponse> update(@RequestBody @Valid final OptionWebRequest entity) {
		return  ResponseEntity.ok(manager.update(entity, entity.getId()));
	}
	
}
