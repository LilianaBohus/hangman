package org.progmatic.hangman;

public class Main {

    public static void main(String[] args) {
        GameController logic = new GameController();
        GameView presentation = new GameView(logic);

        presentation.run(args);
        presentation.end();

        System.exit(0);
    }
}
