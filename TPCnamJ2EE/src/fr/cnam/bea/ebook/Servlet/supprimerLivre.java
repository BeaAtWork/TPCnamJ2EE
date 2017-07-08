
package fr.cnam.bea.ebook.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.cnam.bea.ebook.Metier.Catalogue.Auteur;
import fr.cnam.bea.ebook.Metier.Catalogue.Editeur;
import fr.cnam.bea.ebook.Metier.Catalogue.Livre;
import fr.cnam.bea.ebook.Metier.Catalogue.TCategorie;
import fr.cnam.bea.ebook.Utilitaires.*;
import fr.cnam.bea.ebook.Beans.LivreBean;
import fr.cnam.bea.ebook.Dao.*;
import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;

/**
 * Servlet implementation class ajouterLivre
 */
@WebServlet(urlPatterns = { "/supprimerLivre" })
public class supprimerLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public supprimerLivre() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// criteres de recherche
		request.setCharacterEncoding("UTF-8");
		 
		 String isbn = request.getParameter("isbnlivre");
		// Créer objet livre		
		Livre livre = new Livre();
		livre.setIsbn(isbn);

		String url = "";
		boolean err = false;
		try {
			// Supprimer
			FabriqueDao.getInstanceLivre().supprimer(isbn);
		

		} catch (ExceptionAccesDonnees exad) {
			// TODO Auto-generated catch block
			err = true;
			request.setAttribute("exceptionBean", exad);
			url = response.encodeURL("/jsp/afficherMessage.jsp");

		}

		if (err == false) {
			// redirection vers servlet d'affichage
			request.setAttribute("message",
					fr.cnam.bea.ebook.Utilitaires.UtilFichier
							.getValeur("confirmation.suppression.livre"));
			url = response.encodeURL("/jsp/afficherMessage.jsp");
		}

		ServletContext sc = this.getServletContext();
		RequestDispatcher requestDispatcher = sc.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

}
