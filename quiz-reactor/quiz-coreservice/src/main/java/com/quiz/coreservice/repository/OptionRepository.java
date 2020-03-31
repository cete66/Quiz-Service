package com.quiz.coreservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quiz.coreservice.repository.entities.Option;

@Repository
public interface OptionRepository extends MongoRepository<Option, String> {

}
