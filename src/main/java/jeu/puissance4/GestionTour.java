package jeu.puissance4;

public class GestionTour {
    private String joueur1;
    private String joueur2;
    private boolean tourJoueur1;

    public GestionTour(String joueur1, String joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.tourJoueur1 = true;
    }

    public String getJoueurActuel() {
        return tourJoueur1 ? joueur1 : joueur2;
    }

    public int getNumeroJoueurActuel() {
        return tourJoueur1 ? 1 : 2;
    }

    public void changerTour() {
        tourJoueur1 = !tourJoueur1;
    }
}