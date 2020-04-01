package com.quiz.coreservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;

import com.quiz.coreservice.repository.GameCRUDRepository;
import com.quiz.framework.converter.Converter;
import com.quiz.framework.converter.ListConverter;

public abstract class GameCRUDAbstractService<T, E, R> implements GameCRUDService<E, R>{

	private final GameCRUDRepository<T> repository;
	private final Converter<T, R> fromCoreConverter;
	private final Converter<E, T> toCoreConverter;
	private final ListConverter<T, R> listConverter;
	private final String errorUpdatingEntity;
	
	public GameCRUDAbstractService(final GameCRUDRepository<T> repository, 
			final Converter<T, R> fromCoreConverter,
			final ListConverter<T, R> listConverter,
			final Converter<E, T> toCoreConverter,
			@Value("${com.quiz.coreservice.repository.error.update.message}")
			final String errorUpdatingEntity){
		this.repository = repository;
		this.fromCoreConverter = fromCoreConverter;
		this.listConverter = listConverter;
		this.toCoreConverter = toCoreConverter;
		this.errorUpdatingEntity = errorUpdatingEntity;
	}

	
	@Override
	public List<R> findAll() {
		return this.listConverter.convert(repository.findAll());
	}

	@Override
	public R findById(String id) {
		return this.fromCoreConverter.convert(this.repository.findById(id).orElse(null));
	}

	@Override
	public Integer deleteById(String id) {
		this.repository.deleteById(id);
		return this.repository.findById(id).isPresent() ? -1 : 1;
	}

	@Override
	public R create(E entity) {
		return this.fromCoreConverter.convert(this.repository
				.insert(toCoreConverter.convert(entity)));
	}

	@Override
	public R update(E entity) {
		T coreEntity = toCoreConverter.convert(entity);
		if (!this.repository.findOne(Example.of(coreEntity)).isPresent()) {
			throw new EmptyResultDataAccessException(errorUpdatingEntity, 1);
		}
		return this.fromCoreConverter.convert(this.repository
									.save(coreEntity));
	}
}
