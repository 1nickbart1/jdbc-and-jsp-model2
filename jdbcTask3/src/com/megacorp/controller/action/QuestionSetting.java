package com.megacorp.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacorp.bean.Question;
import com.megacorp.controller.Action;
import com.megacorp.controller.Router;
import com.megacorp.properties.Property;
import com.megacorp.service.QuestionSettingService;
import com.megacorp.utils.StringUtils;

public class QuestionSetting implements Action {

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
		String jsp = Property.getProperty().getQuestSettingJsp();		
		
		if (operationDelete.equals(operation)) {
			deleteQuestion(request);			
		}
		if (operationUpdate.equals(operation)) {
			updateQuestion(request);			
		}
		if(operationInsert.equals(operation)){
			insertQuestion(request);
		}
		
		removeCaptcha(request);
		loadAllQuestions(request);
		return new Router(jsp);
	}

	private void removeCaptcha(HttpServletRequest request){
		String captchaListParam = Property.getProperty().getCaptchaList();
		HttpSession session = request.getSession();
		Object captcha = session.getAttribute(captchaListParam);		
		
		if(captcha != null){
			session.removeAttribute(captchaListParam);
		}
	}
	
	private void loadAllQuestions(HttpServletRequest request) {
		String requestParam = Property.getProperty()
				.getRequestAllQuestionsParam();
		if (request.getAttribute(requestParam) == null) {
			List<Question> questList = new QuestionSettingService().loadQuestions();
			request.setAttribute(requestParam, questList);
		}
	}

	private void updateQuestion(HttpServletRequest request) {
		String idParam = Property.getProperty().getSettingIdParam();
		String questParam = Property.getProperty().getSettingQuestionParam();
		String errParam = Property.getProperty().getError();
		String idRequest = request.getParameter(idParam);
		String questionTxt = request.getParameter(questParam);

		if (StringUtils.isBlank(idRequest)) {
			request.setAttribute(errParam, "вопрос не выбран");
			return;
		}
		if (StringUtils.isBlank(questionTxt)) {
			request.setAttribute(errParam, "вопрос не может быть пустым");
			return;
		}

		int id = Integer.parseInt(idRequest);
		Question quest = new Question(id, questionTxt);
		new QuestionSettingService().updateQuestion(quest);

	}

	private void deleteQuestion(HttpServletRequest request) {
		String idParam = Property.getProperty().getSettingIdParam();
		String errParam = Property.getProperty().getError();
		int cnt = new QuestionSettingService().countRows();
		String idRequest = request.getParameter(idParam);

		if (StringUtils.isBlank(idRequest)) {
			request.setAttribute(errParam, "вопрос не выбран");
			return;
		}
		if (cnt < 1) {
			request.setAttribute(errParam, "последний вопрос нельзя удалить");
			return;
		}

		int id = Integer.parseInt(idRequest);
		Question quest = new Question(id);
		new QuestionSettingService().deleteQuestion(quest);

	}
	
	private void insertQuestion(HttpServletRequest request) {
		String questParam = Property.getProperty().getSettingQuestionParam();
		String errParam = Property.getProperty().getError();
		String questionTxt = request.getParameter(questParam);

		if (StringUtils.isBlank(questionTxt)) {
			request.setAttribute(errParam, "вопрос не может быть пустым");
			return;
		}

		Question quest = new Question( questionTxt);
		new QuestionSettingService().insertQuestion(quest);

	}

}
