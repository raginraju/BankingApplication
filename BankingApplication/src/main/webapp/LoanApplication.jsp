<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apply for loan</title>
<link rel="stylesheet" href="style.css">
</head>
<body>


	<nav class="navbar">
		<div class="container">
			<div class="logo">YOB</div>
			<ul class="nav">
				<li><a href="CheckBalance1">Check Balance</a></li>
				<li><a href="TransactionFilter.jsp">View Transactions</a></li>
				<li><a href="TransferMoney_GetUserListToDisplay">Transfer
						Money</a></li>
				<li class="open"><a href="LoanApplication.jsp">Apply for
						Loan</a></li>
				<li><a href="ChangePassword.html">Change Password</a></li>
				<li><a href="Logout">Logout</a></li>
			</ul>
		</div>
	</nav>



	<header class="header">
		<div class="container">
			<div>
				<h1>
					Dear <% out.println(session.getAttribute("sname"));%>  Please enter the below details
				</h1>
				<form action="/BankingApplication/LoanApplication">
					<table>
						<tr>
							<td>Loan Amount ($)</td>
							<td><input id="amount" type="text" name="amount" required></td>
						</tr>
						<tr>
							<td>Loan Type</td>
<!-- 							<td><input type="text" name="type" required></td> -->
							<td class="drop-down"><select class="drop-down"  id="t" name="type">
									<option value="Personal">Personal</option>
									<option value="Student">Student</option>
									<option value="Vehicle">Vehicle</option>
									<option value="housing">housing</option>
							</select></td>
						</tr>
					</table>
					<input id="submit" class="button" type="submit" value="Apply Now">
					
					<script>
       				var amount = document.getElementById("amount");
       				
       				document.getElementById("submit").addEventListener("click", function(e){
         			var amount_value=amount.value.trim();
         			
         		 	
         		 	if(isNaN(amount_value)){
         				 alert( "Please enter a numerical value for amount" );
          		 	 	e.preventDefault();
         		   	}
         		 	if(amount_value<=0){
        				 alert( "Please enter an amount greater than zero" );
         		 	 	e.preventDefault();
        		   	}
  
       			 });
   				 </script>
				</form>
			</div>
		</div>
	</header>




</body>
</html>