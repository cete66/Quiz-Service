package com.quiz.coreservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GameCRUDRepository<T> extends MongoRepository<T, String> {

}
