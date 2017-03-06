/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.util.Evaluator;
import com.twiceagain.game2048.strategy.implement.StrategyParametric2;
import com.twiceagain.game2048.util.Serializor;
import java.io.IOException;
import com.twiceagain.game2048.strategy.interfaces.TeacheableStrategy;

/**
 *
 * @author xavier
 * 
 * This strategy, once educated, seems to top around 2 700 on average ...
 * 
Creating empty board
Creating random strategy
Loaded : 'lastStratParam2'
Strategy                     		nb	min	med	avg	max	sigma
======================================================================================

Parametric2              		10000	288	2888	2746	4712	913		0,908 millis/test
Computed score : 2746.84
Computed score : 2649.32
Computed score : 2942.76
Computed score : 2578.24
Saved : 'lastStratParam2'
Parametric2 improved     		10000	340	2748	2697	4696	888		0,626 millis/test
======================================================================================

 */
public class MainParametricLearn2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.printf("\nCreating empty board");
        Board b = new BoardImpl(4);

        System.out.printf("\nCreating random strategy");

        TeacheableStrategy strat;
        try {
            strat = Serializor.deserialize("lastStratParam2");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.printf("\n%s\n", ex);
            strat = new StrategyParametric2(4);
        }

        System.out.printf("\nStrategy                     \t%s", Evaluator.statsHeader());
        System.out.printf("\n======================================================================================\n");
        Evaluator.report("Parametric2", b, strat, 10000);
        System.out.printf("\nComputed score : %s", strat.computeAverageFinalScore(100));
        for (int i = 0; i < 3; i++) {
            strat = strat.improve(500, 0.3); 
            // start with 100 & 0.8 then fine tune progresiveley up to 10000 & 0.1
            System.out.printf("\nComputed score : %s", strat.computeAverageFinalScore(100));
        }
        try {
            Serializor.serialize(strat, "lastStratParam2");
        } catch (IOException ex) {
            System.out.printf("\n%s\n", ex);
        }
        Evaluator.report("Parametric2 improved", b, strat, 10000);
        System.out.printf("\n======================================================================================\n");
        System.out.printf("\n%s", strat);
    }

}
