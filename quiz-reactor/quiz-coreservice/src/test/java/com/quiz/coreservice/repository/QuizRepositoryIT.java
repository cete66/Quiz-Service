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
import com.quiz.coreservice.repository.entities.Quiz;

@DataMongoTest
public class QuizRepositoryIT {

	private static final String Q = "qst1";
	@Autowired
	private QuizRepository repository;
	private final Option opt = Option.builder().withValue("asd").build();
	private final Option opt1 = opt.cloneBuilder().build();
	private final Question qst1 = Question.builder().withAnswer(opt).withQuestion(Q).withOptions(Arrays.asList(opt1)).build();
	
	
	@Test
	public void givenValidDocumentShouldFindAllReturnDocument() {
		Quiz expected = Quiz.builder().withName("quiz1").withQuestions(Arrays.asList(qst1)).build();
		expected = this.repository.insert(expected);
		List<Quiz> actual = this.repository.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected)));
	}
	
	@Test
	public void givenValidIdShouldFindByIdReturnDocument() {
		Quiz expected = Quiz.builder().withName("quiz1").withQuestions(Arrays.asList(qst1)).build();
		expected = this.repository.insert(expected);
		Quiz actual = this.repository.findById(expected.getId()).get();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenValidIdShouldDeleteByIdDeleteDocument() {
		Quiz data = Quiz.builder().withName("quiz1").withQuestions(Arrays.asList(qst1)).build();
		data = this.repository.insert(data);
		this.repository.deleteById(data.getId());
		Quiz actual = this.repository.findById(data.getId()).orElse(null);
		Assertions.assertNull(actual);
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Quiz expected = Quiz.builder().withName("quiz1").withQuestions(Arrays.asList(qst1)).build();
		expected = this.repository.insert(expected);
		Quiz actual = this.repository.findById(expected.getId()).get();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() {
		Quiz data = Quiz.builder().withName("quiz1").withQuestions(Arrays.asList(qst1)).build();
		data = this.repository.insert(data);
		Quiz expected = data.cloneBuilder().withName("quiz2").build();
		Quiz actual = this.repository.save(expected);
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
}
