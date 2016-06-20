package com.megacorp.domain;

public class QuestionEntity {
	private int id;
	private String question;

	public QuestionEntity() {
		super();
	}

	public QuestionEntity(int id) {
		super();
		this.id = id;
	}
	
	public QuestionEntity(int id, String question) {
		super();
		this.id = id;
		this.question = question;
	}
	public QuestionEntity(String question) {
		super();		
		this.question = question;
	}


	public int getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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

		QuestionEntity other = (QuestionEntity) obj;

		if (question == null) {
			return false;
		}
		if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return question;
	}

}
