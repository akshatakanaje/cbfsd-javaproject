package com.example.cli;

import java.util.Scanner;

import com.example.cli.db.DB;
import com.example.cli.model.Participant;

public class ParticipantManager {

	public static void main(String[] args) {

		//creating scanner object here
		Scanner scanner = new Scanner(System.in);
		
		 System.out.println("Connection to DB......");
		 DB db = new DB();           //declaring the DB method
		 db.createConnection();
        
		 
		 //running infinite loop
		while (true) {
			
			//reading input from user
			System.out.println("Enter Your Name");
			String name = scanner.nextLine();

			System.out.println("Enter Your Phone Number");
			String phone = scanner.nextLine();

			System.out.println("Enter Your Email");
			String email = scanner.nextLine();

			System.out.println("Enter Your Age");
			int age = Integer.parseInt(scanner.nextLine());

			System.out.println("Enter Your BatchId");
			int batchId = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Where pid = ");
			int pid = Integer.parseInt(scanner.nextLine());

			
			// checking if batchId is exits or not
			if (!db.isBatchIdPresent(batchId)) {
				System.out.println("****");
				System.out.println("Invalid batchId = " + batchId + ". Please check and try again.");
				continue; 
			}  
			
			System.out.println("Full Name :: " + name);
			System.out.println("Phone Number :: " + phone);
			System.out.println("Email :: " + email);
			System.out.println("Age :: " + age);
			System.out.println("BatchId :: " + batchId);
			System.out.println("pid :: "+pid);
			
			final Participant participant = new Participant(0, name, phone, email, age, batchId);
			saveToDatabase(db, participant);
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Do you want to add more participants? (y/n)");
			String more = scanner.nextLine();
            
			//validating the input
			if (more.equalsIgnoreCase("y")) {
				continue;
			}

			break;

		}

		db.closeConnection();
		System.out.println("Exited");   
		
				
		}
			
	
	private static void saveToDatabase(DB db, Participant participant) {
			db.createParticipant(participant);    
		
		
		
		
		// updating and deleting the participant 
	/*	System.out.println("Welcome to Gym Management System");
		Participant participant = new Participant();
		
		participant.setPid(20);
		participant.setName("Test T");
		participant.setPhone("123");
		participant.setEmail("test@test.com");
		participant.setAge(34);
		participant.setBatchId(5);
		
		System.out.println("Connection to DB......");
		DB db = new DB();
		db.createConnection();
		
		//db.updateParticipant(participant);
		db.deleteParticipant(20);
		
		db.closeConnection();       */
	}   

} 
