
<!doctype html>
<html lang="pl">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Smart Door Card</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/sign-in/">

    <!-- Bootstrap core CSS -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


    <style>
		    html,
		body {
		  height: 100%;
		}
		
		body {
		  display: -ms-flexbox;
		  display: flex;
		  -ms-flex-align: center;
		  align-items: center;
		  padding-top: 40px;
		  padding-bottom: 40px;
		  background-color: #f5f5f5;
		}
		
		.logo-text{
		margin-bottom: 20px;
		}
		
		.form-signin {
		  width: 100%;
		  max-width: 330px;
		  padding: 15px;
		  margin: auto;
		}
		.form-signin .checkbox {
		  font-weight: 400;
		}
		.form-signin .form-control {
		  position: relative;
		  box-sizing: border-box;
		  height: auto;
		  padding: 10px;
		  font-size: 16px;
		}
		.form-signin .form-control:focus {
		  z-index: 2;
		}
		.top{
		  margin-bottom: -1px;
		  margin-bottom: 30px;
		  
		}
		
		.middle1{
			margin-bottom: -1px;
		  	border-bottom-right-radius: 0;
		  	border-bottom-left-radius: 0;
		}
		.middle2{
			border-radius: 0;
			margin-bottom: -1px;
		}
		
		.bottom{
		  margin-bottom: 10px;
		  border-top-left-radius: 0;
		  border-top-right-radius: 0;
		  
		}
		
		.set-button{
		margin-top: 30px; 
		}
		
		.logo-footer{
		margin-top: 10px;
		}
		
	      .bd-placeholder-img {
	        font-size: 1.125rem;
	        text-anchor: middle;
	        -webkit-user-select: none;
	        -moz-user-select: none;
	        -ms-user-select: none;
	        user-select: none;
	      }

	      @media (min-width: 768px) {
	        .bd-placeholder-img-lg {
	          font-size: 3.5rem;
	        }
      	}
    	</style>
    	
    <!-- Custom styles for this template -->
  </head>
  <body class="text-center">
  
 <form action="/" method="POST" class="form-signin" enctype="application/x-www-form-urlencoded" autocomplete="off">
  <i class="fas fa-pen-square fa-7x"></i>
  <h1 class="h3 mb-3 font-weight-normal logo-text">Smart Door Card</h1>
  
  <input type="text" name="name" class="form-control top" placeholder="Name" required autofocus>
  <input type="text" name="status1" class="form-control middle1" placeholder="Set Status">
  <input type="text" name="status2" class="form-control middle2" placeholder="Set Status">
  <input type="text" name="status3" class="form-control bottom" placeholder="Set Status">
  
  <button class="btn btn-lg btn-primary btn-block set-button" type="submit">Set Your Card</button>
  <p class="mt-5 mb-3 text-muted logo-footer">&copy; The Smart Door Card</p>
</form>
</body>
</html>