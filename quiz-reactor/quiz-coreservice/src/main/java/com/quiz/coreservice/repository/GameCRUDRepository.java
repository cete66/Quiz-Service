package com.quiz.coreservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameCRUDRepository<T> extends MongoRepository<T, String> {

}
