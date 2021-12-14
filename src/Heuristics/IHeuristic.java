package Heuristics;

import GameBoard.GameState;

public interface IHeuristic {
    int calculateHeuristicVal(GameState s, boolean isP1);
}
