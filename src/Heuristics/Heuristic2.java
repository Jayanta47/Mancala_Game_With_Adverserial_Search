package Heuristics;

import GameBoard.GameState;

public class Heuristic2 implements IHeuristic{
    @Override
    public int calculateHeuristicVal(GameState s, boolean isP1) {
        int val = 2*(s.getP1Score()-s.getP2Score()) + 3*(s.getP1RowSum()-s.getP2RowSum());
        return (isP1)? val:-val;
    }
}
