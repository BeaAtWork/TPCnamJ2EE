package fr.cnam.bea.ebook.Utilitaires;



public class HistoriqueRecherche {
	
		 String titre="";
		 int nbre=1;
		
		public HistoriqueRecherche(String t)
		{this.titre=t;			
		}
		public void ajouter()
		{
			this.nbre+=1;
		}
	  
		 @Override
		public boolean equals (Object o) {
			 HistoriqueRecherche rech = (HistoriqueRecherche)o;
	        if (rech.titre.equals(this.titre)) return true;
	        return false;
	    }
		@Override
		public String toString() {
			return "HistoriqueRecherche [titre=" + titre + ", nbre=" + nbre + "]";
		}
		 
	}



