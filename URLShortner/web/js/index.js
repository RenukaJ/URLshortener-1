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
			longToShort: {
				inputHandler: "",
					processButton: "",
			},
			showShortUrls:{

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
				
			}

	};
	
	
	var longUrl = $(uSh_handler.jumbotron.longUrlVal).text();
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

}

   

$(document).ready(main);