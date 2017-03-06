/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy.implement;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author xavier
 */
public class StrategyParametric2 extends StrategyParametric {

    public StrategyParametric2(int size) {
        this.size = size;
        paramSize = size * size * 8;
        rdm = new Random();
        randomize();    
    }
    
    
        
    
    /**
     * Compute the score for a given direction.
     * Use a linear sum of Xi and Xi2.
     * @param d
     * @param b
     * @return
     */
    @Override
    protected Double compute(Direction d, Board b) {
        Double r = 0.;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (b.at(i, j) != null) {
                    r += b.at(i, j) * params.get(d.ordinal() * size * size + i * size + j);
                    r += b.at(i,j)*b.at(i,j) * params.get(size*size*4 + d.ordinal() * size * size + i * size + j);
                }
            }
        }
        //System.out.printf("\n   compute %s -> %s", d, r);
        return r;
    }

    @Override
    public StrategyParametric2 cloneAndModify(double temperature) {
        StrategyParametric2 s = new StrategyParametric2(size);
        
        s.params = new ArrayList<>(paramSize);
        for (Double p : params) {
            double delta = temperature * (2 * rdm.nextDouble() - 1);
            s.params.add(p + delta);
        }
        return s;
    }
}
