package com.ncs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Model {
	//****Customer table******
	private String name;
	private String usr;
	private String pwd;
	private int phone;
	private String email;
	private float acc_bal;
	
	//****Loan table******
	private int loan_amount;
	private String type;
	private String status;
	
	//****Transaction table******
	

	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet res = null;
	static float default_amt = 500f;
	static String default_status = "Pending Approval";
	
	public Model() {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			System.out.println("Driver loaded succesfully");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/banking_application?useSSL=false&createDatabaseIfNotExist=true",
					"root", "raginraju");
			System.out.println("Connection established successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getAcc_bal() {
		return acc_bal;
	}

	public void setAcc_bal(float acc_bal) {
		this.acc_bal = acc_bal;
	}
	
	public int getLoan_amount() {
		return loan_amount;
	}

	public void setLoan_amount(int loan_amount) {
		this.loan_amount = loan_amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	public ArrayList<String> getList(String columName) {
		ArrayList<String> nameList = new ArrayList<String>();
		try {
			String s = "select username from customer";
			if(columName.equals("email")) {
				s = "select email from customer";
			}
			pstmt = con.prepareStatement(s);
			res = pstmt.executeQuery();
			System.out.println(" select Query executed successfully");

			while (res.next()) {
				nameList.add((String) res.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return nameList;
	}
	
	public String usernameMatchEmail() {
		try {
			String s = "select email from customer where username=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, usr);
			res = pstmt.executeQuery();
			System.out.println(" select email Query executed successfully");

			if (res.next()) {
				System.out.println(usr);
				System.out.println(res.getString(1));
				return res.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "none";
	}
	
	public int register() {
		try {
			String s = "insert into customer values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(s);

			pstmt.setString(1, name);
			pstmt.setString(2, usr);
			pstmt.setString(3, pwd);
			pstmt.setInt(4, phone);
			pstmt.setString(5, email);
			pstmt.setFloat(6, default_amt); // amount set to default amount for minimum balance while registration

			int rows = pstmt.executeUpdate();
			System.out.println(" Register Query executed successfully");

			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int login() {
		try {

			String s = "select * from customer where username=?";
			pstmt = con.prepareStatement(s);

			pstmt.setString(1, usr);
			res = pstmt.executeQuery();
			System.out.println(" Login Query executed successfully");

			if (res.next()) {
				if (pwd.equals(res.getString(3))) {
					// for the session
					name = res.getString(1);
					usr = res.getString(2);
					pwd = res.getString(3);
					phone = res.getInt(4);
					email = res.getString(5);
					acc_bal = res.getFloat(6);
					return 1; // login success
				} else {
					return 0; // incorrect password; password donot match with db pwd
				}

			} else {
				return -1;//  username donot exists
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	
	public int pendingLoanAmount() {
		int totalLoanAmout = 0;
		try {
			
			String s = "select loan_amount from loan where username=? and status=?";
			pstmt = con.prepareStatement(s);

			pstmt.setString(1, usr);
			pstmt.setString(2, "Approved");
			res = pstmt.executeQuery();
			System.out.println(" Login Query executed successfully");

			while (res.next()) {
				totalLoanAmout += res.getInt(1);
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalLoanAmout;
	}
	

	public int changePassword(String new_pwd) {
		try {

			String s = "update customer set password=? where username=?";
			pstmt = con.prepareStatement(s);
			
			pstmt.setString(1, new_pwd);
			pstmt.setString(2, usr);

			int rows = pstmt.executeUpdate();
			System.out.println(" change password Query executed successfully");
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateLoanAmount(int loanID, int repayAmt) {
		try {

			String s = "update loan set loan_amount=loan_amount-? where loan_id=?";
			pstmt = con.prepareStatement(s);
			
			pstmt.setInt(1, repayAmt);
			pstmt.setInt(2, loanID);

			int rows = pstmt.executeUpdate();
			System.out.println(" loan amount update Query executed successfully");
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	public ArrayList<Transaction> allDebitedTransactions() {
		ArrayList<Transaction> rows = new ArrayList<Transaction>();
		try {
			
			String s = "select * from transactions where type=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, "Debit");			
			
			
			res = pstmt.executeQuery();
			System.out.println(" Debit Query executed successfully");

			while (res.next()) {

				rows.add(new Transaction(res.getString(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	
	
	
	
	
	
	
	
	public ArrayList<Transaction> transactionlist(String from, String to , String type) {
		//System.out.println(from.isEmpty() +" "+ to.isEmpty() +" "+type);
		ArrayList<Transaction> rows = new ArrayList<Transaction>();
		try {
			int flag = 0;
			String s ="";
			if(type.equals("All")) { //both credit and debit type
				
				if(from.isEmpty()==false && to.isEmpty()) { //only from value given
					s = "select * from transactions where username=? and todaysDate>=?";
					flag=0;
				}
				else if(from.isEmpty() && to.isEmpty()==false) {  //only to value given
					s = "select * from transactions where username=? and todaysDate<=?";	
					flag=1;
				}
				else if(from.isEmpty()==false && to.isEmpty()==false){ //both from or to value given
					s = "select * from transactions where username=? and todaysDate>=? and todaysDate<=?";
					flag=2;
				}
				else { //both from and to not given
					s = "select * from transactions where username=?";
					flag=3;
				}	
				
			}
			
			else { // only one type , either credit or debit
				
				if(from.isEmpty()==false && to.isEmpty()) { //only from value given
					s = "select * from transactions where username=? and todaysDate>=? and type=?";
					flag=4;
				}
				else if(from.isEmpty() && to.isEmpty()==false) {  //only to value given
					s = "select * from transactions where username=? and todaysDate<=? and type=?";	
					flag=5;
				}
				else if(from.isEmpty()==false && to.isEmpty()==false){ //both from or to value given
					s = "select * from transactions where username=? and todaysDate>=? and todaysDate<=? and type=?";
					flag=6;
				}
				else { //both from and to not given
					s = "select * from transactions where username=? and type=?";
					flag=7;
				}
				
			}
			
			pstmt = con.prepareStatement(s);
			if (flag==0) {pstmt.setString(1, usr); pstmt.setString(2, from); };
			if (flag==1) {pstmt.setString(1, usr); pstmt.setString(2, to); };
			if (flag==2) {pstmt.setString(1, usr); pstmt.setString(2, from); pstmt.setString(3, to); };	
			if (flag==3) {pstmt.setString(1, usr);};
			if (flag==4) {pstmt.setString(1, usr); pstmt.setString(2, from);  pstmt.setString(3, type); };
			if (flag==5) {pstmt.setString(1, usr); pstmt.setString(2, to); pstmt.setString(3, type);};
			if (flag==6) {pstmt.setString(1, usr); pstmt.setString(2, from); pstmt.setString(3, to); pstmt.setString(4, type);};	
			if (flag==7) {pstmt.setString(1, usr);pstmt.setString(2, type);};
			
			
			res = pstmt.executeQuery();
			System.out.println(" Transactions Query executed successfully");

			while (res.next()) {
				System.out.println(res.getString(1));
				System.out.println(res.getString(2));
				System.out.println(res.getInt(3));
				
				
				rows.add(new Transaction(res.getString(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	
	
	public int transfer(int Transfer_amount, String payee, boolean isNotLoan) {
		try {
			//when its not loan there will be two entities involved ,(credit and debit)
			int rows=0;
			if(isNotLoan) { // donot need to this part (update recipient account) if it is a loan	; 
				String to = "update customer set acc_bal=acc_bal+? where username=?";
				pstmt = con.prepareStatement(to);	
				pstmt.setInt(1, Transfer_amount);
				pstmt.setString(2, payee);
				rows = pstmt.executeUpdate();
				System.out.println(" payee Query executed successfully");
			}
			
			
			String from = "update customer set acc_bal=acc_bal-? where username=?";
			pstmt = con.prepareStatement(from);	
			pstmt.setInt(1, Transfer_amount);
			pstmt.setString(2, usr);
			rows += pstmt.executeUpdate();
			System.out.println(" sender Query executed successfully");
			
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateTransactionTable(int Transfer_amount,String payee, boolean isNotLoan) {
		try {
			int rows=0;
			//when its not loan there will be two entities involved ,(credit and debit)
			if(isNotLoan) { // donot need to this part (update credit part/ recipient) if it is a loan	
				String to = "insert into transactions values(curdate(),?,?,'Credit',?)";
				pstmt = con.prepareStatement(to);
				pstmt.setString(1, payee);
				pstmt.setInt(2, Transfer_amount);
				pstmt.setString(3, usr);
				rows = pstmt.executeUpdate();
				System.out.println(" payee insert transaction Query executed successfully");
			}
		
			
			String from = "insert into transactions values(curdate(),?,?,'Debit',?)";
			pstmt = con.prepareStatement(from);
			pstmt.setString(1, usr);
			pstmt.setInt(2, Transfer_amount);
			pstmt.setString(3, payee);
			rows += pstmt.executeUpdate();
			System.out.println(" user insert transaction Query executed successfully");

			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public String encrypt(String p) {
		String newPass = new String();
		int len = p.length();
		char new_char;
		
		for(int j = 0; j<p.length();j++) {
			
			System.out.println(p.charAt(j));
			new_char =  (char) ( p.charAt(j) + len);
			newPass += new_char;
			len++;
			//3
		}
		//System.out.println("new Pass = "+ newPass);
		return newPass;
		
	}
	
	public void sendEmail(String email, int newPass) throws AddressException, MessagingException 
	{
		String fromEmail=Credentials.email; //sender's mail id.
		String pwd=Credentials.pwd;		//sender's mail pwd.
		String toEmail=email; //reciever's mail id.
		
		String subject="DO NOT REPLY: Reset forgotten password"; // mail subject line
		String msg="Hi, your new password is: " +newPass; //mail body
		
		//Creating Session Object
		Properties prop = new Properties();
		//prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.host", "smtp.office365.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		//prop.put("mail.smtp.starttls.required", "true");
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				//sender's mail id and pwd is encapsulated inside "SendersCredentials.java"
				return new PasswordAuthentication(fromEmail, pwd);
			}
		});

		
		//Composing the Mail
		MimeMessage mesg = new MimeMessage(session);
		mesg.setFrom(new InternetAddress(fromEmail));
		mesg.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
		mesg.setSubject(subject);  
		mesg.setText(msg);  
		
		//Sending the Mail
		Transport.send(mesg);
		System.out.println("Mail Sent!!");
	}
	
	
	
	//**** Admin operations *********
	
	public int adminLogin() {
		try {

			String s = "select * from admin where username=?";
			pstmt = con.prepareStatement(s);

			pstmt.setString(1, usr);
			res = pstmt.executeQuery();
			System.out.println(" Admin login Query executed successfully");

			if (res.next()) {
				if (pwd.equals(res.getString(2))) {
					return 1; // login success
				} else {
					return 0; // incorrect password
				}

			} else {
				return -1;// invalid username
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public ArrayList<Customer> customerList() {
		ArrayList<Customer> rows = new ArrayList<Customer>();
		try {
			
			String s = "select * from customer";
			
			pstmt = con.prepareStatement(s);
			res = pstmt.executeQuery();
			System.out.println(" Admin customer Query executed successfully");

			while (res.next()) {
				rows.add(new Customer(res.getString(1),res.getString(2),res.getInt(4), res.getString(5),res.getInt(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	
	public ArrayList<Loan> loanList() {
		ArrayList<Loan> rows = new ArrayList<Loan>();
		try {
			
			String s = "select * from loan";
			
			pstmt = con.prepareStatement(s);
			res = pstmt.executeQuery();
			System.out.println(" Admin loan Query executed successfully");

			while (res.next()) {
				rows.add(new Loan(res.getString(1),res.getInt(2), res.getString(3), res.getString(4), res.getInt(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	
	public int getAccBalanceofApplicant(String tempUsr) {
		int balance=0;
		try {

			String s = "select * from customer where username=?";
			pstmt = con.prepareStatement(s);

			pstmt.setString(1, tempUsr);
			res = pstmt.executeQuery();
			System.out.println(" evaluateUpdateLoan login Query executed successfully");

			if (res.next()) {
				balance = res.getInt(6);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return balance;
	}
	
	
	public int UpdateLoanStatus(boolean flag) {
		try {
			//to create loan id
			Random r = new Random(System.currentTimeMillis());
		    int loan_id = r.nextInt(10000);// generates a number between [0..9999].
		    
			String s = "insert into loan values(?,?,?,?,?)";	
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, usr);
			pstmt.setInt(2, loan_amount);
			pstmt.setString(3, type);
			
			//Approved or rejected?
			if(flag==true) {		
				pstmt.setString(4, "Approved");
			}
			else {
				pstmt.setString(4, "Rejected");
			}
			pstmt.setInt(5, loan_id);
			
			int rows = pstmt.executeUpdate();
			System.out.println(" loan evaluation and table update successfully");
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
