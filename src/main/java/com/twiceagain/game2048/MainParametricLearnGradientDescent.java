/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.util.Evaluator;
import com.twiceagain.game2048.strategy.implement.StrategyParametric;
import com.twiceagain.game2048.strategy.interfaces.TeacheableStrategy;
import java.util.List;

/**
 * Implementing a "gradient descent strategy" for automatic learning.
 *
 * @author xavier Will typically converge around ???? with a simple parametric
 * model. Seems to be much slower than pure "simulated annealing" with random
 * pick of alternative neighbour.
 *
 *
 */
public class MainParametricLearnGradientDescent {

    /**
     * Implementing "Gradient Descent".
     *
     * Appears to be very innefficient, slow progress, and limited ability to
     * find global optima.
     *
     * @param args the command line arguments
     * @throws java.lang.CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {

        System.out.printf("\nCreating empty board");
        Board b = new BoardImpl(4);

        System.out.printf("\nCreating random strategy");

        TeacheableStrategy strat;

        // Do not reload previous attempt ...
        strat = new StrategyParametric(4);

        System.out.printf("\nStrategy                     \t%s", Evaluator.statsHeader());
        System.out.printf("\n======================================================================================\n");
        Evaluator.report("Parametric (SGD)", b, strat, 10000);
        System.out.printf("\nComputed score : %s", strat.computeAverageFinalScore(100));
        double lambda = 0.01;
        for (int i = 0; i < 20; i++) {
            int nb = 100;
            List<Double> grad = strat.computeGradient(nb, lambda);
            for (int k = 0; k < grad.size(); k++) {
                strat.getParams().set(k, strat.getParams().get(k) + lambda * grad.get(k));
            }
            System.out.printf("\nComputed score : %.0f\tnb : %s\tlambda %.8f",
                    strat.computeAverageFinalScore(100),
                    nb,
                    lambda);

        }

        Evaluator.report("Parametric improved (SGD)", b, strat, 10000);
        System.out.printf("\n======================================================================================\n");
        System.out.printf("\n%s", strat);
    }

}
