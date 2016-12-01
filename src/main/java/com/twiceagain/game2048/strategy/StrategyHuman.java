/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.strategy;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.Direction;
import static com.twiceagain.game2048.board.Direction.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Strategy when the human player is asked waht to play.
 *
 * @author xavier
 */
public class StrategyHuman implements Strategy {

    @Override
    public Direction selectMove(Board bb) {
        try {
            while (true) {
                System.out.printf(bb.toString());
                System.out.printf("Tapez 4 8 6 ou 2 pour jouer. O pour sortir.\n>");

                int read = System.in.read();
                switch (read) {
                    case 48 + 8:
                        return UP;

                    case 48 + 6:
                        return RIGHT;
                    case 48 + 4:
                        return LEFT;
                    case 48 + 2:
                        return DOWN;

                    case 48:
                        System.out.println("\n Abandon .. ...\n");
                        System.exit(1);
                    default:
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(StrategyHuman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
