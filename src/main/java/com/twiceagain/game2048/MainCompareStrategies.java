/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.util.Evaluator;
import com.twiceagain.game2048.strategy.implement.StrategyDelta;
import com.twiceagain.game2048.strategy.implement.StrategyGreedy;
import com.twiceagain.game2048.strategy.implement.StrategyLTExpectation;
import com.twiceagain.game2048.strategy.implement.StrategyRandom;
import com.twiceagain.game2048.strategy.implement.StrategyRotate;
import com.twiceagain.game2048.util.Serializor;
import java.io.IOException;

/**
 *
 * @author xavier
 */
public class MainCompareStrategies {

    /**
     * Compare the performance of the various strategies. Current results. Note
     * that LTExp with Delta is disappointing. Most promising is LTExp Random
     * x50.
     * <pre>
     *
     * Strategy                     		nb	min	med	avg	max	sigma
     * ======================================================================================
     *
     * Random                   		1000	168	1148	1164	3144	541		0,628 millis/test
     * Rotate                   		1000	160	1140	1175	3172	556		0,241 millis/test
     * Greedy                   		1000	680	2040	1929	2040	189		1,449 millis/test
     * Delta                    		1000	544	2848	2885	11544	1438		2,699 millis/test
     * LTExp Rotate x4          		10	6908	11692	11281	17044	4174		531,200 millis/test
     * LTExp Random x1          		10	3340	7552	8750	15024	3785		106,400 millis/test
     * LTExp Random x4          		10	7468	16572	14617	16708	3584		590,900 millis/test
     * LTExp Greedy x 1         		10	2936	10688	9001	14444	3795		1005,800 millis/test
     * LTExp Greedy x 4         		10	3440	10844	8804	15688	4350		4252,900 millis/test
     * LTExp Random x10         		10	6948	28956	24061	36936	10105		2131,300 millis/test
     * LTExp Random x50         		10	7280	36988	42258	72904	21453		17300,000 millis/test
     * LTExp Random x100        		10	16632	37656	46223	77352	17144		38905,800 millis/test
     * LTExp Random x200        		10	35244	61512	57479	80336	15291		109380,900 millis/test
     * ======================================================================================
     *
     * ======================================================================================
     * ------------------------------------------------------------------------
     * BUILD SUCCESS
     * ------------------------------------------------------------------------
     * Total time: 29:07.966s
     * Finished at: Tue Mar 07 12:04:15 CET 2017
     * Final Memory: 7M/172M
     * ------------------------------------------------------------------------
     * </pre>
     *
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new BoardImpl(4);
        System.out.printf("\nStrategy                     \t%s", Evaluator.statsHeader());
        System.out.printf("\n======================================================================================\n");

        Evaluator.report("Random", b, new StrategyRandom(), 1000);
        Evaluator.report("Rotate", b, new StrategyRotate(), 1000);
        Evaluator.report("Greedy", b, new StrategyGreedy(), 1000);
        Evaluator.report("Delta", b, new StrategyDelta(-1), 1000);
        Evaluator.report("LTExp Rotate x4", b, new StrategyLTExpectation(new StrategyRotate(), 4), 10);
        //Evaluator.report("LTExp Rotate x10", b, new StrategyLTExpectation(new StrategyRotate(), 10), 30);
        // LTExp with Delta is VERY disappointing (slower, less performance)
        // Evaluator.report("LTExpDt", b, new StrategyLTExpectation(new StrategyDelta(-1), 3), 10);
        Evaluator.report("LTExp Random x1", b, new StrategyLTExpectation(new StrategyRandom(), 1), 10);
        Evaluator.report("LTExp Random x4", b, new StrategyLTExpectation(new StrategyRandom(), 4), 10);
        Evaluator.report("LTExp Greedy x 1", b, new StrategyLTExpectation(new StrategyGreedy(), 1), 10);
        Evaluator.report("LTExp Greedy x 4", b, new StrategyLTExpectation(new StrategyGreedy(), 4), 10);
        Evaluator.report("LTExp Random x10", b, new StrategyLTExpectation(new StrategyRandom(), 10), 10);
        Evaluator.report("LTExp Random x50", b, new StrategyLTExpectation(new StrategyRandom(), 50), 10);
        Evaluator.report("LTExp Random x100", b, new StrategyLTExpectation(new StrategyRandom(), 100), 10);
        Evaluator.report("LTExp Random x200", b, new StrategyLTExpectation(new StrategyRandom(), 200), 10);
        try {
            System.out.printf("\n======================================================================================\n");
            Evaluator.report("Parametric trained", b, Serializor.deserialize("lastStratParam"), 1000);
        } catch (IOException | ClassNotFoundException ex) {
        }

        System.out.printf("\n======================================================================================\n");
    }

}
