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
    
   <nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" data-toggle="modal" data-target=".login-modal" >Login</a></li>
        <li><a href="#" data-toggle="modal" data-target=".signup-modal" >SignUp</a>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


<div class="modal fade login-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <h1>Login</h1>
	<form method="POST" action="<c:url value="/login" />">
		Username<br /> <input type="text" name="username" /><br />
		<br /> Password<br /> <input type="password" name="password" /><br />
		<br /> <input type="submit" value="Log In" />
	</form>
    </div>
  </div>
</div>


<div class="modal fade signup-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <h1>Login</h1>
	<form method="POST" action="<c:url value="/signup" />">
		Username<br /> <input type="text" name="new_username" /><br />
		<br /> Password<br /> <input type="password" name="new_password" /><br />
		<br /> <input type="submit" value="Sign Up" />
	</form>
    </div>
  </div>
</div>


<div class="modal fade signup-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <h1>Signup</h1>
    </div>
  </div>
</div>


<div class="jumbotron">
  
  <div class="input-group">
  <input type="text" class="form-control" placeholder="Shortened URL" aria-describedby="basic-addon2">
  <span class="input-group-addon" id="basic-addon">URL</span>
  </div>
  
</div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>

