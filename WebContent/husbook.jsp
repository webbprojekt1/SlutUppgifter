<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<link href='https://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.9.1.js"></script>
	<script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!-- Include Date Range Picker -->
<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
<meta charset="UTF-8">
<title>Booking</title>
</head>

<body id="housebookbody">

<body onload = "loadDate()">


	<!-- Author Kais Ghedamsi -->
	<div id="header-1">
		<h1>BookingBird</h1>
	</div>
	
<form id="bookform" method="get" target="_self" action="Servlet" onsubmit="return validate(this)"
		accept-charset="UTF-8">
		
	<div class="wrapper">
		<div id="titel-1">
			<h1>${house.name}</h1>
			<hr>
		</div>


		<div class="img-one">
			<img src="${house.url}" alt="${house.url}" width="400" height="200" id="main-pic"/>
			<input type="hidden" name="bild" value="${house.name}">
		</div>
		
        <div class="img-two">
          <table id="picstable">
            <tr class="more-pics">
            <td><img src="${house.url}" alt="${house.url}" width="200" height="100" onclick='getElementById("main-pic").src="${house.url}"'></td>
			<td><img src="${pics[0]}" alt="${pics[0]}" width="200" height="100" onclick='getElementById("main-pic").src="${pics[0]}"'/></td>
			<td><img src="${pics[1]}" alt="${pics[1]}" width="200" height="100" onclick='getElementById("main-pic").src="${pics[1]}"'/></td>
		    </tr> 
		   </table>
		</div>


		<div id="titel-2">
			<h2>Description:</h2>
			<div id="text-1">${house.description} </div>
		</div>
	</div>

	<div class="wrapper-2">
		<div class="form-action">
		<h2>Contact information:</h2>
			<input type="text" id="fullname" name="fullname" placeholder="Name">*<br>
			<input type="email" id="email" name="email" class="required" placeholder="Email">*<br>
			<input type="text" name="datefilterIN" value="" placeholder="Choose check-in date">*<br>
  			<input type="text" name="datefilterOUT" value="" placeholder="Choose check-out date">*<br>
			<textarea name="message" placeholder="Message"></textarea><br>
			<input type="submit" name="submit" value="Book" id="booksubmit">

			
		</div>
	</div>
	
	</form>
	<!-- authur:Bo Yang -->
	<script type="text/javascript" language="javascript">
	<!-- Author:Irina Fatkoulin -->
	var dates;
	//var dates = ['2016-01-05', '2016-01-27'];
	function loadDate(){
	
	 var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function(){
		 if(xhttp.readyState == 4 && xhttp.status == 200){
			 
			dates =
				  xhttp.responseText;
			//disableDates(txt);
			
		 }
	 };
	 xhttp.open("POST", "Reader", true);
	 xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xhttp.send("bild=${house.name}");
}

	


function disableDates(date){
	var y = date.getFullYear().toString(); // get full year
	var m = (date.getMonth() + 1).toString(); // get month.
	var d = date.getDate().toString(); // get Day
	if(m.length == 1){ m = '0' + m; } // append zero(0) if single digit
	if(d.length == 1){ d = '0' + d; } // append zero(0) if single digit
	var currDate = y+'-'+m+'-'+d;
	if(dates.indexOf(currDate) >= 0){
		return [false, "ui-highlight", "NOT AVAILABLE"];	
	}else{
		return [true,"ui-highlight1"];
	}					
}






$(document).ready(function(){
	$('input[name="datefilterIN"]').datepicker({
		minDate:0,
		beforeShowDay : disableDates,
		onClose: function( selectedDate ) {
			var date2 = $('input[name="datefilterIN"]').datepicker('getDate');
			var nextDayDate = new Date();
			nextDayDate.setDate(date2.getDate() + 1);
			
	        $( 'input[name="datefilterOUT"]' ).datepicker( "option", "minDate", nextDayDate );
	      }
	
	});

	
	
		$('input[name="datefilterOUT"]').datepicker({
			minDate:0,
			beforeShowDay : disableDates,
			onClose: function( selectedDate ) {
		        $( 'input[name="datefilterIN"]' ).datepicker( "option", "maxDate", selectedDate );
		      }
			
		});
	});
		
		

  /**Author: Bo Yang **/
  
		function validate(form) {
			var returnValue = true;
			var fullname = bookform.fullname.value;
			var email = bookform.email.value;
			var date = bookform.datefilter.value;
			//test name length
			if (fullname.length < 5) {
				returnValue = false;
				alert("The fullname must be at least 5 characters long");
				bookform.fullname.focus();

			}
			//test email address
			else if (!email.match(/\S+@\S+\.\S+/)) {
				returnValue = false;
				alert("You did not enter a valid email address");
				bookform.email.focus();
			}
			/*
			if( val.indexOf(' ')!=-1 || val.indexOf('..')!=-1){
				returnValue=false;
				alert("You did not enter a valid email address");
				bookform.email.focus();
			} */
			//test date
			else if (date.length == 0) {
				returnValue = false;
				alert("Please choose check-in date");
				bookform.datefilterIN.focus();
			} else if (dateOUT.length == 0) {
				returnValue = false;
				alert("Please choose check-out date");
				bookform.datefilterOUT.focus();
				
			}
			return returnValue;
		}
	</script>


<!-- Author Kais Ghedamsi -->
<div id="footer">
	<div id="contact-text">
		<h4> Copyright BookingBird 2016</h4>
	</div>
</div>

</body>
</html>