package jeu.puissance4;

public class LogiqueJeu {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private int[][] grid;

    public LogiqueJeu() {
        grid = new int[ROWS][COLS];
    }

    //methode pour verifier si un joueur a gagner 
    public boolean verifierVictoire(int row, int col, int joueur) {
        return verifierHorizontal(row, joueur) ||
               verifierVertical(col, joueur) ||
               verifierDiagonalDescendante(row, col, joueur)||
               verifierDiagonalMontante(row, col, joueur);
    }

    private boolean verifierHorizontal(int row, int joueur) {
        int count = 0;
        for (int col = 0; col < COLS; col++) {
            count = (grid[row][col] == joueur) ? count + 1 : 0;
            if (count >= 4) return true;
        }
        return false;
    }

    private boolean verifierVertical(int col, int joueur) {
        int count = 0;
        for (int row = 0; row < ROWS; row++) {
            count = (grid[row][col] == joueur) ? count + 1 : 0;
            if (count >= 4) return true;
        }
        return false;
    }

    private boolean verifierDiagonalDescendante(int row, int col, int joueur) {
        int count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row + i;
            int c = col + i;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
                count = (grid[r][c] == joueur) ? count + 1 : 0;
                if (count >= 4) return true;
            }
        }
        return false;
    }

    private boolean verifierDiagonalMontante(int row, int col, int joueur) {
        int count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row - i;
            int c = col + i;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
                count = (grid[r][c] == joueur) ? count + 1 : 0;
                if (count >= 4) return true;
            }
        }
        return false;
    }
    
    //verifier quelle type de vistoire il s'agit
    public String verifierVictoireAvecType(int row, int col, int joueur) {
        if (verifierHorizontal(row, joueur)) return "HORIZONTALE";
        if (verifierVertical(col, joueur)) return "VERTICALE";
        if (verifierDiagonalDescendante(row, col, joueur)) return "DIAGONALE DESCENDANTE";
        if (verifierDiagonalMontante(row, col, joueur)) return "DIAGONALE MONTANTE";
        return "AUCUNE";
    }

    public void setGrid(int row, int col, int valeur) {
        grid[row][col] = valeur;
    }
}
