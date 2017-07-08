


function selectionLigne(lig)
{
	lig.style.cursor="hand";	
	lig.className="ligneencours";
	
}

function nonselectionLigne(lig)
{
	lig.style.cursor="default";
	lig.className="ligne";
}

function envoyer()
{
	// au moins un livre sélectionné
	  var checkboxes = document.getElementsByTagName('input');
	  for (var i = 0; i < checkboxes.length; i++)
	     {
	         if (checkboxes[i].type == 'checkbox')
	         {
	           if(checkboxes[i].checked)
	           document.forms.frmSelection.submit();
	           return;
	         }
	     }
	  
	 
}