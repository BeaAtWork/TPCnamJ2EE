package fr.cnam.bea.ebook.Utilitaires;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Beatrice
 *
 */

public class UtilFichier {
	
	
private static ResourceBundle conf = init("config");
private static File f=null;



/**
 * @param fichier
 * @return
 */
private static ResourceBundle init(String fichier) {
	try {
		return ResourceBundle.getBundle(fichier);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


/**
 * @param key
 * @return
 */
public static String getValeur(String key) {
	try {
		return conf.getString(key);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


	/**
	 * @param nomFic
	 * @param heureDepart
	 */
	public static void lireFichier(String nomFic, Date heureDepart) {
		// TODO Auto-generated method stub
		System.out.println("création fichier");
		try
		{
			  f = new File(nomFic);		
			  FileWriter fw = new FileWriter(f,true);
		  SimpleDateFormat  sdf  = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				 String sd = sdf.format(heureDepart);
				fw.write("-------- Début enregistrement : "+sd+" ----------\n" );
				fw.close();
				System.out.println(" fichier de stats à : " + f.getAbsolutePath());		 
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @param nomFic
	 * @param lRecherches
	 * @param heureFin
	 */
	public static void sauverFichier(String nomFic, List<HistoriqueRecherche> lRecherches, Date heureFin) {
		// TODO Auto-generated method stub
		
		try
		{
			FileWriter fw = new FileWriter(f,true);
			 SimpleDateFormat  sdf  = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			 String sd = sdf.format(heureFin);
			
			for(HistoriqueRecherche rech : lRecherches)
			{
				fw.write(rech.titre+","+rech.nbre+";\n");
			}
			fw.write("-------- Fin enregistrement le "+sd+" ----------\n\r" );
			
			fw.close();
			System.out.println("Le fichier a été enregistré");
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			
		}
	}


	}
