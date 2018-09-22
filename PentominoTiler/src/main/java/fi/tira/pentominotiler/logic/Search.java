package fi.tira.pentominotiler.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A Search object represents a single tiling problem.
 *
 * @author juha
 */
public class Search {

    private Board initialBoard;
    private List<Board> solutions;
    private Set<String> tried;
    private final List<List<ArrayPiece>> pieces;
    private final int[] indexOrder;

    /**
     * Constructs a Search object. The shape of the Board object defines the
     * search problem. The board must be empty (i.e. no pieces may be placed on
     * it).
     *
     * @param initialBoard an empty Board
     */
    public Search(Board initialBoard) {
        this.initialBoard = initialBoard;
        this.solutions = new ArrayList<>();
        this.tried = new HashSet<>(30000000);
        this.pieces = PieceUtils
                .allPieces()
                .stream()
                .map(p -> {
                    List<ArrayPiece> orientations = PieceUtils.nonRedundant(p);
                    List<ArrayPiece> centered = new ArrayList<>();
                    orientations.forEach(pc -> centered.addAll(PieceUtils.centered(pc)));
                    return centered;
                })
                .collect(Collectors.toList());
        this.indexOrder = createOrderIndex(initialBoard);
    }

    /**
     * Finds all solutions to the tiling problem. Preplaces the "x" pentomino in
     * each of its legal positions in the first quadrant and calls search
     * separately on each placement. In the search proper, pieces are placed on
     * squares of increasing Euclidian distance from the origin.
     */
    public void runSearch() {
        // Hard coded for 6x10 boards! Refactor later.
        ArrayPiece centeredX = pieces.get(0).get(2);
        search(initialBoard.placePiece(centeredX, 2, 4, 0));
        search(initialBoard.placePiece(centeredX, 2, 3, 0));
        search(initialBoard.placePiece(centeredX, 1, 4, 0));
        search(initialBoard.placePiece(centeredX, 1, 3, 0));
        search(initialBoard.placePiece(centeredX, 2, 2, 0));
        search(initialBoard.placePiece(centeredX, 1, 2, 0));
        search(initialBoard.placePiece(centeredX, 2, 1, 0));
        System.out.println("Size of 'tried' set: " + tried.size());
        System.out.println("Search finished. Found " + solutions.size() + " solutions.");
    }

    private void search(Board board) {
        if (board.getUnused() == 0) {
            System.out.println("Found solution number " + solutions.size());
//            board.printBoard();
            solutions.add(board);
            return;
        }
        int index = getNextIndex(board);
        int row = index / board.getCols();
        int col = index % board.getCols();
        for (int i = 0; i < 12; i++) {
            if (!board.isUsed(i)) {
                for (ArrayPiece candidate : pieces.get(i)) {
                    if (board.canPlace(candidate, row, col)) {
                        Board newBoard = board.placePiece(candidate, row, col, i);
                        List<String> symmetries = newBoard.symmetryStrings();
                        if (symmetries.stream().noneMatch(s -> tried.contains(s))) {
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
     * Returns the tried set.
     * This method exists for troubleshooting purposes and will likely be
     * removed later.
     * @return the symmetry strings of placements tried
     */
    public Set<String> getTried() {
        return tried;
    }

}
