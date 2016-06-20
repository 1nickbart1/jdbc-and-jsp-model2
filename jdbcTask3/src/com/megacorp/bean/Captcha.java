package com.megacorp.bean;

import java.util.Collections;
import java.util.List;

public class Captcha {

	private Question question;
	private List<Answer> answerList;

	public Captcha() {
		super();
	}

	public Captcha(Question question, List<Answer> answerList) {
		super();
		this.question = question;
		this.answerList = answerList;
		Collections.shuffle(this.answerList);
	}

	public Question getQuestion() {
		return question;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((answerList == null) ? 0 : answerList.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Captcha other = (Captcha) obj;
		if (answerList == null) {
			if (other.answerList != null)
				return false;
		} else if (!answerList.equals(other.answerList))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Captcha [question=" + question + ", answerList=" + answerList
				+ "]";
	}

}
