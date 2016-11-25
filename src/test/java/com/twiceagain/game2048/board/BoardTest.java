package com.twiceagain.game2048.board;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.twiceagain.game2048.board.Direction;
import static com.twiceagain.game2048.board.Direction.UP;
import com.twiceagain.game2048.board.Position;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author xavier
 */
public class BoardTest {

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("\n***********************"
                + "\n*******************\n");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSetAtUnset() {
        System.out.println("\nTesting Set-At-Unset");

        BoardImpl bb = new BoardImpl(10, 42L);
        bb.set(2, 5, 25);
        assertTrue(bb.isValid(new Position(2, 5)));
        assertEquals((Integer) 25, bb.at(2, 5));
        assertNull(bb.at(2, 6));
        bb.unset(2, 5);
        assertNull(bb.at(2, 5));
    }

    @Test
    public void displayRandomBoards() {

        System.out.println("\nTesting displaying random boards");

        BoardImpl bb = new BoardImpl(4, 42L);
        for (int i = 0; i < 3; i++) {
            bb.randomize(16);
            System.out.println(bb);
            assertEquals(0, bb.countEmptyPositions());
        }

        bb = new BoardImpl(4, 42L);
        for (int i = 0; i < 3; i++) {
            bb.randomize(15);
            System.out.println(bb);
            assertEquals(1, bb.countEmptyPositions());
        }
    }

    @Test(timeout = 2000)
    public void testNext() {
        System.out.println("\nTesting fill with next");

        BoardImpl bb = new BoardImpl(5, 42L);
        System.out.println(bb);

        for (int i = 0; i < 25; i++) {
            bb.next();
            System.out.println(bb);
        }
        assertEquals(0, bb.countEmptyPositions());
    }

    @Test
    public void testSelectPositions() {
        System.out.println("\nTesting SelectPosition");

        BoardImpl bb = new BoardImpl(5, 42L);
        for (Direction d : Direction.values()) {
            System.out.printf("\nSelected positions for %s : %s",
                    d,
                    bb.selectPositions(d, 3));
        }
    }

    @Test(timeout = 3000)
    public void testMove() {
        System.out.println("\nTesting move ");

        BoardImpl bb = new BoardImpl(5, 18L);
        for (int i = 0; i < 3; i++) {
            bb.randomize(15);
            System.out.println(bb);
            for (Direction d : Direction.values()) {

                System.out.println("\nTesting moving " + d);
                bb.move(d);
                System.out.println(bb);
            }
        }
    }

    @Test(timeout = 3000)
    public void testMoveAndNext() {
        System.out.println("\nTesting move and next");
        BoardImpl bb = new BoardImpl(5, 17L);
        bb.randomize(15);
        System.out.println(bb);
        for (int i = 0; i < 5; i++) {
            for (Direction d : Direction.values()) {
                System.out.println("\nTesting moving " + d);
                bb.move(d);
                System.out.println(bb);
                System.out.println("\nTesting next");
                bb.next();
                System.out.println(bb);
            }
        }
    }

    @Test(timeout = 5000)
    public void testMoveUPAndNextUntilBlocked() {
        System.out.println("\nTesting UP and next");
        BoardImpl bb = new BoardImpl(5, 11L);
        bb.randomize(10);
        while (bb.canMove(UP)) {

            System.out.println("\nNext ..");

            bb.next();
            System.out.println(bb);
            System.out.println("\nUp ..");
            bb.move(UP);
            System.out.println(bb);
        }
    }

}
