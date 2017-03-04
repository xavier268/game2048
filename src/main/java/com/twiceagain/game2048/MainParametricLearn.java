/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.strategy.Evaluator;
import com.twiceagain.game2048.strategy.StrategyParametric;
import java.util.List;

/**
 *
 * @author xavier
 */
public class MainParametricLearn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.printf("\nCreating empty board");
        Board b = new BoardImpl(4);

        System.out.printf("\nCreating random strategy");
        StrategyParametric strat = new StrategyParametric(4);

        System.out.printf("\nStrategy                     \t%s", Evaluator.statsHeader());
        System.out.printf("\n======================================================================================\n");
        Evaluator.report("Parametric", b, strat, 1000);
        System.out.printf("\nComputed score : %s", evalStratScore(b, strat, 1000));
        for(int i=0; i<5; i++) {
            strat = improve(b, strat, 1000, 0.5);
            System.out.printf("\nComputed score : %s", evalStratScore(b, strat, 1000));        
        }
        Evaluator.report("Parametric improved", b, strat, 1000);        
        System.out.printf("\n======================================================================================\n");

    }

    protected static double evalStratScore(Board b, StrategyParametric s, int count) {
        double r = 0.;
        List<Integer> scores = Evaluator.getFinalScores(b, s, count);
        
        for (int sc : scores) {
            r += sc;
        }
        return r / count;
    }

    /**
     * Improve the current strategy. Tries different modified strategies until a
     * best one is found.
     *
     * @param b
     * @param s
     * @param count
     * @param temperature
     * @return A (slightly) better strategy.
     */
    protected static StrategyParametric improve(Board b, StrategyParametric s, int count, double temperature) {

        double baseScore = evalStratScore(b, s, count);
        double newScore = Double.NEGATIVE_INFINITY;
        StrategyParametric ss = null;
        
        while (baseScore >= newScore) {
            ss = s.makeModified(temperature);
            newScore = evalStratScore(b, ss, count);            
        }
        return ss;

    }

}
