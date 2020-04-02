package com.quiz.coreservice.repository;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.quiz.coreservice.repository.entities.Option;
import com.quiz.coreservice.repository.entities.Question;

@DataMongoTest
public class QuestionRepositoryIT {

	private static final String Q = "qst1";
	@Autowired
	private QuestionRepository repository;
	private final Option opt = Option.builder().withCorrect(true).withValue("asd").build();
	private final Option opt1 = opt.cloneBuilder().withCorrect(false).build();
	
	@Test
	public void givenValidDocumentShouldFindAllReturnDocument() {
		Question expected = Question.builder().withAnswer(opt).withQuestion(Q).withOptions(Arrays.asList(opt1)).build();
		expected = this.repository.insert(expected);
		List<Question> actual = this.repository.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected)));
	}
	
	@Test
	public void givenValidIdShouldFindByIdReturnDocument() {
		Question expected = Question.builder().withAnswer(opt).withQuestion(Q).withOptions(Arrays.asList(opt1)).build();
		expected = this.repository.insert(expected);
		Question actual = this.repository.findById(expected.getId()).get();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenValidIdShouldDeleteByIdDeleteDocument() {
		Question data = Question.builder().withAnswer(opt).withQuestion(Q).withOptions(Arrays.asList(opt1)).build();
		data = this.repository.insert(data);
		this.repository.deleteById(data.getId());
		Question actual = this.repository.findById(data.getId()).orElse(null);
		Assertions.assertNull(actual);
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Question expected = Question.builder().withAnswer(opt).withQuestion(Q).withOptions(Arrays.asList(opt1)).build();
		expected = this.repository.insert(expected);
		Question actual = this.repository.findById(expected.getId()).get();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() {
		Question data = Question.builder().withAnswer(opt).withQuestion(Q).withOptions(Arrays.asList(opt1)).build();
		data = this.repository.save(data);
		Question expected = data.cloneBuilder().withQuestion("asdsad").build();
		Question actual = this.repository.save(expected);
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
}
