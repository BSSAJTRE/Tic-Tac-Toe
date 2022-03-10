package tictactoe;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Game game = new Game();
        menu.run(game);
    }
}
