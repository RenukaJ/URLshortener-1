<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

  </head>
  <body>
    <!--  Navbar -->
    
  <nav class="navbar navbar-inverse navbar-fixed-top" id = "uSh_navbar">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#" id="uSh_brand">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" data-toggle="modal" data-target=".login-modal" id="uSh_login" >Login</a></li>
        <li><a href="#" data-toggle="modal" data-target=".signup-modal" id="uSh_Signup" >SignUp</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>



 <div aria-labelledby="myModalLabel" class="modal fade login-modal" id="uSh_loginCard" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button aria-label="Close" class="close" data-dismiss="modal" type="button">
            <span aria-hidden="true">&times;</span>
          </button>
          <h4 class="modal-title" id="myModalLabel">Login</h4>
        </div>
        <div class="modal-body">
          <form method="POST" action="<c:url value="/login"><c:param name="action" value="login" /></c:url>">
            <div class="form-group">
              <label class="control-label" for="uSh_loginCard-loginId">Username:</label>
              <input name="username" class="form-control" id="uSh_loginCard-loginId" type="text">
            </div>
            <div class="form-group">
              <label class="control-label" for="uSh_loginCard-loginPassword">Password:</label>
              <input name="password" class="form-control" id="uSh_loginCard-loginPassword" type="password">
            </div>
            <button type="submit" class="btn btn-primary uSh_loginAction" type="button">Sign In</button>
          </form>
        </div>
        <div class="modal-footer">
          <div>
            <p id="pt_loginCard-errorMessage"></p>
          </div>
        </div>
      </div>
    </div>
  </div>

 <div aria-labelledby="myModalLabel" class="modal fade signup-modal" id="uSh_signupCard" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button aria-label="Close" class="close" data-dismiss="modal" type="button">
            <span aria-hidden="true">&times;</span>
          </button>
          <h4 class="modal-title" id="myModalLabel">I want to join</h4>
        </div>
        <div class="modal-body">
          <form method="POST" action="<c:url value="/login"><c:param name="action" value="signup" /></c:url>">
            <div class="form-group">
              <label class="control-label" for="uSh_signupCard-loginId">Username:</label>
              <input name="new_username" class="form-control" id="uSh_signupCard-loginId" type="text">
            </div>
            <div class="form-group">
              <label class="control-label" for="uSh_signupCard-password">Password:</label>
              <input name="new_password" class="form-control" id="uSh_signupCard-password" type="password">
            </div>
            <button type="submit" class="btn btn-primary uSh_signupAction" type="button">Sign Me Up !</button>
          </form>
        </div>
        <div class="modal-footer">
          <div>
            <p id="pt_signupCard-errorMessage"></p>
          </div>
        </div>
      </div>
    </div>
  </div>
<!--  
<div class="modal fade signup-modal" tabindex="-1" role="dialog" aria-labelledby="mySignupModalLabel">
  <div class="modal-dialog modal-md" role="document">
    <div class="modal-content">
      <h1>SignUp</h1>
	<form method="POST" action="<c:url value="/login"><c:param name="action" value="signup" /></c:url>">
		Username<br /> <input type="text" name="new_username" /><br />
		<br /> Password<br /> <input type="password" name="new_password" /><br />
		<br /> <input type="submit" value="Sign Up" />
	</form>
    </div>
  </div>
</div>
-->


<div class="jumbotron">
  <div class="col-xs-3"></div>
  <div class="col-xs-6">
  
  
  <form method="POST" action="<c:url value="/userprofile"><c:param name="action" value="shortenURL" /></c:url>">
  <div class="input-group">
  <input type="text" class="form-control" placeholder="URL" name="shortUrl">
  <span class="input-group-addon" id="us_convertShortToLong"><input type="submit" value="Get Long URL" /></span>
  </div>
  </form>

  
  </div>
  <div class="col-xs-3"></div>
</div>





    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/index.js"></script>
  </body>
</html>

