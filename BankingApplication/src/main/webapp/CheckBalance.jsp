<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ncs.model.Loan"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Balance</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<nav class="navbar">
	<div class="container">
		<div class="logo">YOB</div>
		<ul class="nav">
			<li class="open"><a  href = "CheckBalance1">Check Balance</a></li>
			<li><a href = "TransactionFilter.jsp">View Transactions</a></li>
			<li><a href = "TransferMoney_GetUserListToDisplay">Transfer Money</a></li>
			<li><a href = "LoanApplication.jsp">Apply for Loan</a></li>
			<li><a href = "ChangePassword.html">Change Password</a></li>
			<li><a href = "Logout">Logout</a> </li>
		</ul>
	</div>
</nav>

<script  > 
		var loans=[];
</script>

<header class="header">
	<div class= "container">
		<div>
			<h1>Dear <% out.println(session.getAttribute("sname"));  %> , </h1>
			<h3>Account Balance:   $<% out.println(request.getAttribute("accBal")); %></h3>
			<hr>			
			<h3>Pending loans: </h3>
			
			<%
			ArrayList<Loan> approvedLoanList = (ArrayList<Loan>) request.getAttribute("approvedLoanList");
			if (approvedLoanList.isEmpty()==false) {%>
			<table>
					<thead class="header-row">
					<tr>
						<th>Loan ID</th>
						<th>Loan Amount</th>
						<th>Type</th>
					</tr>
					</thead>
			<%for (Loan l : approvedLoanList) {
			%>
				
					
					<tbody class="body-row"  >
					<tr>
						<td ><%=l.getLoanID()%></td>
						<td ><%=l.getLoan_amount()%></td>
						<td ><%=l.getType()%></td>
				<script >
				loans.push("<%out.print(l.getLoanID());%>");
				loans.push("<%out.print(l.getLoan_amount());%>");
				</script>	
					<%
					}
					%>
					
							
					</tr>
					</tbody>
				</table >
				
			<h3>Pay back your loans: </h3>
				<form  action="/BankingApplication/RepayLoan">			
					<select class="loan-form"  id="loanID" name="loanID">
					<%
					ArrayList<Loan> approvedLoanList1 = (ArrayList<Loan>) request.getAttribute("approvedLoanList");
					for (Loan l : approvedLoanList1) {
					%>
					
							<option   value="<%=l.getLoanID() %>"> <%=l.getLoanID() %></option>
					<%
					}
					%>
					</select>		
					<input class="loan-form" type="text" id="repayAmt" class="size" placeholder="$"  name="repayAmt" >
					<input id="submit" class="red-back loan-form" type="submit" value="Pay Back">
				</form>	
				
			<%} else { %>
						<center><h3>None</h3></center>
						
						<%
						} 
						%>	
				
				<script>
					var accBal =    "<% out.print(request.getAttribute("accBal")); %>"; 
					
         			var parsed_accBal = parseInt(accBal, 10); //convert to integer value
					//console.log("loans");
					//console.log(loans);
					
       				document.getElementById("submit").addEventListener("click", function(e){
       					// repay amount
       					var amount = document.getElementById("repayAmt");
       					var amount_value=amount.value.trim(); //remove white space
    					var repay_val = parseInt(amount_value, 10); //convert to integer value
    					
    					
    					//loan ID
    					var loanID = document.getElementById("loanID");
    					var loanID_value=loanID.value.trim();// remove white space 
    					var indexOfID = loans.indexOf(loanID_value);//get the corresponding loan amount
    					var loanAmt = parseInt(loans.at(indexOfID+1), 10);//convert to integer value
    					
         		 	if(isNaN(repay_val) || repay_val>parsed_accBal){
         		 		
          		 	 if(isNaN(repay_val)){
          		 		alert( "Please enter a numerical value for amount" ); 
           		 		 }
          		 	 else{
          		 		alert( "insufficient balance" );
          		 		 }
          		 	e.preventDefault();
          		 	 return;
         		   	}
         		 	
					
         		 	
         		 	if( repay_val>loanAmt){
         		 		alert("Entered amount exceeds loan amount; Please enter lower value");
         		 		e.preventDefault();
         		 	}
         		 	
         		 	if( repay_val<=0){
         		 		alert("Please enter a value greater than zero");
         		 		e.preventDefault();
         		 	}
  
       			 });
   			</script>
				
			<hr>
		</div>
	</div>
</header>


</body>
</html>