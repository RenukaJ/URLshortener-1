<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>

<%
    @SuppressWarnings("unchecked")
    Map<String, String> urlMapping =
            (Map<String, String>)request.getAttribute("links");

%>

<%
    @SuppressWarnings("unchecked")
    Map<String, Integer> urlCount =
            (Map<String, Integer>)request.getAttribute("linksCount");

%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>URL Shortner</title>

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
        <li><a href="<c:url value="/userprofile"><c:param name="action" value="logout" /></c:url>" >Logout</a></li>
        
        <li><h3>${username}</h3></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<h1> Welcome ${username}</h1>

<div class="jumbotron">
  <div class="col-xs-3"></div>
  <div class="col-xs-6">
  <form method="POST" action="<c:url value="/userprofile"><c:param name="action" value="shortenURL" /></c:url>">
  <div class="input-group">
  <input type="text" class="form-control" placeholder="URL" aria-describedby="basic-addon2" name="longUrl">
  <span class="input-group-addon" id="basic-addon"><input type="submit" value="Submit" /></span>
  </div>
  </form>
  </div>
  <div class="col-xs-3"></div>
</div>
<%if(urlMapping != null){ %>
<% Iterator it = urlMapping.entrySet().iterator(); %>
<% while (it.hasNext()) { %>
      <% Map.Entry pair = (Map.Entry)it.next(); %>
      <% String shortUrl = (String)pair.getKey(); %>
      <% String longUrl = (String)pair.getValue(); %>
      <h4>Main URL:<%=longUrl %> </h4>
      <a href="<c:url value="/short/*">
        <c:param name="action" value="gotoUrl"/>
        <c:param name="url" value="<%=shortUrl%>"/>
        </c:url>"><%= shortUrl%></a>
      <h5>Count: <%=urlCount.get(shortUrl) %></h5>
    <%} %>
    <%} %>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>

