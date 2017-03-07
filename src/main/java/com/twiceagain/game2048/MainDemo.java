/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.board.Direction;
import com.twiceagain.game2048.strategy.implement.StrategyLTExpectation;
import com.twiceagain.game2048.strategy.implement.StrategyRandom;
import com.twiceagain.game2048.strategy.interfaces.Strategy;

/**
 * Demo auto solving.
 * @author xavier
 */
public class MainDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new BoardImpl(4, System.currentTimeMillis());
        Strategy s = new StrategyLTExpectation(new StrategyRandom(), 200);
        
        while(b.canMove()) {
            Direction d = s.selectMove(b);
            b.move(d);
            System.out.printf("\n Selected move : %s", d);
            b.next();
            System.out.println(b);
        }
        
    }
    
}
