package com.megacorp.dao.exception;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DaoException(Throwable arg0) {
		super(arg0);
	}

}
