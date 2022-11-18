package com.jee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jee.beans.User;
import com.jee.beans.PasswordAuthentication;


public class UsersDAO {
	
public static List<User> getAllUsers(){
		
		List<User> res = new ArrayList<>();
		
		try {
			Connection con = UtilConnexion.seConnecter();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Users");	

			
			while (rs.next() ) {
				
				PasswordAuthentication pa = new PasswordAuthentication();
				//Set d'un utilisateur avec chaque ligne de la bdd + hash password
				User user = new User
						.Builder(rs.getInt("id"),rs.getString("login"))
						.password((pa.hash(rs.getString("password").toCharArray())))
						.localisation(rs.getString("localisation"))
						.img(rs.getString("img"))
						.build();
				
				//Ajout dans la List de retour
				res.add(user);
			}
			
			con.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}


	public static User GetUserById(int id) {
		
		User user = null;
		try {
			Connection con = UtilConnexion.seConnecter();
			PreparedStatement ps = con.prepareStatement(("SELECT * FROM Users WHERE id=?"));	
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				
			user = new User
					.Builder(rs.getInt("id"),rs.getString("login"))
					.password(rs.getString("password"))
					.localisation(rs.getString("localisation"))
					.img(rs.getString("img"))
					.build();
			
			}
			
			con.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public static User GetUserByLogin(String login) {
		
		User user = null;
		try {
			Connection con = UtilConnexion.seConnecter();
			PreparedStatement ps = con.prepareStatement(("SELECT * FROM Users WHERE login=?"));	
			ps.setString(1,login);
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				user = new User
						.Builder(rs.getInt("id"),rs.getString("login"))
						.localisation(rs.getString("localisation"))
						.password(rs.getString("password"))
						.img(rs.getString("img"))
						.build();
			}
			con.close();
			rs.close();
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return user;
	}
	
	public static Boolean tryLogin(User user, String password) {
		
		PasswordAuthentication pa = new PasswordAuthentication();
		if (pa.authenticate(password.toCharArray(), user.getPassword())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Fonction qui supprime un User et renvoie un Bool
	 * @param id
	 * @return true si l'utilisateur a été supprimé
	 * false en cas d'echec de la suppression
	 */
	public static boolean deleteUser(int id) {
		try {
			Connection con = UtilConnexion.seConnecter();			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Users WHERE id= ?");
			ps.setInt(1, id);
			
			ps.executeUpdate();
			con.close();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
	
	/**
	 * Fonction qui modifie un User et renvoie un Bool
	 * @param User
	 * @return true si l'utilisateur a été modifié
	 * false en cas d'echec de la modification
	 */
	public static boolean updateUser(User user) {
		try {
			Connection con = UtilConnexion.seConnecter();			
			PreparedStatement ps = con.prepareStatement("UPDATE users SET login=?, password=? WHERE id= ?");
			ps.setString(1, user.getLogin());
			
			PasswordAuthentication pa = new PasswordAuthentication();
			ps.setString(2, pa.hash(user.getPassword().toCharArray()));
			
			ps.setInt(3, user.getId());
			
			ps.executeUpdate();
			con.close();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
	
	public static boolean createUser(String login) {
		try {
			User user = new User
	                .Builder(0 , login)
	                .build();
			Connection con = UtilConnexion.seConnecter();			
			PreparedStatement ps = con.prepareStatement("INSERT INTO users(login, password, localisation, img) VALUE (?, ?, ?, ?);");
			ps.setString(1, user.getLogin());
			PasswordAuthentication pa = new PasswordAuthentication();
			ps.setString(2, pa.hash(user.getPassword().toCharArray()));
			ps.setString(3, user.getLocalisation());
			ps.setString(4, user.getImg());
			
			ps.executeUpdate();
			con.close();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
	
	
	
	
	
}
