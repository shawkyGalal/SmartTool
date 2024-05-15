/**
 * 
 */
 function buildSelectionLink(populateItemId , linkItemId , selectionPageUrl , windowWidth , windowHeight )
    {
    const selectedUnitInput = document.getElementById(populateItemId );
    const openSelectionLink = document.getElementById(linkItemId);
	message = {"operation" : "XXXXX" , "abc" :"123" }  ; 

    openSelectionLink.addEventListener("click", () => {
      
      const windowDimensions = getWindowDimensions();
  	  const windowPosition = calculateWindowPosition(windowDimensions, windowWidth, windowHeight);
      const windowFeatures = `width=${windowWidth},height=${windowHeight},top=${windowPosition.top},left=${windowPosition.left}`;
      const selectionWindow = window.open(selectionPageUrl, "selectionWindow", windowFeatures);

      // Function to handle messages received from the selection window
      const handleReceiveMessage = (event) => {
        if (event.origin !== window.location.origin) {
          return; // Ignore messages from different origins (security)
        }
        if (event.data && typeof event.data === "string") {
          selectedUnitInput.value = event.data; // Update selected unit ID
          message.operation= "CloseYourSelf" ; 
		  selectionWindow.postMessage(message, "*"); 
        }
      };

      // Listen for messages from the receiver window
      window.addEventListener("message", handleReceiveMessage);

      // Function to send a message requesting unit selection to the receiver
      const requestUnitSelection = () => {
        if (selectionWindow) {
           message.operation="sendBackUserSelection" ;
           message.currentSelectedId= selectedUnitInput.value ; 
           selectionWindow.postMessage(message, "*"); // Send message to receiver
        }
        else {alert('Selection Window Page is not configured'); }
      };

     	selectionWindow.addEventListener("load", () => {
     	// Trigger unit selection request on opening the receiver page
     	requestUnitSelection(); // Now send the message after the window loads
    	});
      
    });
    }
    
    const getWindowDimensions = () => {
    	  return {
    	    width: screen.width,
    	    height: screen.height
    	  };
    	};

    	const calculateWindowPosition = (windowDimensions, selectionWindowWidth, selectionWindowHeight) => {
    	  const top = Math.floor((windowDimensions.height - selectionWindowHeight) / 2);
    	  const left = Math.floor((windowDimensions.width - selectionWindowWidth) / 2);
    	  return {
    	    top,
    	    left
    	  };
    	};