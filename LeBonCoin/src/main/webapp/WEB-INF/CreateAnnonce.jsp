<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://myapp.fr/tld/extratags" prefix="mytags" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <!-- Inserer ici le titre  -->
    <title>Liste des annonces</title>
</head>


<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Le bon coin</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="annonces">Les annonces</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="liste">Les utilisateurs</a>
        </li>
        <c:if test="${connect == null}">
	        <li class="nav-item">
	          <a class="nav-link" href="connect">Se connecter</a>
	        </li>
        </c:if>
        <c:if test="${'ok' == connect}">
	        <li class="nav-item">
	          <a class="nav-link" href="deconnect">Se deconnecter</a>
	        </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link disabled">
          <c:if test="${ 'ok' == connect}">
          		User n°${user_id} connecté
          </c:if>
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container-fluid">

	<div class="row py-3">
		<div class="col-12 text-center bg-secondary">
			<h1 class="text-light"><strong>La liste des annonces</strong></h1>
			<p><a class="link-light" href="createAnnonce">Créer une annonce</a></p>
		</div>
	</div>
<!-- Inserer ici le body  -->

	<div class="row p-3">
		<div class="offset-3 col-6 text-center">
			<form method="post">
			  <div class="mb-3">
			    <label for="titre" class="form-label">Titre</label>
			    <input type="text" class="form-control" name="titre" id="titre" aria-describedby="titrehelp">
			    <div id="titrehelp" class="form-text">Le titre de votre annonce (max 255 caracteres)</div>
			  </div>
			  <div class="mb-3">
			    <label for="description" class="form-label">Description</label>
			    <input type="text" class="form-control" name="description" id="description">
			  </div>
			  <button type="submit" class="btn btn-primary">Créer l'annonce</button>
			</form>
		</div>
	</div>




<!-- Fin de l'insertion -->
</div>
</body>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>