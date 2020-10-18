package com.company;

import java.util.*;

public class Main {

    public static final Random RANDOM = new Random();
    public static int CompScore = 0;
    public static int UserScore = 0;
    public static int rounds = 1;


    public static void main(String[] args) {

        KrestikiNoliki();

    }


    public static void KrestikiNoliki(){
        Board b = new Board();
        Scanner scanner = new Scanner(System.in);
        b.displayBoard();
        System.out.println("Оберіть хто буде ходити перший:\n 1. Комп'ютер (X) / 2. Користувач (0) : ");

        int choice = scanner.nextInt();

        if (choice == Board.PLAYER_X){
            Point p = new Point(RANDOM.nextInt(3), RANDOM.nextInt(3));
            b.placeAMove(p, Board.PLAYER_X);
            b.displayBoard();
        }

        while (!b.isGameOver()){
            boolean moveOk = true;

            do {
                if (!moveOk){
                    System.out.println("Комірка вже заповнена!");
                }
                System.out.println("Ваш крок: ");
                Point userMove = new Point(scanner.nextInt(), scanner.nextInt());
                moveOk = b.placeAMove(userMove, Board.PLAYER_O);
            } while (!moveOk);

            b.displayBoard();

            if (b.isGameOver())
                break;

            b.minimax(0, Board.PLAYER_X);
            System.out.println("Комп'ютер обрав координати: " + b.computerMove);

            b.placeAMove(b.computerMove, Board.PLAYER_X);
            b.displayBoard();
        }

        if (b.hasPlayerWon(Board.PLAYER_X)) {
            CompScore = CompScore + 1;
            System.out.println("Ви програли!");
            System.out.println("Переміг комп'ютер!!");
        } else if (b.hasPlayerWon(Board.PLAYER_O)) {
            UserScore = UserScore + 1;
            System.out.println("Ви виграли!");
            System.out.println("Переміг користувач!!");
        } else {
            System.out.println("Нічия!");
        }

        System.out.println("\nРахунок: \n Комп'ютер - " + CompScore + "\n Користувач - " + UserScore);

        System.out.println("\nБажаєте продовжити гру?\n 1. Так\n 2. Ні");
        int reverse = scanner.nextInt();

        if (reverse == 1) {
            rounds = rounds + 1;
            KrestikiNoliki();
        } else if (reverse == 2){
            if (CompScore > UserScore){
                System.out.println("За результатами " + rounds + " раундів перемагає Комп'ютер з рахунком - " + CompScore + ":" + UserScore);
            } else {
                System.out.println("За результатами " + rounds + " раундів перемагає Користувач з рахунком - " + UserScore + ":" + CompScore);
            }
            System.exit(0);
        } else {
            if (CompScore > UserScore){
                System.out.println("За результатами " + rounds + " раундів перемагає Комп'ютер з рахунком - " + CompScore + ":" + UserScore);
            } else {
                System.out.println("За результатами " + rounds + " раундів перемагає Користувач з рахунком - " + UserScore + ":" + CompScore);
            }
            System.exit(0);
        }
    }
}
