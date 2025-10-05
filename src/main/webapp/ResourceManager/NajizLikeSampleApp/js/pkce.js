/**
 *  
 */
	const configPath = './moj-environments.json' ; 
	let selectedInfraCache = null;

	async function getSelectedInfra(configPath) {
	    // 2. Check the cache first
	    if (selectedInfraCache != null) {
	        console.log("Returning selectedInfra from cache.");
	        return selectedInfraCache;
	    }
	
	    // 3. If not in cache, perform the asynchronous fetch
	    try {
	        console.log("Fetching data and populating cache...");
	        const response = await fetch(configPath);
	
	        if (!response.ok) {
	            throw new Error(`HTTP error! status: ${response.status}`);
	        }
	
	        const data = await response.json();
	
	        // 4. Find the item and store it in the cache variable
	        const selectedInfra = data.Environments.find(infra => infra.selected === true);
	        selectedInfraCache = selectedInfra; // Store the result for future calls
	        
	        return selectedInfra;
	
	    } catch (error) {
	        console.error('Error fetching data:', error);
	        // Important: Propagate the error so the caller knows the operation failed
	        throw error;
	    }
	} 

  
	//---1-  For Building Authorization requerst ---- 
	function buildAuthReqWithPKCE( m_scope , m_anchorObjectId )
	{
		getSelectedInfra(configPath).then(infra => 
		{
    		buildAuthorizationLinkUsingPKCE(infra , m_scope , m_anchorObjectId ) ;
    	})
	  
	}

	async function buildAuthorizationLinkUsingPKCE(m_infra ,  m_scope ,  m_anchorObjectId )
	{
		var result ; 
		var code_verifier = generateCodeVerifier(); 
		var code_challange = await  generateCodeChallenge(code_verifier); 
		localStorage.setItem('code_verifier', code_verifier) ; 

		authorizationUrl = m_infra.mojServicesBaseUrl + "/" + m_infra.nafath.authorizationUrl  ; 
		clientId = m_infra.credential.clientId ; 
		var redirectUri = m_infra.nafath.redirectUri ; 
		var response_type = 'code' ; 
		var state = generateRandom(8) ; 
		var scope = m_scope ; //'openid' ;
		var nonce = generateRandom(8) ;  
		//https://api.cloudypedia.sa/oidc-core/oauth2/authorize?client_id=pGUORa3vPdXOZfQ9XfspYf7Shz4xEaaf&redirect_uri=yyyy&response_type=code&state=6r5yxu&scope=openid&nonce=06h2kn&force-authn=true&userId=null&code_challenge_method=S256&code_challenge=Ol-ccBIqBV_hpOmPr8xAGXFRGrW9gLVJGNyyNRuCWz8
		var result = authorizationUrl + '?client_id=' + clientId 
					+'&code_challenge='+code_challange 
					+'&redirect_uri='+redirectUri
					+'&response_type=' + response_type
					+'&state=' + state
					+'&nonce=' + nonce
					+'&scope=' + scope 
					+'&force-authn='+false
					+'&code_challenge_method=S256'; 
		// Get the anchor element by its ID
		const myLink = document.getElementById(m_anchorObjectId);
		// Update the href attribute
		myLink.href = result;
	}



	// --2- For Getting AccessToken End point -----------
	function getTokenWithPKCE(elementId)
	{
		getSelectedInfra(configPath).then(infra => 
			{
	    		printTokenUsingPKCE(infra , elementId ) ;
	    	}
    	)
	} 
	
	
	
	function printTokenUsingPKCE(infra , elementId )
	{
		const queryString = window.location.search;
		const params = new URLSearchParams(queryString);
		const code = params.get('code');
		//WARNING: For POST requests, body is set to null by browsers.
		var code_verifier =  localStorage.getItem('code_verifier') ; 
		var client_id = infra.credential.clientId ; 
		var redirect_uri = infra.nafath.redirectUri ; 
		var tokenUrl = infra.nafath.tokenWithPkceUrl ; 
		var data = "grant_type=authorization_code"
					+"&code="+code
					+"&redirect_uri="+redirect_uri
					+"&code_verifier="+code_verifier
					+"&client_id="+client_id;
		sendPostRequest( tokenUrl+"?client_id="+client_id , data , elementId ) 
	}		



function sendPostRequest( url , data , elementId )
{
	getSelectedInfra(configPath).then(infra => 
		{
    		mojServicesBaseUrl = infra.mojServicesBaseUrl ; 
			var xhr = new XMLHttpRequest();
			//xhr.withCredentials = true;
					
			xhr.open("POST", mojServicesBaseUrl+"/"+url);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send(data);
			xhr.onload = function() {
			  if (xhr.status === 200) {
			    localStorage.setItem('accessToken' , xhr.responseText) ;
			    var retAccessToken = localStorage.getItem('accessToken') ;  
			    console.log(JSON.parse(retAccessToken)); 
			    document.getElementById(elementId).innerHTML = retAccessToken ; 
			  } else {
				document.getElementById(elementId).innerHTML = "" ; 
				document.getElementById("error-container").innerHTML = xhr.responseText 
			    console.error(xhr.statusText);
			  }
			};
		
			xhr.onerror = function(error) {
			  console.error(error);
			};
    	}
    )
	
}

function sendGetRequest( url , elementId  )
{
	getSelectedInfra(configPath).then(infra => 
		{
			mojServicesBaseUrl = infra.mojServicesBaseUrl ; 
			var xhr = new XMLHttpRequest();
			//xhr.withCredentials = true;
					
			xhr.open("GET", mojServicesBaseUrl+"/"+url);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			var at = localStorage.getItem('accessToken')
			accessToken = JSON.parse(at) ; 
			xhr.setRequestHeader("Authorization" , "Bearer " + accessToken.access_token  ) ; 
			xhr.send();
			xhr.onload = function() {
			  if (xhr.status === 200) {
			    localStorage.setItem('response' , xhr.responseText) ;
			    document.getElementById(elementId).innerHTML = xhr.responseText ; 
			  } else {
				document.getElementById(elementId).innerHTML = "" ; 
				document.getElementById("error-container").innerHTML = xhr.responseText 
			  }
			};
		
			xhr.onerror = function(error) {
			document.getElementById("error-container").innerHTML = error ; 
			console.error(error);
			};
		}
	)
}

//--- Building logout Request
	function buildLogoutReq( m_anchorObjectId , userId , sessionIndex )
	{
		getSelectedInfra('config.json').then(m_infra => 
			{
	    		var result ; 
				logoutUrl = m_infra.nafath.logoutUrl  ; 
				clientId = m_infra.credential.clientId ; 
				var state = generateRandom(8) ; 
				var nonce = generateRandom(8) ;  
				var result = logoutUrl + '?client_id=' + clientId 
						+'&state=' + state
						+'&nonce=' + nonce 
						+'userId=' + userId
						+'sessionIndex=' +sessionIndex; 
				// Get the anchor element by its ID
				const myLink = document.getElementById(m_anchorObjectId);
				// Update the href attribute
				myLink.href = result;
	    	}
    	)
	}

 // Function to generate a random string for the code verifier
function generateCodeVerifier() {
  const charset = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~';
  const verifier = Array.from(crypto.getRandomValues(new Uint8Array(43)))
    .map((value) => charset[value % charset.length])
    .join('');
  return verifier;
}


// Function to generate the code challenge from the code verifier using SHA-256  
function generateCodeChallenge(codeVerifier) {
  // Hash the codeVerifier using SHA-256
  return base64url(CryptoJS.SHA256(codeVerifier))
}

function base64url(source) {
    var encodedSource = CryptoJS.enc.Base64
      .stringify(source)
      .replace(/=+$/, '')
      .replace(/\+/g, '-')
      .replace(/\//g, '_');
    return encodedSource;
}

function generateRandom(length) 
{
  const charset = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
  let nonce = '';

  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * charset.length);
    nonce += charset.charAt(randomIndex);
  }
  return nonce;
}


