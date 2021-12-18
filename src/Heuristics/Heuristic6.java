package Heuristics;

import GameBoard.GameState;

public class Heuristic6 implements IHeuristic{
    @Override
    public int calculateHeuristicVal(GameState s, boolean isP1) {
        // weighted sum of the number of beads in the row of the user
        // this is the preferred bad heuristic
        return 64*s.getValOfBox(6, isP1)+
                32*s.getValOfBox(5, isP1)+
                16*s.getValOfBox(4, isP1)+
                8*s.getValOfBox(3,isP1)+
                4*s.getValOfBox(2, isP1)+
                2*s.getValOfBox(1,isP1);
    }
}
