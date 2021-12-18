package Player;

import GameBoard.GameState;
import GameBoard.MancalaBoard;
import Heuristics.IHeuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.System.exit;

public class Player {
    IHeuristic hn;
    public int playerNo;
    MancalaBoard mb;
    private int MaxDepth = 10;
    int[] returnVal;
    boolean testMode=false;
    boolean performIDS=false;
    boolean searchByAlphaBeta = true;
    ArrayList<HashMap<Integer, Integer>> record;

    public Player(IHeuristic hn, int playerNo, MancalaBoard mb) {
        this.hn = hn;
        this.playerNo = playerNo;
        this.mb = mb;
        returnVal = new int[7];
        record = new ArrayList<>();
    }

    public void setHeuristic(IHeuristic hn) {
        this.hn = hn;
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
            if (this.performIDS) {
                move = alpha_beta_search_IDS(s);
                if(move==-1) {
                    System.out.println("ids wrong");
                }
            }
            else {
                move = alpha_beta_search(s);
                if(move==-1) {
                    System.out.println("alpha-beta wrong");
                }
            }
        }
        else {
            move = MiniMax(s);
        }


        if (move!=-1) mb.clickSquare(move);

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
        record.clear();
        int resultV = Integer.MIN_VALUE;

        for(int i=0;i<this.MaxDepth;i++) {
            record.add(i, new HashMap<>());
            for(int j=1;j<=6;j++) {
                record.get(i).put(j,1);
            }

        }
        int searchLimit = this.MaxDepth;
        for(int depthIDS = 5; depthIDS<=searchLimit;depthIDS++) {
            this.MaxDepth = depthIDS;
            for (int i=0;i<7;i++) {
                this.returnVal[i]=Integer.MIN_VALUE;
            }

            // initiating the search
            resultV = MaxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE, this.MaxDepth, this.playerNo-1);

        }

        for(int i=6;i>=1;i--) {
            if (this.returnVal[i]==resultV && s.getValOfBox(i, this.playerNo==1)>0) return i;
        }

        return -1;
    }

    // 0 for p1 and 1 for P2

    private int MaxValue(GameState s, int alpha, int beta, int depth, int turn_player) {

        if (depth == 0 || s.isEndState()) {
            return this.hn.calculateHeuristicVal(s, (turn_player==0));
        }

        int v = Integer.MIN_VALUE;
        int bestIdx=-1;


        ArrayList<Integer> moveOrder;
        if (this.performIDS) {
            moveOrder = freqAnalysis(this.MaxDepth-depth);
        }
        else {
            moveOrder = new ArrayList<>();
            for(int i=6;i>=1;i--) {
                moveOrder.add(i);
            }
        }

        if (moveOrder.size()!=6) {
            System.err.println("error in move ordering, exiting program");
            exit(0);
        }

        for (int i:moveOrder) {
            if (s.getValOfBox(i, (turn_player==0)) > 0) {
                GameState nxtState = s.nextState(i, (turn_player==0));
                if (nxtState.isFreeTurn()){
                    if (testMode) {
                        System.out.printf("Entering Max Value Mode, depth %d -> %d, i = %d\n", depth,depth-1,  i);
                        System.out.println(nxtState);
//                        record.get(depth-1).add("i="+i+" turn="+turn_player+"\n"+ nxtState);
                    }
                    int v_=v;
                    v=Math.max(v, MaxValue(nxtState, alpha, beta, depth-1, turn_player));
                    if (v>v_) {
                        bestIdx = i;
                    }
                }
                else {
                    if (testMode) {
                        System.out.printf("Entering Min Value Mode, depth %d -> %d, i = %d\n", depth,depth-1,  i);
                        System.out.println(nxtState);
//                        record.get(depth-1).add("i="+i+" turn="+turn_player+"\n"+ nxtState);
                    }
                    int v_=v;
                    v = Math.max(v, MinValue(nxtState, alpha, beta, depth-1, (turn_player+1)%2));
                    if (v>v_) {
                        bestIdx = i;
                    }
                }

                if (depth==MaxDepth) {
                    returnVal[i] = v;
                }
                if (v>=beta) {
                    if (depth==MaxDepth-1 && testMode) System.out.println("returning "+v);
                    if (bestIdx!=-1 && this.performIDS)this.record.get(this.MaxDepth-depth).put(bestIdx, this.record.get(this.MaxDepth-depth).get(bestIdx)+1);
                    return v;
                }
                alpha = Math.max(alpha, v);
            }
            else if (depth==MaxDepth){
                returnVal[i]=Integer.MIN_VALUE;
            }
        }
        if (depth==MaxDepth && testMode) System.out.println("returning "+v);
        if (bestIdx!=-1 && this.performIDS)this.record.get(this.MaxDepth-depth).put(bestIdx, this.record.get(this.MaxDepth-depth).get(bestIdx)+1);
        return v;
    }

    private ArrayList<Integer> freqAnalysis(int depth) {
        ArrayList<Integer> mo = new ArrayList<>();
        HashMap<Integer, Integer> freq = this.record.get(depth);
        int[] arr = new int[6];
        for(int i=1;i<=6;i++) {
            arr[i-1] = freq.get(i);
        }
        Arrays.sort(arr);
        boolean[] taken = new boolean[6];
        for(int i=0;i<6;i++) taken[i]=false;

        for(int arrIdx = 0;arrIdx<6;arrIdx++) {
            for(int freqIdx = 6; freqIdx>=1;freqIdx--) {
                if (freq.get(freqIdx)==arr[arrIdx] && !taken[freqIdx-1]) {
                    mo.add(freqIdx);
                    taken[freqIdx-1] = true;
                }
            }
        }

        return mo;

    }

    private int MinValue(GameState s, int alpha, int beta, int depth, int turn_player) {

        if (depth == 0 || s.isEndState()) {
            return this.hn.calculateHeuristicVal(s, (turn_player==0));
        }

        int v = Integer.MAX_VALUE;
        int bestIdx=-1;


        ArrayList<Integer> moveOrder=null;
        if (this.performIDS) {
            moveOrder = freqAnalysis(depth);
        }
        else {
            moveOrder = new ArrayList<>();
            for(int i=6;i>=1;i--) {
                moveOrder.add(i);
            }
        }

        if (moveOrder.size()!=6) {
            System.err.println("error in move ordering, exiting program");
            exit(0);
        }

        for (int i : moveOrder) {
            if (s.getValOfBox(i, (turn_player==0)) > 0) {
                GameState nxtState = s.nextState(i, (turn_player==0));
                if (nxtState.isFreeTurn()) {
                    if (testMode) {
                        System.out.printf("Entering min value mode, depth %d -> %d, i= %d\n", depth,depth-1, i);
                        System.out.println(nxtState);
//                        record.get(depth-1).add("i="+i+" turn="+turn_player+"\n"+ nxtState);
                    }
                    int v_ = v;
                    v = Math.min(v, MinValue(nxtState, alpha, beta, depth-1, turn_player));
                    if (v_>v) {
                        bestIdx=i;
                    }
                }
                else {
                    if (testMode) {
                        System.out.printf("Entering max Value Mode, depth %d -> %d, i = %d\n", depth,depth-1, i);
                        System.out.println(nxtState);
//                        record.get(depth-1).add("i="+i+" turn="+turn_player+"\n"+ nxtState);
                    }
                    int v_ = v;
                    v = Math.min(v, MaxValue(nxtState, alpha, beta, depth-1, (turn_player+1)%2));
                    if (v_>v) {
                        bestIdx=i;
                    }
                }
                if (v<=alpha) {
                    if (testMode) System.out.println("returning "+v);
                    if (bestIdx!=-1 && this.performIDS)this.record.get(this.MaxDepth-depth).put(bestIdx, this.record.get(this.MaxDepth-depth).get(bestIdx)+1);
                    return v;
                }
                beta = Math.min(beta, v);
            }
        }
        if (testMode) System.out.println("returning "+v);
        if (bestIdx!=-1 && this.performIDS)this.record.get(this.MaxDepth-depth).put(bestIdx, this.record.get(this.MaxDepth-depth).get(bestIdx)+1);
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
