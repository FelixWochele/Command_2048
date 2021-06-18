package Project_2048;

import java.util.Random;

public class Board {

    public enum Direction {Up, Down, Left, Right}

    ;


    public boolean gameOverFlag = false;
    private int[][] board;
    int score;

    public Board(int boardSize) {

        board = new int[boardSize][boardSize];
        score = 0;

    }

    public void move(Direction directtion) {


        //Erzeuge temporäres Array zum zwischenspeichern
        int[][] tempArray = new int[board.length][board.length];
        int x;
        boolean sumUpLast = false;

        //Fälle für die einzelnen richtungen -> jedes mal das selbe nur die 2D itteretierichtung ist unterschiedlich (j<->k)
        switch (directtion) {

            case Up:

                //Verschieben nach oben
                x = 0;
                for (int j = 0; j < board.length; j++) {
                    for (int k = 0; k < board.length; k++) {

                        //Wenn auf ein nicht leeres Feld gestoßen wird
                        if (board[k][j] != 0) {

                            //Prüfe ob Vorgänger gleich
                            if (x != 0 && tempArray[x - 1][j] == board[k][j] && !sumUpLast) {
                                //Verdopple letztes Element
                                tempArray[x - 1][j] = tempArray[x - 1][j] * 2;
                                score = score + tempArray[x - 1][j];
                                sumUpLast = true;
                            } else {
                                //Füge neues Element ein und counter++
                                tempArray[x][j] = board[k][j];
                                x++;
                                sumUpLast = false;
                            }
                        }
                    }
                    x = 0;
                }

                break;

            case Down:

                //Verschieben nach unten
                x = board.length - 1;
                for (int j = 0; j < board.length; j++) {
                    for (int k = board.length - 1; k >= 0; k--) {

                        //Wenn auf ein nicht leeres Feld gestoßen wird
                        if (board[k][j] != 0) {

                            //Prüfe ob Vorgänger gleich
                            if (x != board.length - 1 && tempArray[x + 1][j] == board[k][j] && !sumUpLast) {
                                //Verdopple letztes Element
                                tempArray[x + 1][j] = tempArray[x + 1][j] * 2;
                                score = score + tempArray[x + 1][j];
                                sumUpLast = true;
                            } else {
                                //Füge neues Element ein und counter++
                                tempArray[x][j] = board[k][j];
                                x--;
                                sumUpLast = false;
                            }
                        }
                    }
                    x = board.length - 1;
                }

                break;

            case Left:

                //Verschieben nach links
                x = 0;
                for (int j = 0; j < board.length; j++) {
                    for (int k = 0; k < board.length; k++) {

                        //Wenn auf ein nicht leeres Feld gestoßen wird
                        if (board[j][k] != 0) {

                            //Prüfe ob Vorgänger gleich
                            if (x != 0 && tempArray[j][x - 1] == board[j][k] && !sumUpLast) {
                                //Verdopple letztes Element
                                tempArray[j][x - 1] = tempArray[j][x - 1] * 2;
                                score = score + tempArray[j][x - 1];
                                sumUpLast = true;
                            } else {
                                //Füge neues Element ein und counter++
                                tempArray[j][x] = board[j][k];
                                x++;
                                sumUpLast = false;
                            }
                        }
                    }
                    x = 0;
                }

                break;

            case Right:

                //Verschieben nach rechts
                x = board.length - 1;
                for (int j = 0; j < board.length; j++) {
                    for (int k = board.length - 1; k >= 0; k--) {

                        //Wenn auf ein nicht leeres Feld gestoßen wird
                        if (board[j][k] != 0) {

                            //Prüfe ob Vorgänger gleich
                            if (x != board.length - 1 && tempArray[j][x + 1] == board[j][k] && !sumUpLast) {
                                //Verdopple letztes Element
                                tempArray[j][x + 1] = tempArray[j][x + 1] * 2;
                                score = score + tempArray[j][x + 1];
                                sumUpLast = true;
                            } else {
                                //Füge neues Element ein und counter++
                                tempArray[j][x] = board[j][k];
                                x--;
                                sumUpLast = false;
                            }
                        }
                    }
                    x = board.length - 1;
                }

                break;

            default:
                break;

        }

        //Setze das board
        board = tempArray;

    }


    //Generiert ein neues Tile an einer freien Position (10% -> 4 | 90% -> 2)
    public void generateNewNumbers() {

        //Generiert eine zufällige Zahl zum bestimmen der nächsten
        int nextNumber;
        Random random = new Random();
        int randomNumber = random.nextInt(10);

        //Wähle die nächste Zahl (10% -> 4 | 90% -> 2)
        if (randomNumber == 1) {
            nextNumber = 4;
        } else {
            nextNumber = 2;
        }

        //Schauen wie viele freie Felder es gibt
        int freeFields = 0;
        for (int j = 0; j < board.length; j++) {
            for (int k = 0; k < board.length; k++) {
                //Bei einem freien Feld wird die laufvariable hochgezählt
                if (board[j][k] == 0) {
                    freeFields++;
                }
            }
        }

        //Keine freien Felder -> gameOver
        if (freeFields == 0) {
            gameOverFlag = true;
        } else {

            randomNumber = random.nextInt(freeFields);
            int fieldCount = 0;

            //Die neue zahl in ein zufälliges freies Feld einsetzen
            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < board.length; k++) {

                    //Bei einem freien Feld wird die laufvariable hochgezählt
                    if (board[j][k] == 0) {
                        //Ist das Feld frei und die laufvariable erreicht wird das Feld gesetzt
                        if (randomNumber == fieldCount) {
                            board[j][k] = nextNumber;
                        }
                        fieldCount++;
                    }
                }
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

}
