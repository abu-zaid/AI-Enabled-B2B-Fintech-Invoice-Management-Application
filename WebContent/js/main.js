let TableData=null;
let PageNumber=0,PAGE_SIZE=20;

const fetchTableData=()=>{
fetch("http://localhost:8080/H2HBABBA1586/table").then(function (response){
	return response.json();
}).then (jsonResult=>{
		TableData=jsonResult;
	showTableOnLoad();
}).catch(function (error){
	console.error("somthing went wrong");
	console.error(error);
})
}

(
		function(){
			fetchTableData()
		})()

		

/**
$(
  document).ready(function () {
  $.getJSON("invoice.json", function (data) {
    var invoice_data = '';

    $.each(data, function (key, value) {
      invoice_data += '<tbody id="myTable">';
      invoice_data += '<tr class="tablerows">';
      invoice_data += '<td><input type="checkbox" class="invoicerows" name="invoicerows" /></td>';
      invoice_data += '<td>' + value.name_customer + '</td>';
      invoice_data += '<td>' + value.cust_number + '</td>';
      invoice_data += '<td>' + value.invoice_id + '</td>';
      invoice_data += '<td>' + value.total_open_amount + '</td>';
      invoice_data += '<td>' + value.due_in_date + '</td>';
      invoice_data += '<td>' + value.predicted_payment_date + '</td>';
      invoice_data += '<td></td>';
      invoice_data += '<tr>';
      invoice_data += '</tbody>';


    });
    $('#invoice').append(invoice_data);
  });
});
 
**/
//select all from checkbox
$(document).ready(function () {
  $('#checkAll').click(function () {
    var checked = this.checked;
    $('input[type="checkbox"]').each(function () {
      this.checked = checked;
    });
  })
});


function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}




//search using invoice list
$(document).ready(function () {
  $("#search").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#middle tr").filter(function () {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});

function remove() {
  document.getElementsByClassName("tablerows").remove();
}



/*Open form of edit button*/
function openFormEdit() {
  document.getElementById("myFormedit").style.display = "block";
}

function closeFormEdit() {
  document.getElementById("myFormedit").style.display = "none";
}


function openFormDelete() {
  document.getElementById("myFormdelete").style.display = "block";
}

function closeFormDelete() {
  document.getElementById("myFormdelete").style.display = "none";
}



function deletefnc() {
  document.getElementById("myTable").deleteRow(0);
}



function tableText(tableRow) {
  var name = tableRow.childNodes[1].innerHTML;
  var age = tableRow.childNodes[3].innerHTML;
  var obj = {'name': name, 'age': age};
  console.log(obj);
}



const showHeader = (headers) => {
	const headerData=headers;
	const tableRef=document.getElementById("middle");
	let tableHeader= "<tr>";
	tableHeader+='<th><input type="checkbox" name="" id="checkAll"></th>';
	headerData.forEach(element =>{
		tableHeader+= "<th>"+element+"</th>"
	});
	tableHeader+="</tr>";
	tableRef.innerHTML+=tableHeader;
}

const showTableOnLoad=(check=true)=>{
	  
	  const currIndex=PAGE_SIZE*PageNumber;
	  const CurrData=TableData.slice(currIndex,currIndex+PAGE_SIZE);
	  const tableRef=document.getElementById("middle");
	  tableRef.innerHTML="";
	  if(check)
	    showHeader(Object.keys(CurrData[0]).map((headerString)=>headerString.toUpperCase()));
	  
	  CurrData.forEach(tableRow=>{
	    let tableEle="<tr>";
	    tableEle+='<td><input type="checkbox" name="" id="checkAll"></td>';

	    Object.entries(tableRow).forEach(tableRowEle=>{
	      const [key,value]=tableRowEle;
	      tableEle+="<td>"+value+"</td>";
	    });
	    tableEle+="</tr>";
	    tableRef.innerHTML+=tableEle;
	  });
	    }

const showNextPage= ()=> {
	  
	  PageNumber=PageNumber+1;
	  showTableOnLoad();
	}

	const showPrevPage= ()=> {
	  
	  PageNumber=PageNumber-1;
	  showTableOnLoad();
	}



/*
$(document).ready(function(){
  var table =  $('#myTable');

  var max_size=mydata.length;
  var sta = 0;
  var elements_per_page = 10;
  var limit = elements_per_page;
  goFun(sta,limit);
  function goFun(sta,limit){
    console.log(sta,limit);
    for(var i=sta;i<limit;i++){
    var tab='<tr><td><input type="checkbox" class="invoicerows" name="invoicerows" /></td><td>'+mydata[i].name_customer+"\n"+'</td><td>'+mydata[i].cust_number+"\n"+'</td><td>'+mydata[i].invoice_id+"\n"+'</td><td>'
              +mydata[i].total_open_amount+"\n"+'</td><td>'+mydata[i].due_in_date+"\n"+'</td><td>'+mydata[i].predicted_payment_date+"\n"+'</td><td>'
              +"NA"+"\n"+'</td></tr>';

     $('#myTable').append(tab)

    }
  }
 
  $('#nextValue').click(function(){
    var next = limit;
    if(max_size>=next) {
    def = limit+elements_per_page;
     limit = def
    table.empty();
    if(limit > max_size) {
    def = max_size;
    }
   
    goFun(next,def);
    
    }
  });
    $('#PreValue').click(function(){
    var pre = limit-(2*elements_per_page);
    if(pre>=0) {
    limit = limit-elements_per_page;
    table.empty();
    goFun(pre,limit); 
    }
  });
var number = Math.round(userDetails.length / elements_per_page);

for(i=0;i<=number;i++) {
$('.nav').append('<button class="btn">'+i+'</button>');
}
$('.nav button').click(function(){
 var start = $(this).text();
 table.empty();
 limit = 3*(parseInt(start)+1) > max_size ? max_size: 3*(parseInt(start)+1)
goFun(start*3,limit); 
});
});
 */

/*
function highlight(e) {
  if (selected[0]) selected[0].className = '';
  e.target.parentNode.className = 'selected';
}


var table = document.getElementById('myTable'),
  selected = table.getElementsByClassName('selected');
table.onclick = highlight;

function fnselect(){
  
  alert($("tr.selected td:first" ).html());
}
*/


/* highlught selected row and read value of invoice_id*/
function ChangeColor(tableRow, highLight)
{
if (highLight)
{
  tableRow.style.backgroundColor = '#dcfac9';


}
else
{
  tableRow.style.backgroundColor = 'white';
}
}
/*
function readvalues(tableRow){
var columns=tableRow.querySelectorAll("td");
for(var i=0;i<columns.length;i++)
console.log('Column '+i+': '+columns[i].innerHTML );
}

*/
const addRows = () =>{
	console.log("addRows()")
	const cust_name=document.getElementById("cust_name").value;
	const cust_number=document.getElementById("cust_number").value;
	const invoice_id=document.getElementById("invoice_number").value;
	const invoice_amount=document.getElementById("invoice_amount").value;
	const due_date=document.getElementById("due_date").value;
	const notes=document.getElementById("notes").value;
	fetch(`http://localhost:8080/H2HBABBA1586/add?cust_name=${cust_name}&cust_number=${cust_number}&invoice_id=${invoice_id}&invoice_amount=${invoice_amount}&due_date=${due_date}&notes=${notes}`,
			{
				method:'POST'
			}).then(()=>{
				closeForm();
				fetchTableData();
			})
}

const showSearchData = (data) =>{

	  const tableRef=document.getElementById("middle");
	  tableRef.innerHTML="";
	 
	    showHeader(Object.keys(data[0]).map((headerString)=>headerString.toUpperCase()));
	  
	  data.forEach(tableRow=>{
	    let tableEle="<tr>";
	    tableEle+='<td><input type="checkbox" name="" id="checkAll"></td>';

	    Object.entries(tableRow).forEach(tableRowEle=>{
	      const [key,value]=tableRowEle;
	      tableEle+="<td>"+value+"</td>";
	    });
	    tableEle+="</tr>";
	    tableRef.innerHTML+=tableEle;
	  });
}

const searchValue = () =>{
	const searchValue=document.getElementById("searchValue").value;
	fetch(`http://localhost:8080/H2HBABBA1586/search?searchValue=${searchValue}`,
			{
				method:'GET'
			}).then((response)=>{
				closeForm();
				return response.json();
			}).then(data =>{
				showSearchData(data);
			})
}

