package com.quiz.coreservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.test.context.TestPropertySource;

import com.quiz.coreservice.converters.OptionRequestToOptionConverter;
import com.quiz.coreservice.converters.OptionToOptionResponseConverter;
import com.quiz.coreservice.domain.OptionRequest;
import com.quiz.coreservice.domain.OptionResponse;
import com.quiz.coreservice.repository.OptionRepository;
import com.quiz.coreservice.repository.entities.Option;
import com.quiz.framework.converter.Converter;

@TestPropertySource("classpath:application.properties")
public class OptionServiceImplTest {

	
	private Converter<Option, OptionResponse> fromCoreConverter;
	private Converter<OptionRequest, Option> toCoreConverter;
	@Mock
	private OptionRepository repository;
	@InjectMocks
	private OptionServiceImpl service;
	private String errorUpdatingEntity;
	private OptionRequest data = OptionRequest.builder().withId("1").withValue("v").build();
	private Option coreData;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		fromCoreConverter = new OptionToOptionResponseConverter();
		toCoreConverter = new OptionRequestToOptionConverter();
		this.errorUpdatingEntity = "error updating";
		coreData = toCoreConverter.convert(data);
		this.service = new OptionServiceImpl(repository, fromCoreConverter, toCoreConverter, errorUpdatingEntity);
	}
	
	@Test
	public void givenExistentDocumentsShouldFindAllReturnDocuments() {
		Mockito.doReturn(coreData).when(this.repository).insert(ArgumentMatchers.eq(coreData));
		Mockito.doReturn(Arrays.asList(coreData)).when(this.repository).findAll();
		OptionResponse expected = this.service.create(data);
		List<OptionResponse> actual = this.service.findAll();
		MatcherAssert.assertThat(actual, Matchers.hasItem(Matchers.is(expected)));
		
	}
	
	@Test
	public void givenExistentDocumentShouldFindByIdReturnDocument() {
		Optional<Option> optCoreData = Optional.ofNullable(coreData);
		Mockito.doReturn(coreData).when(this.repository).insert(ArgumentMatchers.eq(coreData));
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(coreData.getId()));
		OptionResponse expected = this.service.create(data);
		OptionResponse actual = this.service.findById(expected.getId());
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenValidDocumentShouldCreateOk() {
		Optional<Option> optCoreData = Optional.ofNullable(coreData);
		Mockito.doReturn(coreData).when(this.repository).insert(ArgumentMatchers.eq(coreData));
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(coreData.getId()));
		OptionResponse expected = this.service.create(data);
		OptionResponse actual = this.service.findById(expected.getId());
		MatcherAssert.assertThat(actual, Matchers.is(expected));
	}
	
	@Test
	public void givenExistentDocumentShouldDeleteByIdOk() {
		Optional<Option> optCoreData = Optional.ofNullable(null);
		Mockito.doReturn(coreData).when(this.repository).insert(ArgumentMatchers.eq(coreData));
		Mockito.doReturn(optCoreData).when(this.repository).findById(ArgumentMatchers.eq(coreData.getId()));
		OptionResponse expected = this.service.create(data);
		Integer actual = this.service.deleteById(expected.getId());
		
		MatcherAssert.assertThat(actual, Matchers.is(Integer.valueOf(1)));
		Mockito.verify(this.repository).deleteById(ArgumentMatchers.eq(coreData.getId()));
	}
	
	@Test
	public void givenExistentDocumentShouldUpdateOk() {
		OptionRequest dataUpdt = data.cloneBuilder().withValue("asdasdasd").build();
		Option coreUpdt = toCoreConverter.convert(dataUpdt);
		Optional<Option> optCoreData = Optional.ofNullable(coreUpdt);
		Mockito.doReturn(optCoreData).when(this.repository).findOne(ArgumentMatchers.eq(Example.of(coreUpdt)));
		Mockito.doReturn(coreUpdt).when(this.repository).save(ArgumentMatchers.eq(coreUpdt));
		OptionResponse actualUpdt = this.service.update(dataUpdt);
		OptionResponse exptUpd = this.fromCoreConverter.convert(coreUpdt);
		MatcherAssert.assertThat(actualUpdt, Matchers.is(exptUpd));
	}
	
	
}
