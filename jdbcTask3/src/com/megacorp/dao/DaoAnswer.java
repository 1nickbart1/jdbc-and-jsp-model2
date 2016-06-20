package com.megacorp.dao;

import java.util.List;

import com.megacorp.domain.AnswerEntity;
import com.megacorp.domain.QuestionEntity;

public interface DaoAnswer {
	public void insertAnswer(AnswerEntity answer);

	public List<AnswerEntity> getAnswersForQuestion(QuestionEntity quest);

	public List<AnswerEntity> getAnswers();

	public AnswerEntity getAnswer(QuestionEntity quest);

	public void updateAnswer(AnswerEntity answer);

	public void deleteAnswer(AnswerEntity answer);
	
	public int countRows();
	
	public List<AnswerEntity> getCorrectAnswers(QuestionEntity quest);

}
