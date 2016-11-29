/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.report;

/**
 *
 * @author xavier
 */
public class Stat {
    
    public double score = 0;
    public double moves = 0;
    public double millis = 0;

    public static String header() {
        return "\n\tScore\tMoves\tMillis\tMove/millis";
    }

    @Override
    public String toString() {
        return String.format("\n\t%.1f\t%.1f\t%.1f\t%.1f", score, moves, millis, moves * 1. / millis);
    }
    
}
