package com.mydlp.ui.dao;

import java.util.List;

public class DAOUtil {
	
	public static <T> T getSingleResult(List<T> list) {
		if (list.size() == 1)
			return list.get(0);
		return null;
	}
}
