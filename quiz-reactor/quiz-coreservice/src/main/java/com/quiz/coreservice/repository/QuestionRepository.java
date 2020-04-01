package com.quiz.coreservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.repository.entities.Question;

@Repository
@Transactional
public interface QuestionRepository extends GameCRUDRepository<Question>{

}
