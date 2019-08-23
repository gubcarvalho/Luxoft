package com.luxoft.testtask.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.testtask.model.Album;
import com.luxoft.testtask.model.Book;
import com.luxoft.testtask.services.LuxoftService;

@RestController
@RequestMapping(value="/v1")
@Api(name = "Challenge test for Luxoft", description = "API exposing albuns and/or books to a webservice consumer.")
@ApiVersion(since = "1.0", until = "2.00")
public class LuxoftController {

	@Autowired
	private LuxoftService luxoftService;
	
	@ApiMethod(description = "Retrives albums and/or books by the artist/author name")
	@Retryable(value= {InterruptedException.class, ExecutionException.class}, maxAttempts = 5)
	@GetMapping(value = "/artists/{searchKey}")
	public @ApiResponseObject HttpEntity<ResponseWrapper> getAllArtists(
			@ApiPathParam(description = "search parameter to find albuns and/or books") @PathVariable("searchKey") String searchKey) throws InterruptedException, ExecutionException {
		
		ResponseWrapper responseWrapper = new ResponseWrapper(searchKey);
		if (searchKey.trim().length() > 0) {
	        CompletableFuture<List<Album>> albuns = this.luxoftService.getAlbuns(searchKey.trim());
	        CompletableFuture<List<Book>> books = this.luxoftService.getBooks(searchKey.trim());
	
	        CompletableFuture.allOf(albuns, books).join();
	        
			responseWrapper.setAlbuns(albuns.get());
			responseWrapper.setBooks(books.get());
		}
		
		return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
	}
	
	@Recover
	public String recover(Throwable t) {
		return "Sorry. We are having some problems";
	}
}
