package com.luxoft.testtask.controllers;

import java.util.ArrayList;
import java.util.List;

import com.luxoft.testtask.model.Album;
import com.luxoft.testtask.model.Book;

public class ResponseWrapper {

	private String name;
	
	private List<Album> albuns;
	
	private List<Book> books;
	
	public ResponseWrapper(String name) {
		super();
		this.name = name;
		this.albuns = new ArrayList<Album>(0);
		this.books = new ArrayList<Book>(0);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Album> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(List<Album> albuns) {
		this.albuns = albuns;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
