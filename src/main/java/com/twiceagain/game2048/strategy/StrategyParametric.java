/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Strategy is defined by a set of parameters.
 *
 * @author xavier
 */
public class StrategyParametric implements Strategy {

    private Map<Direction, List<Double>> params;
    private final int size;

    /**
     * Initialize the parameters of the strategy randomly.
     *
     * @param size
     */
    public StrategyParametric(int size) {
        this.size = size;
        params = new HashMap<>(4);
        for (Direction d : Direction.values()) {
            ArrayList<Double> list = new ArrayList<>(size * size);
            for (int i = 0; i < size * size; i++) {
                list.add(Math.random());
            }
            params.put(d, list);
        }
    }

    public int getSize() {
        return size;
    }

    public Map<Direction, List<Double>> getParams() {
        return params;
    }

    public void setParams(Map<Direction, List<Double>> params) {
        this.params = params;
    }

    /**
     * Compute the score for a given direction.
     *
     * @param d
     * @param b
     * @return
     */
    protected Double compute(Direction d, Board b) {
        Double r = 0.;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (b.at(i, j) != null) {
                    r += b.at(i, j) * params.get(d).get(i * size + j);
                } else {
                }
            }
        }
        //System.out.printf("\n   compute %s -> %s", d, r);
        return r;
    }

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

    /**
     * Build a modified strategy. The strength of the modification is
     * proportionate to the temperature. For temperature == 0, then this returns
     * a clone of the existing strategy. For temperature >= 1, returns a fully
     * randomized new strategy.
     *
     * @param temperature : between 0. and 1.
     * @return
     */
    public StrategyParametric makeModified(double temperature) {

        StrategyParametric s = new StrategyParametric(size);
        if (temperature >= 1) {
            return s;
        }

        for (Direction d : Direction.values()) {
            List<Double> list = s.params.get(d);
            for (int i = 0; i < size * size; i++) {
                double delta = temperature * (2 * Math.random() - 1);
                // delta between -1 and 1
                list.set(i, delta + list.get(i));
            }
            s.params.put(d, list);
        }
        return s;
    }

}
