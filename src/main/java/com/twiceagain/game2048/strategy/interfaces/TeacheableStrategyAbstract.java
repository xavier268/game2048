/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy.interfaces;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.board.Direction;
import com.twiceagain.game2048.util.Evaluator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author xavier
 */
public abstract class TeacheableStrategyAbstract implements Serializable, TeacheableStrategy {

    protected List<Double> params;
    protected Integer size;
    protected Integer paramSize;
    protected Random rdm;

    public TeacheableStrategyAbstract() {
    }

    public int getSize() {
        return size;
    }

    /**
     * Compute the score for a given direction.
     *
     * @param d
     * @param b
     * @return
     */
    protected abstract Double compute(Direction d, Board b);

    @Override
    public Direction selectMove(Board b) {
        Double bestval = Double.NEGATIVE_INFINITY;
        Direction bestdir = Direction.DOWN;
        for (Direction d : Direction.values()) {
            if (b.canMove(d)) {
                Double val = compute(d, b);
                if (val > bestval) {
                    bestval = val;
                    bestdir = d;
                }
            }
        }
        //System.out.printf("\nBest direction : %s", bestdir);
        return bestdir;
    }

    @Override
    public final void randomize() {
        params = new ArrayList<>(paramSize);
        for (int i = 0; i < paramSize; i++) {
            params.add(2 * rdm.nextDouble() - 1);
        }
    }

    @Override
    public void setRandomSeed(long seed) {
        rdm = new Random(seed);
    }

    @Override
    public List<Double> getParams() {
        return params;
    }

    @Override
    public void setParams(List<Double> params) {
        this.params = params;
    }

    @Override
    public double computeAverageFinalScore(Board b, int count) {
        double r = 0.;
        List<Integer> scores = Evaluator.getFinalScores(b, this, count);

        for (int sc : scores) {
            r += sc;
        }
        return r / count;
    }

    @Override
    public double computeAverageFinalScore(int count) {
        return computeAverageFinalScore(new BoardImpl(size), count);
    }

    @Override
    public TeacheableStrategy improve(int count, double temperature) {
        return improve(count, temperature, 1000L * 3600); // Default 1 hour ...
    }

    @Override
    public TeacheableStrategy improve(int count, double temperature, long timeoutmillis) {

        long start = System.currentTimeMillis();
        double baseScore = computeAverageFinalScore(count);
        double newScore = Double.NEGATIVE_INFINITY;
        TeacheableStrategy ss = this;

        while ((baseScore >= newScore) && (System.currentTimeMillis() < start + timeoutmillis)) {
            ss = cloneAndModify(temperature);
            newScore = ss.computeAverageFinalScore(count);
        }
        return ss;
    }

    
}
