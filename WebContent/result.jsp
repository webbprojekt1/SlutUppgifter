<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>MyTest</title>
 
</head>
<body>
 
 <p>Your name is ${bi.name}</p>
 <p>Your email is ${bi.email}</p>
 <p>Check-in date is ${bi.startDate}</p>
 <p>Check-out date is ${bi.endDate}</p>
 <p>Message:${bi.message} </p>
 <form method="get" target="_self" action="Servlet"
		accept-charset="UTF-8">
 <input type="submit" name="submit" value="Confirm">
 </form>
 
</body>
</html>