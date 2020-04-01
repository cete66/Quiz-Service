package com.quiz.coreservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.coreservice.repository.entities.Option;

@Repository
@Transactional
public interface OptionRepository extends GameCRUDRepository<Option> {

}
