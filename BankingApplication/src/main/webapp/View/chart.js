/**
 * 
 src="style.css"
 src="script.js"
 type="text/javascript" 
 */


//*******supporting functions */
//converting type of loans array to an object together with the unique name and frequency
function freqCounter(_arrayobj){
	let counts = {};
	for (var element of _arrayobj) {
  	counts[element] = counts[element] ? counts[element] + 1 : 1; //if the element alr exists=> type: count +1  
	}
	return counts;
}

//const arr = ['a', 'b', 'a', 'a', 'c', 'c'];
//
//const count = {};
//
//for (const element of arr) {
//  if (count[element]) {
//    count[element] += 1;
//  } else {
//    count[element] = 1;
//  }
//}
//
//console.log(count); // {a: 3, b: 1, c: 2}


//seperating the  unique values and frequency
function getDistinctval(_obj){
	let distinctval=[];
	for (const property in _obj) {
//  	console.log(`${property}: ${counts[property]}`);
  	distinctval.push(property);
	}
	return distinctval;
}

function getFreq(_obj){
	let freq =[];
	for (const property in _obj) {
//  	console.log(`${property}: ${counts[property]}`);
  	freq.push(_obj[property]);
	}
	return freq;
}




//1.*******Loan type Distribution */
var loanTypeCounts = freqCounter(typesofloans);//get distinct val vs freq  table
var distinctloans=getDistinctval(loanTypeCounts); //x axis
var freqloans = getFreq(loanTypeCounts); //y axis

//LoanDistribution chart

var ctx = document.getElementById("LoanDistribution").getContext("2d");
var labels = distinctloans;//x axis
var data = {
	labels,
	datasets:[{
		data:freqloans, 
		label: 'Loan Type Distribution (Freq vs Loan Type)',
		borderColor: 'yellow',
		backgroundColor: 'black',
		borderWidth: 3,				
	}]
}

var config = {
	type:"bar",
	data: data,
	options: {
		responsive: true,
	}
}

var LoanDistribution = new Chart(ctx, config);


//2.*******Loan Applicants distributions */

console.log(loanApplicants);
var loanApplicantCounts = freqCounter(loanApplicants); //get distinct val vs freq  table
var distinctApplicants=getDistinctval(loanApplicantCounts); //x axis
var freqApplicants = getFreq(loanApplicantCounts);
console.log(freqApplicants);


//Loan application Distribution chart
var ctx = document.getElementById("LoanApplicants").getContext("2d");
var labels = distinctApplicants;
var data = {
	labels,
	datasets:[{
		data:freqApplicants,
		label: 'Loan applicant Distribution (Freq vs Applicant)',
		borderColor: 'yellow',
		backgroundColor: 'black',
		borderWidth: 3,
	}]
}

var config = {
	type:"bar",
	data: data,
	options: {
		responsive: true,
	}
}

const LoanApplicants = new Chart(ctx, config);

//3.*******AccountBalance distributions */
console.log("account balance")
console.log(customerList);
console.log(accBal);

var ctx = document.getElementById("AccountBalance").getContext("2d");
var labels = customerList;
var data = {
	labels,
	datasets:[{
		data:accBal,
		label: 'Account balance Distribution(Amount vs Customer)',
		borderColor: 'yellow',
		backgroundColor: 'black',
		borderWidth: 3,
	}]
}

var config = {
	type:"bar",
	data: data,
	options: {
		responsive: true,
	}
}

const AccountBalance = new Chart(ctx, config);


//4.*******Transactions distributions */
console.log("Transactions")
console.log(date);
console.log(amount);

var ctx = document.getElementById("allDebitedTransactionsList").getContext("2d");
var labels = date;
var data = {
	labels,
	datasets:[{
		data:amount,
		label: 'Debited Transaction list (Amount vs Date)',
		borderColor: 'yellow',
		backgroundColor: 'black',
		borderWidth: 3,
	}]
}

var config = {
	type:"line",
	data: data,
	options: {
		responsive: true,
		
	}
}


const allDebitedTransactionsList = new Chart(ctx, config);
Chart.defaults.global.defaultFontColor = 'darkblue';
Chart.defaults.global.defaultFontSize = 15;
Chart.defaults.global.defaultFontWeight = 'bold';