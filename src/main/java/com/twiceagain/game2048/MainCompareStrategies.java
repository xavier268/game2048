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
     * Compre the performance of the various strategies.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new BoardImpl(4);        
        System.out.printf("\nStrategy%s", Evaluator.statsHeader());
        System.out.printf("\n==========================================================================\n");

        Evaluator.report("Random", b, new StrategyRandom(), 1000);
        Evaluator.report("Rotate", b, new StrategyRotate(), 1000);
        Evaluator.report("Greedy", b, new StrategyGreedy(), 1000);
        Evaluator.report("Delta", b, new StrategyDelta(-1), 1000);
        Evaluator.report("LTExp", b, new StrategyLTExpectation(), 2);

        System.out.printf("\n==========================================================================\n");
    }

}
