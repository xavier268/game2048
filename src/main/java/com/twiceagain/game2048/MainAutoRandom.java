/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.automate.AutoRandom;
import com.twiceagain.game2048.automate.Report;
import com.twiceagain.game2048.automate.Report.Stat;
import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import java.util.List;

/**
 *
 * @author xavier
 */
public class MainAutoRandom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Report rep = new Report();
        Board b = new BoardImpl(4);
        b.randomize(5);
        List<Report.Stat> stats = rep.run(new AutoRandom(), b, 100000);

        Stat best = new Stat();
        Stat sum = new Stat();

        int i = 0;
        System.out.println(Stat.header());
        for (Stat s : stats) {
            i++;
            sum.millis += s.millis;
            sum.score += s.score;
            sum.moves += s.moves;
            if (s.score > best.score) {
                best.score = s.score;
                best.millis = s.millis;
                best.moves = s.moves;
            }
            if (i % 1000 == 0) {
                System.out.print(s);
            }
        }
        sum.millis = sum.millis / i;
        sum.score = sum.score / i;
        sum.moves = sum.moves  / i;

        System.out.printf("\nMEAN%s%s", Stat.header(), sum);

        System.out.printf("\nBEST%s%s", Stat.header(), best);

    }

}
