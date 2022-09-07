/**
 * 
 src="style.css"
 src="script.js"
 type="text/javascript" 
 */

const cells = document.querySelectorAll('td');
const table_array = [];
const inner_array = [];

//geting data from each cell of the table and storing inside table_array
cells.forEach(function(cell) {
	console.log(cell.innerHTML);
	table_array.push(cell.innerHTML);
})

//function to extract nth element
const nthElement = (arr, n = 0) => (n > 0 ? arr.slice(n, n + 1) : arr.slice(n))[0];

//displaying all usernames
console.log("usernames");
var i = 1;
while (i < table_array.length) {
	console.log(nthElement(table_array, i));	
	i += 4;
}

//getting all loans and pushing to typesofloans array
var typesofloans=[];
console.log("type of loans");
var i = 3;
while (i < table_array.length) {
	console.log(nthElement(table_array, i));
	typesofloans.push(nthElement(table_array, i));
	i += 4;
}

//console.log("type of loans");
//console.log(typesofloans);
//converting type of loans array to an object together with the unique name and frequency
var counts = {};
for (var num of typesofloans) {
  counts[num] = counts[num] ? counts[num] + 1 : 1;
}
console.log(counts)

//seperating the  unique values and frequency
var distinctloans=[];
var freq =[];
for (const property in counts) {
  console.log(`${property}: ${counts[property]}`);
  distinctloans.push(property);
  freq.push(counts[property]);
}

console.log(distinctloans);
console.log(freq);

//charts
const ctx = document.getElementById("myChart").getContext("2d");
const labels = distinctloans;
const data = {
	labels,
	datasets:[{
		data:freq,
		label: 'loan distribution'
	}]
}

const config = {
	type:"bar",
	data: data,
	options: {
		responsive: true,
	}
}

const myChart = new Chart(ctx, config)




