package jeu.puissance4;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;

public class SplashScreen {
    private Scene scene;

    public SplashScreen() {
        // Creation du conteneur principle
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #1E3A8A;"); // background de l'arrière plan

        // Le titre de notre jeu
        Label titre = new Label("Puissance 4");
        titre.setFont(Font.font("Roboto", 48)); // Style de police
        titre.setTextFill(Color.WHITE); // couleur du texte

        // Code pour ajouter un shadow au texte
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        titre.setEffect(shadow);

        // Ajouter le titre au conteneur ou dans le frame
        root.getChildren().add(titre);

        // Créer la scène avec des dimensions fixeée
        scene = new Scene(root, 500, 500);
    }

    // recupérer et retourner la scene
    @SuppressWarnings("exports")
    public Scene getScene() {
        return scene;
    }
}
