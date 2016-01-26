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

<!-- Include Date Range Picker -->
<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
<meta charset="UTF-8">
<title>Booking</title>
</head>
<body>
	<!-- Author Kais Ghedamsi -->

	<div id="header">
		<h1>BookingBird</h1>
	</div>
<form id="bookform" method="get" target="_self" action="Servlet" onsubmit="return validate(this)"
		accept-charset="UTF-8">
		
	<div class="wrapper">
		<div id="titel-1">
			<h1>${house.name}</h1>
			<hr>
		</div>


		<div class="img-two">
			<img src="${house.url}" alt="${house.url}" width="400" height="200" id="main-pic"/>
			<input type="hidden" name="bild" value="florida">
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

	<div class="wrapper">
		<div class="form-action">

			Name:<input type="text" id="fullname" name="fullname" /><br>
			Email:<input type="email" id="email" name="email" class="required" /><br>
			Choose the period: <input type="text" name="datefilter" value="" /><br>
			Message:
			<textarea name="message"></textarea>
			<input type="submit" name="submit" value="Book" id="booksubmit" />
			
		</div>
	</div>
	</form>
	<!-- authur:Bo Yang -->
	<script type="text/javascript">
		$(document).ready(
				function() {

					$('input[name="datefilter"]').daterangepicker({
						autoUpdateInput : false,
						locale : {
							cancelLabel : 'Clear'
						}
					});

					$('input[name="datefilter"]').on(
							'apply.daterangepicker',
							function(ev, picker) {
								$(this).val(
										picker.startDate.format('MM/DD/YYYY')
												+ ' - '
												+ picker.endDate
														.format('MM/DD/YYYY'));
							});

					$('input[name="datefilter"]').on('cancel.daterangepicker',
							function(ev, picker) {
								$(this).val('');
							});

				});
		
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
				alert("Please choose the date");
				bookform.datefilter.focus();
			} else if (date.length != 0) {
				date = date.substring(0, 10);
				var date1 = new Date(date);
				var today = new Date();
				if (date1 < today) {
					//alert(date1+":"+today);
					returnValue = false;
					alert("Please choose a date after today");
				}
			}
			return returnValue;
		}
	</script>


	<!-- Author Kais Ghedamsi -->
	<div id="footer">
		<p>Posted By: Kais, Irina & Bo</p>
	</div>

</body>
</html>