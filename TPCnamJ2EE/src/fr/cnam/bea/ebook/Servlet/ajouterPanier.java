package fr.cnam.bea.ebook.Servlet;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cnam.bea.ebook.Beans.LivreBean;
import fr.cnam.bea.ebook.Metier.Catalogue.Auteur;
import fr.cnam.bea.ebook.Metier.Catalogue.Editeur;
import fr.cnam.bea.ebook.Metier.Catalogue.Livre;
import fr.cnam.bea.ebook.Metier.Catalogue.TCategorie;
import fr.cnam.bea.ebook.Metier.Gestion.Panier;

/**
 * Servlet implementation class ajouterPanier
 */
@WebServlet(urlPatterns = { "/ajouterPanier" })
public class ajouterPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ajouterPanier() {
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
		request.setCharacterEncoding("UTF-8");

		
		String url=null;
		boolean err=false;
		Panier panier=null;
		try
		{
			// Panier en session : s'il n'existe pas création
			
			if(request.getSession().getAttribute("panier") == null)
			{
				 panier = new Panier();
			}
			else
			{	panier = (Panier)request.getSession().getAttribute("panier") ;
			}
			
		
			
			//on recupère les isbn des lignes  sélectionnées				
		
				String[] tabIsbn = request.getParameterValues("check");  
			
			
			// On récupère les livres correspondant 
			    for (int j = 0; j< tabIsbn.length; j++)
			    {   
				    	// chercher livre en session
			    	    LivreBean livrebean = (LivreBean)request.getSession().getAttribute("livreBean");
			    	    if(livrebean != null)
			    	    {
			    	    	for(Livre livre : livrebean.getListeLivres())
			    	    	{
			    	    		if(livre.getIsbn().equals(tabIsbn[j]))
			    	    		{
			    	    			 panier.ajouterLigne(livre);
			    	    		}
			    	    	}
			    	    }		 
				    						
			 	} // fin des livres
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			err=true;
			request.setAttribute("exception",ex.getMessage());
			url = "/jsp/afficherMessage.jsp";
		}
		
		if(err==false)
		{
		  request.getSession().setAttribute("panier", panier);
		   url="/jsp/afficherPanier.jsp";
		}

			ServletContext sc = this.getServletContext();
			RequestDispatcher requestDispatcher = sc.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		
	}

}
