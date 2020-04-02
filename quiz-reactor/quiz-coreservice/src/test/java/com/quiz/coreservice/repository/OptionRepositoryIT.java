package com.quiz.coreservice.repository;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.quiz.coreservice.repository.entities.Option;

@DataMongoTest
public class OptionRepositoryIT {

	@Autowired
	private OptionRepository repository;
	
	@Test
	public void givenValidDocumentShouldFindAllReturnDocument() {
		Option expected = Option.builder().withCorrect(true).withValue("findAll").build();
		expected = this.repository.insert(expected);
		List<Option> actual = this.repository.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected)));
	}
	
	@Test
	public void givenValidIdShouldFindByIdReturnDocument() {
		Option expected = Option.builder().withCorrect(true).withValue("findId").build();
		expected = this.repository.insert(expected);
		Option actual = this.repository.findById(expected.getId()).get();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenValidIdShouldDeleteByIdDeleteDocument() {
		Option data = Option.builder().withCorrect(true).withValue("del").build();
		data = this.repository.insert(data);
		this.repository.deleteById(data.getId());
		Option actual = this.repository.findById(data.getId()).orElse(null);
		Assertions.assertNull(actual);
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Option expected = Option.builder().withCorrect(true).withValue("create").build();
		expected = this.repository.insert(expected);
		Option actual = this.repository.findById(expected.getId()).get();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() {
		Option data = Option.builder().withCorrect(true).withValue("update").build();
		data = this.repository.save(data);
		Option expected = data.cloneBuilder().withValue("updated").build();
		Option actual = this.repository.save(expected);
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
}
