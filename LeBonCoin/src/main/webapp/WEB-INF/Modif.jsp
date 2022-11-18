<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://myapp.fr/tld/extratags" prefix="mytags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier le compte</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>

<mytags:Header userName='Page pour modifier le compte : ${user.login }' />


<form method="post" style="margin-top:20px">
	<input type="hidden" name = "id_text" value="${user.id}">
	
	<label for="login_text">Changer de login</label>
	<input class="form_field" name = "login_text" type="text" value="${user.login }"><br>
	
	<label for="password_text">Changer de password.</label>
	<input class="form_field" name ="password_text" type= "text" value="${user.password }"><br>
	
	<button type="submit">Valider</button>
</form>

</body>
</html>