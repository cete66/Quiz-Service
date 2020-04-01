package com.quiz.framework.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ListConverter<T, K> implements Converter<List<T>, List<K>> {

	private final Converter<T, K> converter;

	public ListConverter(Converter<T, K> converter) {
		this.converter = converter;
	}

	@Override
	public List<K> convert(List<T> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.stream()
				.filter(e -> e!=null)
				.map(e -> this.converter.convert(e))
				.collect(Collectors.toList());
	}
}
