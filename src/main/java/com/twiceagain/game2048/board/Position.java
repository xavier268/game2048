/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.board;

import java.io.Serializable;

/**
 * A position on the Board.
 * @author xavier
 */
public class Position implements Serializable{
    
    public int i;
    public int j;
    
    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    /**
     * Get a new position, shifted in the specified direction. There is no
     * check that the new Position is valid.
     *
     * @param d
     * @return
     */
    public Position shift(Direction d) {
        switch (d) {
            case UP:
                return new Position(i - 1, 0);
            case DOWN:
                return new Position(i + 1, 0);
            case LEFT:
                return new Position(i, j - 1);
            case RIGHT:
                return new Position(i, j + 1);
            default:
                throw new RuntimeException("Unexpected excpetion - should never happen");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.i;
        hash = 59 * hash + this.j;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.i != other.i) {
            return false;
        }
        return this.j == other.j;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", i, j);
    }
    
    
}
