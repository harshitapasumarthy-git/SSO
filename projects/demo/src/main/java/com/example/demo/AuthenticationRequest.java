package com.example.demo;

public class AuthenticationRequest {
	
	    private int id;
	    private String userName;
		private String password;
		public AuthenticationRequest() {
		
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		

	}


