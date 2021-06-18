package Project_2048;

import java.awt.*;
import java.util.Scanner;

public class CommandlineOutput {

    public CommandlineOutput() {

    }

    public void startGameLoop() {

        boolean gameLoopFlag = true;
        int[][] res;


        Scanner scanner = new Scanner(System.in);

        //möglichkeit die größe des Spielfelds zu bestimmen
        System.out.println("Pleas enter the boad size (e.g. '4')");
        int boardSize = scanner.nextInt();

        //Erzeugt instatz eines Spielfelds
        Board board = new Board(boardSize);
        GameEvaluation gameEvaluation = new GameEvaluation();

        //Generiert zu beginn zwei Zahlen zum Spielen
        board.generateNewNumbers();
        board.generateNewNumbers();

        //Gibt das gamboard per cmd line aus -> einmal am Anfang
        System.out.println("Score:  " + board.score);
        res = board.getBoard();
        printArrayToCmd(res);


        //Gameloop
        while (gameLoopFlag) {


            String inputString = scanner.next();

            //Wertet input aus (Welche Richtung?)
            switch (inputString) {
                case "u" -> board.move(Board.Direction.Up);
                case "d" -> board.move(Board.Direction.Down);
                case "l" -> board.move(Board.Direction.Left);
                case "r" -> board.move(Board.Direction.Right);
                case "exit" -> gameLoopFlag = false;
                default -> System.out.println("Didnt got it");
            }

            /*
            //Evaluate the game Board -> Nur zum testen
            gameEvaluation.setGameBoard(board.getBoard());
            //Point bestPointFor2 = gameEvaluation.getBestEvaluation(2);
            Point bestPointFor4 = gameEvaluation.getBestEvaluation(4);
            System.out.println("Evaluation of the Board : ");
            printArrayToCmd(gameEvaluation.getResBoard());
            System.out.println("");
             */

            //Generiere eine neue Nummber nach jedem zug
            board.generateNewNumbers();

            //Test für gameOverFlag
            if (board.gameOverFlag) {
                gameLoopFlag = false;
                System.out.println("Game Over!");
            }

            //Gibt das gamboard per cmd line aus
            System.out.println("Score:  " + board.score);
            res = board.getBoard();
            printArrayToCmd(res);

        }
    }

    //Funktion zur Ausgabe eines 2D-Arrays per Konsole
    private static void printArrayToCmd(int[][] array) {

        for (int[] x : array) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

}
