package com.luxoft.testtask.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.luxoft.testtask.AsyncConfiguration;
import com.luxoft.testtask.model.Album;
import com.luxoft.testtask.model.Book;
import com.luxoft.testtask.model.GoogleResponse;
import com.luxoft.testtask.model.ITunesResponse;

@Service
public class LuxoftServiceImpl implements LuxoftService {

	@Value("${luxoft.challenge.api-albuns-url}")
	private String albunsUrl;
	
	@Value("${luxoft.challenge.api-books-url}")
	private String booksUrl;
	
    @Autowired
    private RestTemplate restTemplate;
	
	@Override
	@Async(AsyncConfiguration.ASYNC_EXECUTOR)
	public CompletableFuture<List<Album>> getAlbuns(String searchKey) throws InterruptedException {

		System.out.println("Começou albuns " + this.albunsUrl);
		ResponseEntity<ITunesResponse> entity = restTemplate.getForEntity(this.albunsUrl+searchKey, ITunesResponse.class);
		List<Album> albuns = entity.getBody().getResults();
		Collections.sort(albuns, new Comparator<Album>() {
			@Override
			public int compare(Album o1, Album o2) {
				return o1.getCollectionName().compareTo(o2.getCollectionName());
			}
		});
		
		Thread.sleep(2 * 60 * 1000);
		
		return CompletableFuture.completedFuture(albuns);
	}

	@Override
	@Async(AsyncConfiguration.ASYNC_EXECUTOR)
	public CompletableFuture<List<Book>> getBooks(String searchKey) throws InterruptedException {
		
		System.out.println("Começou books: " + this.booksUrl);
		
		ResponseEntity<GoogleResponse> entity = restTemplate.getForEntity(this.booksUrl+searchKey, GoogleResponse.class);
		List<Book> books = entity.getBody().getItems();
		Collections.sort(books, new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getVolumeInfo().getTitle().compareTo(o2.getVolumeInfo().getTitle());
			}
		});
		
		System.out.println("Completou books: " + books);
		
		return CompletableFuture.completedFuture(books);
	}

}
