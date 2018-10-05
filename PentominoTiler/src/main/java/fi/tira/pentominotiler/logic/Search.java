package fi.tira.pentominotiler.logic;

import fi.tira.pentominotiler.datastructures.MyArrayList;
import fi.tira.pentominotiler.datastructures.MyHashSet;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A Search object represents a single tiling problem. To run the search, call
 * the method runSearch(). Getters are provided for retrieving the solutions and
 * the duration of the search in milliseconds. The Integer Property "found"
 * (retrievable by foundProperty()) keeps a running count of the number of
 * solutions found – this is convenient for displaying the progress of the
 * search in a GUI.
 *
 * @author juha
 */
public class Search {

    private Board initialBoard;
    private MyArrayList<Board> solutions;
    private MyHashSet<String> tried;
    private final MyArrayList<MyArrayList<Piece>> pieces;
    private final int[] indexOrder;
    private final IntegerProperty found = new SimpleIntegerProperty(0);
    private long duration;

    /**
     * Construct a Search object. The shape of the Board object defines the
     * search problem. The board must be empty (i.e. no pieces may be placed on
     * it).
     *
     * @param initialBoard an empty Board
     */
    public Search(Board initialBoard) {
        this.initialBoard = initialBoard;
        this.solutions = new MyArrayList<>();
        this.tried = initialBoard.getRows() == 6
                ? new MyHashSet<>(4500000, 3.0) // The 6x10 board is resource intensive.
                : new MyHashSet<>(1000000, 2.0);
        this.pieces = PieceUtils
                .allPieces()
                .stream()
                .map(p -> {
                    MyArrayList<Piece> orientations = PieceUtils.nonRedundant(p);
                    MyArrayList<Piece> centered = new MyArrayList<>();
                    orientations.forEach(pc -> centered.addAll(PieceUtils.centered(pc)));
                    return centered;
                })
                .collect(Collectors.toCollection(MyArrayList::new));
        this.indexOrder = createOrderIndex(initialBoard);
    }

    /**
     * Find all solutions to the tiling problem. Preplaces the "X" pentomino in
     * each of its legal positions in or near the quadrant nearest the origin
     * (some positions may be outside the quadrant if the number of rows or
     * columns is odd) and calls search separately on each placement. In the
     * search proper, pieces are placed on squares of increasing Euclidian
     * distance from the origin.
     */
    public void runSearch() {

        duration = 0L;

        int rows = initialBoard.getRows();
        int cols = initialBoard.getCols();

        int rowsToCover = rows % 2 == 0 ? rows / 2 : rows / 2 + 1;
        int colsToCover = cols % 2 == 0 ? cols / 2 : cols / 2 + 1;

        Piece centeredX = pieces.get(0).get(2);

        Instant startTime = Instant.now();

        for (int row = 1; row < rowsToCover; row++) {
            for (int col = 1; col < colsToCover; col++) {
                if (!(row == 1 && col == 1)) {
                    search(initialBoard.placePiece(centeredX, row, col, 0));
                }
            }
        }

        Instant stopTime = Instant.now();
        duration = Duration.between(startTime, stopTime).toMillis();
        System.out.println("The search took " + duration + " milliseconds.");
    }

    /**
     * The search method is the heart of the search algorithm. It is recursive
     * and takes as its sole argument a board which may be filled partially,
     * completely or not at all. If the board is full, the method adds it to the
     * list of solutions, increments the "found" counter and returns. If not, it
     * queries the board for the next empty square and tries to fill it, calling
     * itself for every legal placement of an unused piece into that square.
     *
     * @param board
     */
    private void search(Board board) {
        if (board.getUnused() == 0) {
            // Solution found.
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
                        MyArrayList<String> symmetries = newBoard.symmetryStrings();
                        if (!tried.contains(newBoard.toString())) {
                            tried.addAll(symmetries);
                            search(newBoard);
                        }
                    }
                }
            }
        }
    }

    /**
     * Query the board for the next empty square.
     * The order in which the search method tries to fill the squares of the
     * board is contained in the field indexOrder.
     *
     * @param bd the board
     * @return the index of the next empty square
     */
    private int getNextIndex(Board bd) {
        int boardSize = bd.getRows() * bd.getCols();
        for (int i = 0; i < boardSize; i++) {
            if (bd.charAtLinearIndex(indexOrder[i]) == 0) {
                return indexOrder[i];
            }
        }
        return -1;
    }

    /**
     * Sort square indices by increasing Euclidian distance from the origin.
     * 
     * @param bd the board
     * @return the ordered array
     */
    private int[] createOrderIndex(Board bd) {
        int boardSize = bd.getRows() * bd.getCols();
        int[] indexArray = new int[boardSize];
        for (int i = 0; i < boardSize; i++) {
            indexArray[i] = i;
        }

        // Sort indexArray with insertion sort (manually implemented).
        // Use the compareIndices method (Euclidian distance) as comparison.
        int i = 1;
        int j, k;
        while (i < boardSize) {
            j = i;
            while (j > 0 && compareIndices(indexArray[j - 1], indexArray[j], bd.getCols()) > 0) {
                k = indexArray[j - 1];
                indexArray[j - 1] = indexArray[j];
                indexArray[j] = k;
                j--;
            }
            i++;
        }
        return indexArray;
    }
    
    /**
     * Compare indices by Euclidian distance from the origin.
     * 
     * @param i1
     * @param i2
     * @param cols number of columns
     * @return -1 if i1 is closer, 1 if i2 is closer, 0 otherwise
     */
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
     * Return the solutions to the problem.
     * If the search has not yet been run, an empty list is returned.
     *
     * @return solutions
     */
    public MyArrayList<Board> getSolutions() {
        return solutions;
    }

    /**
     * Return the tried set. The set contains a String representation of each
     * partially filled board such that a mirrored and/or rotated version of the
     * board has been discovered by the search. This method exists for
     * troubleshooting purposes and will likely be removed later.
     *
     * @return the symmetry strings of placements tried
     */
    public MyHashSet<String> getTried() {
        return tried;
    }

    /**
     * Get the Property containing the number of solutions found so far.
     * 
     * @return 
     */
    public IntegerProperty foundProperty() {
        return found;
    }

    /**
     * Get the duration of the search.
     * The search must be run before using this method.
     * 
     * @return the search duration
     */
    public long getDuration() {
        return duration;
    }

}
