package com.luxoft.testtask.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.luxoft.testtask.model.Album;
import com.luxoft.testtask.model.Book;

public interface LuxoftService {

	CompletableFuture<List<Album>> getAlbuns(String name) throws InterruptedException ;
	
	CompletableFuture<List<Book>> getBooks(String name) throws InterruptedException ;
	
}
