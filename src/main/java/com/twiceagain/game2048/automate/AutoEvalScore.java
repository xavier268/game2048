/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.automate;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;

/**
 * Select the best move to make based upon the result of an eval function. Here,
 * the eval is just the score.
 *
 * @author xavier
 */
public class AutoEvalScore extends AutoRandom {

    @Override
    public Direction selectDirection(Board board) {
        Direction bestd = null;
        double beste = Double.NEGATIVE_INFINITY;
        for (Direction dd : Direction.values()) {
            if (board.canMove(dd)) {
                double e = eval(board, dd);
                if (e > beste) {
                    beste = e;
                    bestd = dd;
                }
            }
        }
        return bestd;
    }

    /**
     * Evaluate a board
     *
     * @param board
     * @return
     */
    protected double eval(Board board) {
        return board.getScore();
    }

    /**
     * Evaluate a board, if move were to be made. Does NOT modify the board.
     *
     * @param board
     * @param d
     * @return
     */
    protected double eval(Board board, Direction d) {
        return eval(board.duplicate().move(d));
    }

}
