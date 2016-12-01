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
import com.twiceagain.game2048.strategy.StrategyRandom;
import com.twiceagain.game2048.strategy.StrategyRotate;

/**
 *
 * @author xavier
 */
public class MainCompareStrategies {

    /**
     * Compre the performance of the various strategies.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new BoardImpl(4);
        int testSize = 1000;
        System.out.printf("\nStrategy%s", Evaluator.statsHeader());
        System.out.printf("\n==========================================================================\n");

        Evaluator.report("Random", b, new StrategyRandom(), testSize);
        Evaluator.report("Rotate", b, new StrategyRotate(), testSize);
        Evaluator.report("Greedy", b, new StrategyGreedy(), testSize);
        Evaluator.report("Delta", b, new StrategyDelta(-1), testSize);

        System.out.printf("\n==========================================================================\n");
    }

    

}
