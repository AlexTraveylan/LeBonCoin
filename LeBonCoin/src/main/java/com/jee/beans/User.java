package com.jee.beans;

public class User {
	private final int id;
	private final String login;
	private final String password;
	private final String localisation;
	private final String img;
	
	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getLocalisation() {
		return localisation;
	}
	
	public String getImg() {
		return img;
	}
	
	public static class Builder {
		//Required
		private final int id;
		private final String login;
		
		//optionnal
		private String password = "0000";
		private String localisation = Math.random() < 0.5 ? "Bordeaux" : "Paris";
		private String img = "https://picsum.photos/200"; 
		
		
		public Builder(int id, String login) {
			this.id = id;
			this.login = login;
		}
		
		public Builder password(String val) {
			password = val; 
			return this;
		}
		
		public Builder localisation(String val) {
			localisation = val; 
			return this;
		}
		
		public Builder img(String val) {
			img = val; 
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
	
	private User(Builder builder) {
		id = builder.id;
		login = builder.login;
		password = builder.password;
		localisation = builder.localisation;
		img = builder.img;
		
	}

	
}
