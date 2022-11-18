package com.jee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jee.beans.Annonce;
import com.jee.beans.User;

public class AnnoncesDAO {

	public static List<Annonce> getAllAnnonces(){
		
		List<Annonce> res = new ArrayList<>();
		
		try {
			Connection con = UtilConnexion.seConnecter();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Annonces");	

			
			while (rs.next() ) {
				
				User user = UsersDAO.GetUserById(rs.getInt("user"));
				
				Annonce annonce = new Annonce
						.Builder(rs.getInt("id"),rs.getString("titre"), rs.getString("description"), user)
						.categorie(rs.getString("categorie"))
						.prix(rs.getInt("prix"))
						.date(rs.getString("date"))
						.img(rs.getString("img"))
						.build();
				
				//Ajout dans la List de retour
				res.add(annonce);
			}
			
			con.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}


	public static Annonce GetAnnonceById(int id) {
		
		Annonce annonce = null;
		try {
			Connection con = UtilConnexion.seConnecter();
			PreparedStatement ps = con.prepareStatement(("SELECT * FROM Annonces WHERE id=?"));	
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				
			User user = UsersDAO.GetUserById(rs.getInt("user"));
			
			annonce = new Annonce
					.Builder(rs.getInt("id"),rs.getString("titre"), rs.getString("description"), user)
					.categorie(rs.getString("categorie"))
					.prix(rs.getInt("prix"))
					.date(rs.getString("date"))
					.img(rs.getString("img"))
					.build();
			}
			
			con.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return annonce;
	}
	

	public static boolean deleteAnnonce(int id) {
		try {
			Connection con = UtilConnexion.seConnecter();			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Annonces WHERE id= ?");
			ps.setInt(1, id);
			
			ps.executeUpdate();
			con.close();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
	

	public static boolean updateAnnonce(Annonce annonce) {
		try {
			Connection con = UtilConnexion.seConnecter();			
			PreparedStatement ps = con.prepareStatement("UPDATE Annonces SET titre=?, description=? WHERE id= ?");
			ps.setString(1, annonce.getTitre());

			ps.setString(2, annonce.getDescription());
			
			ps.setInt(3, annonce.getId());
			
			ps.executeUpdate();
			con.close();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
	
	public static boolean createAnnonce(String titre, String description, User user) {
		try {
			
			Annonce annonce = new Annonce
					.Builder(0,titre, description , user)
					.build();
			
		
			Connection con = UtilConnexion.seConnecter();			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Annonces(titre, description, categorie, prix, user, img) VALUE (?, ?, ?, ?, ?, ?);");
			ps.setString(1, annonce.getTitre());
			ps.setString(2, annonce.getDescription());
			ps.setString(3, annonce.getCategorie());
			ps.setInt(4, annonce.getPrix());
			ps.setInt(5, annonce.getUser().getId());
			ps.setString(6, annonce.getImg());
			
			ps.executeUpdate();
			con.close();
			System.out.println("Reussite de la creation d'une annonce");
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Erreur de la creation d'une annonce");
			e.printStackTrace();

			return false;
		}
	}
	
}
