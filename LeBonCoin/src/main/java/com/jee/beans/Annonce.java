package com.jee.beans;


public class Annonce {
	private final int id;
	private final String titre;
	private final String description;
	private final String categorie;
	private final int prix;
	private final String date;
	private final User user;
	private final String img;
	
	
	
	public int getId() {
		return id;
	}

	public String getImg() {
		return img;
	}

	public String getTitre() {
		return titre;
	}

	public String getDescription() {
		return description;
	}

	public String getCategorie() {
		return categorie;
	}

	public int getPrix() {
		return prix;
	}

	public String getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public static class Builder {
		//Required
		private final int id;
		private final String titre;
		private final String description;
		private final User user;

		
		//optionnal
		private String categorie=Math.random()<0.5?"Cat1":"Cat2";
		private int prix=1+(int)(Math.random()*1000);
		private String date="";
		private String img = "https://picsum.photos/id/"+(1+(int)(Math.random()*300))+"/200/300";

		
		
		public Builder(int id, String titre, String description, User user) {
			this.id = id;
			this.titre = titre;
			this.description=description;
			this.user = user;
		}
		
		public Builder categorie(String val) {
			categorie = val; 
			return this;
		}
		
		public Builder prix(int val) {
			prix = val; 
			return this;
		}
		
		public Builder date(String val) {
			date = val; 
			return this;
		}
		
		public Builder img(String val) {
			img = val; 
			return this;
		}
		
		public Annonce build() {
			return new Annonce(this);
		}
	}
	
	private Annonce(Builder builder) {
		id = builder.id;
		titre = builder.titre;
		description = builder.description;
		categorie = builder.categorie;
		prix = builder.prix;
		date = builder.date;
		user = builder.user;
		img = builder.img;
		
	}

}



