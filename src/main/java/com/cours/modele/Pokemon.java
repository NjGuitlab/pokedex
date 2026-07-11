package com.cours.modele;

import java.util.List;

public class Pokemon {

    private int id;
    private String nom;
    private String imageUrl;
    private int taille;
    private int poids;
    private List<String> types = List.of();


    // Premier constructeur vide pour les appels d'api
    public Pokemon(){}

    // Deuxieme constructeur pour la création d'objets
    public Pokemon(int id, String nom, String imageUrl, int taille, int poids, List<String> types){

        if (id <= 0){
            throw new IllegalArgumentException("L'identifiant doit être supérieur à zéro.");
        }

        if (nom == null || nom.isBlank()){
            throw new IllegalArgumentException("Le nom ne doit pas etre vide.");
        }
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException(
                    "L'URL de l'image est obligatoire."
            );
        }

        if (taille <= 0){
            throw new IllegalArgumentException("La taille doit être supérieure à zéro.");
        }

        if (poids <= 0){
            throw new IllegalArgumentException("Le poids doit être supérieur à zéro.");
        }
        if(types == null || types.isEmpty()){
            throw new IllegalArgumentException("Un pokémon doit avoir au moins un type.");
        }

        this.id = id;
        this.nom = nom;
        this.imageUrl = imageUrl;
        this.taille = taille;
        this.poids = poids;
        this.types = List.copyOf(types);

    }


    // ----------------------- Les getters de la classe ----------------------------------- //

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getTaille() {
        return taille;
    }

    public int getPoids() {
        return poids;
    }

    public List<String> getTypes() {
        return List.copyOf(types);
    }

    // ------------------------- Les setters de la classe -------------------------------- //
    public void setId(int id) {
        if (id <= 0){
            throw new IllegalArgumentException("L'identifiant doit être supérieur à zéro.");
        }
        this.id = id;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank()){
            throw new IllegalArgumentException("Le nom ne doit pas etre vide.");
        }
        this.nom = nom;
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException(
                    "L'URL de l'image est obligatoire."
            );
        }
        this.imageUrl = imageUrl;
    }

    public void setTaille(int taille) {
        if (taille <= 0){
            throw new IllegalArgumentException("La taille doit être supérieure à zéro.");
        }
        this.taille = taille;
    }

    public void setPoids(int poids) {
        if (poids <= 0){
            throw new IllegalArgumentException("Le poids doit être supérieur à zéro.");
        }
        this.poids = poids;
    }

    public void setTypes(List<String> types) {
        if(types == null || types.isEmpty()){
            throw new IllegalArgumentException("Un pokémon doit avoir au moins un type.");
        }
        this.types = List.copyOf(types);
    }

    // --------------------- Methode toString ----------------------------------- //
    @Override
    public String toString() {
        return nom;
    }
}
