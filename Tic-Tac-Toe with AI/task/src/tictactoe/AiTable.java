package tictactoe;

public class AiTable extends Table{
    private final int[][] availableCells;
    private final int[] minimaxScore;
    private final char selfSymbol;
    private final char opponentSymbol;

    public void undoStep(int[] coords) {
        super.setCell(coords[0], coords[1], '_');
    }

    public AiTable(char[] cells, char selfSymbol, char opponentSymbol) {
        super(cells);
        this.selfSymbol = selfSymbol;
        this.opponentSymbol = opponentSymbol;
        this.availableCells = this.getVacantPositions();
        this.minimaxScore = new int[availableCells.length];
    }

    public int[][] getAvailableCells() {
        return this.availableCells;
    }

    public int getMinimaxScore(char resultGame){
        if (resultGame == this.selfSymbol) {
            return 1;
        } else if (resultGame == this.opponentSymbol) {
            return -1;
        }
        return 0;
    }

    private void fillMinimaxScore(){
        int[][] availableCells = this.getAvailableCells();

        for (int i = 0; i < availableCells.length; i++) {
            this.doStep(availableCells[i]);
            char resultGame = this.getResultGame();
            if (resultGame != '1') {
                this.minimaxScore[i] = this.getMinimaxScore(resultGame);
            } else {
                AiTable aiTable = new AiTable(this.getCells(), this.selfSymbol, this.opponentSymbol);
                this.minimaxScore[i] = aiTable.minimax();
            }
            this.undoStep(availableCells[i]);
        }
    }

    public int minimax() {
        this.fillMinimaxScore();

        char nextSymbol = this.getNextSymbol();

        if (nextSymbol == this.selfSymbol) {
            int maxScore = -100;
            for (int score : this.minimaxScore) {
                if (score > maxScore) {
                    maxScore = score;
                }
            }

            return maxScore;
        } else {
            int minScore = 100;
            for (int score : this.minimaxScore) {
                if (score < minScore) {
                    minScore = score;
                }
            }

            return minScore;
        }
    }

    public int[] getCoords() {
        this.fillMinimaxScore();

        int maxScore = -100;
        int indexAvailableCells = 0;
        for (int j = 0; j < this.minimaxScore.length; j++) {
            if (this.minimaxScore[j] > maxScore) {
                maxScore = this.minimaxScore[j];
                indexAvailableCells = j;
            }
        }

        return this.availableCells[indexAvailableCells];
    }
}
