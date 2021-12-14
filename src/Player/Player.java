package Player;

import GameBoard.GameState;
import GameBoard.MancalaBoard;
import Heuristics.IHeuristic;

import java.util.Arrays;

public class Player {
    IHeuristic hn;
    int playerNo;
    MancalaBoard mb;
    private final int MaxDepth = 3;
    int[] returnVal;
    boolean testmode=false;

    public Player(IHeuristic hn, int playerNo, MancalaBoard mb) {
        this.hn = hn;
        this.playerNo = playerNo;
        this.mb = mb;
        returnVal = new int[7];
    }

    public void turnOnTestMode() {
        this.testmode = true;
    }

    public void makeMove() {
        GameState s = mb.getStateCopy();
        int move = alpha_beta_search(s);
//        mb.clickSquare(move);
        System.out.println("Move made by p: ");
        System.out.println(move);
        mb.clickSquare(move);
    }

    private int alpha_beta_search(GameState s) {
        for (int i=0;i<7;i++) {
            this.returnVal[i]=0;
        }

        int resultV= MaxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE, MaxDepth, this.playerNo-1);
        if(testmode) System.out.println(Arrays.toString(this.returnVal));
        for(int i=1;i<=6;i++) {
            if (this.returnVal[i]==resultV) return i;
        }
        return -1;
    }

    // 0 for p1 and 1 for P2

    private int MaxValue(GameState s, int alpha, int beta, int depth, int turn_player) {

//        if(testmode) {
//            System.out.println("Entered Maxval");
//        }
        if (depth == 0 || s.isEndState()) {
            return this.hn.calculateHeuristicVal(s, (turn_player==0));
        }

        int v = Integer.MIN_VALUE;

        for (int i=1;i<=6;i++) {
            if (s.getValOfBox(i, (turn_player==0)) > 0) {
                GameState nxtState = s.nextState(i, (turn_player==0));
                if (nxtState.isFreeTurn()){
                    if (testmode) {
                        System.out.printf("Entering Max Value Mode, depth %d -> %d, i = %d\n", depth,depth-1,  i);
                        System.out.println(nxtState);
                    }
                    v=Math.max(v, MaxValue(nxtState, alpha, beta, depth-1, turn_player));
                }
                else {
                    if (testmode) {
                        System.out.printf("Entering Min Value Mode, depth %d -> %d, i = %d\n", depth,depth-1,  i);
                        System.out.println(nxtState);
                    }
                    v = Math.max(v, MinValue(nxtState, alpha, beta, depth-1, (turn_player+1)%2));
                }

                if (depth==MaxDepth) {
                    returnVal[i] = v;
                }
                if (v>=beta) {
                    return v;
                }
                alpha = Math.max(alpha, v);
            }
            else if (depth==MaxDepth){
                returnVal[i]=-1;
            }
        }
        return v;
    }

    private int MinValue(GameState s, int alpha, int beta, int depth, int turn_player) {

//        if(testmode) {
//            System.out.println("Entered Minval");
//        }

        if (depth == 0 || s.isEndState()) {
            return this.hn.calculateHeuristicVal(s, (turn_player==0));
        }

        int v = Integer.MAX_VALUE;

        for (int i=1;i<=6;i++) {
            if (s.getValOfBox(i, (turn_player==0)) > 0) {
                GameState nxtState = s.nextState(i, (turn_player==0));
                if (nxtState.isFreeTurn()) {
                    if (testmode) {
                        System.out.printf("Entering min value mode, depth %d -> %d, i= %d\n", depth,depth-1, i);
                        System.out.println(nxtState);
                    }
                    v = Math.min(v, MinValue(nxtState, alpha, beta, depth-1, turn_player));
                }
                else {
                    if (testmode) {
                        System.out.printf("Entering max Value Mode, depth %d -> %d, i = %d\n", depth,depth-1, i);
                        System.out.println(nxtState);
                    }

                    v = Math.min(v, MaxValue(nxtState, alpha, beta, depth-1, (turn_player+1)%2));
                }
                if (v<=alpha) {
                    return v;
                }
                beta = Math.min(beta, v);
            }
        }
        return v;
    }
}
