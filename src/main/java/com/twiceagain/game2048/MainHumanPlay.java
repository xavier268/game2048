/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.strategy.Evaluator;
import com.twiceagain.game2048.strategy.StrategyHuman;
import java.io.IOException;

/**
 * MainHumanPlay loop to play.
 *
 * @author xavier
 */
public class MainHumanPlay {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int s = 4;
        try {
            s = Integer.decode(args[0]);
        } catch (NumberFormatException ex) {
        }
        
        Board bb = new BoardImpl(s);
        Evaluator.getFinalScore(bb, new StrategyHuman());
        System.out.println("\nNo more move ...\n");
    }
        
}
