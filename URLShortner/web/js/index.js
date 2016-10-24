/**
 * 
 */
var main = function () {

	var uSh_handler = {
			landpage:{
				navbar:{
					logout: "#uSh_logout",
					signup: "#uSh_Signup",
					login: "#uSh_login",
					brand: "#uSh_brand",
					username: "#uSh_username"
				}
			},
			loginCard: {
				username: "",
				password: "",
				loginButton:"",
					errorMessage: ""
			},
			signupCard:{
				username:"",
					password: "",
						signupButton:"",
							errorMessage:""
			},
			jumbotron_longToShort: {
				inputHandler: "#uSh_LongtoShjumbotron",
					processButton: "#us_convertlongToShort",
					inputbox: "#uSh_getLongUrl",
						errorLToS: "#errorLToS"
			},
			jumbotron:{
				handle: "#uSh_jumbotron",
				longUrlSubmit: "#us_convertShortToLong",
				longUrlVal: "#uSh_longUrlVal"
			},
			showLongUrlCard:{
				handle: "#uSh_showlongUrlModal",
				urlSuccessDiv: "#getLongUrlSuccess",
				urlFailDiv: "#getLongUrlFail",
				setUrlVal: "#uSh_setlongVal"
				
			},
			userPageUrlList:{
				handle: "#uSh_userUrlList",
				longUrl: "#uSh_userLongUrl",
				shortUrl: "#uSh_userShorturl",
				copyButton: "#uSh_copy",
				deleteButton: "#uSh_Delete"
			},
			errorModal:{
				handle: "#errorModal"
			}
			

	};
	
	
	var longUrl = $(uSh_handler.jumbotron_longToShort.inputbox).text();
	console.log(longUrl);
	if(longUrl === "" || longUrl === "null"){
		
	}
	else{
		if(longUrl === "undefined"){
		$(uSh_handler.showLongUrlCard.urlSuccessDiv).hide();
		$(uSh_handler.showLongUrlCard.urlFailDiv).show();
		$(uSh_handler.showLongUrlCard.handle).modal("show");
	}
		else{
			$(uSh_handler.showLongUrlCard.urlSuccessDiv).show();
			$(uSh_handler.showLongUrlCard.urlFailDiv).hide();
			
			
			
			$(uSh_handler.showLongUrlCard.setUrlVal).text(longUrl);
			$(uSh_handler.showLongUrlCard.setUrlVal).attr("href", longUrl);
			$(uSh_handler.showLongUrlCard.handle).modal("show");
		}
	}
	
	var errorLToS = $(uSh_handler.jumbotron.errorLToS).text();
	console.log(errorLToS);
	
	
	$(uSh_handler.jumbotron_longToShort.processButton).click(function(){
		
		var val = $(uSh_handler.jumbotron_longToShort.inputbox).val();
		if (val.indexOf('http://') === -1 && val.indexOf('https://') === -1) {
		    val = 'http://' + val;
		}
		
		 $.ajax({
	         url: "userprofile",
	         data: {"action":"shortenURL","longUrl":val},
	         method: "POST",
	         success: function(){
	             location.reload();           
	         },
	         error: function(){
	        	 console.log("error sending")
	         }
	     });
	});

}

function copyToClipboard(element) {

	var textarea = document.createElement("textarea");
	textarea.textContent = element;
	textarea.style.position = "fixed"; // Prevent scrolling to bottom of page in MS Edge.
	document.body.appendChild(textarea);
	textarea.select();
	try {
		document.execCommand("copy"); // Security exception may be thrown by some browsers.
	} catch (ex) {
		console.warn("Copy to clipboard failed.", ex);
	} finally {
		alert("Copied ShortUrl to clipboard:" + element);
		document.body.removeChild(textarea);
	}
}

function urldelete(element){
	 $.ajax({
         url: "userprofile",
         data: {"action":"deleteUrl","urlToRemove":element},
         method: "POST",
         success: function(){
             location.reload();           
         },
         error: function(){
        	 console.log("error sending")
         }
     });
}
 


$(document).ready(main);