package com.quiz.web.res;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.coreservice.manager.OptionManagerImpl;
import com.quiz.request.OptionWebRequest;
import com.quiz.request.OptionWebRequest.OptionWebRequestBuilder;
import com.quiz.response.OptionWebResponse;
import com.quiz.web.QuizServiceApplication;
import com.quiz.web.rs.OptionController;

@SpringBootTest(classes = QuizServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application.properties")
public class OptionControllerIT {

	private static final String ID = "1";
	private static final String VALID_VALUE = "value1";
	private static final String ROOT = "/game";
	private static final String ENDPOINT = "/option";
	private static final String FIND_ALL = "/findAll";
	private static final String CREATE = "/create";
	private static ObjectMapper objectMapper;
	private final OptionWebRequestBuilder webRequestBuilder = OptionWebRequest.builder()
																				.withId(ID)
																				.withValue(VALID_VALUE);

	MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;
	@Autowired
	@Qualifier("optionManagerImpl")
	OptionManagerImpl manager;
	@Autowired
	@Qualifier("optionController")
	OptionController controller;

	@BeforeAll
	public static void setUpClass() {
		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
	}

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
	}
	
	@Test
	public void whenFindAllShouldReturnAllDocuments() throws JsonProcessingException, Exception {
		OptionWebRequest req = this.webRequestBuilder.build();
		
		MockHttpServletResponse response = mockMvc.perform(post(ROOT + ENDPOINT + CREATE).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper
				.writeValueAsString(req)))
		.andReturn().getResponse();

		OptionWebResponse actual = objectMapper.readValue(response.getContentAsString(),
				OptionWebResponse.class);
		OptionWebResponse expected = OptionWebResponse.builder().withId(req.getId()).withValue(req.getValue()).build();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	/**
	 * final TransactionStatusWebRequest request = TransactionStatusWebRequest.builder()
				.withChannel(Channel.CLIENT)
				.withReference(null).build();

		mockMvc.perform(post(ROOT + STATUS).contentType(MediaType.APPLICATION_JSON_UTF8)
								.accept(MediaType.APPLICATION_JSON_UTF8)
								.content(objectMapper
								.writeValueAsString(request)))
		.andExpect(status().isBadRequest());
	 */
}
