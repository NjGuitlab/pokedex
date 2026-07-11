package com.cours.controller;

import com.cours.modele.Pokemon;
import com.cours.modele.PokemonDAO;
import com.cours.service.PokemonApiService;
import com.cours.view.PokemonViewFx;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.application.Platform;

public class PokemonController {


    private final PokemonDAO dao = new PokemonDAO();
    private final PokemonApiService service = new PokemonApiService();
    private final PokemonViewFx view;

    public PokemonController(PokemonViewFx view) {

        this.view = view;
        // On initialise les événements
        initialiser();
        // On charge les Pokémons déjà enregistrés
        chargerPokemon();

    }

    // --------------------- Initialisation ---------------------------- //

    private void initialiser() {

        view.getBtnRechercher().setOnAction(e -> {

            try {
                rechercherPokemon(view.getTxtRecherche().getText());

            } catch (Exception ex) {

                afficherErreur(ex.getMessage());

            }

        });

        view.getBtnSupprimer().setOnAction(e -> supprimerPokemon());

        // Quand on clique sur un Pokémon de la liste
        view.getListPokemons()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, ancien, nouveau) -> {

                    if (nouveau != null) {
                        view.afficherPokemon(nouveau);
                    }

                });

    }

    // --------------------- Recherche ---------------------------- //

    public void rechercherPokemon(String recherche) {

        new Thread(() -> {

            try {
                // Recherche dans l'API
                Pokemon p = service.recuperer(recherche);
                // Sauvegarde BD
                dao.sauvegarder(p);
                // Mise à jour de l'interface
                Platform.runLater(() -> {
                    view.afficherPokemon(p);
                    view.ajouterPokemon(p);
                    view.getTxtRecherche().clear();
                });

            } catch (Exception e) {

                Platform.runLater(() ->
                        afficherErreur("Pokémon introuvable ou erreur API.")
                );

            }

        }).start();

    }

    // --------------------- Chargement de la liste ---------------------------- //

    private void chargerPokemon() {

        try {
            view.getListPokemons().getItems().clear();
            view.getListPokemons().getItems().addAll(
                    dao.recupererTous()
            );

        } catch (Exception e) {

            afficherErreur(e.getMessage());

        }

    }

    // -------------------- Supprimer Pokemon ------------------------------------ //

    private void supprimerPokemon() {

        // Pokémon sélectionné
        Pokemon p = view.getListPokemons()
                .getSelectionModel()
                .getSelectedItem();

        // Rien de sélectionné
        if (p == null) {
            return;
        }

        // Fenêtre de confirmation
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Suppression");
        confirmation.setHeaderText(null);
        confirmation.setContentText(
                "Voulez-vous supprimer " + p.getNom() + " ?"
        );

        if (confirmation.showAndWait().orElse(ButtonType.CANCEL)
                == ButtonType.OK) {

            try {
                // Suppression BD
                dao.supprimer(p.getId());

                // Suppression ListView
                view.getListPokemons()
                        .getItems()
                        .remove(p);

            } catch (Exception e) {

                afficherErreur(e.getMessage());

            }

        }

    }

    // ---------- gestion des erreurs -------------- //
    private void afficherErreur(String message){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

}