<%@page import="com.megacorp.bean.Question"%>
<%@page import="java.util.List"%>
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
	StringBuilder urlToAnswers = new StringBuilder();
	String reqQuestParam = Property.getProperty()
			.getRequestAllQuestionsParam();
	String operationParam = Property.getProperty()
			.getSettingOperationParam();
	String delete = Property.getProperty().getSettingOperationDelete();
	String update = Property.getProperty().getSettingOpertionUpdate();
	String insert = Property.getProperty().getSettingOpertionInsert();
	String errParam = Property.getProperty().getError();
	String idParam = Property.getProperty().getSettingIdParam();
	String questId = Property.getProperty().getSettingQuestIdParam();
	String questParam = Property.getProperty()
			.getSettingQuestionParam();
	String err = (String) request.getAttribute(errParam);
	List<Question> questList = (List<Question>) request
			.getAttribute(reqQuestParam);
%>

<script src=".\scripts\scripts.js"></script>
<body>
	<%
		if (err != null) {
	%>
	<%=err%>
	<br />
	<%
		}
	%><br />
	<%
		for (Question quest : questList) {
			urlToAnswers.setLength(0);
			urlToAnswers.append("AnswerSetting.do?").append(questId)
					.append("=").append(quest.getId());
	%>

	<form method="get" action="QuestionSetting.do" >
		<table>
			<tr>
				<td><input type="text" name="<%=idParam%>"
					value="<%=quest.getId()%>" /></td>
				<td><textarea name="<%=questParam%>"><%=quest.getQuestion()%></textarea></td>
				<td><input type="submit" name="<%=operationParam%>"
					value="<%=delete%>" onClick="return submitForm()"/></td>
				<td><input type="submit" name="<%=operationParam%>"
					value="<%=update%>" /></td>
			    <td><a  href ="<%=urlToAnswers.toString() %>"> к списку ответов</a></td>
			</tr>
		</table>
	</form>
	<%
		}
	%>
	<br />
	<form method="get" action="QuestionSetting.do">
		Добавить вопрос
		<textarea name="<%=questParam%>"></textarea>
		<input type="submit" name="<%=operationParam%>" value="<%=insert%>" />
	</form>
<a href = "CaptchaAction.do">вернуться к проверке</a>
</body>
</html>