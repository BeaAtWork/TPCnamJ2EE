package fr.cnam.bea.ebook.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cnam.bea.ebook.Beans.LivreBean;
import fr.cnam.bea.ebook.Metier.Catalogue.Livre;
import fr.cnam.bea.ebook.Metier.Gestion.Panier;

/**
 * Servlet implementation class prepareAfficherPanier
 */
@WebServlet("/prepareAfficherPanier")
public class prepareAfficherPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prepareAfficherPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url=null;
		boolean err=false;
		Panier panier=null;
		try
		{
			// Panier en session : s'il n'existe pas , envoi message
			
			if(request.getSession().getAttribute("panier") == null)
			{
				err=true;
				request.setAttribute("message","Le panier est vide");
				url = "/jsp/afficherMessage.jsp";
			}
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
		 
		   url="/jsp/afficherPanier.jsp";
		}

			ServletContext sc = this.getServletContext();
			RequestDispatcher requestDispatcher = sc.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		
	}

}
