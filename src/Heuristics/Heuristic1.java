package Heuristics;

import GameBoard.GameState;

public class Heuristic1 implements IHeuristic{
    @Override
    public int calculateHeuristicVal(GameState s, boolean isP1) {
        return isP1? s.getP1Score()-s.getP2Score():s.getP2Score()-s.getP1Score();
    }
}
