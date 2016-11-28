/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.automate.Auto;
import com.twiceagain.game2048.automate.AutoEvalCustom1;
import com.twiceagain.game2048.automate.AutoEvalMultilevelScore;
import com.twiceagain.game2048.automate.AutoEvalScore;
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
public class MainEvaluateAutomaticMoves {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        evalAutoRandom(4,1000,100000);
        evalAutoEvalScore(4,1000,100000);
        evalAutoEvalCustom1(4,1000,100000,10, 0);
        evalAutoEvalCustom1(4,1000,100000,-10, 0);        
        evalAutoEvalCustom1(4,1000,100000,0, -0.1);        
        evalAutoEvalCustom1(4,1000,100000,0, -1.);  // << This provides the best MEAN    
        evalAutoEvalCustom1(4,1000,100000,1, -1.);  // << Best overall choice for the moment ...
        evalAutoEvalCustom1(4,1000,100000,0, -5.);        
        evalAutoEvalCustom1(4,1000,100000,10, -5.); // << This one is the best BEST!!  
        evalAutoEvalCustom1(4,1000,100000,0, -100);        
        evalAutoEvalMultilevelScore(4,10,100000,5); // Very disappointing and very slow ...
        
    }
    
    public static void evalAutoRandom(int size, int nb, int step) {
        Report rep = new Report();
        Board b = new BoardImpl(size);
        System.out.println("\n***********Evaluating a 4x4 board with AutoRandom");
        System.out.printf("\nIterations : %d, display steps : %d", nb,step);
        display(rep, new AutoRandom(), b, nb, step);
    }
    
    public static void evalAutoEvalScore(int size, int nb, int step) {
        Report rep = new Report();
        Board b = new BoardImpl(size);
        System.out.println("\n**********Evaluating a 4x4 board with AutoEvalScore");
        System.out.printf("\nIterations : %d, display steps : %d", nb,step);
        display(rep, new AutoEvalScore(), b, nb, step);
    }
    
    public static void evalAutoEvalCustom1(int size, int nb, int step, int weight, double delta) {
        Report rep = new Report();
        Board b = new BoardImpl(size);        
        System.out.println("\n**********Evaluating a 4x4 board with AutoEvalCutsom1");
        System.out.printf("\nIterations : %d, display steps : %d, weight: %d, delta %f", nb,step, weight, delta);
        display(rep, new AutoEvalCustom1(weight, delta), b, nb, step);
    }

    public static void evalAutoEvalMultilevelScore(int size, int nb, int step, int level) {
        Report rep = new Report();
        Board b = new BoardImpl(size);        
        System.out.println("\n**********Evaluating a 4x4 board with AutoEvalMultilevelScore");
        System.out.printf("\nIterations : %d, display steps : %d, nb of levels : %d", nb,step,level);
        display(rep, new AutoEvalMultilevelScore(level), b, nb, step);
    }
    
    public static void display(Report rep, Auto auto, Board b, int nbloop, int step) {
        List<Report.Stat> stats = rep.run(auto, b, nbloop);
        
        Stat best = new Stat();
        Stat sum = new Stat();
        
        int i = 0;
        boolean displHeader = true;
        
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
            if (i % step == 0) {
                if (displHeader) {
                    System.out.println(Stat.header());
                    displHeader = false;
                }
                System.out.print(s);
            }
        }
        sum.millis = sum.millis / i;
        sum.score = sum.score / i;
        sum.moves = sum.moves / i;
        
        System.out.printf("\nMEAN%s%s", Stat.header(), sum);
        
        System.out.printf("\nBEST%s%s", Stat.header(), best);
        System.out.println("\n***************************************************\n");
        
    }
    
}
