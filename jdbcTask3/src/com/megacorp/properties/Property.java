package com.megacorp.properties;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public class Property {

	private static Property property = new Property();;

	private final String captchaList;
	private final String captcha;
	private final String answer;
	private final String captchaJsp;
	private final String resultJsp;
	private final String questSettingJsp;
	private final String answerSettingJsp;
	private final String answerStatus;
	private final String error;
	private final String actionPack;
	private final String dbInitParam;
	private final String settingIdParam;
	private final String settingQuestIdParam;
	private final String settingQuestionParam;
	private final String settingOperationParam;
	private final String requestAllAnswersParam;
	private final String requestAllQuestionsParam;
	private final String settingOperationDelete;
	private final String settingOpertionUpdate;
	private final String settingOpertionInsert;
	private final String settingAnswerParam;
	private final String settingCorrectParam;

	private static final String FILE_NAME = "/com/megacorp/properties/config.properties";

	private Property() {
		Properties props = new Properties();
		InputStream stream = null;
		Path path = FileSystems.getDefault().getPath(FILE_NAME);

		try {
			stream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(path.toString());
			props.load(stream);
		} catch (IOException e) {
			throw new RuntimeException("Cant read properties");
		}

		captchaList = props.getProperty("sessionCaptchaListParam");
		captcha = props.getProperty("sessionCaptchaParam");
		answer = props.getProperty("requestAnswerParam");
		error = props.getProperty("errorParam");
		answerStatus = props.getProperty("answerStatusParam");
		requestAllAnswersParam = props.getProperty("requestAllAnswersParam");
		requestAllQuestionsParam = props.getProperty("requestAllQuestionsParam");		
		
		captchaJsp = props.getProperty("captchaJsp");
		resultJsp = props.getProperty("resultJsp");
		questSettingJsp =  props.getProperty("questSettingJsp");	
		answerSettingJsp =  props.getProperty("answerSettingJsp");	
		
		actionPack = props.getProperty("actionPack");
		dbInitParam = props.getProperty("dbInitParam");
		
		settingIdParam = props.getProperty("settingIdParam");
		settingQuestIdParam = props.getProperty("settingQuestIdParam");
		settingQuestionParam = props.getProperty("settingQuestionParam");		
		settingAnswerParam = props.getProperty("settingAnswerParam");
		settingCorrectParam = props.getProperty("settingCorrectParam");		
		
		settingOpertionInsert = props.getProperty("settingOpertionInsert");	
		settingOperationParam = props.getProperty("settingOperationParam");
		settingOperationDelete = props.getProperty("settingOperationDelete");
		settingOpertionUpdate = props.getProperty("SettingOpertionUpdate");	
		
	}

	public static Property getProperty() {
		return property;
	}

	public String getCaptchaList() {
		return captchaList;
	}

	public String getCaptcha() {
		return captcha;
	}

	public String getAnswer() {
		return answer;
	}

	public String getCaptchaJsp() {
		return captchaJsp;
	}

	public String getResultJsp() {
		return resultJsp;
	}

	public String getAnswerStatus() {
		return answerStatus;
	}

	public String getError() {
		return error;
	}

	public String getActionPack() {
		return actionPack;
	}

	public String getDbInitParam() {
		return dbInitParam;
	}

	public String getSettingIdParam() {
		return settingIdParam;
	}

	public String getSettingQuestionParam() {
		return settingQuestionParam;
	}

	public String getSettingOperationParam() {
		return settingOperationParam;
	}

	public String getRequestAllAnswersParam() {
		return requestAllAnswersParam;
	}

	public String getRequestAllQuestionsParam() {
		return requestAllQuestionsParam;
	}

	public String getSettingOperationDelete() {
		return settingOperationDelete;
	}

	public String getSettingOpertionUpdate() {
		return settingOpertionUpdate;
	}

	public String getQuestSettingJsp() {
		return questSettingJsp;
	}

	public String getSettingOpertionInsert() {
		return settingOpertionInsert;
	}

	public String getSettingAnswerParam() {
		return settingAnswerParam;
	}

	public String getSettingCorrectParam() {
		return settingCorrectParam;
	}

	public String getSettingQuestIdParam() {
		return settingQuestIdParam;
	}

	public String getAnswerSettingJsp() {
		return answerSettingJsp;
	}

}
