package com.luxoft.testtask.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITunesResponse {
	
	private int resultCount;
	
	private List<Album> results;

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<Album> getResults() {
		return results;
	}

	public void setResults(List<Album> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "ResultWrapper [resultCount=" + resultCount + ", results=" + results + "]";
	}

}
