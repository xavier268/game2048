/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy.implement;

import com.twiceagain.game2048.strategy.interfaces.Strategy;
import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import static com.twiceagain.game2048.board.Direction.UP;

/**
 * Stategy the minimize deltas between values for the immediate next move. Using
 * the max value obtained does not significatly affect performance.
 *
 * @author xavier
 */
public class StrategyDelta implements Strategy {

    protected double wMax = 0, wDelta = 0;

    @Deprecated
    public StrategyDelta(double wMax, double wDelta) {
        this.wMax = wMax;
        this.wDelta = wDelta;
    }

    public StrategyDelta(double wDelta) {
        this.wMax = 0;
        this.wDelta = wDelta;
    }

    

    @Override
    public Direction selectMove(Board b) {
        Direction bd = UP; // best direction
        double bs = 0; // best eval
        for (Direction d : Direction.values()) {
            if (b.canMove(d)) {
                double s = eval(b.duplicate().play(d));
                if (bs < s) {
                    bs = s;
                    bd = d;
                }
            }
        }
        return bd;
    }

    protected double eval(Board b) {
        if (wMax == 0) {
            return delta(b) * wDelta
                    + b.getScore();
        } else {
            return max(b) * wMax
                    + delta(b) * wDelta
                    + b.getScore();
        }
    }

    /**
     * Compute the max value in the board.
     *
     * @param b
     * @return
     */
    protected double max(Board b) {
        int max = 0;
        for (int i = 0; i < b.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                if (b.at(i, j) != null && b.at(i, j) > max) {
                    max = b.at(i, j);
                }
            }

        }
        return max;
    }

    /**
     * Compute the deltas between the log of neighbouring values.
     *
     * @param b
     * @return
     */
    protected double delta(Board b) {
        double d = 0;
        for (int i = 1; i < b.getSize(); i++) {
            for (int j = 1; j < b.getSize(); j++) {
                d += delta(b.at(i, j), b.at(i - 1, j)) + delta(b.at(i, j), b.at(i, j - 1));
            }
        }
        return d;
    }

    protected double delta(Integer v1, Integer v2) {
        if (v1 == null || v2 == null) {
            return 0.;
        }
        double r = Math.log(v1) - Math.log(v2);
        return r * r;
    }

}
