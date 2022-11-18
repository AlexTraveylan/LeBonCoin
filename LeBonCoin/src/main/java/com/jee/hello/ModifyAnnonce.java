package com.jee.hello;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jee.beans.Annonce;
import com.jee.beans.User;
import com.jee.dao.AnnoncesDAO;
import com.jee.dao.UsersDAO;


@WebServlet("/modifyAnnonce")
public class ModifyAnnonce extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Annonce annonce =  AnnoncesDAO.GetAnnonceById(id);
		request.setAttribute("annonce", annonce);
		HttpSession session = request.getSession(true);
		if ((int)session.getAttribute("user_id") == id) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/ModifAnnonce.jsp").forward(request, response);	
		} else {
			response.sendRedirect("annonces");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		User user = UsersDAO.GetUserById(Integer.parseInt(request.getParameter("user_id")));
			Annonce annonce = new Annonce
					.Builder(Integer.parseInt(request.getParameter("id")), request.getParameter("titre"), request.getParameter("description"), user)
					.build();

			request.setAttribute("msg", AnnoncesDAO.updateAnnonce(annonce)?"Annonce n°" + annonce.getId() + " modifié": "Erreur");
			response.sendRedirect("annonces");	
	}
}

