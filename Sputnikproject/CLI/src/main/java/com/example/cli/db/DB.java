package com.example.cli.db;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.cli.model.Batch;
import com.example.cli.model.Participant;

public class DB implements DAO{
	
	/*
	 JDBC Procedure:
	 1. Download the Driver and Link it to the Project. For Maven Project simply configure the dependency in the pom.xml file
	      (1st step you can see in the Maven Dependencies)
	 2. Load the Driver from the Library i.e jar file
	 3. Create connection to the DataBase with url, user and password
	 4. Execute CRUD operations
	 5. Close the connection
	 */

	//to create & close connection we need a API from the JDBC
	Connection connection;
	
	//in order to insert a data into the table we need a API called statement
	Statement statement;
	PreparedStatement preparedStatement;
	CallableStatement callableStatement; 
	
	final String TAG = getClass().getSimpleName();        //this tag going to be a class name
	
	public DB(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");    //Driver path from the library
			System.out.println(TAG+" Driver Loaded");
			
		} catch(Exception e) {
			System.out.println("Exception Occurred "+e);
		}
		
	}

	
	//creating the connection by using 3 major things
	public void createConnection() {
		try {
			
			String user = "root";
			String password = "Yellow#123";
			String url = "jdbc:mysql://localhost/gym";
			
			connection = DriverManager.getConnection(url, user, password);
			
			System.out.println(TAG+" Connection Created");
			
		} catch(Exception e) {
			System.out.println("Exception Occurred "+e);
		}
		
	}
    
	
	//closing the DB connection
	public void closeConnection() {
		try {
			
			connection.close();
			System.out.println(TAG+" Connection Closed");
			
		} catch(Exception e) {
			System.out.println("Exception Occurred "+e);
		}
		
	}
	
    
	
	//creating the batch
	public void createBatch(Batch batch) {
		try {
			
			//Date -> YYYY-MM-DD
			//Duration -> YYYY-MM-DD hh:mm:ss
			
			SimpleDateFormat pattern1 = new SimpleDateFormat("YYYY-MM-DD");
			SimpleDateFormat pattern2 = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
			
			Date date = new Date();
			String date1 = pattern1.format(date);
			String date2 = pattern2.format(date);
			
			//Using Statement
			String sql = "insert into batches(name, date, duration)values( '"+batch.getName()+"', '"+batch.getDate()+"', '"+batch.getDuration()+"')";
			System.out.println("SQL is: "+sql);
			
			statement = connection.createStatement();  //create the statement then on that statement you can execute the function 
			int result = statement.executeUpdate(sql);   //called executeUpdate
			String message = result > 0 ? "Batch is Created" : "Batch is Not Created. Please Try Again";
			System.out.println(TAG+message);  
			
		} catch(Exception e) {
			System.out.println("Exception Occurred "+e);
		}
		
	}

	
	
	//creating the participant
	public void createParticipant(Participant participant) {
		try {
			
			//Using preparedStatement			
			String sql = "insert into participant values(null, ?, ?, ?, ?, ?)";
			System.out.println("SQL is: "+sql);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, participant.getName());
			preparedStatement.setString(2, participant.getPhone());
			preparedStatement.setString(3, participant.getEmail());
			preparedStatement.setInt(4, participant.getAge());
			preparedStatement.setInt(5, participant.getBatchId());
			
			int result = preparedStatement.executeUpdate();
			String message = result > 0 ? "Participant Added Successfully" : "Participant Not Added. Please Try Again";
			System.out.println(TAG+message);
			
		} catch(Exception e) {
			System.out.println("Exception occured "+e);
		}
	} 
    
	
	
	//updating the participant
	public void updateParticipant(Participant participant) {
		try {
			
			String sql = "update Participant set name = ?, phone = ?, email = ?, age = ?, batchId = ? where pid = ?";
			System.out.println("SQL is: "+sql);
			
            preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setNString(1, participant.getName());
			preparedStatement.setString(2, participant.getPhone());
			preparedStatement.setString(3, participant.getEmail());
			preparedStatement.setInt(4, participant.getAge());
			preparedStatement.setInt(5, participant.getBatchId());
			
			preparedStatement.setInt(6, participant.getPid());
			
			int result = preparedStatement.executeUpdate();
			String message = result > 0 ? "Participant Updated Successfully" : "Participant is Not Updated. Please Try Again";
			System.out.println(TAG+message);
			
		} catch(Exception e) {
			System.out.println("Exception occured "+e);
		}
	}
     
	
	
	//deleting the participant
	public void deleteParticipant(int pid) {
         try {
        	 
        	 String sql = "delete from participant where pid = ?";
        	 
        	 preparedStatement = connection.prepareStatement(sql);
        	 preparedStatement.setInt(1, pid);
        	 
        	 int result = preparedStatement.executeUpdate();
        	 String message = result > 0 ? "Participant Deleted Successfully" : "Participant is Not Deleted. Please Try Again";
 			 System.out.println(TAG+message);
        	 
		} catch(Exception e) {
			System.out.println("Exception occured "+e);
		}
	}  
	
	
	//Using callableStatement 
	/* public void executeProcedure(String name, String phone, String email, int age, int batchId) {
		try {
			
			String sql = "{ call addparticipant(?, ?, ?, ?, ?) }";
			
			
			callableStatement = connection.prepareCall(sql);
			
			callableStatement.setString(1, name);
			callableStatement.setString(2, phone);
			callableStatement.setString(3, email);
			callableStatement.setInt(4, age);
			callableStatement.setInt(5, batchId);
			
			callableStatement.execute();
			System.out.println("Stored Procedure is Executed");
			
			} catch(Exception e) {
			System.out.println("Exception Occurred "+e);
		} 
	}  */
	
	
	//checking the batchId matches with participant or not
	public void selectParticipant(int pid) {
		try {
			
			String sql = " select * from participant where pid = ?";
			
			callableStatement = connection.prepareCall(sql);
			
			callableStatement.setInt(1, pid);			
			
			ResultSet set = callableStatement.executeQuery();
			if(set.next()) {
				System.out.println("Found the Participant with ID: "+pid);
			} else {
				System.out.println("Sorry! No Participant Found with ID: "+pid);
			}
			
			System.out.println("Selected Procedure is Executed");
		} catch(Exception e) {
			System.out.println("Exception Occurred "+e);
		} 
	} 
	
	
	//checking the batchId is present or not
	public boolean isBatchIdPresent(int batchId) {
		try {
			
			String sql = "select * from batches where batchId('?')";
			System.out.println("SQL is "+sql);
			
        /*  callableStatement = connection.prepareCall(sql);
   			callableStatement.setInt(1, batchId);  */
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(5, batchId);
			
			ResultSet set = preparedStatement.executeQuery();
			if(set.next()) {
				System.out.println("Found batch by batchId = " + batchId);
				return true;
			} else {
				System.out.println("Batch with batchId = " + batchId + " not found");
				return false;
			}		
						
		} catch(Exception e) {
			System.out.println("Exception Occurred "+e);
		} 
		return false;
	}
	

}
