package com.cours.view;

import com.cours.controller.PokemonController;
import com.cours.modele.Pokemon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PokemonViewFx {

    // --------------------- Fenêtre principale ---------------------------- //

    // Notre fenêtre JavaFX
    private final Stage stage;

    // Conteneur principal de l'application
    private final VBox root = new VBox(20);

    // Notre controller
    private final PokemonController controller;

    // --------------------- Partie recherche ---------------------------- //

    // Titre
    private final Label lblTitre = new Label("POKEDEX");

    // Sous titre
    private final Label lblRecherche = new Label("Rechercher un Pokémon");

    // Champ de recherche
    private final TextField txtRecherche = new TextField();

    // Bouton rechercher
    private final Button btnRechercher = new Button("Rechercher");

    // --------------------- Partie carte Pokémon ---------------------------- //

    // Image
    private final ImageView imagePokemon = new ImageView();

    // Informations
    private final Label lblNom = new Label("Nom : -");
    private final Label lblId = new Label("ID : -");
    private final Label lblTypes = new Label("Types : -");
    private final Label lblTaille = new Label("Taille : -");
    private final Label lblPoids = new Label("Poids : -");

    // Carte centrale
    private final VBox cartePokemon = new VBox(10);

    // --------------------- Partie Liste ---------------------------- //

    private final Label lblListe = new Label("Pokémons capturés");

    private final ListView<Pokemon> listPokemons = new ListView<>();

    private final Button btnSupprimer = new Button("Supprimer");

    // --------------------- Constructeur ---------------------------- //

    public PokemonViewFx(Stage stage) {

        // On récupère la fenêtre principale
        this.stage = stage;

        // Création du controller
        controller = new PokemonController(this);

        // Construction de notre interface
        construireInterface();

        // Création de la scène
        Scene scene = new Scene(root, 500, 700);

        // style
        scene.getStylesheets().add(
                getClass()
                        .getResource("/css/style.css")
                        .toExternalForm()
        );

        // Association de la scène
        stage.setScene(scene);

        // Titre
        stage.setTitle("Pokédex");

        // Taille fixe
        stage.setResizable(false);

        // Affichage
        stage.show();

    }

    // --------------------- Construction de l'interface ---------------------------- //

    // Cette méthode construit toute notre interface
    private void construireInterface() {

        // On espace les composants
        root.setPadding(new Insets(20));

        // Les composants seront centrés
        root.setAlignment(Pos.TOP_CENTER);

        // Construction des différentes parties
        construireHaut();
        construireCentre();
        construireBas();

    }

    // --------------------- Partie haute ---------------------------- //

    private void construireHaut() {

        // Style du titre
        lblTitre.getStyleClass().add("titre");

        // Style du sous titre
        lblRecherche.getStyleClass().add("sousTitre");

        // Texte affiché dans le champ
        txtRecherche.setPromptText("Nom ou ID");

        // Largeur du champ
        txtRecherche.setPrefWidth(250);

        // Bouton
        btnRechercher.setPrefWidth(140);

        // Ligne contenant le champ + bouton
        HBox ligneRecherche = new HBox(10);

        ligneRecherche.setAlignment(Pos.CENTER);

        ligneRecherche.getChildren().addAll(
                txtRecherche,
                btnRechercher
        );

        // Ajout dans la fenêtre principale
        root.getChildren().addAll(

                lblTitre,
                lblRecherche,
                ligneRecherche

        );

    }

    // --------------------- Partie centrale ---------------------------- //

    private void construireCentre() {

        // Taille de l'image
        imagePokemon.setFitWidth(170);
        imagePokemon.setFitHeight(170);
        imagePokemon.setPreserveRatio(true);

        // Construction de la carte
        cartePokemon.setAlignment(Pos.CENTER);
        cartePokemon.setPadding(new Insets(20));
        cartePokemon.getStyleClass().add("carte");
        cartePokemon.setMaxWidth(350);
        cartePokemon.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:15;" +
                        "-fx-border-color:lightgray;" +
                        "-fx-border-radius:15;"
        );

        // Ajout des informations
        cartePokemon.getChildren().addAll(

                imagePokemon,
                lblNom,
                lblId,
                lblTypes,
                lblTaille,
                lblPoids

        );

        // Ajout de la carte dans la fenêtre
        root.getChildren().add(cartePokemon);

    }

    // --------------------- Partie basse ---------------------------- //

    private void construireBas() {

        // Taille de la liste
        listPokemons.setPrefHeight(160);
        listPokemons.setPrefWidth(350);

        // Bouton supprimer
        btnSupprimer.setPrefWidth(160);

        HBox ligneBouton = new HBox(btnSupprimer);
        ligneBouton.setAlignment(Pos.CENTER);

        VBox bas = new VBox(10);
        bas.setAlignment(Pos.CENTER);

        bas.getChildren().addAll(

                lblListe,
                listPokemons,
                ligneBouton
        );

        // Ajout dans la fenêtre
        root.getChildren().add(bas);

    }

    // --------------------- Getters ---------------------------- //

    // Retourne le champ de recherche
    public TextField getTxtRecherche() {
        return txtRecherche;
    }

    // Retourne le bouton Rechercher
    public Button getBtnRechercher() {
        return btnRechercher;
    }

    // Retourne le bouton Supprimer
    public Button getBtnSupprimer() {
        return btnSupprimer;
    }

    // Retourne la liste des Pokémons
    public ListView<Pokemon> getListPokemons() {
        return listPokemons;
    }

    // --------------------- Affichage d'un Pokémon ---------------------------- //

    // Cette méthode met à jour la carte avec les informations du Pokémon
    public void afficherPokemon(Pokemon p) {

        // Mise à jour des informations
        lblNom.setText("Nom : " + p.getNom());
        lblId.setText("ID : " + p.getId());
        lblTypes.setText("Types : " + String.join(", ", p.getTypes()));
        lblTaille.setText("Taille : " + p.getTaille());
        lblPoids.setText("Poids : " + p.getPoids());

        // Mise à jour de l'image
        imagePokemon.setImage(new Image(p.getImageUrl()));

    }

    // --------------------- Ajout dans la liste ---------------------------- //

    // Ajoute un Pokémon dans la ListView
    public void ajouterPokemon(Pokemon p) {

        // On évite les doublons
        if (!listPokemons.getItems().contains(p)) {

            listPokemons.getItems().add(p);

        }

    }

}