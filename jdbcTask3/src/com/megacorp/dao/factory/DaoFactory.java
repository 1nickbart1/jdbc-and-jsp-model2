package com.megacorp.dao.factory;

import com.megacorp.dao.DaoAnswer;
import com.megacorp.dao.DaoQuestion;

public abstract class DaoFactory {

	public static DaoFactory getFactory() {
		return getFactory(DaoType.MySql);
	}

	public static DaoFactory getFactory(DaoType type) {
		switch (type) {
		case MySql: {
			return  new MysqlDaoFactory();
		}
		}
		
		throw new IllegalArgumentException();
	}

	public abstract DaoAnswer getDaoAnswer();

	public abstract DaoQuestion getDaoQuestion();
}
