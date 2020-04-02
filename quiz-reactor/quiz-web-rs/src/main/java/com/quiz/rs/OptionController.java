package com.quiz.rs;

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
import com.quiz.request.OptionWebRequest;
import com.quiz.response.OptionWebResponse;

@RestController("optionController")
@RequestMapping(path = "/game/option")
@Validated
public class OptionController extends GameCRUDAbstractController<OptionWebRequest, OptionWebResponse>{

	@Autowired
	public OptionController(
			@Qualifier("optionManagerImpl")
			final GameCRUDManager<OptionWebRequest, OptionWebResponse> manager) {
		super(manager);
	}

	@Override
	@GetMapping(path = "/findAll",
				consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, /** for compatibility with all browsers*/
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<List<OptionWebResponse>> findAll() {
		return super.findAll();
	}
	
	@Override
	@GetMapping(path = "/findById/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, /** for compatibility with all browsers*/
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<OptionWebResponse> findById(@PathParam("id") @Valid @NotBlank final String id) {
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
	public ResponseEntity<OptionWebResponse> create(@RequestBody @Valid final OptionWebRequest entity) {
		return super.create(entity);
	}
	
	@Override
	@PutMapping(path = "/update",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, /** for compatibility with all browsers*/
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<OptionWebResponse> update(@RequestBody @Valid final OptionWebRequest entity) {
		return super.update(entity);
	}
	
}
