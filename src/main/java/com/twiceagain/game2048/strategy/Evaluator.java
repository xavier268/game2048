/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy;

import com.twiceagain.game2048.board.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * Evaluate and report about a given strategy.
 *
 * @author xavier
 */
public interface Evaluator {

    /**
     * Get the final board, iterating over the provided board, until no more
     * move is possible. The initial board IS modified.
     *
     * @param board
     * @param strategy
     * @return
     */
    public static Board getFinalBoard(Board board, Strategy strategy) {

        while(board.canMove()) {
            board.play(strategy.selectMove(board));
        }
        return board;
    }
    
    /**
     * Get the final score achieved with the provided starting board.
     * @param board
     * @param strategy
     * @return 
     */
    public static int getFinalScore(Board board, Strategy strategy) {
        return getFinalBoard(board, strategy).getScore();
    }
    
    /**
     * Start form a common Board position, iterate and return the various final scores achieved.
     * @param board
     * @param strategy
     * @param count
     * @return 
     */
    public static List<Integer> getFinalScores(Board board, Strategy strategy, int count) {
        List<Integer> lr = new ArrayList<>();
        for (int i = 0; i<count; i++) {
            lr.add(getFinalScore(board.duplicate(), strategy));
        }
        return lr;
    }
    
    public static String statsHeader() {
        return "\tnb\tmin\tmed\tavg\tmax\tsigma";
    }
    
    public static String stats(List<Integer> stats) {
        double sum , sum2, min, max, median, avg, var,sigma;  
        int n = stats.size();        
        stats.sort(Integer::compare);
        median = stats.get(n/2);
        min = stats.get(0);
        max = stats.get(n-1);
        sum = sum2 = 0;
        for(Integer i : stats) {
            sum += i;
            sum2 += i*i;
        }
        avg = sum/n;
        var = (sum2/n - avg*avg);
        sigma = Math.sqrt(var);
        return String.format("\t%d\t%.0f\t%.0f\t%.0f\t%.0f\t%.0f", 
                n,min, median, avg, max, sigma);
        
    }
    /**
     * Display a report for the experiment.
     * @param message
     * @param b
     * @param s
     * @param count 
     */
    public static void report(String message, Board b, Strategy s, int count) {
        double t = System.currentTimeMillis();
        String m = (message + "                    ").substring(0,25);
        List<Integer> stats = getFinalScores(b, s, count);
        t = (System.currentTimeMillis() - t) / stats.size();
        System.out.printf("\n%s\t%s\t\t%.3f millis/test",
                m,
                stats(stats),
                t);
    }
    
    

}
