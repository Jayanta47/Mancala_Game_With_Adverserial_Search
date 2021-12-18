package Player;

import GameBoard.GameState;
import GameBoard.MancalaBoard;
import Heuristics.IHeuristic;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    IHeuristic hn;
    public int playerNo;
    MancalaBoard mb;
    private int MaxDepth = 10;
    int[] returnVal;
    boolean testMode=false;
    boolean performIDS=false;
    boolean searchByAlphaBeta = true;
    ArrayList<ArrayList<String>> record;

    public Player(IHeuristic hn, int playerNo, MancalaBoard mb) {
        this.hn = hn;
        this.playerNo = playerNo;
        this.mb = mb;
        returnVal = new int[7];
        record = new ArrayList<>();
        for(int i=0;i<MaxDepth;i++) {
            record.add(new ArrayList<>());
        }
    }

    public void resetAll() {
        this.MaxDepth = 10;
        testMode=false;
        performIDS=false;
        searchByAlphaBeta = true;
        record.clear();
    }

    public void turnOnTestMode() {
        this.testMode = true;
    }

    public void turnOnIDS() {
        this.performIDS = true;
    }

    public void turnOffIDS() {
        this.performIDS = false;
    }

    public void performAlphaBeta() {
        this.searchByAlphaBeta = true;
    }

    public void performMinimax() {
        this.searchByAlphaBeta = false;
    }

    public void setMaxDepth(int depth) {
        this.MaxDepth = depth;
    }

    public int makeMove() {
        GameState s = mb.getStateCopy();
        int move = -1;
        if (this.searchByAlphaBeta) {
            move = alpha_beta_search(s);
        }
        else {
            move = MiniMax(s);
        }

//        mb.clickSquare(move);
//        System.out.println("Move made by p: ");
//        System.out.println(move);
        if (move!=-1) mb.clickSquare(move);
        else System.out.println("Error Occurred");
        return move;
    }

    private int alpha_beta_search(GameState s) {
        for (int i=0;i<7;i++) {
            this.returnVal[i]=Integer.MIN_VALUE;
        }

        int resultV= MaxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE, MaxDepth, this.playerNo-1);
        if (testMode) {
            System.out.println(Arrays.toString(this.returnVal));
            System.out.println("result="+resultV);
        }
        for(int i=6;i>=1;i--) {
            if (this.returnVal[i]==resultV && s.getValOfBox(i, this.playerNo==1)>0) return i;
        }

        return -1;
    }

    private int alpha_beta_search_IDS(GameState s) {
        for (int i=0;i<7;i++) {
            this.returnVal[i]=Integer.MIN_VALUE;
        }

        int resultV= MaxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE, MaxDepth, this.playerNo-1);
        if (testMode) {
            System.out.println(Arrays.toString(this.returnVal));
            System.out.println("result="+resultV);
        }
        for(int i=6;i>=1;i--) {
            if (this.returnVal[i]==resultV && s.getValOfBox(i, this.playerNo==1)>0) return i;
        }

        return -1;
    }

    // 0 for p1 and 1 for P2

    private int MaxValue(GameState s, int alpha, int beta, int depth, int turn_player) {

//        if(testMode) {
//            System.out.println("Entered Maxval");
//        }
        if (depth == 0 || s.isEndState()) {
            return this.hn.calculateHeuristicVal(s, (turn_player==0));
        }

        int v = Integer.MIN_VALUE;

        for (int i=6;i>=1;i--) {
            if (s.getValOfBox(i, (turn_player==0)) > 0) {
                GameState nxtState = s.nextState(i, (turn_player==0));
                if (nxtState.isFreeTurn()){
                    if (testMode) {
                        System.out.printf("Entering Max Value Mode, depth %d -> %d, i = %d\n", depth,depth-1,  i);
                        System.out.println(nxtState);
                        record.get(depth-1).add("i="+i+" turn="+turn_player+"\n"+ nxtState);
                    }
                    v=Math.max(v, MaxValue(nxtState, alpha, beta, depth-1, turn_player));
                }
                else {
                    if (testMode) {
                        System.out.printf("Entering Min Value Mode, depth %d -> %d, i = %d\n", depth,depth-1,  i);
                        System.out.println(nxtState);
                        record.get(depth-1).add("i="+i+" turn="+turn_player+"\n"+ nxtState);
                    }
                    v = Math.max(v, MinValue(nxtState, alpha, beta, depth-1, (turn_player+1)%2));
                }

                if (depth==MaxDepth) {
                    returnVal[i] = v;
                }
                if (v>=beta) {
                    if (depth==MaxDepth-1 && testMode) System.out.println("returning "+v);
                    return v;
                }
                alpha = Math.max(alpha, v);
            }
            else if (depth==MaxDepth){
                returnVal[i]=Integer.MIN_VALUE;
            }
        }
        if (depth==MaxDepth && testMode) System.out.println("returning "+v);
        return v;
    }

    private int MinValue(GameState s, int alpha, int beta, int depth, int turn_player) {

//        if(testMode) {
//            System.out.println("Entered Minval");
//        }

        if (depth == 0 || s.isEndState()) {
            return this.hn.calculateHeuristicVal(s, (turn_player==0));
        }

        int v = Integer.MAX_VALUE;

        for (int i=6;i>=1;i--) {
            if (s.getValOfBox(i, (turn_player==0)) > 0) {
                GameState nxtState = s.nextState(i, (turn_player==0));
                if (nxtState.isFreeTurn()) {
                    if (testMode) {
                        System.out.printf("Entering min value mode, depth %d -> %d, i= %d\n", depth,depth-1, i);
                        System.out.println(nxtState);
                        record.get(depth-1).add("i="+i+" turn="+turn_player+"\n"+ nxtState);
                    }
                    v = Math.min(v, MinValue(nxtState, alpha, beta, depth-1, turn_player));
                }
                else {
                    if (testMode) {
                        System.out.printf("Entering max Value Mode, depth %d -> %d, i = %d\n", depth,depth-1, i);
                        System.out.println(nxtState);
                        record.get(depth-1).add("i="+i+" turn="+turn_player+"\n"+ nxtState);
                    }

                    v = Math.min(v, MaxValue(nxtState, alpha, beta, depth-1, (turn_player+1)%2));
                }
                if (v<=alpha) {
                    if (testMode) System.out.println("returning "+v);
                    return v;
                }
                beta = Math.min(beta, v);
            }
        }
        if (testMode) System.out.println("returning "+v);
        return v;
    }


    private int MiniMax(GameState s) {
        for (int i=0;i<7;i++) {
            this.returnVal[i]=Integer.MIN_VALUE;
        }

        int resultV = Minimax_Max(s, MaxDepth, this.playerNo-1);
        for(int i=6;i>=1;i--) {
            if (this.returnVal[i]==resultV && s.getValOfBox(i, this.playerNo==1)>0) return i;
        }
        return -1;
    }

    private int Minimax_Max(GameState s, int depth, int turn_player) {
        if (depth == 0 || s.isEndState()) {
            return this.hn.calculateHeuristicVal(s, (turn_player==0));
        }

        int v = Integer.MIN_VALUE;

        for (int i=6;i>=1;i--) {
            if (s.getValOfBox(i, (turn_player==0)) > 0) {
                GameState nxtState = s.nextState(i, (turn_player==0));
                if (nxtState.isFreeTurn()){
                    v=Math.max(v, Minimax_Max(nxtState, depth-1, turn_player));
                }
                else {

                    v = Math.max(v, Minimax_Min(nxtState,depth-1, (turn_player+1)%2));
                }

                if (depth==MaxDepth) {
                    returnVal[i] = v;
                }

            }
            else if (depth==MaxDepth){
                returnVal[i]=Integer.MIN_VALUE;
            }
        }
        return v;
    }

    private int Minimax_Min(GameState s, int depth, int turn_player) {
        if (depth == 0 || s.isEndState()) {
            return this.hn.calculateHeuristicVal(s, (turn_player==0));
        }

        int v = Integer.MAX_VALUE;

        for (int i=6;i>=1;i--) {
            if (s.getValOfBox(i, (turn_player==0)) > 0) {
                GameState nxtState = s.nextState(i, (turn_player==0));
                if (nxtState.isFreeTurn()) {

                    v = Math.min(v, Minimax_Min(nxtState, depth-1, turn_player));
                }
                else {
                    v = Math.min(v, Minimax_Max(nxtState, depth-1, (turn_player+1)%2));
                }
            }
        }
        return v;
    }
}
