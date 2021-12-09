package GameBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MancalaBoard {
    private int[] boardSpaces;
    private final int player_1_box = 6;
    private final int player_2_box = 13;
    private int turns_played = 1;
    private int current_turn = 0; // 0 for player 1, 1 for player 2
    private boolean isEndOfGame = false;
    private LinkedList<Integer> boxSelected;

    private int player_1_row_sum;
    private int player_2_row_sum;

    public MancalaBoard() {
        this.boardSpaces = new int[14];
        for (int i = 0; i < 14; i++) {
            boardSpaces[i] = 4;
        }
        boardSpaces[player_1_box] = 0;
        boardSpaces[player_2_box] = 0;
        boxSelected = new LinkedList<>();
    }

    private boolean isRowEmpty() {
        this.player_1_row_sum = 0;
        for (int i=0;i<6;i++) {
            this.player_1_row_sum+=this.boardSpaces[i];
        }

        if (this.player_1_row_sum==0) return true;

        this.player_2_row_sum = 0;
        for(int i=7;i<13;i++) {
            this.player_2_row_sum+=this.boardSpaces[i];
        }

        if (this.player_2_row_sum==0) return true;
        return false;

    }

    public boolean clickSquare(int n_box) {
        if (this.isEndOfGame) {
            System.out.println("End of Game Achieved");
            return false;
        }
        System.out.printf("Current Turn: P%d\n", (this.current_turn)%2+1);
        this.boxSelected.addFirst(n_box);
        System.out.printf("Box Selected: %d\n\n", n_box);
        // the boxes will be 1 indexed
        int idx, forbiddenBox, playerBox, lowerBound, upperBound;
        boolean changeTurn = true;
        // if turn is for player 1
        if ((this.current_turn%2)==0) {
            idx = (n_box-1);
            forbiddenBox=13;
            playerBox=6;
            lowerBound=0;
            upperBound=5;
        }
        else {
            idx = (n_box+6);
            forbiddenBox=6;
            playerBox=13;
            lowerBound=7;
            upperBound=12;
        }

        if (this.boardSpaces[idx]==0) {
            System.out.println("0 box cannot be selected");
            return false;
        }

        int boxVal = this.boardSpaces[idx];

        // change boxVal to 0 for that index
        this.boardSpaces[idx] = 0;

        // increment all the next boxes
        while (boxVal>0) {
            idx = (idx+1)%14;
            if (idx!=forbiddenBox) {
                boxVal--;
                this.boardSpaces[idx]++;
                // last stone goes to player's box
                if (idx == playerBox && boxVal ==0) {
                    changeTurn = false;
                }
                // check for capture
                else if (boxVal == 0 && idx>=lowerBound && idx<=upperBound &&
                        this.boardSpaces[idx]==1 && this.boardSpaces[12-idx] != 0) {
                    int captureVal = this.boardSpaces[12-idx];
                    this.boardSpaces[12-idx]=0;
                    captureVal+=this.boardSpaces[idx];
                    this.boardSpaces[idx]=0;
                    this.boardSpaces[playerBox]+=captureVal;
                }

            }
        }

        if (isRowEmpty()) {
            if (this.player_1_row_sum == 0) {
                this.boardSpaces[this.player_2_box] += this.player_2_row_sum;

            }
            else {
                this.boardSpaces[this.player_1_box] += this.player_1_row_sum;
            }
            this.isEndOfGame = true;
            for(int i=0;i<14;i++) {
                if (!(i==this.player_1_box || i==this.player_2_box)) {
                    this.boardSpaces[i]=0;
                }
            }
            return true;
        }
        if (changeTurn) {
            this.current_turn=(this.current_turn+1)%2;
            this.turns_played++;
        }
        return changeTurn;

    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
//        board.append("Current Turn: ");
//        if ((this.current_turn+1)%2==0) board.append("P1").append("\n");
//        else board.append("P2").append("\n");
        board.append("Mancala Board: \n");
        board.append("P2: ").append(this.boardSpaces[this.player_2_box]).append("\n");
        board.append("-------------------------------------\n");
        board.append("|\t|\t");

        for (int i = 12; i > 6; i--) {
            board.append(this.boardSpaces[i]);
            if (i > 7) board.append(" | ");
            else board.append(" ");
        }


        board.append("\t|\t|");

        board.append("\n");

        board.append("| ").append("P2").append("|\t");
        board.append("----".repeat(5));
        board.append("\t| ").append("P1").append("|");

        board.append("\n");

        board.append("|\t|\t");
        for (int i = 0; i < 6; i++) {
            board.append(this.boardSpaces[i]);
            if (i < 5) board.append(" | ");
            else board.append(" ");
        }
        board.append("\t|\t|\n");
        board.append("-------------------------------------\n");
        board.append("\t\t\t\t\t\t\tP1: ").append(this.boardSpaces[this.player_1_box]);
        board.append("\n\n");

        return board.toString();
    }
}
