package org.progmatic.hangman.model;

import java.util.ArrayList;
import java.util.List;

public class HangmanState extends State<Integer, List<char[]>> {

    private final int startLife;

    public HangmanState(int lifePool) {
        this.pool = this.startLife = lifePool;
        this.representativeState = new ArrayList<>();
        updateState();
    }

    @Override
    public void setPool(Integer poolOfLife) {
        this.pool = poolOfLife;
        updateState();
    }

    @Override
    public void updateState() {
        if (this.pool == startLife - 1) {
            this.representativeState.add("-----    ".toCharArray());
        }
        if ((this.pool < startLife - 1) && (this.pool > 7)) {
            for (int i = 0; i < 4; i++) {
                representativeState.add(" |       ".toCharArray());
            }
        }
        if (this.pool == 7) {
            representativeState.add("---------".toCharArray());
        }
        if (this.pool == 6) {
            representativeState.get(representativeState.size() - 2)[7] = '|';
        }
        if (this.pool == 5) {
            representativeState.get(representativeState.size() - 3)[7] = '0';
        }
        if (this.pool == 4) {
            representativeState.get(representativeState.size() - 4)[7] = '|';
        }
        if (this.pool == 3) {
            representativeState.get(representativeState.size() - 4)[6] = '\\';
        }
        if (this.pool == 2) {
            representativeState.get(representativeState.size() - 4)[8] = '/';
        }
        if (this.pool == 1) {
            representativeState.get(representativeState.size() - 5)[6] = '/';
        }
        if (this.pool == 0) {
            representativeState.get(representativeState.size() - 5)[8] = '\\';
        }
    }
}

