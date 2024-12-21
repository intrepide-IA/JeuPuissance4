module jeu.puissance4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens jeu.puissance4 to javafx.fxml;
    exports jeu.puissance4;
}
