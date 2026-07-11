package com.cours;

import com.cours.view.PokemonViewFx;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Création de notre interface graphique
        new PokemonViewFx(primaryStage);

    }
}