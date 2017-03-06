/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy.implement;

import com.twiceagain.game2048.strategy.interfaces.Strategy;
import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import java.util.Random;

/**
 * A strategy where the direction is chosen randomly, from the available Directions.
 * @author xavier
 */
public class StrategyRandom implements Strategy {

    Random rdm = new Random();
    
    @Override
    public Direction selectMove(Board b) {
        Direction d = Direction.values()[rdm.nextInt(4)];
        if (b.canMove(d)) {
            return d;
        } else {
            return selectMove(b);
        }
    }

}
