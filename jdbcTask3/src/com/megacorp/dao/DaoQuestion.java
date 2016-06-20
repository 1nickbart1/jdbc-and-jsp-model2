package com.megacorp.dao;

import java.util.List;

import com.megacorp.domain.QuestionEntity;

public interface DaoQuestion {
	public void insertQuestion(QuestionEntity question);

	public List<QuestionEntity> getQuestions();

	public QuestionEntity getQuestion(QuestionEntity question);

	public void updateQuestion(QuestionEntity question);

	public void deleteQuestion(QuestionEntity question);
	
	public int countRows();
}
