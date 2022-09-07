<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ncs.model.Loan"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer money</title>
<link rel="stylesheet" href="style.css">


</head>
<body>



	<nav class="navbar">
		<div class="container">
			<div class="logo">YOB</div>
			<ul class="nav">
				<li><a href="CheckBalance1">Check Balance</a></li>
				<li><a href="TransactionFilter.jsp">View Transactions</a></li>
				<li class="open"><a href="TransferMoney_GetUserListToDisplay">Transfer
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
					Dear
					<%out.println(session.getAttribute("sname"));%>;  Below are the Usernames
				</h1>
			</div>
			<div>
				<table>
					<thead>
					<tr class="header-row">
						<th>SI NO</th>
						<th>Usernames</th>
					</tr>
					</thead>
					<tbody>	
					<%
					ArrayList<String> usernameList = (ArrayList<String>) request.getAttribute("usernameList");
					int i = 0;
					for (String s : usernameList) {
						i++;
					%>
					<tr class="body-row">
						<td><%=i%></td>
						<td><%=s%></td>
					</tr>
					<%
					}
					%>
					</tbody>
				</table>
			</div>
			<div>
				<form  id="form" action="/BankingApplication/TransferMoney">
				<div id="message"></div>
					<table>
						<tr>
							<td>Please enter the SI NO from the above list</td>
							<td><input  id="payee" type="text" name="payee" required></td>
						</tr>
						<tr>
							<td >Please enter the amount</td>
							<td><input id="amount" type="text" name= "amount" placeholder="$" required></td>
						</tr>


					</table>
					<input  id="submit" class="button" type="submit" value="Transfer Now">
				
				<script>
       				var amount = document.getElementById("amount");
       				var payee = document.getElementById("payee");
       				var maxrows =    "<% out.print(i);%>"; 
//        				gettting maximum rows from scripplet
       				
       				document.getElementById("submit").addEventListener("click", function(e){
         			
//          			trimming entered values to remove any spaces
       				var amount_value=amount.value.trim();
         			var payee_SI =payee.value.trim();

//						pasring string values to int
         			var parsed_maxrows = parseInt(maxrows, 10);
         			var parsed_SI = parseInt(payee_SI, 10);
	 			
         		 	if(parsed_SI> parsed_maxrows || isNaN(parsed_SI)|| (parsed_SI<=0) ||isNaN(amount_value) || (amount_value<=0) ){
         		 		     		 		
         		 		if(isNaN(amount_value) || (amount_value<=0)) {
         		 			alert( "Please enter a valid amount for transfer " ); 
         		 		}

         		 		else{
         		 			alert( "Please enter a valid Serial number " ); 
         		 		}
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