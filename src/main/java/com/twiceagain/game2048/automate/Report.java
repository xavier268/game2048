/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.automate;

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

    /**
     * Run the experiment n times and report tehe results.
     *
     * @param auto
     * @param b
     * @param nb
     * @return
     */
    public List<Stat> run(Auto auto, Board b, int nb) {
        for (int i = 0; i < nb; i++) {
            stats.add(runOnce(auto, b));
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

    public static class Stat {

        public double score = 0;
        public double moves = 0;
        public double millis = 0;

        public static String header() {
            return "\n\tScore\tMoves\tMillis\tMove/millis";
        }

        @Override
        public String toString() {
            return String.format("\n\t%.1f\t%.1f\t%.1f\t%.1f",
                    score,
                    moves,
                    millis,
                    moves * 1. / millis);
        }

    }
}
