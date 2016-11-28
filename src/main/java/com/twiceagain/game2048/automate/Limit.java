/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.automate;

import com.twiceagain.game2048.board.Board;

/**
 * Defines an object that controls when automatic play should stop.
 */
public interface Limit {

    public boolean shouldContinue(Board board);
}
