package Heuristics;

import GameBoard.GameState;

public class Heuristic3 implements IHeuristic{
    @Override
    public int calculateHeuristicVal(GameState s, boolean isP1) {
        int val = 2*(s.getP1Score()-s.getP2Score()) + 3*(s.getP1RowSum()-s.getP2RowSum());
        val = (isP1)? val:-val;
        val+=(s.isFreeTurn()?10:0);
        return val;
    }
}
