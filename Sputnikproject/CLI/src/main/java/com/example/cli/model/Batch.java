package com.example.cli.model;

public class Batch {
	
	int batchId;
	String name;
	String date;
	String duration;
	
	public Batch() {
		
	}

	public Batch(int batchId, String name, String time, String duration) {
		super();
		this.batchId = batchId;
		this.name = name;
		this.date = time;
		this.duration = duration;
		
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}		

	public String getDate() {
		return date;
	}

	public void setDate(String time) {
		this.date = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", name=" + name + ", time=" + date+ ", duration=" + duration + "]";
	}


	}	


