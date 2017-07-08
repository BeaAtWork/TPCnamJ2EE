package fr.cnam.bea.ebook.Servlet;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.cnam.bea.ebook.Metier.Catalogue.Livre;
import fr.cnam.bea.ebook.Utilitaires.*;
import fr.cnam.bea.ebook.Beans.LivreBean;
import fr.cnam.bea.ebook.Dao.*;
import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;

/**
 * Servlet implementation class chercherLivre
 */
@WebServlet(urlPatterns = { "/chercherLivres" }, initParams = { @WebInitParam(name = "ListeRecherches", value = "C:/Users/Beatrice/Logs/FicHistoriqueRecherche.txt") })
public class chercherLivres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int compteur = 0;

	private static List<HistoriqueRecherche> lRecherches = null;
	private String nomFic = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public chercherLivres() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		lRecherches = new ArrayList<HistoriqueRecherche>();
		compteur = 0;
		nomFic = config.getInitParameter("ListeRecherches");
		Calendar cal = Calendar.getInstance(); // GregorianCalendar
		Date heureDepart = cal.getTime();// date de recherche
		UtilFichier.lireFichier(nomFic, heureDepart);

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance(); // GregorianCalendar
		Date heureFin = cal.getTime();// date de recherche
		UtilFichier.sauverFichier(nomFic, lRecherches, heureFin);
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
		// out.println(request.getCharacterEncoding());

		if(request.isUserInRole("administrateur"))
		{
			System.out.println("logué en role administrateur");
		}
		
		String titre = request.getParameter("titre");
		String[] categories = request.getParameterValues("cat");
		String categorie ="Indéfinie";
		if(categories!=null && categories.length>0)
		  {categorie= categories[0];}
		String isbn = request.getParameter("isbn");


		Calendar cal = Calendar.getInstance(); // GregorianCalendar
		Date datejour = cal.getTime();// date de recherche

		List<Livre> liste = null;
		String url = "";
		boolean err = false;

		try {
			// recherche dans le catalogue par titre
			liste=	FabriqueDao.getInstanceLivre().rechercher(titre, categorie, isbn);
			
		} catch (ExceptionAccesDonnees exad) {
			// TODO Auto-generated catch block
			err = true;
			request.setAttribute("exceptionBean", exad);
			url = response.encodeURL("/jsp/afficherMessage.jsp");
		}

		if (err == false) {
			if(liste==null || liste.size()==0)
			{
			   url = response.encodeURL("/jsp/afficherAucunLivre.jsp");
			}
			else
			{
				LivreBean livreBean = new LivreBean();
				livreBean.setListeLivres(liste);
				livreBean.setDateRecherche(datejour);
	
				request.getSession().setAttribute("livreBean", livreBean);

			// redirection vers servlet d'affichage
				url = response.encodeURL("/jsp/afficherLivre.jsp");
			} 
		}
		

		ServletContext sc = this.getServletContext();
		RequestDispatcher requestDispatcher = sc.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

}
