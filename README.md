# TD du 18/11/2022 (Contrainte en temps de developpement : 1 journée)

- Creation de compte, connection, modifiation, suppression fonctionnel.
- Sécurité contre faille xss, injection sql, mdp crypté avec une fonction hash.
- Creation, modification, suppression d'annonces fonctionnel
- Quelques droits d'acces mis en place.

# Versions

- jdk 17.0.5
- tomcat 8.5.83

# JAR dans le fichier de tomcat/lib

- mysql-connector-java-8.0.30.jar
- protobuf-java-3.19.4.jar

# Dans le dossier lib de WEB_INF

- jstl-1.2.jar

# Base de donnée mysql (nom de la table : leboncoin)

CREATE TABLE Annonces(
id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
titre varchar(255),
description varchar(2048),
categorie varchar(64),
prix int(10),
date TIMESTAMP DEFAULT NOW(),
user BIGINT UNSIGNED
img VARCHAR(255));

CREATE TABLE Users (
id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
login Varchar(150),
password varchar(150),
localisation varchar(150),
img varchar(200));

ALTER TABLE Annonces ADD CONSTRAINT fk FOREIGN KEY (user) REFERENCES Users(id);
