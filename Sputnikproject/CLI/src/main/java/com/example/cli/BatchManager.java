package com.example.cli;

import com.example.cli.db.DB;
import com.example.cli.model.Batch;


public class BatchManager {
	public static void main(String[] args) {
		System.out.println("Welcome to Gym Management System");

		// batchId 3 = Morning batch
		// batchId 4 = Evening batch

		final Batch batch = new Batch();
		batch.setName("Evening");
		batch.setDate("2022-11-01 18:15:20");
		batch.setDuration("2022-11-01 19:15:10");

		DB db = new DB();
		db.createConnection();

		db.createBatch(batch);

		db.closeConnection();
	}
}
