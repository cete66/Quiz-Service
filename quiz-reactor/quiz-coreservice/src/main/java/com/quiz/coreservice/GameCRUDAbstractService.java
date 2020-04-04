package com.quiz.coreservice;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;

public abstract class GameCRUDAbstractService<T, E, R, ID>{

	private final MongoRepository<T, ID> repository;
	private final Converter<T, R> fromCoreConverter;
	private final Converter<E, T> toCoreConverter;
	private final ListConverter<T, R> listConverter;
	private final String errorUpdatingEntity;
	
	public GameCRUDAbstractService(final MongoRepository<T, ID> repository, 
			final Converter<T, R> fromCoreConverter,
			final Converter<E, T> toCoreConverter,
			final String errorUpdatingEntity){
		this.repository = repository;
		this.fromCoreConverter = fromCoreConverter;
		this.listConverter = new ListConverter<T, R>(fromCoreConverter);
		this.toCoreConverter = toCoreConverter;
		this.errorUpdatingEntity = errorUpdatingEntity;
	}

	
	public List<R> findAll() {
		return this.listConverter.convert(repository.findAll());
	}

	public R findById(ID id) {
		return this.fromCoreConverter.convert(this.repository.findById(id).orElse(null));
	}

	public Integer deleteById(ID id) {
		this.repository.deleteById(id);
		return this.repository.findById(id).isPresent() ? -1 : 1;
	}

	public R create(E entity) {
		return this.fromCoreConverter.convert(this.repository
				.insert(toCoreConverter.convert(entity)));
	}

	public R update(E entity, ID id) {
		this.repository.findById(id)
		.orElseThrow(() -> new EmptyResultDataAccessException(errorUpdatingEntity, 1));

		return this.fromCoreConverter.convert(this.repository
									.save(toCoreConverter.convert(entity)));
	}
}
