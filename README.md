# Pokédex JavaFX

## Description

Cette application permet de rechercher un Pokémon via la PokéAPI, d'afficher ses informations et de sauvegarder automatiquement les résultats dans une base de données PostgreSQL.

Le projet a été développé en JavaFX selon l'architecture MVC (Model - View - Controller).

---

## Fonctionnalités

- Recherche d'un Pokémon par son nom ou son ID
- Récupération des informations depuis la PokéAPI
- Sauvegarde automatique dans PostgreSQL (UPSERT)
- Affichage des informations du Pokémon
- Liste des Pokémons enregistrés
- Suppression d'un Pokémon avec confirmation
- Interface graphique JavaFX avec feuille de style CSS

---

## Technologies utilisées

- Java 21
- JavaFX
- Maven
- PostgreSQL
- JDBC
- Jackson
- PokéAPI

---

## Architecture du projet

```
src
│
├── controller
│      PokemonController.java
│
├── modele
│      Pokemon.java
│      PokemonDAO.java
│
├── service
│      PokemonApiService.java
│
├── util
│      Connexion.java
│
├── view
│      PokemonViewFx.java
│
└── resources
       css
          style.css
```

---

## API utilisée

PokéAPI

https://pokeapi.co/api/v2/pokemon/

Exemple :

```
https://pokeapi.co/api/v2/pokemon/pikachu
```

ou

```
https://pokeapi.co/api/v2/pokemon/25
```

---

## Base de données

Le projet utilise PostgreSQL.

Structure de la table :

```sql
CREATE TABLE pokemon (

    id INTEGER PRIMARY KEY,

    nom VARCHAR(100),

    image_url TEXT,

    taille INTEGER,

    poids INTEGER,

    types TEXT

);
```

---

## Lancer le projet

1. Cloner le dépôt

```bash
git clone <url-du-projet>
```

2. Ouvrir le projet dans IntelliJ IDEA.

3. Configurer les paramètres de connexion à PostgreSQL dans la classe `Connexion.java`.

4. Lancer l'application :

```bash
mvn javafx:run
```

---

## Auteur

Projet réalisé par AMMOUR Nadjib dans le cadre du cours de Développement d'applications Java.