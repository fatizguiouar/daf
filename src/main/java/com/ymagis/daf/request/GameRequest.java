package com.ymagis.daf.request;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class GameRequest {
	public MultivaluedMap<String, String> getParameters() {
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		/*
		 * if (contentOrderIds != null && !contentOrderIds.isEmpty()) {
		 * queryParams.add("contentOrderIds", contentOrderIds); }
		 */
		return queryParams;
	}
}
