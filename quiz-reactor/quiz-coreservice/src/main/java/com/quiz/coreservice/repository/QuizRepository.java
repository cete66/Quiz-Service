package com.quiz.coreservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quiz.coreservice.repository.entities.Quiz;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String>{

}
