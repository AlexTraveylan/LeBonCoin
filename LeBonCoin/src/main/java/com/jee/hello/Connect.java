package com.jee.hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jee.beans.User;
import com.jee.dao.UsersDAO;

@WebServlet("/connect")
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			this.getServletContext().getRequestDispatcher("/WEB-INF/Connect.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String msg = "Pas de message";
		String login = request.getParameter("login_text");
		String password = request.getParameter("password_text");
		User user = UsersDAO.GetUserByLogin(login);
		System.out.println(user);
		Boolean test = false;
		if ( user != null) {
			if (UsersDAO.tryLogin(user, password)) {
				msg = "Connection de " + user.getLogin() + " réussie";
				session.setAttribute("connect", "ok");
				session.setAttribute("user_id", user.getId());
				test = true;
			} else {
				msg = "Mauvais mot de passe pour " + user.getLogin();
			}
			
		} else {
			msg="L'utilisateur n'existe pas dans la base de donnée";
		}
		
		request.setAttribute("msg", msg);
		if (test) {
			response.sendRedirect("annonces");
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/Connect.jsp").forward(request, response);
		}

	}

}
