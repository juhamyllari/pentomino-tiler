# Testing Document

## Running the Tests

To run all tests, issue the command
```
mvn test
```
in `<project directory>/PentominoTiler`. The perfomance tests may take several minutes.

## Unit Testing

The program logic and the self-implemented data structures have been unit tested using JUnit.

The insertion sort algorithm used in the method "orderIndices" in the Search class was manually tested for correctness for all board shapes in Debug mode in NetBeans. By visual inspection, the time complexity of the algorithm is obviously O(n^2). The sorting is conducted in-place, and the space complexity of the algorithm is O(1) (or O(n) if the original array is included).

## Performance Testing

Pentomino Tiler can solve 4 tiling problems, corresponding to board dimensions 3×20, 4×15, 5×12 and 6×10. A board whose shape is close to a square (like 6×10) is harder to tile than a more rectangular one (like 3×20).

The search algorithm uses a set (MyHashSet) to prune search branches where a mirrored and/or rotated version of the same partially filled board has already been discovered. In the more computationally demanding problems the set grows quite large (several million entries in the 6×10 problem). The speed at which the set can be updated and accessed is critical for performance.

Using a fill factor of 0.75 for the set causes very poor performance as the internal array grows too large. Currently a fill factor of 3.0 is used for the 6×10 problem, leading to greatly enhanced performance. In the 6×10 problem, rehashing the set requires a tremendous amount of memory; it is therefore desirable to set the initial size large enough that rehashing is not required. (In the GUI this is done automatically.)

### Tradeoff: More Set Lookups vs. More Stored Items

In an early implementation of the search algorithm, search nodes leading to duplicate solutions (see [Implementation](Implementation_Document.md) for details) were pruned by looking up each candidate board in the "tried" set; if the board was found in the set, the search call was omitted. If the node was not found, all three alternative (i.e. rotated and/or mirrored) versions of the board were added to the set.

In order to reduce memory usage, an alternative pruning method was devised. Instead of adding the three alternative versions of a board to the "tried" set, only the board itself was added. This, of course, necessitated three set lookups (one for each alternative version of the board) instead of one. Reducing stored items at the cost of additional lookups turned out to be an improvement overall. In addition to saving memory, the new pruning method lead to significantly enhanced performance on the 6×10 problem, as seen in the graph below.

![Plot of search times by pruning method](https://github.com/juhamyllari/pentomino-tiler/blob/master/Documentation/pruning.png)

### Comparing Heuristics

By default, Pentomino Tiler tiles the board starting from the origin and proceeding in order of increasing Euclidean distance (sqrt(row^2 + column^2)). In addition to this Euclidean heuristic, Pentomino Tiler supports running performance tests using a Manhattan heuristic (i.e. the board is filled in order of increasing Manhattan distance from the origin (row + column)).

The Euclidean heuristic performs better on all board shapes. In Pentomino Tiler version 1.0, the following running times were obtained:
* 3×20 board: 20.36 ms (Euclidean) vs 32.72 ms (Manhattan) – average of 100 tests
* 4×15 board: 469.7 ms (Euclidean) vs 968.4 ms (Manhattan) – average of 20 tests
* 5×12 board: 2336.4 ms (Euclidean) vs 7477.9 ms (Manhattan) – average of 10 tests
* 6×10 board: 14537.6 ms (Euclidean) vs 15967.4 ms (Manhattan) – average of 5 tests

![Plot of search times by heuristic ](https://github.com/juhamyllari/pentomino-tiler/blob/master/Documentation/durations.png)
![Log plot of search times by heuristic](https://github.com/juhamyllari/pentomino-tiler/blob/master/Documentation/logdurations.png)

The testing method ignores the first results on each testing round as they tend to be significantly larger (i.e. the search tends to be much slower) than in later iterations. This difference is likely a result of JIT compilation.
