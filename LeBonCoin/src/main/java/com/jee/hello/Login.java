package com.jee.hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jee.dao.UsersDAO;

@WebServlet("/create")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println(getServletContext().getInitParameter("DATABASE_URL"));
		// Récuperation et envoie de l'eventuel message d'erreur.
		String erreur = (String) request.getAttribute("erreur");
		request.setAttribute("erreur", erreur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String erreur = "";

		// Recuperation des données du formulaire.
		String login = request.getParameter("login");

		// Verification des erreurs.
		if ("".equals(login))
			erreur += "Le champ username est vide.<br/>";

		// Si erreur, envoie de l'erreur a GET.
		if (!erreur.equals("")) {
			request.setAttribute("erreur", erreur);
			doGet(request, response);

			// Sinon envoie des données de l'utilisateur a la page.
		} else {

			// connexion et insertion de l'utilisateur.
			
			if (UsersDAO.createUser(login)) {
				response.sendRedirect("liste");
			} else {
				doGet(request, response);
			}
		}
	}
}
