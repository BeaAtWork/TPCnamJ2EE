package fr.cnam.bea.ebook.Servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cnam.bea.ebook.Dao.FabriqueDao;
import fr.cnam.bea.ebook.Dao.ILivreDao;
import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;
import fr.cnam.bea.ebook.Metier.Catalogue.Auteur;
import fr.cnam.bea.ebook.Metier.Catalogue.Editeur;



@WebServlet("/prepareAjouterLivre")
public class prepareAjouterLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public prepareAjouterLivre() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		boolean err = false;
		try
		{
			// Rercherche des auteurs dans la base 
		
			List<Auteur> listeAuteurs =  FabriqueDao.getInstanceLivre().rechercherAuteurs(); 
			
			List<Editeur> listeEditeurs = FabriqueDao.getInstanceLivre(). rechercherEditeurs();
			
			request.setAttribute("listeauteurs" , listeAuteurs); 
			request.setAttribute("listeediteurs" , listeEditeurs); 
			url="jsp/admin/ajouterLivre.jsp";
		}
		 catch (ExceptionAccesDonnees exad) {
				// TODO Auto-generated catch block
				err = true;
				request.setAttribute("exceptionBean", exad);
				url = response.encodeURL("/jsp/afficherMessage.jsp");
			}
	
		request.getRequestDispatcher(url).forward(request, response ) ; 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
