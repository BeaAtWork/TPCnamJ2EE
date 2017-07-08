/**
 * controles du formulaire de la page ajouter livre
 * */
  var exptitre=new RegExp ("^[a-zA-Z _,:0-9\u00C0-\uFFFF']*$");  // titre
  var expnum=new RegExp ("^[0-9]*$");  //isbn et quantite
  var expdate=new RegExp ("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");  // date
  var expprix=new RegExp ("^[0-9]*.[0-9]{2}$");  //prix
  

  
window.onload = function()
{	
	
	document.getElementById("titre").focus() ;  
	
	Event.observe( "titre", "blur", controleTitre);
	Event.observe( "isbn", "blur", controleIsbn);
	Event.observe( "prix", "blur", controlePrix);
	Event.observe( "quantite", "blur", controleQuantite);
	Event.observe( "dateparution", "blur", controleDateparution );
	
}



var msgErreurs={
	"contenu": "Veuillez saisir une valeur",
	"spanTitre": "Caract&egrave;res incorrects",
	"spanIsbn": "Chiffres seulement",
	"spanDateparution":"Date de parution invalide",
	"spanPrix": "Nombre d&eacute;cimal seulement",
	"spanQuantite": "Chiffres seulement"
};

var isValid={		
	"titreerr": false,
	"isbnerr":false,
	"dateparutionerr": false,
	"prixerr":false,
	"quantiteerr":false
	
};

function controleContenu ( contenu , span  , zone  )
{
	var etat = true ;
	if ( contenu == "" || contenu == null ) {
		document.getElementById(span).innerHTML = msgErreurs["contenu"] ; 
		etat = false ; 
	}	
	return etat ;  
}	

function effaceComposant (composant)
{
	document.getElementById(composant).innerHTML = "" ;
	isValid[composant]=true ; 
	validerBouton () ; 

}



function validerBouton () 
{
	var valid = true ; 
	for (var cle in isValid) {
		if (isValid[cle] == false) 
			valid = false;
	}
	if ( valid == true )
		{
		document.getElementById("cmdValider").disabled = false; 
		}
}

function controleTitre(){
	
	var tit = document.getElementById("titre").value;
	
	var resContenu = controleContenu ( tit , "titreerr" , "titre" ); 
	if ( resContenu == true )
	{
		
		//alert("titre " + (tit.match(exptitre)?"valide":"invalide") ); 
		if(!tit.match(exptitre))
		    document.getElementById("titreerr").innerHTML = msgErreurs["spanTitre"] ; 
		else 
			effaceComposant ("titreerr");
	}
}


function controleIsbn()
{
	
	var isbn = document.getElementById("isbn").value;
	
	var resContenu = controleContenu ( isbn , "isbnerr" , "isbn" ); 
	if ( resContenu == true )
	{
		
		if(!isbn.match(expnum))
		    document.getElementById("isbnerr").innerHTML = msgErreurs["spanIsbn"] ; 
		else 
			effaceComposant ("isbnerr");
	}
}

function controleDateparution()
{
	
	var datep = document.getElementById("dateparution").value;
	
	var resContenu = controleContenu ( datep , "dateparutionerr" , "dateparution" ); 
	if ( resContenu == true )
	{
		
		if(!datep.match(expdate))
		    document.getElementById("dateparutionerr").innerHTML = msgErreurs["spanDateparution"] ; 
		else 
			effaceComposant ("dateparutionerr");
	}
}

function controlePrix()
{
	
	var prix = document.getElementById("prix").value;
	
	var resContenu = controleContenu ( prix , "prixerr" , "prix" ); 
	if ( resContenu == true )
	{
		
		if(!prix.match(expprix))
		    document.getElementById("prixerr").innerHTML = msgErreurs["spanPrix"] ; 
		else 
			effaceComposant ("prixerr");
	}
}


function controleQuantite()
{
	
	var quantite = document.getElementById("quantite").value;
	
	var resContenu = controleContenu ( quantite , "quantiteerr" , "quantite" ); 
	if ( resContenu == true )
	{
		
		if(!quantite.match(expnum))
		    document.getElementById("quantiteerr").innerHTML = msgErreurs["spanQuantite"] ; 
		else 
			effaceComposant ("quantiteerr");
	}
}

function effacer(){

	document.getElementById("cmdValider").disabled = true; 
	document.getElementById("titreerr").innerHTML = "";
	document.getElementById("isbnerr").innerHTML = "";
	document.getElementById("dateparution").innerHTML = "";
	document.getElementById("prix").innerHTML = "";
	document.getElementById("quantite").innerHTML = "";
	
	
	document.getElementById("titre").value="";
	document.getElementById("isbn").value="";
	document.getElementById("dateparution").value="";
	document.getElementById("prix").value="";
	document.getElementById("quantite").value="";
	document.getElementById("auteur").value="";
	document.getElementById("editeur").value="";
	
}


function ajouter(qui)
{

	if(qui=="auteur")
	{
		var element = document.getElementById("auteur").value;
		if(element!= null && element!="")
			{	   document.getElementById("txtAuteur").options[ document.getElementById("txtAuteur").length] = new Option(element,element);
			      document.getElementById("auteur").value="";
			     alert("L'auteur a été ajouté dans la liste");
			}
		else
			{alert("Veuillez saisir un nom et un prénom");}
	}
	if(qui=="editeur")
	{
		var element = document.getElementById("editeur").value;
		if(element!= null && element!="")
		{	
			  document.getElementById("txtEditeur").options[ document.getElementById("txtEditeur").length] = new Option(element,element, true, true);
			   document.getElementById("editeur").value="";
			   alert("L'éditeur a été ajouté dans la liste");
		}
		else
		{alert("Veuillez saisir un nom et un pays");}
		 
	}
	
	return;
}


