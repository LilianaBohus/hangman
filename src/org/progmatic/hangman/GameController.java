package org.progmatic.hangman;

import java.util.ArrayList;
import java.util.List;
import org.progmatic.hangman.model.Dictionary;
import org.progmatic.hangman.model.HangmanState;
import org.progmatic.hangman.model.State;
import org.progmatic.hangman.model.WordState;

public class GameController {

    private String riddleWord;
    private State wordState;
    private State hangmanState;
    private List<Character> triedLetters;
    private int lifeRemaining = Settings.MAX_LIFE;

    GameController() {
        this.triedLetters = new ArrayList<>();
    }

    public String getRiddleWord() {
        return this.riddleWord;
    }

    public boolean riddleIsSolved() {
        for (int i = 0; i < this.riddleWord.length(); i++) {
            if (!this.triedLetters.contains(this.riddleWord.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isAMiss(char letter) {
        return !this.riddleWord.contains(Character.toString(letter));
    }

    void initializeGame(String... life) throws IllegalArgumentException {
        if (life.length != 0) {
            if (NumberUtil.isNaN(life[0])) {
                throw new IllegalArgumentException("The given argument is not a number!");
            }
            int inputLife = Integer.parseInt(life[0]);
            if (NumberUtil.isBiggerThan(inputLife, Settings.MAX_LIFE)) {
                throw new IllegalArgumentException("The given number is bigger than the maximum " + Settings.MAX_LIFE);
            }
            if (NumberUtil.isLessThan(inputLife, Settings.MIN_LIFE)) {
                throw new IllegalArgumentException("The given number is less than the minimum " + Settings.MIN_LIFE);
            }
            this.lifeRemaining = inputLife;
        }
        this.riddleWord = Dictionary.getRandomWord();
        this.wordState = new WordState(this.riddleWord);
        this.hangmanState = new HangmanState(this.lifeRemaining);
    }

    public boolean guessLetter(char letter) {
        char lowercaseLetter = Character.toLowerCase(letter);
        char uppercaseLetter = Character.toUpperCase(letter);
        if (this.triedLetters.contains(lowercaseLetter) || this.triedLetters.contains(uppercaseLetter)) {
            return false;
        }
        triedLetters.add(lowercaseLetter);
        triedLetters.add(Character.toUpperCase(uppercaseLetter));
        wordState.setPool(triedLetters);
        if (isAMiss(lowercaseLetter) && isAMiss(uppercaseLetter)) {
            hangmanState.setPool(--lifeRemaining);
        }
        return true;
    }

    public State getWordState() {
        return wordState;
    }

    public void setWordState(State wordState) {
        this.wordState = wordState;
    }

    public State getHangmanState() {
        return hangmanState;
    }

    public void setHangmanState(State hangmanState) {
        this.hangmanState = hangmanState;
    }

    public List<Character> getTriedLetters() {
        return this.triedLetters;
    }

    public void setTriedLetters(List<Character> triedLetters) {
        this.triedLetters = triedLetters;
    }

    public int getLifeRemaining() {
        return lifeRemaining;
    }

    public void setLifeRemaining(int lifeRemaining) {
        this.lifeRemaining = lifeRemaining;
    }
}
