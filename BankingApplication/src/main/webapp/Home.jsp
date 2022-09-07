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
			<li><a href = "CheckBalance1">Check Balance</a></li>
			<li><a href = "TransactionFilter.jsp">View Transactions</a></li>
			<li><a href = "TransferMoney_GetUserListToDisplay">Transfer Money</a></li>
			<li><a href = "LoanApplication.jsp">Apply for Loan</a></li>
			<li><a href = "ChangePassword.html">Change Password</a></li>
			<li><a href = "Logout">Logout</a> </li>
		</ul>
	</div>
</nav>

<header class="header">
	<div class= "container">
		<div>
 			<h1>Welcome <% out.println(session.getAttribute("sname"));  %> you have successfully logged in!!</h1>
		</div>
	</div>
</header>




</body>
</html>