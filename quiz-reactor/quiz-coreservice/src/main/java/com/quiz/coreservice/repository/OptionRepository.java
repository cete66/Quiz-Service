package com.quiz.coreservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.repository.entities.Option;

@Repository("optionRepository")
public interface OptionRepository extends MongoRepository<Option, String> {

}
