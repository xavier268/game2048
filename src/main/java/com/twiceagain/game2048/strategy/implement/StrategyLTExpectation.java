/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy.implement;

import com.twiceagain.game2048.util.Evaluator;
import com.twiceagain.game2048.strategy.interfaces.Strategy;
import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import static com.twiceagain.game2048.board.Direction.UP;

/**
 * Strategy based upon the long term expectation associated with a move. The
 * consequence of each potential move is evaluated based on the expected average
 * final score.
 *
 * @author xavier
 */
public class StrategyLTExpectation implements Strategy {
    
    protected Strategy str;
    protected int count = 1;

    public StrategyLTExpectation(Strategy longTermStrategy, int count) {
        this.str = longTermStrategy;
        this.count=count;
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

    /**
     * Main evaluation function for the board.
     *
     * @param b
     * @return
     */
    protected double eval(Board b) {
        Integer s = 0;
        return Evaluator
                .getFinalScores(b, str, count)
                .stream()
                .reduce(s,Integer::sum);
    }

}
