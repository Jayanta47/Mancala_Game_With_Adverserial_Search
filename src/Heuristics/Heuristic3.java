package Heuristics;

import GameBoard.GameState;

public class Heuristic3 implements IHeuristic{
    @Override
    public int calculateHeuristicVal(GameState s, boolean isP1) {
        int val = 5*(s.getP1Score()-s.getP2Score()) + 2*(s.getP1RowSum()-s.getP2RowSum());
        val = (isP1)? val:-val;
//        GameState ns = s.nextState()
        val+=(s.isFreeTurn()?2:0);
        return val;
    }
}
