package com.megacorp.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacorp.bean.Answer;
import com.megacorp.bean.Question;
import com.megacorp.controller.Action;
import com.megacorp.controller.Router;
import com.megacorp.properties.Property;
import com.megacorp.service.AnswerSettingService;
import com.megacorp.utils.StringUtils;

public class AnswerSetting implements Action {

	@Override
	public Router perform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String operationParam = Property.getProperty()
				.getSettingOperationParam();
		String operation = request.getParameter(operationParam);
		String operationDelete = Property.getProperty()
				.getSettingOperationDelete();
		String operationUpdate = Property.getProperty()
				.getSettingOpertionUpdate();
		String operationInsert = Property.getProperty()
				.getSettingOpertionInsert();
		String questSettingJsp = Property.getProperty().getQuestSettingJsp();
		String answerSettingJsp = Property.getProperty().getAnswerSettingJsp();

		if (!checkQuestId(request)) {
			return new Router(questSettingJsp);
		}

		if (operationDelete.equals(operation)) {
			deleteAnswer(request);
		}
		if (operationUpdate.equals(operation)) {
			updateAnswer(request);
		}
		if (operationInsert.equals(operation)) {
			insertAnswer(request);
		}

		loadAllAnswers(request);

		return new Router(answerSettingJsp);

	}

	private boolean checkQuestId(HttpServletRequest request) {
		String errParam = Property.getProperty().getError();
		String questIdParam = Property.getProperty().getSettingQuestIdParam();
		HttpSession session = request.getSession();
		String questIdRequest = request.getParameter(questIdParam);
		Integer quesstIdSession = (Integer) session.getAttribute(questIdParam);

		if (!StringUtils.isBlank(questIdRequest)) {
			Integer questId = Integer.parseInt(questIdRequest);
			session.setAttribute(questIdParam, questId);

			return true;
		}
		if (quesstIdSession == null) {
			request.setAttribute(errParam,
					"Не выбран вопрос для отображения ответов");

			return false;
		}

		return true;

	}

	private void loadAllAnswers(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String requestParam = Property.getProperty()
				.getRequestAllAnswersParam();
		String questIdParam = Property.getProperty().getSettingQuestIdParam();
		int questIdSession = (int) session.getAttribute(questIdParam);

		if (request.getAttribute(requestParam) == null) {
			List<Answer> answerList = new AnswerSettingService()
					.getAnswersForQuestion(new Question(questIdSession));
			request.setAttribute(requestParam, answerList);
		}
	}

	private void updateAnswer(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String idParam = Property.getProperty().getSettingIdParam();
		String answerParam = Property.getProperty().getSettingAnswerParam();
		String questIdParam = Property.getProperty().getSettingQuestIdParam();
		String correctParam = Property.getProperty().getSettingCorrectParam();

		String errParam = Property.getProperty().getError();
		String answerIdRequest = request.getParameter(idParam);
		int questIdSession = (int) session.getAttribute(questIdParam);
		String answerTxt = request.getParameter(answerParam);
		String correctRequest = request.getParameter(correctParam);

		if (StringUtils.isBlank(answerTxt)) {
			request.setAttribute(errParam, "ответ не может быть пустым");
			return;
		}

		int id = Integer.parseInt(answerIdRequest);
		boolean correct = true;

		if (StringUtils.isBlank(correctRequest)) {
			correct = false;
		}

		Answer answer = new Answer(id, answerTxt, questIdSession, correct);

		if (!checkCorrect(answer)) {
			request.setAttribute(errParam,
					"это последний правильный ответ, он не может быть отмечен как неправильный!");
			return;
		}

		new AnswerSettingService().updateAnswer(answer);

	}

	private boolean checkCorrect(Answer answer) {
		Question quest = new Question(answer.getQuestionId());
		List<Answer> answerList = new AnswerSettingService()
				.getCorrectAnswers(quest);

		if (answerList.size() > 1) {
			return true;
		}

		boolean setNotCorrect = !answer.isCorrect();
		boolean lastAnswer = answerList.contains(answer);
		if (setNotCorrect && lastAnswer) {
			return false;
		}

		return true;

	}

	private void deleteAnswer(HttpServletRequest request) {
		String answerParam = Property.getProperty().getSettingAnswerParam();
		String answerTxt = request.getParameter(answerParam);
		String idAnswer = Property.getProperty().getSettingIdParam();
		String errParam = Property.getProperty().getError();
		String questIdParam = Property.getProperty().getSettingQuestIdParam();
		HttpSession session = request.getSession();
		int questIdSession = (int) session.getAttribute(questIdParam);
		int cnt = new AnswerSettingService().countRows();
		String idRequest = request.getParameter(idAnswer);

		if (StringUtils.isBlank(idRequest)) {
			request.setAttribute(errParam, "вопрос не выбран");
			return;
		}
		if (cnt < 1) {
			request.setAttribute(errParam, "последний вопрос нельзя удалить");
			return;
		}

		int id = Integer.parseInt(idRequest);
		Answer answer = new Answer(id, questIdSession, answerTxt);

		if (!checkCorrect(answer)) {
			request.setAttribute(errParam,
					"это последний правильный ответ, он не может быть удален!");
			return;
		}

		new AnswerSettingService().deleteAnswer(answer);

	}

	private void insertAnswer(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String answerParam = Property.getProperty().getSettingAnswerParam();
		String questIdParam = Property.getProperty().getSettingQuestIdParam();
		String correctParam = Property.getProperty().getSettingCorrectParam();
		String errParam = Property.getProperty().getError();

		String answerTxt = request.getParameter(answerParam);
		int questIdSession = (int) session.getAttribute(questIdParam);
		String correctRequest = request.getParameter(correctParam);

		if (StringUtils.isBlank(answerTxt)) {
			request.setAttribute(errParam, "вопрос не может быть пустым");
			return;
		}

		boolean correct = true;

		if (StringUtils.isBlank(correctRequest)) {
			correct = false;
		}

		Answer answer = new Answer(answerTxt, questIdSession, correct);
		new AnswerSettingService().insertAnswer(answer);

	}

}
