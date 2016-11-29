/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.automate.Auto;
import com.twiceagain.game2048.automate.AutoEvalCustom;
import com.twiceagain.game2048.automate.AutoEvalMultilevelScore;
import com.twiceagain.game2048.automate.AutoEvalScore;
import com.twiceagain.game2048.automate.AutoRandom;
import com.twiceagain.game2048.report.Report;
import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.report.Stat;
import java.util.List;

/**
 *
 * @author xavier
 */
public class MainEvaluateAutomaticMoves {

    private static final long SEED = 654654L;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        evalAutoRandom(4, 1000, 100000);        
        evalAutoEvalScore(4, 1000, 100000);
        evalAutoEvalCustom(4, 1000, 100000, 10, 0,0);
        evalAutoEvalCustom(4, 1000, 100000, -10, 0,0);
        evalAutoEvalCustom(4, 1000, 100000, 0, -0.1,0);
        evalAutoEvalCustom(4, 1000, 100000, 0, -1.,0);
        evalAutoEvalCustom(4, 1000, 100000, 1, -1.,0); // << Best overall choice for the moment ...
        evalAutoEvalCustom(4, 1000, 100000, 1, -1,-0.5); 
        evalAutoEvalCustom(4, 1000, 100000, 0, -5.,0);
        evalAutoEvalCustom(4, 1000, 100000, 10, -5.,0);
        evalAutoEvalCustom(4, 1000, 100000, 0, -100,0);
        evalAutoEvalMultilevelScore(4, 10, 100000, 5); // Very disappointing and very slow ...

    }

    public static void  evalAutoRandom(int size, int nb, int step) {
        Report rep = new Report();
        Board b = new BoardImpl(size, SEED);
        System.out.println("\n***********Evaluating a 4x4 board with AutoRandom");
        System.out.printf("\nIterations : %d, display steps : %d", nb, step);
        display(rep, new AutoRandom(), b, nb, step);
    }

    public static void  evalAutoEvalScore(int size, int nb, int step) {
        Report rep = new Report();
        Board b = new BoardImpl(size, SEED);
        System.out.println("\n**********Evaluating a 4x4 board with AutoEvalScore");
        System.out.printf("\nIterations : %d, display steps : %d", nb, step);
        display(rep, new AutoEvalScore(), b, nb, step);
    }

    public static void   evalAutoEvalCustom(int size, int nb, int step, int weight, double delta, double delta2) {
        Report rep = new Report();
        Board b = new BoardImpl(size, SEED);
        System.out.println("\n**********Evaluating a 4x4 board with AutoEvalCutsom1");
        System.out.printf("\nIterations : %d, display steps : %d, weight: %d, delta %f, deta2 %f", nb, step, weight, delta, delta2);
        display(rep, new AutoEvalCustom(weight, delta, delta2), b, nb, step);
    }

    public static void evalAutoEvalMultilevelScore(int size, int nb, int step, int level) {
        Report rep = new Report();
        Board b = new BoardImpl(size, SEED);
        System.out.println("\n**********Evaluating a 4x4 board with AutoEvalMultilevelScore");
        System.out.printf("\nIterations : %d, display steps : %d, nb of levels : %d", nb, step, level);
        display(rep, new AutoEvalMultilevelScore(level), b, nb, step);
        
    }

    public static void display(Report rep, Auto auto, Board b, int nbloop, int step) {
        List<Stat> stats = rep.run(auto, b, nbloop);

        int i = 0;
        boolean displHeader = true;

        for (Stat s : stats) {
            i++;
            if (i % step == 0) {
                if (displHeader) {
                    System.out.println(Stat.header());
                    displHeader = false;
                }
                System.out.print(s);
            }
        }
        Stat sum = rep.getAverage();
        Stat best = rep.getBest();
        
        System.out.printf("\nMEAN%s%s", Stat.header(), sum);

        System.out.printf("\nBEST%s%s", Stat.header(), best);
        System.out.println("\n***************************************************\n");

    }

}
