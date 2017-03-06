/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy.interfaces;

import com.twiceagain.game2048.board.Board;
import java.util.List;

/**
 * Define a strategy to be "teacheable". This means that the strategy is driven
 * by a list of parameters, and provides various ways to modify/extract these
 * parameters.
 *
 * @author xavier
 */
public interface TeacheableStrategy extends Strategy {

    /**
     * Randomize the parameters. Use a a starting point in the learning process.
     */
    public void randomize();

    public void setRandomSeed(long seed);

    /**
     * Build a modified strategy. The strength of the modification increases as
     * temperature increases. If temperature is 0, this returns a clone of the
     * strategy.
     *
     * @param temperature : between 0. and 1.
     * @return
     */
    TeacheableStrategy cloneAndModify(double temperature);

    List<Double> getParams();

    void setParams(List<Double> params);
    
    double computeAverageFinalScore(Board b, int count);
    double computeAverageFinalScore(int count);
    
    /**
     * Compute the gradient, averaging on count number of evaluations.
     * Usually very slow and inefficient ...
     * @param count
     * @param lambda
     * @return
     */
    @Deprecated
    List<Double> computeGradient(int count, double lambda);
    
    
    /**
     * Improve the current strategy. Tries different modified strategies until a
     * best one is found.
     *
     * @param count
     * @param temperature
     * @param timeoutmillis - millisecs before timeout
     * @return A (slightly) better strategy.
     */
    TeacheableStrategy improve(int count, double temperature, long timeoutmillis);
    TeacheableStrategy improve(int count, double temperature);
    
   
}
