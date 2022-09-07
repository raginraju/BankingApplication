<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ncs.model.Loan"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Application status update success</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<nav class="navbar">
	<div class="container">
		<div class="logo">YOB</div>
		<ul class="nav">
			<li><a href = "AdminViewRegiseredCustomers">View Registered Customers</a></li>
			<li class="open"><a href = "AdminViewLoanApplications">View Loan Applications</a></li>
			<li><a href="Chart">Charts</a></li>
			<li><a href = "AdminLogout">Logout</a></li>
		</ul>
	</div>
</nav>

<header class="header">
	<div class= "container">
		<div>
			<h1>Dear <%out.println(session.getAttribute("susr"));%>Status update Success!!</h1>
			
		</div>
	</div>
</header>


</body>
</html>