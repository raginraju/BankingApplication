<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ncs.model.Loan"%>
<%@page import="com.ncs.model.Customer"%>
<%@page import="com.ncs.model.Transaction"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Charts</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<a id="top"></a>
	<nav class="navbar">
		<div class="container">
			<div class="logo">YOB</div>
			<ul class="nav">
				<li><a href="AdminViewRegiseredCustomers">View Registered
						Customers</a></li>
				<li ><a href="AdminViewLoanApplications">View
						Loan Applications</a></li>
				<li class="open"><a href="Chart">Charts</a></li>
				<li><a href="AdminLogout">Logout</a></li>	
			</ul>
		</div>
	</nav>
	
	<script  > 
		var typesofloans=[];
		var loanApplicants=[];
		var customerList=[];
		var accBal=[];
		var date=[];
		var amount=[];
	</script>
<!-- 	1. loan List -->
	<%ArrayList<Loan> loanList = (ArrayList<Loan>) request.getAttribute("loanlist");
		int i = 0;
		for (Loan l : loanList) {
		i++;	
	%>
	<script >
		typesofloans.push("<%out.print(l.getType());%>");
		loanApplicants.push("<%out.print(l.getUsr());%>");
	</script>
	<%}%>
	
<!-- 	2. customer list -->
	<%ArrayList<Customer> customerlist = (ArrayList<Customer>) request.getAttribute("customerlist");
		for (Customer c : customerlist) {
	%>

	<script >
		customerList.push("<%out.print(c.getUsr());%>");
		accBal.push("<%out.print(c.getAcc_bal());%>");
	</script>
	<%}%>
	
<!-- 	3. customer list -->
	<%ArrayList<Transaction> allDebitedTransactionsList = (ArrayList<Transaction>) request.getAttribute("allDebitedTransactionsList");
		for (Transaction T : allDebitedTransactionsList) {
	%>

	<script >
		date.push("<%out.print(T.getDate());%>");
		amount.push("<%out.print(T.getAmount());%>");
	</script>
	<%}%>

	<header class="header">
		<div class="container">
			<div class="chart">
				<canvas class ="back-color" id=LoanDistribution></canvas>
				<canvas class ="back-color"  id=LoanApplicants></canvas>
				<canvas class ="back-color"  id=AccountBalance></canvas>
				<canvas class ="back-color"  id=allDebitedTransactionsList></canvas>
				<a  class="button1" href="#top">back to top</a>
			</div>
			
		</div>
	</header>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
<script src="./View/chart.js">
 </script>

	
</body>
</html>