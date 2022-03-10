package tictactoe;

import java.util.Scanner;

public class Menu {
    final Scanner scanner = new Scanner(System.in);

    public boolean isValidStageName(String stageName) {
        return stageName.equals(Game.EASY) || stageName.equals(Game.MEDIUM) || stageName.equals(Game.HARD) || stageName.equals(Game.USER);
    }

    public void run(Game game) {
        while(true){
            System.out.print("Input command: > ");
            String[] params = new String[2];
            String enter = this.scanner.nextLine();
            Scanner enterScanner = new Scanner(enter);

            String command = "";
            if(enterScanner.hasNext()) {
                command = enterScanner.next();
            }
            if(enterScanner.hasNext()) {
                params[0] = enterScanner.next();
            }
            if(enterScanner.hasNext()) {
                params[1] = enterScanner.next();
            }

            if(command.equals(Game.START)) {
                if(
                    params[0] != null && params[1] != null &&
                    this.isValidStageName(params[0]) && this.isValidStageName(params[1])
                ) {
                    game.start(params[0], params[1]);
                } else {
                    System.out.println("Bad parameters!");
                }
            } else if(command.equals(Game.EXIT)) {
                break;
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }
}
