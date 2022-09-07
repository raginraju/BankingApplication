<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ncs.model.Loan"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Applications</title>
<link rel="stylesheet" href="style.css">

</head>
<body >

	<nav class="navbar">
		<div class="container">
			<div class="logo">YOB</div>
			<ul class="nav">
				<li><a href="AdminViewRegiseredCustomers">View Registered
						Customers</a></li>
				<li class="open"><a href="AdminViewLoanApplications">View
						Loan Applications</a></li>
				<li><a href="Chart">Charts</a></li>
				<li><a href="AdminLogout">Logout</a></li>	
			</ul>
		</div>
	</nav>
	
	

	<header class="header">
		<div class="container">
				<h1>Dear <%out.println(session.getAttribute("susr"));%>, Below are the Loan Applications:
				</h1>
				<div class="inner-tables">
				
				<div class="twosides">
				<div class="approved">
				 <p >Approved Loans</p>
				<table>
				<thead class="header-row">
					<tr>
						<th>SI NO</th>
						<th>Username</th>
						<th>loan_amount</th>
						<th>type</th>
<!-- 						<th>status</th> -->
					</tr>
					</thead>
					
					<tbody class="body-row"  >
					<%
					ArrayList<Loan> approvedLoanList = (ArrayList<Loan>) request.getAttribute("approvedLoanList");
					int i = 0;
					for (Loan l : approvedLoanList) {
						i++;
					%>
					<tr>
						<td ><%=i%></td>
						<td ><%=l.getUsr()%></td>
						<td ><%=l.getLoan_amount()%></td>
						<td ><%=l.getType()%></td>
<%-- 						<td ><%=l.getStatus()%></td> --%>
						 
					</tr>
					<%
					}
					%>
					</tbody>
				</table >
				</div>
				</div>	
				
				<div class="twosides">
				<div class="rejected">
				<p >Rejected Loans</p>
				<table  >
				<thead class="header-row">
					<tr>
						<th>SI NO</th>
						<th>Username</th>
						<th>loan_amount</th>
						<th>type</th>
<!-- 						<th>status</th> -->
					</tr>
					</thead>
					
					<tbody class="body-row" >
					<%
					ArrayList<Loan> rejectedLoanList = (ArrayList<Loan>) request.getAttribute("rejectedLoanList");
					int j = 0;
					for (Loan l : rejectedLoanList) {
						j++;
					%>
					<tr>
						<td ><%=j%></td>
						<td ><%=l.getUsr()%></td>
						<td ><%=l.getLoan_amount()%></td>
						<td ><%=l.getType()%></td>
<%-- 						<td><%=l.getStatus()%></td> --%>
						 
					</tr>
					<%
					}
					%>
					</tbody>
				</table  >
				</div>
					</div>
				</div>	
			</div>
	</header>
	


	
</body>
</html>