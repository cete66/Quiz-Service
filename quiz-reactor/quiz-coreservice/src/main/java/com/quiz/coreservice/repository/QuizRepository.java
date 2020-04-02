package com.quiz.coreservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.repository.entities.Quiz;

@Repository("quizRepository")
@Transactional
public interface QuizRepository extends GameCRUDRepository<Quiz>{

}
