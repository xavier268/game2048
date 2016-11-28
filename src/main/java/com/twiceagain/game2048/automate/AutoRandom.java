/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.automate;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Play random moves until limit. The initial board is NOT modified.
 *
 * @author xavier
 */
public class AutoRandom implements Auto {

    protected Random rdm;
    protected List<Direction> playList = new ArrayList<>();
    private static long SEED = 0;

    /**
     * Select a valid, random, direction.
     *
     * @param board
     * @return
     */
    @Override
    public Direction selectDirection(Board board) {
        Direction d = Direction.values()[rdm.nextInt(4)];
        if (board.canMove(d)) {
            return d;
        } else {
            return selectDirection(board);
        }
    }

    public AutoRandom() {
        SEED += 10000L;
        rdm = new Random(SEED + System.currentTimeMillis());
    }

    public AutoRandom(long seed) {
        rdm = new Random(seed);
    }

    @Override
    public void auto(Board board) {
        auto(board, (Board bb) -> bb.canMove());
    }

    @Override
    public void auto(Board board, Limit limit) {
        if (limit == null) {
            auto(board);
            return;
        }

        while (limit.shouldContinue(board)) {
            Direction d = selectDirection(board);
            board.play(d);
            playList.add(d);

        }

    }

    @Override
    public List<Direction> getMoves() {
        return playList;
    }

    @Override
    public void reset() {
        playList = new ArrayList<>();
    }

}
