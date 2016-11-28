/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.board;

import static com.twiceagain.game2048.board.Direction.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Implements the board data structure.
 *
 * @author xavier
 */
public class BoardImpl implements Board {

    protected int size;
    protected int score = 0;
    protected Random rdm;
    protected final Map<Position, Integer> content = new HashMap();
    private static long SEED = 0;

    /**
     * Construct a size x size board.
     *
     * @param size
     */
    public BoardImpl(int size) {
        this.size = Math.max(3, size);
        SEED += 10000L;
        rdm = new Random(SEED + System.currentTimeMillis());
    }

    /**
     * Construct a size x size board.
     *
     * @param size
     * @param randomSeed - random seed for the board.
     */
    public BoardImpl(int size, long randomSeed) {
        this.size = Math.max(3, size);
        rdm = new Random(randomSeed);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public final void reset() {
        content.clear();
        score = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < size; i++) {
            sb.append("\t").append(i);
        }
        sb.append("\n   --");
        for (int i = 0; i < size; i++) {
            sb.append("\t----");
        }

        for (int i = 0; i < size; i++) {
            sb.append("\n").append(i).append(" |");
            for (int j = 0; j < size; j++) {
                sb.append("\t");
                Integer v = at(i, j);
                if (v != null) {
                    sb.append(v);
                }
            }
        }
        sb.append("\n\n"
                + "");
        sb.append(String.format("Score : %d\t\tMoves: ", getScore()));
        if (canMove(UP)) {
            sb.append("UP ");
        }
        if (canMove(DOWN)) {
            sb.append("DOWN ");
        }
        if (canMove(RIGHT)) {
            sb.append("RIGHT ");
        }
        if (canMove(LEFT)) {
            sb.append("LEFT ");
        }
        sb.append("\n");

        return sb.toString();
    }

    @Override
    public boolean canMove() {
        return canMove(UP) || canMove(DOWN) || canMove(LEFT) || canMove(RIGHT);
    }

    @Override
    public boolean canMove(Direction direction) {
        for (int i = 0; i < size; i++) {
            if (canMove(selectPositions(direction, i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Can we move for the given row or column ?
     *
     * @param selection - a ordered selection of "size" positions, representing
     * a row or column.
     * @return
     */
    boolean canMove(List<Position> selection) {
        for (int i = 0; i < size - 1; i++) {
            if (at(selection.get(i)) == null
                    || at(selection.get(i)).equals(at(selection.get(i + 1)))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFull() {
        return countEmptyPositions() == 0;
    }

    /**
     * Get a uniform random number between 0 and n-1
     *
     * @param n
     * @return
     */
    int getRandomInt(int n) {
        return rdm.nextInt(n);
    }

    @Override
    public boolean isValid(Position p) {
        return p != null && isValid(p.i, p.j);
    }

    @Override
    public Integer at(Position p) {
        return content.get(p);
    }

    @Override
    public void set(Position p, Integer value) {
        if (isValid(p)) {
            if (value == null) {
                unset(p);
                return;
            }
            content.put(p, value);
        }
    }

    @Override
    public void move(Direction d) {
        for (int i = 0; i < size; i++) {
            move(selectPositions(d, i));
        }
    }

    /**
     * Attempts to move a vector of positions, that represents an ordered row or
     * column.
     *
     * @param sel
     */
    void move(List<Position> sel) {
        trim(sel);
        collapse(sel);
        trim(sel);

    }

    /**
     * Collapse duplicated neighbouring non null values.
     */
    void collapse(List<Position> sel) {
        for (int i = 0; i < size - 1; i++) {
            if (at(sel.get(i)) != null && at(sel.get(i)).equals(at(sel.get(i + 1)))) {
                set(sel.get(i), 2 * at(sel.get(i)));
                score += at(sel.get(i));
                unset(sel.get(i + 1));
            }
        }
    }

    /**
     * Remove empty spaces by shifting to the beginning of the vector.
     *
     * @param sel
     */
    @SuppressWarnings("empty-statement")
    void trim(List<Position> sel) {
        while (trim1(sel));
    }

    /**
     * Trim 1 position, returning true if something changed.
     *
     * @param sel
     * @return
     */
    boolean trim1(List<Position> sel) {
        boolean changed = false;
        for (int i = 0; i < size - 1; i++) {
            if (at(sel.get(i)) == null && at(sel.get(i + 1)) != null) {
                changed = true;
                set(sel.get(i), at(sel.get(i + 1)));
                unset(sel.get(i + 1));
            }
        }
        return changed;
    }

    @Override
    public boolean next() {
        if (countEmptyPositions() == 0) {
            return false;
        }
        int ri = getRandomInt(size);
        int rj = getRandomInt(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Position p = new Position((ri + i) % size, (rj + j) % size);
                if (at(p) == null) {
                    set(p, 2);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int countEmptyPositions() {
        return size * size - content.size();
    }

    @Override
    public void unset(Position p) {
        content.remove(p);
    }

    @Override
    public Integer at(int i, int j) {
        Position p = new Position(i, j);
        return at(p);
    }

    @Override
    public void randomize(int nb) {
        reset();
        while (content.size() != nb) {
            int i = getRandomInt(size);
            int j = getRandomInt(size);
            int v = (int) Math.pow(2, 1 + getRandomInt(6));
            set(i, j, v);
        }

    }

    @Override
    public void set(int i, int j, Integer value) {
        set(new Position(i, j), value);
    }

    @Override
    public boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < size && j < size;
    }

    @Override
    public void unset(int i, int j) {
        unset(new Position(i, j));
    }

    /**
     * Provide a sorted list of positions that correspond to a specific row of
     * column (as specified by rank). Ordering is direction dependent : for UP,
     * it will return columns, with the topmost cells (smaller i) first.
     *
     * @param d
     * @param rank
     * @return
     */
    final List<Position> selectPositions(Direction d, int rank) {
        List<Position> r = new ArrayList<>();
        switch (d) {
            case UP:
                for (int i = 0; i < size; i++) {
                    r.add(new Position(i, rank));
                }
                ;
                return r;
            case DOWN:
                for (int i = size - 1; i >= 0; i--) {
                    r.add(new Position(i, rank));
                }
                ;
                return r;
            case LEFT:
                for (int i = 0; i < size; i++) {
                    r.add(new Position(rank, i));
                }
                ;
                return r;
            case RIGHT:
                for (int i = size - 1; i >= 0; i--) {
                    r.add(new Position(rank, i));
                }
                ;
                return r;
            default:
                return null;
        }
    }

    @Override
    public void play(Direction direction) {
        move(direction);
        next();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public Board duplicate()  {
    BoardImpl b = new BoardImpl(size);
    b.content.putAll(this.content);
    b.score = score;
    return b;
    }

}
