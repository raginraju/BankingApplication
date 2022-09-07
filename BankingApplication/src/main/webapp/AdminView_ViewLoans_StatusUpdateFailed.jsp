<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ncs.model.Loan"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Applications status udpate failure</title>
<link rel="stylesheet" href="style.css">
<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js"
		integrity="sha512-sW/w8s4RWTdFFSduOTGtk4isV1+190E/GghVffMA9XczdJ2MDzSzLEubKAs5h0wzgSJOQTRYyaz73L3d6RtJSg=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
			<h1>Dear <%out.println(session.getAttribute("susr"));%> status update failed due to invalid input; Please try again</h1>
			
		</div> 
	</div>
</header>

</body>
</html>