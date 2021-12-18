package Heuristics;

import GameBoard.GameState;

public class Heuristic5 implements IHeuristic{
    //Maximize repeat turns. This heuristic attempts to maximize the chain moves that are
    //performed by the main player, therefore it prioritizes moves in which the player deposits the last seed
    //into their own store.
    // also give weighted advantage to how close a player is to winning
    @Override
    public int calculateHeuristicVal(GameState s, boolean isP1) {
        int v=0;
        for(int i=1;i<=6;i++) {
            int boxVal = s.getValOfBox(i, isP1);
            if ((boxVal+i)==7) {
                v+=Math.pow(2, i);
            }
        }
        return 0;
    }
}
