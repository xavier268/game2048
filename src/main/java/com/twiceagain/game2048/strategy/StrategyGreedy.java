/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import static com.twiceagain.game2048.board.Direction.UP;

/**
 * Greedy strategy tries to maximise short term score.
 * @author xavier
 */
public class StrategyGreedy implements Strategy{

    @Override
    public Direction selectMove(Board b) {
        Direction bd = UP; // best direction
        int bs = 0;
        for(Direction d: Direction.values()) {
            int s = b.duplicate().play(d).getScore();
            if(bs<s){bs = s; bd=d;}
        }
        return bd;
    }
    
    
}
