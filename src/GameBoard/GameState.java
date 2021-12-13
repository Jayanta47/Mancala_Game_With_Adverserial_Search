package GameBoard;

public class GameState {
    int[] boardSpaces;
    GameState parent;
    boolean allowFT=false;

    public GameState() {
        this.boardSpaces = new int[14];
        for (int i = 0; i < 14; i++) {
            this.boardSpaces[i] = 4;
        }
        this.boardSpaces[6] = 0;
        this.boardSpaces[13] = 0;
    }

    public GameState(int[] boardSpaces) {
        this.boardSpaces = boardSpaces;
    }

    void setParent(GameState s) {
        this.parent = s;
    }

    boolean isFreeTurn() {
        return this.allowFT;
    }

    int getP1RowSum() {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += this.boardSpaces[i];
        }

        return sum;
    }

    int getP1RowSum(int[] boardSpaces) {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += boardSpaces[i];
        }

        return sum;
    }

    int getP2RowSum() {
        int sum = 0;
        for (int i = 7; i < 13; i++) {
            sum += this.boardSpaces[i];
        }

        return sum;
    }

    int getP2RowSum(int[] boardSpaces) {
        int sum = 0;
        for (int i = 7; i < 13; i++) {
            sum += boardSpaces[i];
        }

        return sum;
    }

    public GameState nextState(int n_box, boolean isP1Turn) {
        int idx, forbiddenBox, playerBox, lowerBound, upperBound;
        boolean changeTurn = true;
        // if turn is for player 1
        if (isP1Turn) {
            idx = (n_box - 1);
            forbiddenBox = 13;
            playerBox = 6;
            lowerBound = 0;
            upperBound = 5;
        } else {
            idx = (n_box + 6);
            forbiddenBox = 6;
            playerBox = 13;
            lowerBound = 7;
            upperBound = 12;
        }

        int [] newBoardSpaces = new int[14];

        System.arraycopy(this.boardSpaces, 0, newBoardSpaces, 0, 14);

        if (newBoardSpaces[idx] == 0) {
            System.out.println("0 box cannot be selected");
            return null;
        }

        int boxVal = newBoardSpaces[idx];

        // change boxVal to 0 for that index
        newBoardSpaces[idx] = 0;

        // increment all the next boxes
        while (boxVal > 0) {
            idx = (idx + 1) % 14;
            if (idx != forbiddenBox) {
                boxVal--;
                newBoardSpaces[idx]++;
                // last stone goes to player's box
                if (idx == playerBox && boxVal == 0) {
                    changeTurn = false;
                }
                // check for capture
                else if (boxVal == 0 && idx >= lowerBound && idx <= upperBound &&
                        newBoardSpaces[idx] == 1 && newBoardSpaces[12 - idx] != 0) {
                    int captureVal = newBoardSpaces[12 - idx];
                    newBoardSpaces[12 - idx] = 0;
                    captureVal += newBoardSpaces[idx];
                    newBoardSpaces[idx] = 0;
                    newBoardSpaces[playerBox] += captureVal;
                }

            }
        }

        int r1 = getP1RowSum(newBoardSpaces);
        int r2 = getP2RowSum(newBoardSpaces);
        if (r1 == 0 || r2 == 0) {
            if (r1 == 0) {
                newBoardSpaces[13] += r2;
            } else {
                newBoardSpaces[6] += r1;
            }
            for (int i = 0; i < 14; i++) {
                if (i != 6 && i != 13) {
                    newBoardSpaces[i] = 0;
                }
            }
        }

        GameState ns = new GameState(newBoardSpaces);
        ns.setParent(this);
        if (!changeTurn)ns.allowFT=true;

        return ns;

    }

    boolean isEndState() {
        return this.getP1RowSum() == 0 && this.getP2RowSum() == 0 ||
                Math.abs(this.boardSpaces[6] - this.boardSpaces[13]) > 24;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
//        board.append("Current Turn: ");
//        if ((this.current_turn+1)%2==0) board.append("P1").append("\n");
//        else board.append("P2").append("\n");
        board.append("Mancala Board: \n");
        board.append("P2: ").append(this.boardSpaces[13]).append("\n");
        board.append("-------------------------------------\n");
        board.append("|\t|\t");

        for (int i = 12; i > 6; i--) {
            board.append(this.boardSpaces[i]);
            if (i > 7) board.append(" | ");
            else board.append(" ");
        }


        board.append("\t|\t|");

        board.append("\n");

        board.append("| ").append("P2").append("|\t");
        board.append("----".repeat(5));
        board.append("\t| ").append("P1").append("|");

        board.append("\n");

        board.append("|\t|\t");
        for (int i = 0; i < 6; i++) {
            board.append(this.boardSpaces[i]);
            if (i < 5) board.append(" | ");
            else board.append(" ");
        }
        board.append("\t|\t|\n");
        board.append("-------------------------------------\n");
        board.append("\t\t\t\t\t\t\tP1: ").append(this.boardSpaces[6]);
        board.append("\n\n");

        return board.toString();
    }
}
