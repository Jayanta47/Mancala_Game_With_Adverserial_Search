package Heuristics;

import GameBoard.GameState;

public class Heuristic4 implements IHeuristic{
//    Maximize the number of seeds in a player's own store
//    desc:
//    This heuristic attempts to maximize the number of seeds
//    that the player captures with a look ahead of one that
//    compares the previous amount of seeds in the store to the current amount.
    @Override
    public int calculateHeuristicVal(GameState s, boolean isP1) {
        int v =0;
        for(int i=1;i<6;i++) {
            int boxVal = s.getValOfBox(i, isP1);
            if ((boxVal+i)==0) {
                v+=(s.getValOfBox(7-(i+boxVal), !isP1)*Math.pow(2, i));
            }
        }

        // how close am I to winning
        // if a player has 1 and half of the beads of the opponent then that player is guaranteed to win
        int s1, s2;
        if (isP1) {
            s1 = s.getP1Score();
            s2 = s.getP2Score();
        }
        else {
            s1 = s.getP2Score();
            s2 = s.getP1Score();
        }

        v+= 2*((int)(1.5*s2)-s1);

        return v;
    }
}
