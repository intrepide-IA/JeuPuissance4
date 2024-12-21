package jeu.puissance4;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuPrincipal {
    private Scene scene;
    private App app;

    public MenuPrincipal(App app) {
        this.app = app;

        // Conteneur principal avec 20 espacement verticale
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #1E3A8A;");//Style du conteneur

        // Les boutons du mode du jeu
        Button deuxJoueurs = creerBouton("Deux Joueurs");
        Button vsBot = creerBouton("VS Bot");
        Button reseau = creerBouton("En Réseau");
        Button parametres = creerBouton("Paramètres");

        // Désactivation des boutons qui ne sont pas encore implémentés
        vsBot.setDisable(true);
        reseau.setDisable(true);
        parametres.setDisable(true);

        // appel a la classe qui recuper le nom des deux joueurs
        deuxJoueurs.setOnAction(e -> app.lancerConfigurationJoueurs());

        // Ajout des boutons au conteneur
        root.getChildren().addAll(deuxJoueurs, vsBot, reseau, parametres);

        // Créer la scène avec des dimensions fixeée
        scene = new Scene(root, 500, 500);
    }
     // recupérer et retourner la scene
    @SuppressWarnings("exports")
    public Scene getScene() {
        return scene;
    }

    // Méthode pour créer le style des  bouton
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

        // Effets quand on survol
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
