/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.automate;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.report.Report;

/**
 * Evaluation will use the score, the highest value reached(wihgted with
 * wMax), and sum of the square delta logs (weighted with wDelta).
 *
 * @author xavier
 */
public class AutoEvalCustom extends AutoEvalScore {

    int wMax;
    double wDelta;

    public AutoEvalCustom(int wMax, double wDelta) {
        this.wMax = wMax;
        this.wDelta = wDelta;
    }

    @Override
    protected double eval(Board b) {
        return max(b) * wMax + delta(b) * wDelta + super.eval(b);
    }
    
    /**
     * Compute the best outcome in the futur with this board.
     */

    /**
     * Compute the max value in the board.
     *
     * @param b
     * @return
     */
    protected double max(Board b) {
        int max = 0;
        for (int i = 0; i < b.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                max = (b.at(i, j) != null && b.at(i, j) > max) ? b.at(i, j) : max;
            }
        }
        return max;
    }

    /**
     * Compute the deltas between the log of the values.
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
