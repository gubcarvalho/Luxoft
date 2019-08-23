package com.luxoft.testtask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
	
	private VolumeInfo volumeInfo;

	public VolumeInfo getVolumeInfo() {
		return volumeInfo;
	}

	public void setVolumeInfo(VolumeInfo volumeInfo) {
		this.volumeInfo = volumeInfo;
	}

	@Override
	public String toString() {
		return "Book [volumeInfo=" + volumeInfo + "]";
	}

}
