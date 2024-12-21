package jeu.puissance4;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JeuPuissance4 {
    private Scene scene;
    private GrilleJeu grille;
    private LogiqueJeu logique;
    private GestionTour gestionTour;
    private Label statusLabel;
    private String joueur1;
    private String joueur2;
    private GridPane buttonGrid;

    public JeuPuissance4(String joueur1, String joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        initialiserJeu();
    }

    private void initialiserJeu() {
        grille = new GrilleJeu();
        logique = new LogiqueJeu();
        gestionTour = new GestionTour(joueur1, joueur2);

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20; -fx-background-color: #1E3A8A;");

        // Application de la typographie et couleur pour le statusLabel
        statusLabel = new Label("Tour de " + gestionTour.getJoueurActuel());
        statusLabel.setFont(Font.font("Roboto", 20));
        statusLabel.setTextFill(Color.WHITE);

        // Création des boutons pour contrôler le jeux dans une HBox
        HBox controlBox = new HBox(10);
        controlBox.setAlignment(Pos.CENTER);

        // Boutons de jeu avec effet de survol et couleur
        buttonGrid = new GridPane();
        buttonGrid.setHgap(5);
        HBox.setHgrow(buttonGrid, Priority.ALWAYS);
        
        for (int col = 0; col < 7; col++) {
            final int colonne = col;
            Button button = new Button("↓");
            button.setPrefWidth(60);
            button.setStyle("-fx-background-color: #1E3A8A; -fx-text-fill: white; -fx-font-size: 18px;");
            button.setOnAction(e -> jouerCoup(colonne));
            button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #38B2AC; -fx-text-fill: white; -fx-font-size: 18px;"));
            button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #1E3A8A; -fx-text-fill: white; -fx-font-size: 18px;"));

            buttonGrid.add(button, col, 0);
        }

        // Bouton de retour
        Button backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color:rgb(255, 47, 0); -fx-text-fill: white;");
        backButton.setOnAction(e -> {
            // Retourner à l'écran de configuration des joueurs
            App.getInstance().lancerConfigurationJoueurs();
        });

        // Bouton Reset
        Button resetButton = new Button("Recommencer");
        resetButton.setStyle("-fx-background-color:rgb(255, 166, 0); -fx-text-fill: white;");
        resetButton.setOnAction(e -> reinitialiserJeu());
        
        // HBox pour le label et le bouton Reset
        HBox topBox = new HBox(70);
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(backButton, statusLabel, resetButton);

        root.getChildren().addAll(topBox, buttonGrid, grille.getGridPane());
        scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(getClass().getResource("/jeu/puissance4/style.css").toExternalForm());//liens vers le style css
    }

    private void reinitialiserJeu() {
        // Supprime tous les éléments actuels
        grille = new GrilleJeu();
        logique = new LogiqueJeu();
        gestionTour = new GestionTour(joueur1, joueur2);

        // Mettre à jour l'interface
        VBox root = (VBox) scene.getRoot();
        root.getChildren().set(2, grille.getGridPane());
        statusLabel.setText("Tour de " + gestionTour.getJoueurActuel());

        // Réactiver tous les boutons
        for (var node : buttonGrid.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(false);
            }
        }
    }

    private void jouerCoup(int colonne) {
        int joueurActuel = gestionTour.getNumeroJoueurActuel();
        
        if (grille.placerJeton(colonne, joueurActuel)) {
            for (int row = 5; row >= 0; row--) {
                if (grille.estCaseVide(row, colonne)) {
                    logique.setGrid(row + 1, colonne, joueurActuel);
                    
                    // Vérifier la victoire
                    String typeVictoire = logique.verifierVictoireAvecType(row + 1, colonne, joueurActuel);
                    if (!typeVictoire.equals("AUCUNE")) {
                        afficherPopupVictoire(gestionTour.getJoueurActuel(), typeVictoire);
                        desactiverBoutons();
                        return;
                    }
                    break;
                }
            }
            
            gestionTour.changerTour();
            statusLabel.setText("Tour de " + gestionTour.getJoueurActuel());
        }
    }

    private void afficherPopupVictoire(String joueur, String typeVictoire) {
    // Création d'un Stage personnalisé comme pop-up
    Stage popupStage = new Stage();
    popupStage.initModality(Modality.APPLICATION_MODAL); // Empêche l'interaction avec la fenêtre principale
    popupStage.setTitle("Victoire !");

    // Création d'un VBox pour le contenu du pop-up
    VBox vbox = new VBox(10);
    vbox.setAlignment(Pos.CENTER);
    vbox.setStyle("-fx-background-color: #2B6B6B; -fx-padding: 20;");

    // Message de victoire
    Label message = new Label(joueur + " a gagné avec une ligne " + typeVictoire + " !");
    message.setFont(Font.font("Arial", 16));
    message.setTextFill(Color.WHITE);

    // Bouton OK pour fermer le pop-up
    Button okButton = new Button("OK");
    okButton.setStyle("-fx-background-color: #FF8C00; -fx-text-fill: white; -fx-font-size: 14px;");
    okButton.setOnAction(e -> {
        // Fermer le pop-up et réinitialiser le jeu
        popupStage.close();
        reinitialiserJeu();
    });

    // Ajouter le message et le bouton dans le VBox
    vbox.getChildren().addAll(message, okButton);

    // Créer une scène pour le pop-up et l'ajouter au stage
    Scene scene = new Scene(vbox, 300, 150);
    scene.getStylesheets().add(getClass().getResource("/jeu/puissance4/style.css").toExternalForm());
    popupStage.setScene(scene);

    // Afficher le pop-up
    popupStage.showAndWait();
}


    private void desactiverBoutons() {
        VBox root = (VBox) scene.getRoot();
        GridPane buttonGrid = (GridPane) root.getChildren().get(1);
        for (var node : buttonGrid.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }
    }

    public Scene getScene() {
        return scene;
    }
}
