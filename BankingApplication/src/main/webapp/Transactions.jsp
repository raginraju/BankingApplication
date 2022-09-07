<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ncs.model.Transaction"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

	<nav class="navbar">
		<div class="container">
			<div class="logo">YOB</div>
			<ul class="nav">
				<li><a href="CheckBalance1">Check Balance</a></li>
				<li class="open"><a href="TransactionFilter.jsp">View
						Transactions</a></li>
				<li><a href="TransferMoney_GetUserListToDisplay">Transfer
						Money</a></li>
				<li><a href="LoanApplication.jsp">Apply for Loan</a></li>
				<li><a href="ChangePassword.html">Change Password</a></li>
				<li><a href="Logout">Logout</a></li>
			</ul>
		</div>
	</nav>

	<header class="header">
		<div class="container">
			<div>
					
				

				<%
				ArrayList<Transaction> transactionList = (ArrayList<Transaction>) request.getAttribute("transactionList");
				int i = 0;
				if (transactionList.isEmpty()==false) {%>
				<h1>
					Dear
					<%out.println(session.getAttribute("sname"));%>, Below are your
					transaction details
				</h1>
					
				<table>
					<thead>
						<tr class="header-row">
							<th>SI NO</th>
							<th>Date</th>
							<th>Amount</th>
							<th>Type</th>
							<th>From/To</th>
						</tr>
					</thead>
					<%for (Transaction t : transactionList) {
						i++;
				%>
					
				
					<tbody>
						<tr class="body-row">
							<td><%=i%></td>
							<td><%=t.getDate()%></td>
							<td><%=t.getAmount()%></td>
							<td><%=t.getType()%></td>
							<td><%=t.getFrom_to()%></td>
						</tr>
					</tbody>					
				
						<%
						} 
						%>
		</table>
				<%} else { %>
						<h2>No Transactions available</h2>
						
						<%
						} 
						%>
					
				
			</div>
		</div>
	</header>

</body>
</html>