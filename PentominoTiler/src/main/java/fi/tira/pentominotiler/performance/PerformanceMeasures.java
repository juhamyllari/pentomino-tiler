package fi.tira.pentominotiler.performance;

import fi.tira.pentominotiler.datastructures.MyArrayList;
import fi.tira.pentominotiler.logic.Board;
import fi.tira.pentominotiler.logic.Search;

/**
 * This class contains methods for performance testing. The tests are run in the
 * test file for this class.
 *
 * @author juha
 */
public class PerformanceMeasures {

    /**
     * Compare search performance on the two heuristics.
     * 
     * @param rows number of rows
     * @param cols number of columns
     * @param times number of test iterations
     */
    public static void comparePerformance(int rows, int cols, int times) {
        Board board = new Board(rows, cols, Board.LETTER_SYMBOLS);

        System.out.println("***************************************");
        System.out.println("Testing performance on a (" + rows + ", " +  cols + ") board.");
        System.out.println("***************************************");
        
        System.out.println("Search durations for the Euclidean heuristic:");
        MyArrayList<Long> durationsEuclidean = PerformanceMeasures.testPerformance(board, false, times);
        System.out.println("Average for the Euclidean heuristic: " + PerformanceMeasures.average(durationsEuclidean) + " ms\n");

        System.out.println("Search durations for the Manhattan heuristic:");
        MyArrayList<Long> durationsManhattan = PerformanceMeasures.testPerformance(board, true, times);
        System.out.println("Average for the Manhattan heuristic: " + PerformanceMeasures.average(durationsManhattan) + " ms\n");
    }

    /**
     * Produce a list of test results.
     * 
     * @param board the board
     * @param Manhattan true if Manhattan heuristic desired
     * @param times number of test iterations
     * @return search durations in ms
     */
    private static MyArrayList<Long> testPerformance(Board board, boolean Manhattan, int times) {
        MyArrayList<Long> list = new MyArrayList<>();
        for (int i = 0; i <= times; i++) {
            Search search = new Search(board);
            if (Manhattan) {
                search.setHeuristicToManhattan();
            }
            search.runSearch();
            if (i > 0) {
                System.out.println("Iteration " + i + ": " + search.getDuration() + " ms");
                list.add(search.getDuration());
            } else {
                System.out.println("Ignoring iteration 0.");
            }
        }
        return list;
    }

    /**
     * Get the average of a list of results (search durations).
     * 
     * @param results the durations
     * @return sum(results) / n(results)
     */
    private static Double average(MyArrayList<Long> results) {
        double sum = 0.0;
        for (long result : results) {
            sum += result;
        }
        return sum / results.size();
    }

}
