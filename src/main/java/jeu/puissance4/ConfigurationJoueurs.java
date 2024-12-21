package jeu.puissance4;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ConfigurationJoueurs {
    private Scene scene;
    private App app;

    public ConfigurationJoueurs(App app) {
        this.app = app;

        // Conteneur principal
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #1E3A8A;");

        // Titre
        Label titre = new Label("Entrez les noms des joueurs");
        titre.setFont(Font.font("Roboto", 20));
        titre.setTextFill(Color.WHITE);

        // Champs de texte
        TextField joueur1Field = creerChampTexte("Nom du Joueur 1");
        TextField joueur2Field = creerChampTexte("Nom du Joueur 2");

        // Boutons
        Button commencer = creerBouton("Commencer la partie");
        commencer.setOnAction(e -> {
            String j1 = joueur1Field.getText().isEmpty() ? "Joueur 1" : joueur1Field.getText();
            String j2 = joueur2Field.getText().isEmpty() ? "Joueur 2" : joueur2Field.getText();
            app.lancerJeu(j1, j2);
        });

        Button backButton = creerBouton("Retour");
        backButton.setOnAction(e -> app.lancerMenuPrincipal());

        // Ajouter les éléments au conteneur
        root.getChildren().addAll(titre, joueur1Field, joueur2Field, commencer, backButton);

        // Créer la scène
        scene = new Scene(root, 500, 500);
    }

    public Scene getScene() {
        return scene;
    }

    // Méthode pour créer un champ de texte stylisé
    private TextField creerChampTexte(String placeholder) {
        TextField champTexte = new TextField();
        champTexte.setPromptText(placeholder);
        champTexte.setStyle(
                "-fx-background-color: #EFF6FF; " + 
                "-fx-text-fill: #1E3A8A; " +       
                "-fx-padding: 10; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; "
        );

        champTexte.setOnMouseEntered(e -> champTexte.setStyle(
                "-fx-background-color: #DBEAFE; " + 
                "-fx-text-fill: #1E3A8A; " +
                "-fx-padding: 10; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; "
        ));
        champTexte.setOnMouseExited(e -> champTexte.setStyle(
                "-fx-background-color: #EFF6FF; " + 
                "-fx-text-fill: #1E3A8A; " +
                "-fx-padding: 10; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; "
        ));
        return champTexte;
    }

    // Méthode pour créer un bouton stylisé
    private Button creerBouton(String texte) {
        Button bouton = new Button(texte);
        bouton.setFont(Font.font("Roboto", 18));
        bouton.setStyle(
                "-fx-background-color: #3B82F6; " + 
                "-fx-text-fill: white; " +
                "-fx-padding: 10 20; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; "
        );

        // Effets de survol
        bouton.setOnMouseEntered(e -> bouton.setStyle(
                "-fx-background-color: #2563EB; " +
                "-fx-text-fill: white; " +
                "-fx-padding: 10 20; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; "
        ));
        bouton.setOnMouseExited(e -> bouton.setStyle(
                "-fx-background-color: #3B82F6; " +
                "-fx-text-fill: white; " +
                "-fx-padding: 10 20; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; "
        ));

        return bouton;
    }
}
