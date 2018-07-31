package com.ymagis.daf.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class TestResponse {
	@JsonProperty("good")
	private int good;

	@JsonProperty("wrong_place")
	private int wrongPlace;

	public TestResponse() {

	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getGood() {
		return good;
	}

	public void setWrongPlace(int wrongPlace) {
		this.wrongPlace = wrongPlace;
	}

	public int getWrongPlace() {
		return wrongPlace;
	}

	@Override
	public String toString() {
		return "Response{" + "good = '" + good + '\'' + ",wrong_place = '" + wrongPlace + '\'' + "}";
	}
}
