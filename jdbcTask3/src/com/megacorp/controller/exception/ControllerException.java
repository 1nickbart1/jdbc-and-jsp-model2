package com.megacorp.controller.exception;

public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ControllerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ControllerException(Throwable arg0) {
		super(arg0);
	}

}
