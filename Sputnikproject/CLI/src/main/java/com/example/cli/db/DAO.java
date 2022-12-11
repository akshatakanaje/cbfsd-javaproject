package com.example.cli.db;

import com.example.cli.model.Batch;
import com.example.cli.model.Participant;


public interface DAO {
	
	//declaring two connections
	void createConnection();
	void closeConnection();
	
	//Declare methods for batches
	void createBatch(Batch batch);
	
	
	//Declare methods for participant
	void createParticipant(Participant participant);
	void updateParticipant(Participant participant);
	void deleteParticipant(int pid);  

}
