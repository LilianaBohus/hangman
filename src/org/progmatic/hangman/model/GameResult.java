package org.progmatic.hangman.model;

public enum GameResult {
    WIN("You won!"),
    LOSE("You lost!"),
    DRAW("It's a draw!");

    private final String message;

    GameResult(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
