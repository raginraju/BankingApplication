<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ncs.model.Customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registered customers</title>
<link rel="stylesheet" href="style.css">
</head>
<body>



	<nav class="navbar">
		<div class="container">
			<div class="logo">YOB</div>
			<ul class="nav">
				<li class="open"><a href="AdminViewRegiseredCustomers">View
						Registered Customers</a></li>
				<li><a href="AdminViewLoanApplications">View Loan
						Applications</a></li>
				<li><a href="Chart">Charts</a></li>
				<li><a href="AdminLogout">Logout</a></li>
			</ul>
		</div>
	</nav>

	<header class="header">
		<div class="container">
			<div>
				<h1>
					Dear <%out.println(session.getAttribute("susr"));%>, Below are the list registered customers:
				</h1>
				<table >
				<thead class="header-row">
					<tr>
						<th>SI NO</th>
						<th>Name</th>
						<th>Username</th>
						<th>Acc Bal</th>
						<th>Email</th>
						<th>Phone</th>
					</tr>
					</thead>
					<tbody class="body-row">
					<%
					ArrayList<Customer> customerlist = (ArrayList<Customer>) request.getAttribute("customerlist");
					int i = 0;
					for (Customer c : customerlist) {
						i++;
					%>
					<tr>
						<td><%=i%></td>
						<td><%=c.getName()%></td>
						<td><%=c.getUsr()%></td>
						<td><%=c.getAcc_bal()%></td>
						<td><%=c.getEmail()%></td>
						<td><%=c.getPhone()%></td>
					</tr>
					<%
					}
					%>
					</tbody>
				</table>

			</div>
		</div>
	</header>

</body>
</html>