package com.lawyer.cores.tools.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
	private ObjectMapper objectMapper = null;

	public JacksonUtil() {
		objectMapper = new ObjectMapper();
	}

	public String getJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
