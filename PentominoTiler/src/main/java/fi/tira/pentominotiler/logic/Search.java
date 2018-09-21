package fi.tira.pentominotiler.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author juha
 */
public class Search {

    private Board initialBoard;
    private List<Board> solutions;
    private Set<String> tried;
    private final List<List<ArrayPiece>> pieces;

    public Search(Board initialBoard) {
        this.initialBoard = initialBoard;
        this.solutions = new ArrayList<>();
        this.tried = new HashSet<>();
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
    }

    public void runSearch() {
        // Hard coded for 6x10 boards! Refactor later.
        ArrayPiece centeredX = pieces.get(0).get(2);
        search(initialBoard.placePiece(centeredX, 1, 2, 0));
        search(initialBoard.placePiece(centeredX, 1, 3, 0));
        search(initialBoard.placePiece(centeredX, 1, 4, 0));
        search(initialBoard.placePiece(centeredX, 2, 1, 0));
        search(initialBoard.placePiece(centeredX, 2, 2, 0));
        search(initialBoard.placePiece(centeredX, 2, 3, 0));
        search(initialBoard.placePiece(centeredX, 2, 4, 0));
        System.out.println("Search finished. Found " + solutions.size() + " solutions.");
    }

    private void search(Board board) {
        if (board.getUnused() == 0) {
            System.out.println("Found solution number " + solutions.size());
            solutions.add(board);
            return;
        }
        int index = board.getNextIndex();
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

}
