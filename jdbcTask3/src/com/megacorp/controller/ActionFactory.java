package com.megacorp.controller;

import java.util.HashMap;
import java.util.Map;

import com.megacorp.controller.exception.ControllerException;
import com.megacorp.properties.Property;

public class ActionFactory {

	private Map<String, Action> actions;

	public ActionFactory() {
		actions = new HashMap<String, Action>();
	}

	public Action getAction(String action) {
		Action newAction = actions.get(action);

		if (newAction == null) {
			newAction = loadAction(action);
		}

		actions.put(action, newAction);

		return newAction;
	}

	private Action loadAction(String action) { 
		Action newAction = null;
		StringBuilder txt = new StringBuilder();
		txt.append(Property.getProperty().getActionPack()).append(action);

		Class<Action> clazz;
		try {
			clazz = (Class<Action>) Class.forName(txt.toString());
			newAction = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			throw new ControllerException("cant load action", e);
		}

		return newAction;

	}
}
