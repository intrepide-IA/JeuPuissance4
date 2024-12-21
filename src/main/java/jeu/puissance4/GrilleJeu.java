package jeu.puissance4;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class GrilleJeu {
    private static final int ROWS = 6;      // Nombre de lignes
    private static final int COLS = 7;      // Nombre de colonnes
    private static final int CELL_SIZE = 60; // Taille des cellules

    private Circle[][] circles;             // Représentation visuelle des cases
    private int[][] grid;                   // Représentation logique des cases
    private GridPane gridPane;              // Conteneur de la grille

    public GrilleJeu() {
        circles = new Circle[ROWS][COLS];
        grid = new int[ROWS][COLS];
        gridPane = new GridPane();
        initialiserGrille();
    }

    private void initialiserGrille() {
        // Style général de la grille
        gridPane.setStyle("-fx-background-color: #1E3A8A; " +
                          "-fx-padding: 14; " +
                          "-fx-hgap: 13; " +  // Espacement horizontal entre les cellules
                          "-fx-vgap: 10; "); // Espacement vertical entre les cellules

        // Ajouter les cercles représentant les cases
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Circle circle = creerCase(); // Créer une case stylisée
                circles[row][col] = circle;  // Stocker la case
                gridPane.add(circle, col, row); // Ajouter au GridPane
            }
        }
    }

    // Méthode pour créer une case stylisée
    private Circle creerCase() {
        Circle circle = new Circle(CELL_SIZE / 2 - 5);
        circle.setFill(Color.WHITE); // Couleur par défaut : blanc
        circle.setEffect(new DropShadow(5, Color.GRAY)); // Ombre subtile pour donner de la profondeur
        return circle;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public boolean estCaseVide(int row, int col) {
        return grid[row][col] == 0;
    }

    public boolean placerJeton(int col, int joueur) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][col] == 0) {
                grid[row][col] = joueur;
                // Mise à jour de la couleur en fonction du joueur
                circles[row][col].setFill(joueur == 1 ? 
                    Color.RED :
                    Color.YELLOW);
                return true;
            }
        }
        return false;
    }
}
