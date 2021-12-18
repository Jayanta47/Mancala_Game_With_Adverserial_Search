package GameBoard;

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

    public int getBeadsAt(int pos, boolean isP1) {
        return this.currentState.getValOfBox(pos, isP1);
    }

    public int getP1Points() {
        return this.currentState.getP1Score();
    }

    public int getP2Points() {
        return this.currentState.getP2Score();
    }

    public boolean isCurrentFT() {
        return this.currentState.allowFT;
    }


}


