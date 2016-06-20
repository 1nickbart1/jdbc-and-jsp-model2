package com.megacorp.bean;

public class Answer {

	private int id;
	private String answer;
	private int questionId;
	private boolean isCorrect;

	public Answer() {
		super();
	}
	public Answer(int id){
		this.id = id;
	}
	public Answer(int id, int questionId, String answer){
		this.id = id;
		this.questionId = questionId;
		this.answer = answer;
	}
	public Answer(String answer){
		this.answer = answer;
	}
	
	public Answer(String answer, int questionId, boolean isCorrect){
		this.answer = answer;
		this.questionId = questionId;
		this.isCorrect = isCorrect;
	}

	public Answer(int id, String answer, int questionId, boolean isCorrect) {
		super();
		this.id = id;
		this.answer = answer;
		this.questionId = questionId;
		this.isCorrect = isCorrect;
	}

	public int getId() {
		return id;
	}

	public String getAnswer() {
		return answer;
	}

	public int getQuestionId() {
		return questionId;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + questionId;
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
		Answer other = (Answer) obj;
		if (answer == null) {
			return false;
		} 
		if (!answer.equals(other.answer))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answer=" + answer + ", questionId="
				+ questionId + ", isCorrect=" + isCorrect + "]";
	}

}
