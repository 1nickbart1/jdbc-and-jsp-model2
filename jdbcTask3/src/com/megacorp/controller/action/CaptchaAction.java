package com.megacorp.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacorp.bean.Captcha;
import com.megacorp.controller.Action;
import com.megacorp.controller.Router;
import com.megacorp.properties.Property;
import com.megacorp.service.CaptchaService;
import com.megacorp.utils.ListUtils;
import com.megacorp.utils.StringUtils;

public class CaptchaAction implements Action {

	public Router perform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String answerParam = Property.getProperty().getAnswer();
		String answer = request.getParameter(answerParam);
		String jsp;

		if (StringUtils.isBlank(answer)) {
			validate(request);
			generateCaptcha(request);
			jsp = Property.getProperty().getCaptchaJsp();

			return new Router(jsp);
		}

		check(request, answer);
		jsp = Property.getProperty().getResultJsp();

		return new Router(jsp);

	}

	private void validate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String captchaParam = Property.getProperty().getCaptcha();
		String errorParam = Property.getProperty().getError();
		Captcha captcha = (Captcha) session.getAttribute(captchaParam);

		if (captcha != null) {
			request.setAttribute(errorParam, "Нужно выбрать ответ");
		}

	}

	private void generateCaptcha(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String captchaListParam = Property.getProperty().getCaptchaList();
		String captchaParam = Property.getProperty().getCaptcha();
		List<Captcha> captchaList = (List<Captcha>) session
				.getAttribute(captchaListParam);

		if (captchaList == null) {
			captchaList = CaptchaService.loadCaptcha();
		}

		Captcha captcha = findNotEmptyCaptcha(captchaList);
		session.setAttribute(captchaListParam, captchaList);
		session.setAttribute(captchaParam, captcha);

	}

	private void check(HttpServletRequest request, String answer) {
		HttpSession session = request.getSession();
		String captchaParam = Property.getProperty().getCaptcha();
		String answerStatusParam = Property.getProperty().getAnswerStatus();
		String answerParam = Property.getProperty().getAnswer();
		Captcha captcha = (Captcha) session.getAttribute(captchaParam);
		boolean correct = CaptchaService.checkAnswer(answer, captcha);
		session.removeAttribute(captchaParam);
		request.setAttribute(captchaParam, captcha);
		request.setAttribute(answerStatusParam, correct);
		request.setAttribute(answerParam, answer);
	}

	private Captcha findNotEmptyCaptcha(List<Captcha> captchaList) {
		if (captchaList.size() == 0) {
			return new Captcha();
		}

		Captcha captcha = null;

		do {
			captcha = ListUtils.getRandomFromList(captchaList);
		} while (captcha.getAnswerList().size() == 0);

		return captcha;

	}

}
