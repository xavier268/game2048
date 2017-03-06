/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy.implement;

import com.twiceagain.game2048.strategy.interfaces.Strategy;
import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;

/**
 * A deterministic strategy, that test each direction in turn.
 * @author xavier
 */
public class StrategyRotate implements Strategy {
    protected static int i = 0;

    @Override
    public Direction selectMove(Board b) {
        i++;
        return Direction.values()[i%4];
    }
    
}
