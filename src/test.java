import GameBoard.MancalaBoard;
import Heuristics.*;
import Player.Player;

import java.util.Scanner;

public class test {
    public static void BoardCheck() {
        MancalaBoard mb = new MancalaBoard();
        System.out.println(mb);
        mb.clickSquare(6);
        System.out.println(mb);
        //p2
        mb.clickSquare(2);
        System.out.println(mb);
        mb.clickSquare(1);
        System.out.println(mb);
        //p1
        mb.clickSquare(3);
        System.out.println(mb);
        mb.clickSquare(6);
        System.out.println(mb);
        mb.clickSquare(1);
        System.out.println(mb);
        //p2
        mb.clickSquare(5);
        System.out.println(mb);
        //p1
        mb.clickSquare(1);
        System.out.println(mb);
        //p2
        mb.clickSquare(3);
        System.out.println(mb);
        //p1
        mb.clickSquare(1);
        System.out.println(mb);
        //p2
        mb.clickSquare(2);
        System.out.println(mb);
        //p1
        mb.clickSquare(2);
        System.out.println(mb);
        //p2
        mb.clickSquare(2);
        System.out.println(mb);
        //p1
        mb.clickSquare(6);
        System.out.println(mb);
        mb.clickSquare(4);
        System.out.println(mb);
        //p2
        mb.clickSquare(1);
        System.out.println(mb);
        // 20
        // 8 1 8 2 0 0
        // 0 0 4 0 0 0
        // 5
        //p1
        mb.clickSquare(3);
        System.out.println(mb);
        mb.clickSquare(6);
        System.out.println(mb);
        mb.clickSquare(5);
        System.out.println(mb);
        //p2
        mb.clickSquare(5);
        System.out.println(mb);
        // 20
        // 9 0 8 2 0 0
        // 0 0 0 1 0 1
        // 7
        //p1
        mb.clickSquare(6);
        System.out.println(mb);
        mb.clickSquare(4);
        System.out.println(mb);
        //p2
        mb.clickSquare(3);
        System.out.println(mb);
        //p1
        mb.clickSquare(5);
        System.out.println(mb);
        //p2
        mb.clickSquare(5);
        System.out.println(mb);
        //p1
        mb.clickSquare(6);
        System.out.println(mb);
        //eog
        mb.clickSquare(5);
        System.out.println(mb);

    }

    public static void AB_search_test() {
        MancalaBoard mb = new MancalaBoard();
        System.out.println(mb);
        Player P1 = new Player(new Heuristic4(), 1, mb);
        Player P2 = new Player(new Heuristic6(), 2, mb);
//        P2.performMinimax();

        while(!mb.isEndOfGame()) {
            if (mb.current_turn%2==0) {
                P1.makeMove();
            }
            else {
                P2.makeMove();
            }

            System.out.println(mb);
        }
    }

    public static void singlePlayerTest() {
        MancalaBoard mb = new MancalaBoard();
        System.out.println(mb);
        Player P1 = new Player(new Heuristic1(), 1, mb);
        Scanner scn = new Scanner(System.in);
        while(!mb.isEndOfGame()) {
            if (mb.current_turn%2==0) {
                P1.makeMove();
            }
            else {
                int move = scn.nextInt();
                mb.clickSquare(move);
            }
            System.out.println(mb);
        }
    }

    public static void Player_test() {
        MancalaBoard mb = new MancalaBoard();
        Player P1 = new Player(new Heuristic1(), 1, mb);
        P1.turnOnTestMode();
        P1.makeMove();

    }

    public static void main(String[] args) {
//        BoardCheck();
        AB_search_test();
//        Player_test();
//        singlePlayerTest();
    }
}
