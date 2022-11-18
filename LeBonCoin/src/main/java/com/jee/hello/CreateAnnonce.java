package com.jee.hello;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jee.dao.AnnoncesDAO;
import com.jee.dao.UsersDAO;
import com.jee.beans.User;


@WebServlet("/createAnnonce")
public class CreateAnnonce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if ("ok".equals(session.getAttribute("connect"))) {
			request.setAttribute("msg", "Vous etes connecté avec le numero d'utilisateur : " + session.getAttribute("user_id") + "valeur de connect : " + session.getAttribute("connect"));
			this.getServletContext().getRequestDispatcher("/WEB-INF/CreateAnnonce.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "Vous devez être connecté pour créer une annonce");
			response.sendRedirect("annonces");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		
		try {
			int user_id = (int) request.getSession().getAttribute("user_id");
			System.out.println("Recuperation de l'user de session : n°" + user_id);
			User user = UsersDAO.GetUserById(user_id);
			// Recuperation des données du formulaire.
			String titre = request.getParameter("titre");
			String description = request.getParameter("description");
			
			// connexion et insertion de l'utilisateur.
			if (AnnoncesDAO.createAnnonce(titre, description, user)) {
				response.sendRedirect("annonces");
			} else {
				msg = "Echec de la creation de l'annonce";
				request.setAttribute("msg", msg);
				doGet(request, response);
			}
			
		} catch (Exception e) {
			msg = "L'utilisateur n'est pas connecté";
			e.printStackTrace();
			doGet(request, response);
		}


	}
}
