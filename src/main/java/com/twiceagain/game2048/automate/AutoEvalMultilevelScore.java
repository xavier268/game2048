/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.automate;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;

/**
 * Evaluation will use the best score achieveable a few levels up.
 *
 * @author xavier
 */
public class AutoEvalMultilevelScore extends AutoEvalScore {

    int level;

    public AutoEvalMultilevelScore(int level) {
        this.level = level;
    }
    

    @Override
    protected double eval(Board board, Direction d) {
        return eval(level, board.duplicate().move(d));
    }

    protected double eval(int level, Board board) {
        if(level == 0) return eval(board);
        
        double max = 0;
        for(Direction d : Direction.values()) {
            if(board.canMove(d)) {
                double e = eval(level-1,board.duplicate());
                if(e>max) max = e;
            }            
        }
        return max;           
    }
}
