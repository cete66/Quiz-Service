package com.quiz.coreservice;

import java.util.List;

import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;

public abstract class GameCRUDAbstractManager<I, O, SI, SO> implements GameCRUDManager<I, O>{

	private final GameCRUDService<SI, SO> service;
	private final Converter<SO, O> fromCoreConverter;
	private final Converter<I, SI> toCoreConverter;
	private final ListConverter<SO, O> fromCoreListConverter;

	
	public GameCRUDAbstractManager(final GameCRUDService<SI, SO> service, 
			final Converter<SO, O> fromCoreConverter,
			final Converter<I, SI> toCoreConverter){
		this.service = service;
		this.fromCoreConverter = fromCoreConverter;
		this.fromCoreListConverter = new ListConverter<SO, O>(fromCoreConverter);
		this.toCoreConverter = toCoreConverter;
	}
	
	@Override
	public List<O> findAll() {
		return this.fromCoreListConverter.convert(this.service.findAll());
	}
	
	@Override
	public O findById(String id) {
		return this.fromCoreConverter.convert(this.service.findById(id));
	}
	
	@Override
	public Boolean deleteById(String id) {
		return this.service.deleteById(id) == 1;
	}
	
	@Override
	public O create(I entity) {
		return this.fromCoreConverter.convert(
				this.service.create(
						this.toCoreConverter.convert(entity)));
	}
	
	@Override
	public O update(I entity) {
		return this.fromCoreConverter.convert(
				this.service.update(
						this.toCoreConverter.convert(entity)));
	}
}
