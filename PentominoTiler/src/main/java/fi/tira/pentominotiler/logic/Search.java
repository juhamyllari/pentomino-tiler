package fi.tira.pentominotiler.logic;

import fi.tira.pentominotiler.datastructures.MyArrayList;
import fi.tira.pentominotiler.datastructures.MyHashSet;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A Search object represents a single tiling problem.
 *
 * @author juha
 */
public class Search {

    private Board initialBoard;
    private List<Board> solutions;
    private MyHashSet<String> tried;
    private final List<List<Piece>> pieces;
    private final int[] indexOrder;
    private final IntegerProperty found = new SimpleIntegerProperty(0);

    /**
     * Constructs a Search object. The shape of the Board object defines the
     * search problem. The board must be empty (i.e. no pieces may be placed on
     * it).
     *
     * @param initialBoard an empty Board
     */
    public Search(Board initialBoard) {
        this.initialBoard = initialBoard;
        this.solutions = new MyArrayList<>();
        this.tried = initialBoard.getRows() == 6
                ? // The 6x10 board is resource intensive.
                new MyHashSet<>(4500000, 3.0)
                : new MyHashSet<>(1000000, 2.0);
        this.pieces = PieceUtils
                .allPieces()
                .stream()
                .map(p -> {
                    List<Piece> orientations = PieceUtils.nonRedundant(p);
                    List<Piece> centered = new MyArrayList<>();
                    orientations.forEach(pc -> centered.addAll(PieceUtils.centered(pc)));
                    return centered;
                })
                .collect(Collectors.toList());
        this.indexOrder = createOrderIndex(initialBoard);
    }

    /**
     * Finds all solutions to the tiling problem. Preplaces the "X" pentomino in
     * each of its legal positions in or near the quadrant nearest the origin
     * (some positions may be outside the quadrant if the number of rows or
     * columns is odd) and calls search separately on each placement. In the
     * search proper, pieces are placed on squares of increasing Euclidian
     * distance from the origin.
     */
    public void runSearch() {

        int rows = initialBoard.getRows();
        int cols = initialBoard.getCols();

        int rowsToCover = rows % 2 == 0 ? rows / 2 : rows / 2 + 1;
        int colsToCover = cols % 2 == 0 ? cols / 2 : cols / 2 + 1;

        Piece centeredX = pieces.get(0).get(2);

        Instant startTime = Instant.now();

        for (int row = 1; row < rowsToCover; row++) {
            for (int col = 1; col < colsToCover; col++) {
                if (!(row == 1 && col == 1)) {
                    timedSearch(initialBoard.placePiece(centeredX, row, col, 0));
                }
            }
        }

        Instant stopTime = Instant.now();

        System.out.println("Size of 'tried' set: " + tried.size());
        System.out.println("Search finished. Found " + solutions.size() + " solutions.");
        System.out.println("The search took " + Duration.between(startTime, stopTime).toMillis() + " milliseconds.");
    }

    private void timedSearch(Board board) {
        Instant startTime = Instant.now();
        search(board);
        Instant stopTime = Instant.now();
        long timeElapsed = Duration.between(startTime, stopTime).toMillis();
        System.out.println("Subsearch took " + timeElapsed + " milliseconds.");
    }

    private void search(Board board) {
        if (board.getUnused() == 0) {
            solutions.add(board);
            found.set(found.get() + 1);
            System.out.println("Found solution number " + found.get());
            return;
        }
        int index = getNextIndex(board);
        int row = index / board.getCols();
        int col = index % board.getCols();
        for (int i = 0; i < 12; i++) {
            if (!board.isUsed(i)) {
                for (Piece candidate : pieces.get(i)) {
                    if (board.canPlace(candidate, row, col)) {
                        Board newBoard = board.placePiece(candidate, row, col, i);
                        List<String> symmetries = newBoard.symmetryStrings();
                        if (!tried.contains(newBoard.toString())) {
                            tried.addAll(symmetries);
                            search(newBoard);
                        }
                    }
                }
            }
        }
    }

    private int getNextIndex(Board bd) {
        int boardSize = bd.getRows() * bd.getCols();
        for (int i = 0; i < boardSize; i++) {
            if (bd.charAtLinearIndex(indexOrder[i]) == 0) {
                return indexOrder[i];
            }
        }
        return -1;
    }

    private int[] createOrderIndex(Board bd) {
        int boardSize = bd.getRows() * bd.getCols();
        int[] indexArray = IntStream
                .rangeClosed(0, boardSize - 1)
                .boxed()
                .sorted((i1, i2) -> compareIndices(i1, i2, bd.getCols()))
                .mapToInt(i -> i)
                .toArray();
        return indexArray;
    }

    private int compareIndices(int i1, int i2, int cols) {
        int row1 = i1 / cols;
        int row2 = i2 / cols;
        int col1 = i1 % cols;
        int col2 = i2 % cols;
        double cmp = Math.sqrt(row1 * row1 + col1 * col1) - Math.sqrt(row2 * row2 + col2 * col2);
        if (cmp < 0) {
            return -1;
        } else if (cmp > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Returns the solutions to the problem.
     *
     * @return solutions
     */
    public List<Board> getSolutions() {
        return solutions;
    }

    /**
     * Returns the tried set. The set contains a String representation of each
     * partially filled board such that a mirrored and/or rotated version of the
     * board has been discovered by the search. This method exists for
     * troubleshooting purposes and will likely be removed later.
     *
     * @return the symmetry strings of placements tried
     */
    public MyHashSet<String> getTried() {
        return tried;
    }
    
    public IntegerProperty foundProperty() {
        return found;
    }

}
