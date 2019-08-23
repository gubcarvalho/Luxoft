package com.luxoft.testtask.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleResponse {
	
	private int totalItems;
	
	private List<Book> items;

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public List<Book> getItems() {
		return items;
	}

	public void setItems(List<Book> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "GoogleResponse [totalItems=" + totalItems + ", items=" + items + "]";
	}

}
