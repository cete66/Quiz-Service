package com.quiz.framework.converter;

public interface Converter<T, K> {

    K convert(T object);
}