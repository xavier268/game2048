/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.automate;

import com.twiceagain.game2048.board.Board;

/**
 * Evaluation will use the score AND the highest value reached.
 *
 * @author xavier
 */
public class AutoEvalCustom1 extends AutoEvalScore {

    int wMax;
    double wDelta;

    public AutoEvalCustom1(int weight, double wDelta) {
        this.wMax = weight;
        this.wDelta = wDelta;
    }

    @Override
    protected double eval(Board b) {
        Integer max = 0;
        for (int i = 0; i < b.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                max = (b.at(i, j) != null && b.at(i, j) > max) ? b.at(i, j) : max;
            }
        }
        return max * wMax + delta(b) * wDelta + super.eval(b);
    }

    /**
     * Compute the deltas between the log vof the values.
     *
     * @param b
     * @return
     */
    protected double delta(Board b) {
        double d = 0;
        for (int i = 1; i < b.getSize(); i++) {
            for (int j = 1; j < b.getSize(); j++) {
                d += delta(b.at(i, j), b.at(i - 1, j)) + delta(b.at(i, j), b.at(i, j - 1));
            }
        }
        return d;
    }

    protected double delta(Integer v1, Integer v2) {
        if (v1 == null || v2 == null) {
            return 0.;
        }
        double r = Math.log(v1) - Math.log(v2);
        return r * r;
    }

}
