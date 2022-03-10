package tictactoe;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Table table;
    final Scanner scanner = new Scanner(System.in);

    public final static String START = "start";
    public final static String EXIT = "exit";
    public final static String USER = "user";

    public final static String EASY = "easy";
    public final static String MEDIUM = "medium";
    public final static String HARD = "hard";

    public int[] requestCoords() {
        int[] coords = new int[2];
        int x = 0;
        int y = 0;
        while (true) {
            System.out.print("Enter the coordinates: > ");

            String enter = this.scanner.nextLine();

            Scanner enterScanner = new Scanner(enter);

            String error = "";

            if (enterScanner.hasNextInt()) {
                x = enterScanner.nextInt();
            } else {
                error = "You should enter numbers!";
            }
            if (enterScanner.hasNextInt()) {
                y = enterScanner.nextInt();
            } else {
                error = "You should enter numbers!";
            }

            if (error.isEmpty()) {
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    error = "Coordinates should be from 1 to 3!";
                } else if (this.table.getCell(x - 1, y - 1) != '_') {
                    error = "This cell is occupied! Choose another one!";
                }
            }

            if (!error.isEmpty()) {
                System.out.println(error);
            } else {
                coords[0] = x - 1;
                coords[1] = y - 1;
                break;
            }
        }

        return coords;
    }

    public char getOpponentSymbol(char selfSymbol) {
        if (selfSymbol == 'X') {
            return 'O';
        }

        return 'X';
    }

    public String getStateGame(){
        char resultGame = this.table.getResultGame();

        if (resultGame == '0') {
            return "Draw";
        } else if(resultGame == 'X') {
            return "X wins";
        } else if (resultGame == 'O') {
            return "O wins";
        }
        return "";
    }

    public int[] getCoordsEaseAI(){
        int[][] vacantPositions = this.table.getVacantPositions();

        Random rnd = new Random();
        int indexNextStep = rnd.nextInt(vacantPositions.length);

        return vacantPositions[indexNextStep];
    }

    public int[] getCoordsMediumAI(int[] easyCoords){
        int[] winCoords = null;
        int[] protectionCoords = null;
        char selfSymbol = table.getNextSymbol();
        char opponentSymbol = this.getOpponentSymbol(selfSymbol);
        for (int[][] root : Table.roots) {
            char[] rootValues = this.table.getRootValues(root[0], root[1], root[2]);
            int countSelfSymbol = 0;
            int countOpponentSymbol = 0;
            int countEmptyCell = 0;
            int[] emptyCell = new int[2];
            for (int j = 0; j < rootValues.length; j++) {
                if (rootValues[j] == selfSymbol) {
                    countSelfSymbol++;
                } else if (rootValues[j] == opponentSymbol) {
                    countOpponentSymbol++;
                } else {
                    emptyCell = root[j];
                    countEmptyCell++;
                }
            }

            if (countEmptyCell == 1 && countSelfSymbol == 2) {
                winCoords = emptyCell;
            } else if (countEmptyCell == 1 && countOpponentSymbol == 2) {
                protectionCoords = emptyCell;
            }
        }

        if (winCoords != null) {
            return winCoords;
        }
        if (protectionCoords != null) {
            return protectionCoords;
        }
        return easyCoords;
    }

    public int[] getCoordsHardAI() {
        char selfSymbol = this.table.getNextSymbol();
        AiTable aiTable = new AiTable(this.table.getCells(), selfSymbol, this.getOpponentSymbol(selfSymbol));
        return aiTable.getCoords();
    }

    public String stageUser() {
        // stage user
        int[] coords = this.requestCoords();
        this.table.doStep(coords);
        this.table.printTable();
        return this.getStateGame();
    }

    public String stageAI(String stage) {
        // stage AI
        System.out.println("Making move level \"" + stage + "\"");
        int[] coordsAi = this.getCoordsEaseAI();
        if (stage.equals(MEDIUM)) {
            coordsAi = this.getCoordsMediumAI(coordsAi);
        } else if (stage.equals(HARD)) {
            coordsAi = this.getCoordsHardAI();
        }
        this.table.doStep(coordsAi);
        this.table.printTable();
        return this.getStateGame();
    }

    public String runStage(String stage) {
        String stateGame = "";
        if (stage.equals(USER)){
            stateGame = this.stageUser();
        } else {
            stateGame = this.stageAI(stage);
        }
        return stateGame;
    }

    public void start(String stageFirst, String stageSecond){
        this.table = new Table();
        this.table.printTable();
        String stateGame;
        while (true) {
            stateGame = this.runStage(stageFirst);
            if (!stateGame.isEmpty()) {
                break;
            }
            stateGame = this.runStage(stageSecond);
            if (!stateGame.isEmpty()) {
                break;
            }
        }
        System.out.println(stateGame);
        System.out.println();
    }
}
