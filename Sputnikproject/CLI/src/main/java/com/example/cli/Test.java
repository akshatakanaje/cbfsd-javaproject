package com.example.cli;


import java.util.Scanner;

import com.example.cli.db.DB;

public class Test {

	public static void main(String[] args) {
		
		// insert participant only if batch id exists
		
	    Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Participant ID");
		int pid = Integer.parseInt(scanner.nextLine());
		
		DB db = new DB();
		db.createConnection();
		
		db.selectParticipant(pid);
		
		db.closeConnection();  
		
	
	}

}
