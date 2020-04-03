package com.quiz.coreservice.manager;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.quiz.coreservice.converters.OptionResponseToOptionWebResponseConverter;
import com.quiz.coreservice.converters.OptionWebRequestToOptionRequestConverter;
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.service.OptionServiceImpl;
import com.quiz.framework.converter.Converter;
import com.quiz.request.OptionWebRequest;
import com.quiz.response.OptionWebResponse;

public class OptionManagerImplTest {

	@Mock
	private OptionServiceImpl service;
	private OptionManagerImpl manager;
	private OptionWebResponse optWebResp;
	private OptionWebRequest optWebReq = OptionWebRequest.builder().withId("1").withValue("value1").build();
	private OptionRequest optCoreReq;
	private OptionResponse optCoreResp = OptionResponse.builder().withId(optWebReq.getId()).withValue(optWebReq.getId()).build();
	private Converter<OptionResponse, OptionWebResponse> optFromCoreConverter = new OptionResponseToOptionWebResponseConverter();
	private Converter<OptionWebRequest, OptionRequest> optToCoreConverter = new OptionWebRequestToOptionRequestConverter();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		optCoreReq = optToCoreConverter.convert(optWebReq);
		optWebResp = optFromCoreConverter.convert(optCoreResp);
		this.manager = new OptionManagerImpl(service, optFromCoreConverter, optToCoreConverter);
	}
	
	@Test
	public void givenDocumentsShouldFindAllOk() {
		Mockito.doReturn(Arrays.asList(optCoreResp)).when(this.service).findAll();
		List<OptionWebResponse> expected = Arrays.asList(optWebResp);
		List<OptionWebResponse> actual = this.manager.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected.get(0))));
	}
	
	@Test
	public void givenDocumentIdShouldFindByIdReturnDocument() {
		Mockito.doReturn(optCoreResp).when(this.service).findById(ArgumentMatchers.eq(optCoreReq.getId()));
		OptionWebResponse actual = this.manager.findById(optWebReq.getId());
		MatcherAssert.assertThat(actual, Matchers.is(optWebResp));
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Mockito.doReturn(optCoreResp).when(this.service).create(ArgumentMatchers.eq(optCoreReq));
		OptionWebResponse actual = this.manager.create(optWebReq);
		MatcherAssert.assertThat(actual, Matchers.is(optWebResp));
	}
	
	@Test
	public void givenValidDocumentIdShouldDeleteOk() {
		Mockito.doReturn(1).when(this.service).deleteById(ArgumentMatchers.eq(optCoreReq.getId()));
		Boolean actual = this.manager.deleteById(optWebReq.getId());
		MatcherAssert.assertThat(actual, Matchers.is(Boolean.TRUE));
	}
	
	@Test
	public void givenValidDocumentShouldUpdateOk() {
		Mockito.doReturn(optCoreResp).when(this.service).update(ArgumentMatchers.eq(optCoreReq));
		OptionWebResponse actual = this.manager.update(optWebReq);
		MatcherAssert.assertThat(actual, Matchers.is(optWebResp));
	}
}
