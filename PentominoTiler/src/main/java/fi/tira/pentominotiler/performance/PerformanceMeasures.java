package fi.tira.pentominotiler.performance;

import fi.tira.pentominotiler.datastructures.MyArrayList;
import fi.tira.pentominotiler.logic.Board;
import fi.tira.pentominotiler.logic.Search;

/**
 *
 * @author juha
 */
public class PerformanceMeasures {

    public static MyArrayList<Long> testPerformance(Board board, boolean Manhattan, int times) {
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
                System.out.println("Ignoring zeroth iteration.");
            }
        }
        return list;
    }

    public static void comparePerformance(int rows, int cols, int times) {
        Board board = new Board(rows, cols, Board.LETTER_SYMBOLS);

        System.out.println("Search durations for the Euclidian heuristic:");
        MyArrayList<Long> durationsEuclidian = PerformanceMeasures.testPerformance(board, false, times);
        System.out.println("Average: " + PerformanceMeasures.average(durationsEuclidian) + " ms\n");

        System.out.println("Search durations for the Manhattan heuristic:");
        MyArrayList<Long> durationsManhattan = PerformanceMeasures.testPerformance(board, true, times);
        System.out.println("Average: " + PerformanceMeasures.average(durationsManhattan) + " ms\n");
    }

    public static Double average(MyArrayList<Long> results) {
        double sum = 0.0;
        for (long result : results) {
            sum += result;
        }
        return sum / results.size();
    }

}
