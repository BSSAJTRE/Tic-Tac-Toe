package tictactoe;

import java.util.Arrays;

public class Table extends TableData {
    static final int[][][] roots = {
            {{0,0}, {1,1}, {2,2}},
            {{0,2}, {1,1}, {2,0}},
            {{0,0}, {0,1}, {0,2}},
            {{1,0}, {1,1}, {1,2}},
            {{2,0}, {2,1}, {2,2}},
            {{0,0}, {1,0}, {2,0}},
            {{0,1}, {1,1}, {2,1}},
            {{0,2}, {1,2}, {2,2}},
    };

    public char getNextSymbol() {
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.getCell(i, j) == 'X') {
                    xCount++;
                }
                if (this.getCell(i, j) == 'O') {
                    oCount++;
                }
            }
        }
        char nextSymbol = 'X';
        if (xCount > oCount) {
            nextSymbol = 'O';
        }
        return nextSymbol;
    }

    public void doStep(int[] coords) {
        this.setCell(coords[0], coords[1], this.getNextSymbol());
    }

    public void doStep(int[] coords, char nextSymbol) {
        this.setCell(coords[0], coords[1], nextSymbol);
    }

    public char[] getRootValues(int[] a, int[] b, int[] c) {
        return new char[] {
                this.getCell(a[0], a[1]),
                this.getCell(b[0], b[1]),
                this.getCell(c[0], c[1])
        };
    }

    // возврвщает символ 'X' или 'O' или '_', если он повторяется в трех точках иначе '_'
    public char checkRoot(int[] a, int[] b, int[] c) {
        char[] rootValues = this.getRootValues(a, b, c);
        if (rootValues[0] == rootValues[1] && rootValues[0] == rootValues[2]) {
            return rootValues[0];
        }
        return '_';
    }

    public char getResultGame() {
        char rootChar = '_';
        for (int[][] root : roots) {
            rootChar = checkRoot(root[0], root[1], root[2]);
            if (rootChar != '_') {
                break;
            }
        }
        boolean fulledTable = true;
        for (int j = 0; j < 9; j++) {
            fulledTable = fulledTable && this.getCell(j / 3, j % 3) != '_';
        }

        if (fulledTable && rootChar == '_') {
            // ничья
            return '0';
        } else if(rootChar == 'X') {
            return 'X';
        } else if (rootChar == 'O') {
            return 'O';
        }
        // игра не закончена
        return '1';
    }

    public Table() {
        super();
    }

    public Table(char[] cells) {
        super(cells);
    }

    public int[][] getVacantPositions() {
        int[][] vacantPositions = new int[9][2];
        int countVacantPosition = 0;
        for (int i = 0; i < 9; i++) {
            if (this.getCell(i / 3, i % 3) == '_') {
                vacantPositions[countVacantPosition][0] = i / 3;
                vacantPositions[countVacantPosition][1] = i % 3;
                countVacantPosition++;
            }
        }

        return Arrays.copyOfRange(vacantPositions, 0, countVacantPosition);
    }
}
