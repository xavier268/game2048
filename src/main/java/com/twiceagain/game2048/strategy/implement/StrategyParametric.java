/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy.implement;

import com.twiceagain.game2048.strategy.interfaces.TeacheableStrategyAbstract;
import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import java.util.Random;
import com.twiceagain.game2048.strategy.interfaces.TeacheableStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * Strategy is defined by applying coeffivcient to the various
 * positions/directions. Total coeff size is 4*size*size.
 *
 *
 * @author xavier
 */
public class StrategyParametric extends TeacheableStrategyAbstract implements TeacheableStrategy {


    /**
     * Initialize the parameters of the strategy randomly.
     *
     * @param size
     */
    
    public StrategyParametric(int size) {
        this.size = size;
        paramSize = size * size * 4;
        rdm = new Random();
        randomize();
    }
    
    /**
     * Dummy protected constructor does nothing.
     */
    protected StrategyParametric() {       
    }


    /**
     * Compute the score for a given direction.
     *
     * @param d
     * @param b
     * @return
     */
    @Override
    protected Double compute(Direction d, Board b) {
        Double r = 0.;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (b.at(i, j) != null) {
                    r += b.at(i, j) * params.get(d.ordinal() * size * size + i * size + j);
                }
            }
        }
        //System.out.printf("\n   compute %s -> %s", d, r);
        return r;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append(getClass().getName())
                .append(" [ size = ")
                .append(size)
                .append("]")
                .append("\n")
                .append(params);
        return sb.toString();
    }
    
    @Override
    public StrategyParametric cloneAndModify(double temperature) {
        StrategyParametric s = new StrategyParametric(size);
        
        s.params = new ArrayList<>(paramSize);
        for (Double p : params) {
            double delta = temperature * (2 * rdm.nextDouble() - 1);
            s.params.add(p + delta);
        }
        return s;
    }
    
    /**
     * Return gradient , normalized .
     * @param count
     * @param lambda
     * @return 
     */
    @Override
    @Deprecated
    public List<Double> computeGradient(int count, double lambda)   {

        List<Double> grad = new ArrayList<>(paramSize);
        double fscore = computeAverageFinalScore(count);
        StrategyParametric s2;

        s2 =  new StrategyParametric(size);
        double n = 0.;
        
        for (int i = 0; i < params.size(); i++) {
            s2.params.set(i, params.get(i) + lambda);            
            double v2 = s2.computeAverageFinalScore(count);           
            grad.add((v2-fscore));
            n += (fscore - v2) * (fscore - v2);
            // Restore s2 params ...
            s2.params.set(i, params.get(i));
        }
        // Normalize gradient ..
        n = Math.sqrt(n);
        for (int i = 0; i < params.size(); i++) {
            grad.set(i,grad.get(i)/n);
        }
        
        return grad;
    }


}
