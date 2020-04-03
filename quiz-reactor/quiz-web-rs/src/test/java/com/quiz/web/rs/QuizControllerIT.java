package com.quiz.web.rs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.QuizServiceApplication;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.OptionWebRequest.OptionWebRequestBuilder;
import com.quiz.request.QuestionWebRequest;
import com.quiz.request.QuestionWebRequest.QuestionWebRequestBuilder;
import com.quiz.request.QuizWebRequest;
import com.quiz.request.QuizWebRequest.QuizWebRequestBuilder;
import com.quiz.response.AllQuizWebResponse;
import com.quiz.response.OptionWebResponse;
import com.quiz.response.OptionWebResponse.OptionWebResponseBuilder;
import com.quiz.response.QuestionWebResponse.QuestionWebResponseBuilder;
import com.quiz.response.QuizWebResponse;
import com.quiz.response.QuizWebResponse.QuizWebResponseBuilder;
import com.quiz.response.QuestionWebResponse;

@SpringBootTest(classes = QuizServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application.properties")
public class QuizControllerIT {

	private static final String ID = "1";
	private static final String VALID_VALUE = "value1";
	private static final String ROOT = "/game";
	private static final String ENDPOINT = "/quiz";
	private static final String FIND_ALL = "/findAll";
	private static final String GEN_ALL_QUIZ = "/allQuiz";
	private static final String CREATE = "/create";
	private static final String UPDATE = "/update";
	private static final String DELETE = "/deleteById/{id}";
	private static final String FIND_BY_ID = "/findById/{id}";
	private static ObjectMapper objectMapper;
	private final OptionWebRequestBuilder webRequestBuilder = OptionWebRequest.builder()
																				.withValue(VALID_VALUE);
	private final QuestionWebRequestBuilder qstWebReqbuilder = QuestionWebRequest.builder()
																				.withAnswer(webRequestBuilder
																						.withValue(VALID_VALUE).build())
																				.withOptions(Arrays.asList(webRequestBuilder.build()))
																				.withQuestion("question1");
	private final QuizWebRequestBuilder qzWebReqBuilder = QuizWebRequest.builder().withName("quiz1").withQuestions(Arrays.asList(qstWebReqbuilder.build()));
	private final OptionWebResponseBuilder optWebRespBuilder = OptionWebResponse.builder().withValue(VALID_VALUE);
	private final QuestionWebResponseBuilder qstWebRespbuilder = QuestionWebResponse.builder()
																				.withAnswer(optWebRespBuilder.build())
																				.withOptions(Arrays.asList(optWebRespBuilder.build()))
																				.withQuestion("question1");
	private final QuizWebResponseBuilder qzWebRespBuilder = QuizWebResponse.builder().withName("quiz1").withQuestions(Arrays.asList(qstWebRespbuilder.build()));

	MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;
	@Autowired
	@Qualifier("quizController")
	QuizController controller;

	@BeforeAll
	public static void setUpClass() {
		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
	}

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();;
	}
	
	@Test
	public void givenValidDocumentShouldCreateAndReturnDocument() throws JsonProcessingException, Exception {
		QuizWebRequest req = this.qzWebReqBuilder.build();
		
		MockHttpServletResponse response = doInsert(req);

		QuizWebResponse actual = objectMapper.readValue(response.getContentAsString(),
				QuizWebResponse.class);
		QuizWebResponse expected = qzWebRespBuilder.build().cloneBuilder().withId(actual.getId()).build();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
	}
	
	@Test
	public void doFindAllShouldReturnAllDocuments() throws Exception {
		
		QuizWebRequest req = this.qzWebReqBuilder.build();
		doInsert(req);
		
		final MockHttpServletResponse response = mockMvc
				.perform(get(ROOT + ENDPOINT + FIND_ALL)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse();
		
		if (!response.getContentAsString().trim().isEmpty()) {
			objectMapper.readValue(response.getContentAsString(),
					new TypeReference<List<QuizWebResponse>>() {});
		}
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));	
	}
	
	@Test
	public void givenDocumentsShouldGenerateAllQuizWebResponseOk() throws Exception {
		
		QuizWebRequest req = this.qzWebReqBuilder.build();
		doInsert(req);
		
		final MockHttpServletResponse response = mockMvc
				.perform(get(ROOT + ENDPOINT + GEN_ALL_QUIZ)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse();
		AllQuizWebResponse actual = objectMapper.readValue(response.getContentAsString(),AllQuizWebResponse.class);
		
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		Assertions.assertNotNull(actual);
	}
	
	@Test
	public void givenValidDocumentIdShouldFindByIdReturnDocument() throws JsonProcessingException, Exception {
		
		QuizWebRequest req = this.qzWebReqBuilder.build();
		
		MockHttpServletResponse response = doInsert(req);

		QuizWebResponse actual = objectMapper.readValue(response.getContentAsString(),
				QuizWebResponse.class);
		MockHttpServletResponse responseDel =  mockMvc
				.perform(delete(ROOT + ENDPOINT + DELETE, actual.getId())
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse();
		Boolean actualDel = objectMapper.readValue(responseDel.getContentAsString(), Boolean.class);
		QuizWebResponse expected = qzWebRespBuilder.build().cloneBuilder().withId(actual.getId()).build();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(responseDel.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(actualDel, Matchers.is(Boolean.TRUE));
		
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() throws JsonProcessingException, Exception {
		
		QuizWebRequest req = this.qzWebReqBuilder.build();
		
		MockHttpServletResponse response = doInsert(req);

		QuizWebResponse actual = objectMapper.readValue(response.getContentAsString(),
				QuizWebResponse.class);
		MockHttpServletResponse responseDel =  mockMvc
				.perform(get(ROOT + ENDPOINT + FIND_BY_ID, actual.getId())
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse();
		QuizWebResponse actualFind = objectMapper.readValue(responseDel.getContentAsString(), QuizWebResponse.class);
		QuizWebResponse expected = qzWebRespBuilder.build().cloneBuilder().withId(actual.getId()).build();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(responseDel.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(actualFind, Matchers.is(actual));
		
	}
	
	@Test
	public void givenValidDocumentIdShouldDeleteByIdOk() throws JsonProcessingException, Exception {
		
		QuizWebRequest req = this.qzWebReqBuilder.build();
		
		MockHttpServletResponse response = doInsert(req);

		QuizWebResponse actual = objectMapper.readValue(response.getContentAsString(),
				QuizWebResponse.class);
		QuizWebRequest reqUpdt = req.cloneBuilder().withId(actual.getId()).withName("qz1").build();
		MockHttpServletResponse responseUpdt =  mockMvc
				.perform(put(ROOT + ENDPOINT + UPDATE)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper
								.writeValueAsString(reqUpdt)))
				.andReturn().getResponse();
		QuizWebResponse actualUpdt = objectMapper.readValue(responseUpdt.getContentAsString(), QuizWebResponse.class);
		QuizWebResponse expected = qzWebRespBuilder.build().cloneBuilder().withId(actual.getId()).withName(req.getName()).build();
		QuizWebResponse expUpdt = qzWebRespBuilder.build().cloneBuilder().withId(reqUpdt.getId()).withName(reqUpdt.getName()).build();
		
		MatcherAssert.assertThat(actual, Matchers.is(expected));
		MatcherAssert.assertThat(actualUpdt, Matchers.is(expUpdt));
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(responseUpdt.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		
		
	}
	
	private MockHttpServletResponse doInsert(QuizWebRequest webRequest) throws JsonProcessingException, Exception {
		return mockMvc.perform(post(ROOT + ENDPOINT + CREATE)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper
				.writeValueAsString(webRequest)))
		.andReturn().getResponse();
	}
}
