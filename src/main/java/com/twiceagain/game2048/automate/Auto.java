/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.automate;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import java.util.List;

/**
 *Interface to autoplay until limit is reached.
 * @author xavier
 */
public interface Auto {
    
    /**
     * Play automatically using the provided board as input.
     * @param board - the board as it is when the autoplay is finished, 
     * for whatever reason.  
     */
    public void auto(Board board);
    /**
     * Play automatically using the provided board as input until limit triggers.
     * @param board - the board to start with. It WILL BE MODIFIED.
     * @param limit  
     */
    public void auto(Board board, Limit limit);
    
    /**
     * The moves that were played.
     * @return 
     */
    public List<Direction> getMoves();
    
    public void reset();
    
    /**
     * Select the best direction to play.
     * @param board
     * @return 
     */
    public Direction selectDirection(Board board);
    
    
}
