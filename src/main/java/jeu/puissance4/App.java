package jeu.puissance4;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private static App instance;

    private Stage primaryStage;
    private SplashScreen splashScreen;
    private MenuPrincipal menuPrincipal;
    private ConfigurationJoueurs configJoueurs;
    private JeuPuissance4 jeuPuissance4;

    @Override
    public void start(Stage primaryStage) {
        instance = this; 
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Puissance 4");
        
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        // Démarrer avec l'écran de démarrage
        lancerSplashScreen();
    }

    public static App getInstance() {
        return instance;
    }

    private void lancerSplashScreen() {
        splashScreen = new SplashScreen();
        primaryStage.setScene(splashScreen.getScene());
        primaryStage.show();

        // Transition vers le menu principal après 3 secondes
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                javafx.application.Platform.runLater(() -> lancerMenuPrincipal());
            }
        }, 3000);
    }

    void lancerMenuPrincipal() {
        menuPrincipal = new MenuPrincipal(this);
        primaryStage.setScene(menuPrincipal.getScene());
    }

    public void lancerConfigurationJoueurs() {
        configJoueurs = new ConfigurationJoueurs(this);
        primaryStage.setScene(configJoueurs.getScene());
    }

    public void lancerJeu(String joueur1, String joueur2) {
        jeuPuissance4 = new JeuPuissance4(joueur1, joueur2);
        primaryStage.setScene(jeuPuissance4.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}