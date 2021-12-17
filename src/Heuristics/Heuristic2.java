package Heuristics;

import GameBoard.GameState;

public class Heuristic2 implements IHeuristic{
    @Override
    public int calculateHeuristicVal(GameState s, boolean isP1) {
        int val = 4*(s.getP1Score()-s.getP2Score()) + 2*(s.getP1RowSum()-s.getP2RowSum());
        return (isP1)? val:-val;
    }
}


// heuristic 2 pitfalls
//the number of beads on my row doesnt
// have much effect on the overall performance of the heuristic