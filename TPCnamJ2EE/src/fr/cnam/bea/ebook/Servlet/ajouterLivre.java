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
@WebServlet(urlPatterns = { "/ajouterLivre" })
public class ajouterLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ajouterLivre() {
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
		 String titre = request.getParameter("titre"); 		 
		 String isbn = request.getParameter("isbn");
		 String[] categories = request.getParameterValues("cat");
		 String sdate = request.getParameter("dateparution");
		 float prix =   Float.parseFloat(request.getParameter("prix")); 		
		 int quantite =  Integer.parseInt( request.getParameter("quantite"));
		 String [] chauteur = request.getParameterValues("txtAuteur"); // multiselect		
		 String [] chediteur = request.getParameterValues("txtEditeur"); // multiselect
		 	 
		 String categorie = categories[0];
		 Date dateParution=null;
		try {
			dateParution = new SimpleDateFormat("dd-MM-yyyy").parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Calendar cal = Calendar.getInstance(); // GregorianCalendar
			cal.set(9999, 99, 99);// date inconnue
			Date datejour = cal.getTime();
		
		}
		 		
		TCategorie tcategorie = TCategorie.valueOf(categorie);
		
		List<Auteur> listeAuteurs = new ArrayList<Auteur>();
		
		for(int i=0;i<chauteur.length;i++)
			{
			Auteur auteur= 	fr.cnam.bea.ebook.Utilitaires.TraitementChaines.creerAuteur(chauteur[i]);		
		    if(auteur!=null) listeAuteurs.add(auteur);
			}
		
		
			List<Editeur> listeEditeurs =new ArrayList<Editeur>();
			for(int i=0;i<chediteur.length;i++)
			{
				Editeur editeur = fr.cnam.bea.ebook.Utilitaires.TraitementChaines.creerEditeur(chediteur[i]);
				if(editeur!=null)  listeEditeurs.add(editeur);
			}
		
		// Créer objet livre		
		Livre livre = new Livre(isbn, titre, prix, quantite, dateParution,	tcategorie, listeAuteurs, listeEditeurs);

		String url = "";
		boolean err = false;
		try {
			// Ajouter
			FabriqueDao.getInstanceLivre().ajouter(livre); 		

		} catch (ExceptionAccesDonnees exad) {
			// TODO Auto-generated catch block
			err = true;
			request.setAttribute("exceptionBean", exad);
			url = response.encodeURL("/jsp/afficherMessage.jsp");
		}
		
		//OK
		if (err == false) {
			// redirection vers servlet d'affichage
			List<Livre> liste = new ArrayList<Livre>();
			liste.add(livre);
			LivreBean livrebean = new LivreBean();
			livrebean.setListeLivres(liste);
			request.getSession().setAttribute("livreBean",livrebean);
			
			
		//	request.setAttribute("message", fr.cnam.bea.ebook.Utilitaires.UtilFichier.getValeur("confirmation.insertion.livre"));
			url = response.encodeURL("/jsp/afficherLivre.jsp");
		}

		ServletContext sc = this.getServletContext();
		RequestDispatcher requestDispatcher = sc.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

}
