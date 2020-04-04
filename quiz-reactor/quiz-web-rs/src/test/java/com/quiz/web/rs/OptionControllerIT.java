package com.quiz.web.rs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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
import com.quiz.response.OptionWebResponse;

@SpringBootTest(classes = QuizServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application.properties")
public class OptionControllerIT {

	private static final String ID = "1";
	private static final String VALID_VALUE = "value1";
	private static final String ROOT = "/game";
	private static final String ENDPOINT = "/option";
	private static final String FIND_ALL = "/findAll";
	private static final String CREATE = "/create";
	private static final String UPDATE = "/update";
	private static final String DELETE = "/deleteById/{id}";
	private static final String FIND_BY_ID = "/findById/{id}";
	private static ObjectMapper objectMapper;
	private final OptionWebRequestBuilder webRequestBuilder = OptionWebRequest.builder()
																				.withValue(VALID_VALUE);

	MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;
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
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();;
	}
	
	@Test
	public void givenValidDocumentShouldCreateAndReturnDocument() throws JsonProcessingException, Exception {
		OptionWebRequest req = this.webRequestBuilder.build();
		
		MockHttpServletResponse response = doInsert(req);

		OptionWebResponse actual = objectMapper.readValue(response.getContentAsString(),
				OptionWebResponse.class);
		OptionWebResponse expected = OptionWebResponse.builder().withId(actual.getId()).withValue(req.getValue()).build();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
	}
	
	@Test
	public void doFindAllShouldReturnAllDocuments() throws Exception {
		
		OptionWebRequest req = this.webRequestBuilder.build();
		doInsert(req);
		
		final MockHttpServletResponse response = mockMvc
				.perform(get(ROOT + ENDPOINT + FIND_ALL)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse();
		
		if (!response.getContentAsString().trim().isEmpty()) {
			objectMapper.readValue(response.getContentAsString(),
					new TypeReference<List<OptionWebResponse>>() {});
		}
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));	
	}
	
	@Test
	public void givenValidDocumentIdShouldFindByIdReturnDocument() throws JsonProcessingException, Exception {
		
		OptionWebRequest req = this.webRequestBuilder.build();
		
		MockHttpServletResponse response = doInsert(req);

		OptionWebResponse actual = objectMapper.readValue(response.getContentAsString(),
				OptionWebResponse.class);
		MockHttpServletResponse responseDel =  mockMvc
				.perform(delete(ROOT + ENDPOINT + DELETE, actual.getId())
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse();
		Boolean actualDel = objectMapper.readValue(responseDel.getContentAsString(), Boolean.class);
		OptionWebResponse expected = OptionWebResponse.builder().withId(actual.getId()).withValue(req.getValue()).build();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(responseDel.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(actualDel, Matchers.is(Boolean.TRUE));
		
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() throws JsonProcessingException, Exception {
		
		OptionWebRequest req = this.webRequestBuilder.build();
		
		MockHttpServletResponse response = doInsert(req);

		OptionWebResponse actual = objectMapper.readValue(response.getContentAsString(),
				OptionWebResponse.class);
		MockHttpServletResponse responseDel =  mockMvc
				.perform(get(ROOT + ENDPOINT + FIND_BY_ID, actual.getId())
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse();
		OptionWebResponse actualFind = objectMapper.readValue(responseDel.getContentAsString(), OptionWebResponse.class);
		OptionWebResponse expected = OptionWebResponse.builder().withId(actual.getId()).withValue(req.getValue()).build();
		MatcherAssert.assertThat(actual, Matchers.is(expected));
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(responseDel.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(actualFind, Matchers.is(actual));
		
	}
	
	@Test
	public void givenValidDocumentIdShouldDeleteByIdOk() throws JsonProcessingException, Exception {
		
		OptionWebRequest req = this.webRequestBuilder.build();
		
		MockHttpServletResponse response = doInsert(req);

		OptionWebResponse actual = objectMapper.readValue(response.getContentAsString(),
				OptionWebResponse.class);
		OptionWebRequest reqUpdt = OptionWebRequest.builder().withId(actual.getId()).withValue("asdasd").build();
		MockHttpServletResponse responseUpdt =  mockMvc
				.perform(put(ROOT + ENDPOINT + UPDATE)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper
								.writeValueAsString(reqUpdt)))
				.andReturn().getResponse();
		OptionWebResponse actualUpdt = objectMapper.readValue(responseUpdt.getContentAsString(), OptionWebResponse.class);
		OptionWebResponse expected = OptionWebResponse.builder().withId(actual.getId()).withValue(req.getValue()).build();
		OptionWebResponse expUpdt = OptionWebResponse.builder().withId(reqUpdt.getId()).withValue(reqUpdt.getValue()).build();
		
		MatcherAssert.assertThat(actual, Matchers.is(expected));
		MatcherAssert.assertThat(actualUpdt, Matchers.is(expUpdt));
		MatcherAssert.assertThat(response.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		MatcherAssert.assertThat(responseUpdt.getStatus(), Matchers.is(Integer.valueOf(HttpStatus.OK.value())));
		
		
	}
	
	private MockHttpServletResponse doInsert(OptionWebRequest webRequest) throws JsonProcessingException, Exception {
		return mockMvc.perform(post(ROOT + ENDPOINT + CREATE)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper
				.writeValueAsString(webRequest)))
		.andReturn().getResponse();
	}
}
