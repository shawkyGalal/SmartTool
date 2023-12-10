
function TransposeTable(tableId)
{        
    var tbl = $('#' + tableId);
    var tbody = tbl.find('tbody');
    var oldWidth = tbody.find('tr:first td').length;
    var oldHeight = tbody.find('tr').length;
    var newWidth = oldHeight;
    var newHeight = oldWidth;

    var jqOldCells = tbody.find('td');        

    var newTbody = $("<tbody></tbody>");
    for(var y=0; y<newHeight; y++)
    {
        var newRow = $("<tr></tr>");
        for(var x=0; x<newWidth; x++)
        {
            newRow.append(jqOldCells.eq((oldWidth*x)+y));
        }
        newTbody.append(newRow);
    }

    tbody.replaceWith(newTbody);        
}

var detailHidden = true;
function toggleShowDetails()
{
	if (detailHidden)
	{
	document.all['DetailLayer'].style.visibility = 'visible';
	detailHidden = false;
	document.all['toggleDetails'].value = 'Hide Details'
	}
	else
	{
	document.all['DetailLayer'].style.visibility = 'hidden';
	detailHidden = true;
	document.all['toggleDetails'].value = 'Show Details'
	}
}


function updaetHref_var(m_inputText , m_hrefLink)
	{
  		var newhref = "javascript:window.open('/selectionTree.jsp?_operationMode=<%=operationMode%>&_selectedIDs=" + m_inputText.value + "&_querySouce=<%=treeQuerySource%>&treeIdInSession=<%=treeIdInSession%>&_fillObject=var<%=i%>' , 'Select From Tree' , 'width=400, height=600' )" ;  
		m_hrefLink.href =  newhref ; 
	}
	

function changeFrameSrc(iframeId , loc) {
	var frameElement = document.getElementById(iframeId) ;  
	if ( frameElement == null )
	{
		frameElement = parent.document.getElementById(iframeId) ;
		if (frameElement == null ) 
		{
			alert ('System Could Not Found the Fram with ID =  : ' +  iframeId + 'You May need to Use (parent.document.getElementById) instead '  ) ;
		}
	}
	frameElement.src = loc;
  }

function closeWindow()
	{
		if(self===top) { window.close(); }
		else { top.history.go(-1) ;  }
	}