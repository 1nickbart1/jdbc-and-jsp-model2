<%@page import="com.megacorp.properties.Property"%>
<%@page import="com.megacorp.bean.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.megacorp.bean.Captcha"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String captchaParam = Property.getProperty().getCaptcha();
	Captcha captcha = (Captcha) session.getAttribute(captchaParam);
	List<Answer> answerList = captcha.getAnswerList();
	String errorParam = Property.getProperty().getError();
	String errorMsg = (String)request.getAttribute(errorParam);	
	String answerParam = Property.getProperty().getAnswer();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<form  method="post" action="CaptchaAction.do">
	<% if (errorMsg != null){ %> <%=errorMsg %> <br /> <%} %>
	
	<table>
		<tr>
			<td><%=captcha.getQuestion().getQuestion()%></td>
		</tr>
		<%
			for (Answer ans : answerList) {
		%>
		<tr>
			<td><input type="radio" name="<%=answerParam%>" value=<%=ans.getAnswer()%>><%=ans.getAnswer()%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</form>
<a href ="QuestionSetting.do">настройки</a>
<body>
</body>
</html>
