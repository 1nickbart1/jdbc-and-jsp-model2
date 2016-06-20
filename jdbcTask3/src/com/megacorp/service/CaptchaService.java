package com.megacorp.service;

import java.util.ArrayList;
import java.util.List;

import com.megacorp.bean.Answer;
import com.megacorp.bean.Captcha;
import com.megacorp.bean.Question;
import com.megacorp.dao.DaoAnswer;
import com.megacorp.dao.DaoQuestion;
import com.megacorp.dao.factory.DaoFactory;
import com.megacorp.domain.AnswerEntity;
import com.megacorp.domain.QuestionEntity;
import com.megacorp.utils.BeanConverter;

public class CaptchaService {

	public static List<Captcha> loadCaptcha() {
		DaoAnswer daoAnswer = DaoFactory.getFactory().getDaoAnswer();
		DaoQuestion daoQuestion = DaoFactory.getFactory().getDaoQuestion();
		List<QuestionEntity> entityQuestList = daoQuestion.getQuestions();
		List<Question> questList = new BeanConverter().convertQuestion(entityQuestList);
		List<Captcha> captchaList = new ArrayList<>();
		
		

		for (Question question : questList) {
			QuestionEntity questEntity = new BeanConverter().convertQuestion(question);
			List<AnswerEntity> entityAnswerList  = daoAnswer.getAnswersForQuestion(questEntity);
			List<Answer> answerList = new BeanConverter().convertAnswer(entityAnswerList);
			Captcha captcha = new Captcha(question, answerList);
			captchaList.add(captcha);
		}

		return captchaList;

	}

	private static Answer findCorrectAnswer(Captcha captcha) {
		List<Answer> answerList = captcha.getAnswerList();

		for (Answer answer : answerList) {
			if (answer.isCorrect()) {
				return answer;
			}
		}

		return new Answer();

	}

	public static boolean checkAnswer(String answer, Captcha captcha) {
		Answer correctAnswer = findCorrectAnswer(captcha);
		Answer chosedAnswer = new Answer(answer);

		if (correctAnswer.equals(chosedAnswer)) {
			return true;
		}

		return false;
	}

}
