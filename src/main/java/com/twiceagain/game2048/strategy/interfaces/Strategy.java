/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy.interfaces;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;

/**
 * A strategy to play the game until the end.
 * @author xavier
 */
public interface Strategy {
    
    public Direction selectMove(Board b);
    
}
