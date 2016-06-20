<%@page import="com.megacorp.bean.Captcha"%>
<%@page import="com.megacorp.properties.Property"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	String captchaParam = Property.getProperty().getCaptcha();
	String answerStatusParam = Property.getProperty().getAnswerStatus();
	String answerParam = Property.getProperty().getAnswer();
	Captcha captcha = (Captcha) request.getAttribute(captchaParam);
	String question = captcha.getQuestion().toString();
	String yourAnswer = (String) request.getAttribute(answerParam);
	boolean isCorrect = (boolean) request
			.getAttribute(answerStatusParam);
	String resultStr = isCorrect ? "Вы правы" : "Вы ошиблись";
%>

<body>
	<div>
		Вопрос был "<%=question%>"
	</div>
	<br />
	<div>
		Вы ответили "<%=yourAnswer%>"
	</div>
	<br />
	<div><%=resultStr%>
	</div>
	<br />
		<br /> <a href = "/jdbcTask3"> повторить</a>
	<div>
	</div>
</body>
</html>