package org.progmatic.hangman.model;

import java.util.ArrayList;
import java.util.List;

public class WordState extends State<List<Character>, char[]> {

    private final String pattern;

    public WordState(String riddleWord) {
        this.pattern = riddleWord;
        this.pool = new ArrayList<>();
        this.representativeState = new char[pattern.length()];
        updateState();
    }

    @Override
    public void setPool(List<Character> poolOfCharacters) {
        this.pool = poolOfCharacters;
        updateState();
    }

    @Override
    public void updateState() {
        for (int i = 0; i < this.pattern.length(); i++) {

            if (pool.contains(pattern.charAt(i))) {
                representativeState[i] = pattern.charAt(i);
            } else {
                representativeState[i] = '_';
            }
        }
    }
}
