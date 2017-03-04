/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.board;

import java.io.Serializable;

/**
 * Object represent a playable board. Player plays using play(direction). Game
 * is over when canMove() becomes false.
 *
 * @author xavier
 */
public interface Board extends Serializable {

    /**
     * Get board size.
     *
     * @return
     */
    int getSize();

    /**
     * Compute current score.
     *
     * @return
     */
    int getScore();

    /**
     * Reset the board.
     */
    void reset();

    /**
     * Play means move + next.
     *
     * @param direction
     * @return 
     */
    public Board play(Direction direction);

    /**
     * Move in the specified direction.
     *
     * @param d
     * @return 
     */
    public Board move(Direction d);

    /**
     * Add a new position, with content=2.
     *
     * @return true if successfull, false if full.
     */
    public boolean next();

    /**
     * Can we make a move ?
     *
     * @return
     */
    public boolean canMove();

    /**
     * Can we make a move in the provided direction ?
     *
     * @param direction
     * @return
     */
    public boolean canMove(Direction direction);

    /**
     * Is the board full ? But move can still be possible by collapsing
     * neighbours.
     *
     * @return
     */
    public boolean isFull();

    /**
     * How many empty positions on the board ?
     *
     * @return
     */
    public int countEmptyPositions();

    /**
     * Is this a valide position ?
     *
     * @param p
     * @return
     */
    public boolean isValid(Position p);

    /**
     * Is this a valide position ?
     *
     * @param i
     * @param j
     * @return
     */
    public boolean isValid(int i, int j);

    /**
     * Get value of provided position. Values are 2, 4, 8, 16, 32, ...
     *
     * @param p
     * @return null if empty.
     */
    public Integer at(Position p);

    /**
     * Get value of provided position. Values are 2, 4, 8, 16, 32, ...
     *
     * @param i
     * @param j
     * @return null if empty.
     */
    public Integer at(int i, int j);

    /**
     * Set position value.
     *
     * @param p
     * @param value
     */
    public void set(Position p, Integer value);

    /**
     * Set position value.
     *
     * @param i
     * @param j
     * @param value
     */
    public void set(int i, int j, Integer value);

    /**
     * Set the position to an empty position. WARNING : This is NOT the same
     * than setting the position value to null ! In particular, setting a
     * position value to null will increment the total number of set positions,
     * and will make the score computation fail. Don't do it !!
     *
     * @param p
     */
    public void unset(Position p);

    /**
     * Set the position to an empty position. WARNING : This is NOT the same
     * than setting the position value to null ! In particular, setting a
     * position value to null will increment the total number of set positions,
     * and will make the score computation fail. Don't do it !!
     *
     * @param i
     * @param j
     */
    public void unset(int i, int j);

    /**
     * Create a randomly populated board.
     *
     * @param nbOfContent - number of positions to populate. Should be less or
     * equal to the total number of positions in the board.
     */
    public void randomize(int nbOfContent);
    
    /**
     * Make a deep copy of the board.
     * But the new random generator is different !
     * @return 
     */
    public Board duplicate() ;
    
   
    
}
