<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://myapp.fr/tld/extratags" prefix="mytags" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Liste des utilisateurs</title>
		<link rel="stylesheet" href="css/style.css" type="text/css">
	</head>
	<body>
		<mytags:Header userName='Page des utilisateurs (${users.size()} utilisateurs)' />
		
		<div style="margin-top:40px">
			<div class="table__container">
				<table>
					<tr> <th>ID</th> <th>Login</th> <th>password</th> <th>localisation</th> <th>img</th></tr>
					
					<c:forEach items="${users}" var="user">
						<tr>
			          		<td> <c:out value='${user.id}'/> </td>
			          		<td> <c:out value='${user.login}'/> </td>
			          		<td> <c:out value='${user.password}'/> </td>
			          		<td> <c:out value='${user.localisation}'/> </td>
			          		<td> <c:out value='${user.img}'/> </td>
			          		<td> <a href='modif?id=<c:out value='${user.id}'/>'>Update</a></td>			
							<td> <a href='supp?id=<c:out value='${user.id}'/>'>Delete</a></td>	
						</tr>
					</c:forEach>
		
				</table>
				
				
				<p><a href="create">Ajouter un élement</a></p>
				<p><a href="connect">Se connecter</a></p>
			</div>
		</div>
		
		
	
	</body>
</html>