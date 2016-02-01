<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<link href='https://fonts.googleapis.com/css?family=Quicksand'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script type="text/javascript"
	src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!-- Include Date Range Picker -->
<script type="text/javascript"
	src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
<meta charset="UTF-8">
<title>Booking</title>
</head>

<body id="housebookbody">
<body onload="loadDate(); loadSistaComment()">


	<!-- Author Kais Ghedamsi -->
	<div id="header-1">
		<h1>BookingBird</h1>
	</div>

	<div class="wrapper">
		<div id="titel-1">
			<h1>${house.name}</h1>
			<hr>
		</div>

        <!-- Author:Bo Yang -->
		<div class="img-one">
			<img src="${house.url}" alt="${house.url}" width="650" height="400"
				id="main-pic" /> <input type="hidden" name="bild"
				value="${house.name}">
		</div>

		<div class="img-two">
			<table id="picstable">
				<tr class="more-pics">
					<td><img src="${house.url}" alt="${house.url}" width="200"
						height="100"
						onclick='getElementById("main-pic").src="${house.url}"'></td>
					<td><img src="${pics[0]}" alt="${pics[0]}" width="200"
						height="100" onclick='getElementById("main-pic").src="${pics[0]}"' /></td>
					<td><img src="${pics[1]}" alt="${pics[1]}" width="200"
						height="100" onclick='getElementById("main-pic").src="${pics[1]}"' /></td>
				</tr>
			</table>
		</div>


		<h2>Description:</h2>
		<div id="description">
			<div id="text-1">${house.description}</div>
		</div>
	</div>

	<div id="wrapper-review">
		<!--Author: Irina Fatkoulin -->
		<div>
			<h2>Guest reviews</h2>
			<hr>
			<div id="feedback"></div>
			<button type="button" onmouseover="loadDoc()" onmouseout="clean()">Read
				all reviews</button>
			<div id="all" onmouseover="loadDoc()" onmouseout="clean()"></div>
		</div>


		<div>
			<form name="commentForm" method="post" action="SparaComment">
				<textarea rows="5" cols="30" name="feedback"
					placeholder="Write feedback:" id="feedback"></textarea>
				<input type="hidden" name="nameOfHouse" id="nameOfHouse"
					value="${house.name}" /><br> <input type="submit"
					onclick="loadSistaComment()" value="Add" />
			</form>
		</div>
	</div>
	
    <!-- Author:Bo Yang -->
	<form id="bookform" method="post" target="_self" action="Servlet"
		onsubmit="return validate(this)" accept-charset="UTF-8">
		<div class="wrapper-2">
			<div class="form-action">
				<h2>Contact information:</h2>
				<input type="hidden" name="bild" value="${house.name}"> <input
					type="text" id="fullname" name="fullname" value="${bi.name}"
					placeholder="Name" required>*<br> <input type="email"
					id="email" name="email" value="${bi.email}" placeholder="Email"
					required>*<br> <input type="text" name="datefilterIN"
					value="${bi.startDate}" placeholder="Choose check-in date" required>*<br>
				<input type="text" name="datefilterOUT" value="${bi.endDate}"
					placeholder="Choose check-out date" required>*<br>
				<textarea name="message" value="${bi.message}" placeholder="Message"></textarea>
				<br> <input type="submit" name="submit" value="Book"
					id="booksubmit">


			</div>
		</div>

	</form>


	<script type="text/javascript">
		var dates;
		/**Author: Bo Yang **/
		function validate(form) {
			var returnValue = true;
			var fullname = bookform.fullname.value;
			var email = bookform.email.value;
			var date = bookform.datefilterIN.value;
			var dateOUT = bookform.datefilterOUT.value;
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
				alert("Please choose check-in date");
				bookform.datefilterIN.focus();
			}
			;
			dates = dates.substring(1, dates.length - 3);
			dates = dates.replace(/'/g, "");
			var arr = dates.split(",");
			for (i = 0; i < arr.length; i++) {
				var date1 = new Date(arr[i]);
				//alert(arr[i]);
				var date2 = new Date(date);
				//alert(date2);
				var date3 = new Date(dateOUT);
				if (((date1 > date2) && (date1 < date3)) || (date1 == date2)
						|| (date1 == date3)) {
					returnValue = false;
					alert("Some dates you have chosen is not available.Please choose another date");
					return returnValue;
				}
			}
			return returnValue;
		}

		/**
		 *  Author:Irina Fatkoulin
		 **/
		var dates;
		//var dates = ['2016-01-05', '2016-01-27'];
		function loadDate() {

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {

					dates = xhttp.responseText;
					//disableDates(txt);

				}
			};
			xhttp.open("POST", "Reader", true);
			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhttp.send("bild=${house.name}");
		}

		function disableDates(date) {
			var y = date.getFullYear().toString(); // get full year
			var m = (date.getMonth() + 1).toString(); // get month.
			var d = date.getDate().toString(); // get Day
			if (m.length == 1) {
				m = '0' + m;
			} // append zero(0) if single digit
			if (d.length == 1) {
				d = '0' + d;
			} // append zero(0) if single digit
			var currDate = y + '-' + m + '-' + d;
			if (dates.indexOf(currDate) >= 0) {
				return [ false, "ui-highlight", "NOT AVAILABLE" ];
			} else {
				return [ true, "ui-highlight1" ];
			}
		}

		$(document).ready(
				function() {
					$('input[name="datefilterIN"]').datepicker(
							{
								minDate : 0,
								beforeShowDay : disableDates,
								onClose : function(selectedDate) {
									var date2 = $('input[name="datefilterIN"]')
											.datepicker('getDate');
									var nextDayDate = new Date();
									nextDayDate.setDate(date2.getDate() + 1);
									nextDayDate.setMonth(date2.getMonth());

									$('input[name="datefilterOUT"]')
											.datepicker("option", "minDate",
													nextDayDate);
								}

							});

					$('input[name="datefilterOUT"]').datepicker(
							{
								minDate : 0,
								beforeShowDay : disableDates,
								onClose : function(selectedDate) {
									$('input[name="datefilterIN"]').datepicker(
											"option", "maxDate", selectedDate);
								}

							});
				});

		function loadSistaComment() {

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					document.getElementById("feedback").innerHTML = xhttp.responseText;

				}
			};
			xhttp.open("POST", "ReadSistaComment", true);
			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhttp.send("bild=${house.name}");
		}

		function loadDoc() {

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					document.getElementById("all").innerHTML = xhttp.responseText;
				}
			};
			xhttp.open("POST", "ReadFeedback", true);

			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhttp.send("bild=${house.name}");
		}
		function clean() {
			document.getElementById("all").innerHTML = "";
		}
	</script>

	<!-- Author Kais Ghedamsi -->
	<div id="footer">
		<div id="contact-text">
			<h4>Copyright BookingBird 2016</h4>
		</div>
	</div>

</body>
</html>