<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Success!!</title>
<link rel="stylesheet" href="style.css">
</head>
<body>


<nav class="navbar">
	<div class="container">
		<div class="logo">YOB</div>
		<ul class="nav">
			<li><a href = "AdminViewRegiseredCustomers">View Registered Customers</a></li>
			<li><a href = "AdminViewLoanApplications">View Loan Applications</a></li>
			<li><a href="Chart">Charts</a></li>
			<li><a href = "AdminLogout">Logout</a></li>
		</ul>
	</div>
</nav>

<header class="header">
	<div class= "container">
		<div>
			<h1>Welcome <% out.println(session.getAttribute("susr"));  %> you have successfully logged in!!</h1>
			
		</div>
	</div>
</header>

</body>
</html>