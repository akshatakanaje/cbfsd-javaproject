package com.example.cli.model;

public class Participant {
	 
	//declaring attributeds
	int pid;
	String name;
	String phone;
	String email;
	int age;
	int batchId;
	
	//default constructor
	public Participant() {
		
	}

	public Participant(int pid, String name, String phone, String email, int age, int batchId) {
		super();
		this.pid = pid;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.age = age;
		this.batchId = batchId;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	@Override
	public String toString() {
		return "participant [pid=" + pid + ", name=" + name + ", phone=" + phone + ", email=" + email + ", age=" + age
				+ ", batchId=" + batchId + "]";
	}
}
