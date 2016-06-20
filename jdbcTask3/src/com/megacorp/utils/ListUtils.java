package com.megacorp.utils;

import java.util.Collections;
import java.util.List;

public class ListUtils {

	public static <T> T getRandomFromList(List<T> list) {
		if (list == null) {
			throw new RuntimeException("list is empty");
		}

		Collections.shuffle(list);

		return list.get(0);

	}

}
