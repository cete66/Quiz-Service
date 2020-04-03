package com.quiz.coreservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;

public abstract class GameCRUDAbstractManager<I, O, SI, SO, ID, T>{

	private final GameCRUDAbstractService<T, SI, SO, ID> service;
	private final Converter<SO, O> fromCoreConverter;
	private final Converter<I, SI> toCoreConverter;
	private final ListConverter<SO, O> fromCoreListConverter;

	@Autowired
	public GameCRUDAbstractManager(final GameCRUDAbstractService<T, SI, SO, ID> service, 
			final Converter<SO, O> fromCoreConverter,
			final Converter<I, SI> toCoreConverter){
		this.service = service;
		this.fromCoreConverter = fromCoreConverter;
		this.fromCoreListConverter = new ListConverter<SO, O>(fromCoreConverter);
		this.toCoreConverter = toCoreConverter;
	}
	
	public List<O> findAll() {
		return this.fromCoreListConverter.convert(this.service.findAll());
	}
	
	public O findById(ID id) {
		return this.fromCoreConverter.convert(this.service.findById(id));
	}
	
	public Boolean deleteById(ID id) {
		return this.service.deleteById(id) == 1;
	}
	
	public O create(I entity) {
		return this.fromCoreConverter.convert(
				this.service.create(
						this.toCoreConverter.convert(entity)));
	}
	
	public O update(I entity) {
		return this.fromCoreConverter.convert(
				this.service.update(
						this.toCoreConverter.convert(entity)));
	}
}
