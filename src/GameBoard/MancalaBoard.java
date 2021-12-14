package GameBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MancalaBoard {
    private GameState currentState;
//    private final int player_1_box = 6;
//    private final int player_2_box = 13;
    public int current_turn = 0; // 0 for player 1, 1 for player 2
    private boolean isEndOfGame = false;
    private LinkedList<Integer> boxSelected;

    public MancalaBoard() {
        currentState = new GameState();
        boxSelected = new LinkedList<>();
    }


    public boolean clickSquare(int n_box) {
        if (this.isEndOfGame) {
            System.out.println("End of Game Achieved");
            return false;
        }
        System.out.printf("Current Turn: P%d\n", (this.current_turn)%2+1);
        this.boxSelected.addFirst(n_box);
        System.out.printf("Box Selected: %d\n\n", n_box);

        GameState ns = this.currentState.nextState(n_box, (this.current_turn%2)==0);
        boolean changeTurn = false;
        if (ns!=null) {
            this.currentState=ns;
//            System.out.println(ns);
            if (!ns.isFreeTurn()) {
                this.current_turn=(this.current_turn+1)%2;
                changeTurn=true;
            }
            if (ns.isEndState()) {
                this.isEndOfGame = true;
            }
        }

        return changeTurn;

    }


    @Override
    public String toString() {
        return this.currentState.toString();
    }

    public GameState getStateCopy() {
        int[] newBoard = this.currentState.boardSpaces;
        return new GameState(newBoard);
    }

    public boolean allowFreeMove() {
        return this.currentState.isFreeTurn();
    }

    public boolean isEndOfGame() {
        return this.currentState.isEndState();
    }

//    @Override
//    public String toString() {
//        StringBuilder board = new StringBuilder();
////        board.append("Current Turn: ");
////        if ((this.current_turn+1)%2==0) board.append("P1").append("\n");
////        else board.append("P2").append("\n");
//        board.append("Mancala Board: \n");
//        board.append("P2: ").append(this.boardSpaces[this.player_2_box]).append("\n");
//        board.append("-------------------------------------\n");
//        board.append("|\t|\t");
//
//        for (int i = 12; i > 6; i--) {
//            board.append(this.boardSpaces[i]);
//            if (i > 7) board.append(" | ");
//            else board.append(" ");
//        }
//
//
//        board.append("\t|\t|");
//
//        board.append("\n");
//
//        board.append("| ").append("P2").append("|\t");
//        board.append("----".repeat(5));
//        board.append("\t| ").append("P1").append("|");
//
//        board.append("\n");
//
//        board.append("|\t|\t");
//        for (int i = 0; i < 6; i++) {
//            board.append(this.boardSpaces[i]);
//            if (i < 5) board.append(" | ");
//            else board.append(" ");
//        }
//        board.append("\t|\t|\n");
//        board.append("-------------------------------------\n");
//        board.append("\t\t\t\t\t\t\tP1: ").append(this.boardSpaces[this.player_1_box]);
//        board.append("\n\n");
//
//        return board.toString();
//    }


}


