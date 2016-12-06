/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.strategy.Evaluator;
import com.twiceagain.game2048.strategy.StrategyDelta;
import com.twiceagain.game2048.strategy.StrategyGreedy;
import com.twiceagain.game2048.strategy.StrategyLTExpectation;
import com.twiceagain.game2048.strategy.StrategyRandom;
import com.twiceagain.game2048.strategy.StrategyRotate;

/**
 *
 * @author xavier
 */
public class MainCompareStrategies {

    /**
     * Compare the performance of the various strategies. Current results. Note
     * that LTExp with Delta is disappointing.
     * <pre>
     * Strategy	nb	min	med	avg	max	sigma
     * ==========================================================================
     *
     * Random		1000	208	1080	1138	3280	563		0,293 millis/test
     * Rotate		1000	244	1168	1191	3432	537		0,172 millis/test
     * Greedy		1000	952	2040	1930	2040	182		1,517 millis/test
     * Delta		1000	356	2860	2931	11276	1468		2,286 millis/test
     * LTExpRd		10	7608	16468	16709	27772	5066		615,100 millis/test
     * LTExpDt		10	6928	15776	14667	32808	7139		8778,300 millis/test
     * ==========================================================================
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
        Evaluator.report("LTExp Random x1", b, new StrategyLTExpectation(new StrategyRandom(), 1), 100);
        Evaluator.report("LTExp Random x2", b, new StrategyLTExpectation(new StrategyRandom(), 2), 30);
        Evaluator.report("LTExp Random x10", b, new StrategyLTExpectation(new StrategyRandom(), 10), 30);
        Evaluator.report("LTExp Rotate x2", b, new StrategyLTExpectation(new StrategyRotate(), 2), 30);
        Evaluator.report("LTExp Rotate x10", b, new StrategyLTExpectation(new StrategyRotate(), 10), 30);
        // LTExp with Delta is VERY disappointing (slower, less performance)
        // Evaluator.report("LTExpDt", b, new StrategyLTExpectation(new StrategyDelta(-1), 3), 10);

        System.out.printf("\n======================================================================================\n");
    }

}
