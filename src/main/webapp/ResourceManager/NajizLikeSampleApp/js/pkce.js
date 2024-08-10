/**
 *  
 */
	const configPath = './moj-environments.json' ; 
  
	//---1-  For Building Authorization requerst ---- 
	function buildAuthReqWithPKCE(infraIndex ,  m_scope , m_anchorObjectId )
	{
		fetch(configPath) // Replace 'data.json' with your actual file name and path
		.then(response => response.json()) // Parse the response as JSON
		.then(data => {
		  // Access and use the data object here
		  buildAuthorizationLinkUsingPKCE(data.Environments[infraIndex] , m_scope , m_anchorObjectId ) ;
		})
		.catch(error => {
		  console.error('Error fetching data:', error);
		});
	}

	async function buildAuthorizationLinkUsingPKCE(m_infra ,  m_scope ,  m_anchorObjectId )
	{
		var result ; 
		var code_verifier = generateCodeVerifier(); 
		var code_challange = await  generateCodeChallenge(code_verifier); 
		localStorage.setItem('code_verifier', code_verifier) ; 

		authorizationUrl = m_infra.nafath.authorizationUrl  ; 
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
	function getTokenWithPKCE(m_infraIndex , elementId)
	{
		fetch(configPath) // Replace 'data.json' with your actual file name and path
		.then(response => response.json()) // Parse the response as JSON
		.then(data => {
		  // Access and use the data object here
		  console.log(data);
		  // You can further process the data and display it on the page
		  printTokenUsingPKCE(data.Environments[m_infraIndex] , elementId) ;
		})
		.catch(error => {
		  console.error('Error fetching data:', error);
		});
	} ; 
	
	
	
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
	var xhr = new XMLHttpRequest();
		//xhr.withCredentials = true;
				
		xhr.open("POST", url);
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

function sendGetRequest( url , elementId  )
{
	var xhr = new XMLHttpRequest();
		//xhr.withCredentials = true;
				
		xhr.open("GET", url);
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



//--- Building logout Request
	function buildLogoutReq(infraIndex ,   m_anchorObjectId , userId , sessionIndex )
	{
		fetch(configPath) // Replace 'data.json' with your actual file name and path
		.then(response => response.json()) // Parse the response as JSON
		.then(data => {
		var result ; 
		m_infra = data.Environments[infraIndex] 
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
		})
		.catch(error => {
		  console.error('Error fetching data:', error);
		});
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

