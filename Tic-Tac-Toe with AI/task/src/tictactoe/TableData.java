package tictactoe;

public class TableData{
    private char[] cells;

    public void printTable() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (this.cells[i * 3 + j] == '_') {
                    System.out.print("  ");
                } else {
                    System.out.print(this.cells[i * 3 + j] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public void setCell(int x, int y, char symbol) {
        this.cells[x * 3 + y] = symbol;
    }

    public char getCell(int x, int y) {
        return this.cells[x * 3 + y];
    }

    public TableData() {
        this.cells = new char[] {
            '_', '_', '_',
            '_', '_', '_',
            '_', '_', '_'
        };
    }

    public TableData(char[] cells){
        this.cells = cells.clone();
    }

    public char[] getCells(){
        return this.cells;
    }
}
