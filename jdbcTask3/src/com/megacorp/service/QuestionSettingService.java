package com.megacorp.service;

import java.util.List;

import com.megacorp.bean.Question;
import com.megacorp.dao.DaoQuestion;
import com.megacorp.dao.factory.DaoFactory;
import com.megacorp.domain.QuestionEntity;
import com.megacorp.utils.BeanConverter;

public class QuestionSettingService {

	public List<Question> loadQuestions(){
		DaoQuestion daoQuestion = DaoFactory.getFactory().getDaoQuestion();
		List<QuestionEntity> entityQuestList = daoQuestion.getQuestions();		
		List<Question> questList = new BeanConverter().convertQuestion(entityQuestList);
		
		return questList;
		
	}
	
	public void updateQuestion(Question question){
		QuestionEntity questEntity = new BeanConverter().convertQuestion(question);
		DaoQuestion daoQuestion = DaoFactory.getFactory().getDaoQuestion();
		daoQuestion.updateQuestion(questEntity);
		
	}
	
	public void deleteQuestion(Question question){
		QuestionEntity questEntity = new BeanConverter().convertQuestion(question);
		DaoQuestion daoQuestion = DaoFactory.getFactory().getDaoQuestion();
		daoQuestion.deleteQuestion(questEntity);
		
	}
	
	public int countRows(){
		DaoQuestion daoQuestion = DaoFactory.getFactory().getDaoQuestion();
		
		return daoQuestion.countRows();
	}
	
	public void insertQuestion(Question question){
		QuestionEntity questEntity = new BeanConverter().convertQuestion(question);
		DaoQuestion daoQuestion = DaoFactory.getFactory().getDaoQuestion();
		daoQuestion.insertQuestion(questEntity);
		
	}
}
