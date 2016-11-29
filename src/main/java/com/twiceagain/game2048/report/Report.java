/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.report;

import com.twiceagain.game2048.automate.Auto;
import com.twiceagain.game2048.board.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * Produce a report for an automated play.
 *
 * @author xavier
 */
public class Report {

    protected List<Stat> stats = new ArrayList<>();
    protected Stat best = new Stat();
    protected Stat sum = new Stat();

    /**
     * Run the experiment n times and report the results.
     *
     * @param auto
     * @param b
     * @param nb
     * @return
     */
    public List<Stat> run(Auto auto, Board b, int nb) {
        for (int i = 0; i < nb; i++) {
            Stat s = runOnce(auto, b);
            stats.add(s);
            sum.score += s.score;
            sum.millis += s.millis;
            sum.moves += s.moves;
            if (s.score > best.score) {
                best.score = s.score;
                best.millis = s.millis;
                best.moves = s.moves;
               
            }
        }
        return stats;
    }

    protected Stat runOnce(Auto auto, Board b) {
        long time = System.currentTimeMillis();
        Stat r = new Stat();
        Board bb = b.duplicate();
        auto.reset();
        auto.auto(bb);
        r.score = bb.getScore();
        r.moves = auto.getMoves().size();
        r.millis = System.currentTimeMillis() - time;
        return r;

    }

    public Stat getAverage() {
        int i = stats.size();
        Stat r = new Stat();
        r.score = sum.score / i;
        r.millis = sum.millis / i;
        r.moves = sum.moves / i;
        return r;
    }

    public Stat getBest() {
        return best;
    }

    

}
