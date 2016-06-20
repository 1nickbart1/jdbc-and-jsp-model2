package com.megacorp.dao.factory;

import com.megacorp.dao.DaoAnswer;
import com.megacorp.dao.DaoQuestion;
import com.megacorp.dao.mysql.DaoAnswerMysql;
import com.megacorp.dao.mysql.DaoQuestionMysql;

public class MysqlDaoFactory extends DaoFactory {

	public MysqlDaoFactory() {
		super();
	}

	@Override
	public DaoAnswer getDaoAnswer() {

		return new DaoAnswerMysql();
	}

	@Override
	public DaoQuestion getDaoQuestion() {

		return new DaoQuestionMysql();
	}

}
