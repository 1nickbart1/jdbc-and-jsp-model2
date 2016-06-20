package com.megacorp.utils;

import java.util.ArrayList;
import java.util.List;

import com.megacorp.bean.Answer;
import com.megacorp.bean.Question;
import com.megacorp.domain.AnswerEntity;
import com.megacorp.domain.QuestionEntity;

public class BeanConverter {

	public AnswerEntity convertAnswer(Answer answer) {
		int id = answer.getId();
		String answerTxt = answer.getAnswer();
		int questId = answer.getQuestionId();
		boolean isCorrect = answer.isCorrect();

		return new AnswerEntity(id, answerTxt, questId, isCorrect);
	}
	
	public Answer convertAnswer(AnswerEntity answer) {
		int id = answer.getId();
		String answerTxt = answer.getAnswer();
		int questId = answer.getQuestionId();
		boolean isCorrect = answer.isCorrect();

		return new Answer(id, answerTxt, questId, isCorrect);
	}
	
	public List<Answer> convertAnswer(List<AnswerEntity> entityList){
		List<Answer> answerList = new ArrayList<>();
		
		for(AnswerEntity entity: entityList){
			answerList.add(convertAnswer(entity));
		}
		
		return answerList;
	}
	
	public Question convertQuestion(QuestionEntity question){
		int id =question.getId();
		String questionTxt = question.getQuestion();
		
		return new Question(id,questionTxt);		
	}
	
	public QuestionEntity convertQuestion(Question question){
		int id =question.getId();
		String questionTxt = question.getQuestion();
		
		return new QuestionEntity(id,questionTxt);		
	}
	
	public List<Question> convertQuestion(List<QuestionEntity> entityList){
		List<Question> answerList = new ArrayList<>();
		
		for(QuestionEntity entity: entityList){
			answerList.add(convertQuestion(entity));
		}
		
		return answerList;
	}
}
