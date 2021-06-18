package Project_2048;

import java.awt.*;

public class GameEvaluation {


    private Point bestPoint;
    private Point worstPoint;
    private int[][] gameBoard;
    private int[][] resBoard;


    public GameEvaluation() {
        this.bestPoint = new Point(0, 0);
        this.worstPoint = new Point(0, 0);

    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
        resBoard = new int[gameBoard.length][gameBoard.length];
    }

    private void evaluateBoard(int numberToEvaluate) {

        //1: 2/4 Count
        for (int j = 0; j < gameBoard.length; j++) {
            for (int k = 0; k < gameBoard.length; k++) {
                if (gameBoard[j][k] == numberToEvaluate) {
                    for (int x = 0; x < 4; x++) {
                        resBoard[x][k] = resBoard[x][k] + 1;
                        resBoard[j][x] = resBoard[j][x] + 1;
                    }
                }
            }
        }


        //TO-DO: Funktioniert nicht :D

        //2: Platz
        //Horizontaler durchgang
        int toAdd = 0;
        for (int j = 0; j < gameBoard.length; j++) {
            for (int k = 0; k < gameBoard.length; k++) {
                if (gameBoard[j][k] != 0) {
                    toAdd = toAdd + gameBoard[j][k];
                }
            }
            for (int x = 0; x < 4; x++) {
                resBoard[j][x] = resBoard[j][x] + toAdd / 100;
            }
            toAdd = 0;
        }
        //Vertikaler durchgang
        for (int j = 0; j < gameBoard.length; j++) {
            for (int k = 0; k < gameBoard.length; k++) {
                if (gameBoard[k][j] != 0) {
                    toAdd = toAdd + gameBoard[k][j];
                }
            }
            for (int x = 0; x < 4; x++) {
                resBoard[x][j] = resBoard[x][j] + toAdd / 100;
            }
            toAdd = 0;
        }

        //3: Differenz

        //Lösche alle Felder an denen eine Zahl steht, da diese Punkte nich in frage kommen
        for (int j = 0; j < gameBoard.length; j++) {
            for (int k = 0; k < gameBoard.length; k++) {
                if (gameBoard[j][k] != 0) {
                    resBoard[j][k] = 0;
                }
            }
        }

    }

    public Point getBestEvaluation(int numberToEvaluate) {
        evaluateBoard(numberToEvaluate);
        return bestPoint;
    }

    public Point getWorstEvaluation(int numberToEvaluate) {
        evaluateBoard(numberToEvaluate);
        return worstPoint;
    }

    public int[][] getResBoard() {
        return resBoard;
    }
}
