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
import com.twiceagain.game2048.util.Serializor;
import java.io.IOException;
import com.twiceagain.game2048.strategy.interfaces.TeacheableStrategy;

/**
 * 
 * @author xavier
 * 
 * This strategy currently converges around 3 000.
 * 
 Strategy                     		nb	min	med	avg	max	sigma
======================================================================================

Parametric               		10000	296	3016	2953	6872	877		0,627 millis/test
Computed score : 3114.24
Computed score : 2861.96
Computed score : 3154.12
Computed score : 2966.76
Computed score : 2959.6
Computed score : 2974.76
Saved : 'lastStratParam'
Parametric improved      		10000	340	3064	3006	5816	848		0,578 millis/test
 */
public class MainParametricLearn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.printf("\nCreating empty board");
        Board b = new BoardImpl(4);

        System.out.printf("\nCreating random strategy");

        TeacheableStrategy strat;
        try {
            strat = Serializor.deserialize("lastStratParam");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.printf("\n%s\n", ex);
            strat = new StrategyParametric(4);
        }

        System.out.printf("\nStrategy                     \t%s", Evaluator.statsHeader());
        System.out.printf("\n======================================================================================\n");
        Evaluator.report("Parametric", b, strat, 10000);
        System.out.printf("\nComputed score : %s", strat.computeAverageFinalScore(100));
        for (int i = 0; i < 5; i++) {
            strat = strat.improve(5000, 1); 
            // start with 100 & 0.8 then fine tune progresiveley up to 10000 & 0.1
            System.out.printf("\nComputed score : %s", strat.computeAverageFinalScore(100));
        }
        try {
            Serializor.serialize(strat, "lastStratParam");
        } catch (IOException ex) {
            System.out.printf("\n%s\n", ex);
        }
        Evaluator.report("Parametric improved", b, strat, 10000);
        System.out.printf("\n======================================================================================\n");
        System.out.printf("\n%s", strat);
    }

}
