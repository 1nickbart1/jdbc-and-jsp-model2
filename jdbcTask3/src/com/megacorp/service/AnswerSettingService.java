package com.megacorp.service;

import java.util.List;

import com.megacorp.bean.Answer;
import com.megacorp.bean.Question;
import com.megacorp.dao.DaoAnswer;
import com.megacorp.dao.factory.DaoFactory;
import com.megacorp.domain.AnswerEntity;
import com.megacorp.domain.QuestionEntity;
import com.megacorp.utils.BeanConverter;

public class AnswerSettingService {

	public List<Answer> getAnswersForQuestion(Question question) {
		QuestionEntity questEntity = new BeanConverter()
				.convertQuestion(question);
		DaoAnswer daoAnswer = DaoFactory.getFactory().getDaoAnswer();
		List<AnswerEntity> answerEntityList = daoAnswer
				.getAnswersForQuestion(questEntity);
		List<Answer> answerList = new BeanConverter()
				.convertAnswer(answerEntityList);

		return answerList;
	}

	public List<Answer> getCorrectAnswers(Question question) {
		QuestionEntity questEntity = new BeanConverter()
				.convertQuestion(question);
		DaoAnswer daoAnswer = DaoFactory.getFactory().getDaoAnswer();
		List<AnswerEntity> answerEntityList = daoAnswer
				.getCorrectAnswers(questEntity);
		List<Answer> answerList = new BeanConverter()
				.convertAnswer(answerEntityList);

		return answerList;
	}

	public void updateAnswer(Answer answer) {
		AnswerEntity answerEntity = new BeanConverter().convertAnswer(answer);
		DaoAnswer daoAnswer = DaoFactory.getFactory().getDaoAnswer();
		daoAnswer.updateAnswer(answerEntity);
	}

	public void deleteAnswer(Answer answer) {
		AnswerEntity answerEntity = new BeanConverter().convertAnswer(answer);
		DaoAnswer daoAnswer = DaoFactory.getFactory().getDaoAnswer();
		daoAnswer.deleteAnswer(answerEntity);
	}

	public void insertAnswer(Answer answer) {
		AnswerEntity answerEntity = new BeanConverter().convertAnswer(answer);
		DaoAnswer daoAnswer = DaoFactory.getFactory().getDaoAnswer();
		daoAnswer.insertAnswer(answerEntity);
	}

	public int countRows() {
		DaoAnswer daoAnswer = DaoFactory.getFactory().getDaoAnswer();

		return daoAnswer.countRows();
	}

}
