<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction filter</title>
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
				<h1>
					Dear <% out.println(session.getAttribute("sname"));%> , Please enter the following details to view the transactions
				</h1>
				
				<form action="/BankingApplication/TransactionList">
					<table>
						<tr>
							<td>From</td>
							<td><input id="from" type="date" name="from" ></td>
						</tr>
						<tr>
							<td>To</td>
							<td><input id="to" type="date" name="to"></td>
						</tr>
						<tr>
							<td>Choose Type</td>
							<td class="drop-down"><select class="drop-down"  id="t" name="type">
									<option value="All">All</option>
									<option value="Credit">Credit</option>
									<option value="Debit">Debit</option>
							</select></td>
						</tr>
					</table>
					<input class="button"  id="submit" type="submit" value="Get Results">
					<p id="demo"></p>
					<script>
					  var from = document.querySelector('#from');
					  var to = document.querySelector('#to');
					  var today = new Date();
					  document.getElementById("submit").addEventListener("click", function(e){
		         			var from_value=from.value.trim();
		         			var to_value =to.value.trim();
//		          			trimming entered values to remove any spaces		         			
		 		 			
		 		 			//convert as date objects
		 		 			const f = new Date(from_value);
		 		 			const t = new Date(to_value);

		 		 			if(f>t || f>today || t>today){
		 		 				e.preventDefault();
		 		 				alert("please enter valid \"from\" or \"to\" range");
		 		 			}
		 		 			
		        
		       			 });
					
   				 </script>
					
				</form>
			</div>
		</div>
	</header>


</body>
</html>