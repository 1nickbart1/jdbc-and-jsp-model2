<%@page import="com.megacorp.bean.Answer"%>
<%@page import="java.util.List"%>
<%@page import="com.megacorp.properties.Property"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	String reqAnswersParam = Property.getProperty()
			.getRequestAllAnswersParam();
	String operationParam = Property.getProperty()
			.getSettingOperationParam();
	String delete = Property.getProperty().getSettingOperationDelete();
	String update = Property.getProperty().getSettingOpertionUpdate();
	String insert = Property.getProperty().getSettingOpertionInsert();
	String errParam = Property.getProperty().getError();
	String idParam = Property.getProperty().getSettingIdParam();
	String answerParam = Property.getProperty().getSettingAnswerParam();
	String correctParam = Property.getProperty()
			.getSettingCorrectParam();
	String err = (String) request.getAttribute(errParam);
	List<Answer> answerList = (List<Answer>) request
			.getAttribute(reqAnswersParam);
%>
<body>
	
	<%if (err != null) {%>
	  <%=err%>
	<%}%>
	
	<br />
	<%
		for (Answer answer : answerList) {
	%>
	<form method="get" action="AnswerSetting.do" id="answer">
		<table>
			<tr>
				<td><input type="text" name="<%=idParam%>"
					value="<%=answer.getId()%>" /></td>
				<td><textarea name="<%=answerParam%>"><%=answer.getAnswer()%></textarea></td>
				<td><input type="checkbox" value="<%=answer.isCorrect()%>"
					name="<%=correctParam%>" <%if (answer.isCorrect()) {%> checked
					<%}%>></td>

				<td><input type="submit" name="<%=operationParam%>"
					value="<%=delete%>" /></td>
				<td><input type="submit" name="<%=operationParam%>"
					value="<%=update%>" /></td>
			</tr>
		</table>
	</form>
	<%
		}
	%>
	<br />
	<form method="get" action="AnswerSetting.do" id="answer">
		Добавить ответ
		<textarea name="<%=answerParam%>"></textarea>
		<input type="checkbox" name="<%=correctParam%>"> <input
			type="submit" name="<%=operationParam%>" value="<%=insert%>" />
	</form>
</body>
<a href = "QuestionSetting.do">вернуться к списку вопросов </a>
</html>