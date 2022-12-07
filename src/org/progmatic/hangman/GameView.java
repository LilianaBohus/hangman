package org.progmatic.hangman;

import java.util.List;
import java.util.Scanner;
import org.progmatic.hangman.model.GameResult;
import org.progmatic.hangman.model.HangmanState;
import org.progmatic.hangman.model.State;
import org.progmatic.hangman.model.WordState;

public class GameView {

    private final Scanner scanner;

    private final GameController logic;

    GameView(GameController logic) {
        this.logic = logic;
        this.scanner = new Scanner(System.in);
    }

    void showState(State state) {
        System.out.println();

        if (state instanceof HangmanState) {
            showHangmanState((HangmanState) state);
        } else if (state instanceof WordState) {
            showWordState((WordState) state);
        }

        System.out.println();
    }

    void showHangmanState(HangmanState hangmanState) {
        List<char[]> drawingLines = hangmanState.getRepresentativeState();
        for (int i = drawingLines.size() - 1; i >= 0; i--) {
            System.out.println(drawingLines.get(i));
        }
    }

    void showWordState(WordState wordState) {
        for (var character : wordState.getRepresentativeState()) {
            System.out.print(character + " ");
        }
    }

    void showInstruction(String instruction) {
        System.out.print("\n" + instruction + "\n");
    }

    void showInfo(String info) {
        System.out.print(info + "\n");
    }

    void showStat() {
        showState(this.logic.getWordState());
        showState(this.logic.getHangmanState());
        showInfo("Life remaining: " + logic.getLifeRemaining());
        showInfo("Tried letters: " + logic.getTriedLetters());
    }

    public void run(String... life) {
        try {
            logic.initializeGame(life);
        } catch (IllegalArgumentException exception) {
            showInfo(exception.getMessage() + "\nThe game starts with default settings.\n");
            logic.initializeGame();
        }

        while (!logic.riddleIsSolved() && logic.getLifeRemaining() != 0) {
            showStat();
            showInstruction("Type a letter!");
            if (!logic.guessLetter(scanner.next().charAt(0))) {
                showInfo("This character has already been tried!\n");
            }
        }
    }

    void end() {
        showResult();
        this.scanner.close();
    }

    void showResult() {
        if (logic.getLifeRemaining() == 0) {
            showState(this.logic.getHangmanState());
            System.out.println(GameResult.LOSE);
            showInfo("The solution would have been " + logic.getRiddleWord());
        } else {
            showState(this.logic.getWordState());
            System.out.println(GameResult.WIN);
        }
    }
}
