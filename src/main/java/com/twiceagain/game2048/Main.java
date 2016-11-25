/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.board.Board;
import static com.twiceagain.game2048.board.Direction.*;
import com.twiceagain.game2048.board.BoardImpl;
import java.io.IOException;

/**
 * Main loop to play.
 *
 * @author xavier
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int s = 4;
        try {
            s = Integer.decode(args[0]);
        } catch (Exception ex) {
        }
        
        Board bb = new BoardImpl(s);
        bb.next();
        bb.next();
        System.out.printf(bb.toString());
        System.out.printf("Tapez 4 8 6 ou 2 pour jouer. O pour sortir.\n>");

        while (bb.canMove()) {

            int read = System.in.read();
            switch (read) {
                case 48 + 8:
                    bb.play(UP);
                    System.out.printf(bb.toString());
                    System.out.printf("Tapez 4 8 6 ou 2 pour jouer. O pour sortir.\n>");
                    break;
                case 48 + 6:
                    bb.play(RIGHT);
                    System.out.printf(bb.toString());
                    System.out.printf("Tapez 4 8 6 ou 2 pour jouer. O pour sortir.\n>");
                    break;
                case 48 + 4:
                    bb.play(LEFT);
                    System.out.printf(bb.toString());
                    System.out.printf("Tapez 4 8 6 ou 2 pour jouer. O pour sortir.\n>");
                    break;
                case 48 + 2:
                    bb.play(DOWN);
                    System.out.printf(bb.toString());
                    System.out.printf("Tapez 4 8 6 ou 2 pour jouer. O pour sortir.\n>");
                    break;

                case 48:
                    System.out.println("\n Abandon .. ...\n");
                    return;
                default:
            }

        }

        System.out.println("Il n'y a plus de coup possible ... Terminé !.\n");

    }

}
