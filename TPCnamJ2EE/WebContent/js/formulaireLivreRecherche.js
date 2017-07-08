 /** controles du formulaire de la page rechercher livres */
 
  var exptitre=new RegExp ("^[a-zA-Z _,:0-9\u00C0-\uFFFF']*$");  // titre
  var expnum=new RegExp ("^[0-9]*$");  //isbn
  var expdate=new RegExp ("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");  // date
  
  
window.onload = function()
{	
	
	document.getElementById("titre").focus() ;  
	
	Event.observe( "rtitre", "click", controleType);
	Event.observe( "risbn", "click", controleType);
	Event.observe( "rcategorie", "click", controleType);
	
	Event.observe( "titre", "blur", controleTitre);
	Event.observe( "isbn", "blur", controleIsbn);
	
	
}

function controleType ()
{
	effacer();
	
  if(document.getElementById("rtitre").checked	)
  {
	 
	  cacher("div2","div3","div1"); 
  }
  
  if(document.getElementById("risbn").checked	)
  {
	
	  cacher("div1","div3","div2");
  }
  
  if(document.getElementById("rcategorie").checked	)
  { 
     cacher("div1","div2","div3");
     document.getElementById("cmdValider").disabled = false; 
     
  }
}

function cacher(x,y,z)
{
	document.getElementById(x).className ="criterecache";
	document.getElementById(y).className ="criterecache";
	document.getElementById(z).className ="criterevisible";
	
}

function initSelect()
{
	//document.getElementById("cat").option[0].checked=true;
}

var msgErreurs={
	"contenu": "Veuillez saisir une valeur",
	"spanTitre": "Caract&egrave;res incorrects",
	"spanIsbn": "Chiffres seulement"
};

var isValid={		
	"titreerr": false,
	"isbnerr":false
	
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
	document.getElementById(composant).innerHTML= "" ;
	isValid[composant]=true ; 
	document.getElementById("cmdValider").disabled = false; 
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




function effacer(){

	document.getElementById("cmdValider").disabled = true; 
	document.getElementById("titreerr").innerHTML = "";
	document.getElementById("isbnerr").innerHTML = "";
	document.getElementById("titre").value="";
	document.getElementById("isbn").value="";

	isValid["titreerr"]=false ;
	isValid["isbnerr"]=false ;
	cacher("div2","div3","div1"); 
	

}



