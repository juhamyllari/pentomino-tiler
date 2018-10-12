# Testing Document

This document is under construction.

## Unit Testing

Each class has been unit tested using JUnit.

The insertion sort algorithm used in the method "orderIndices" in the Search class was manually tested for correctness for all board shapes in Debug mode in NetBeans. By visual inspection, the time complexity of the algorithm is obviously O(n^2). The sorting is conducted in-place, and the space complexity of the algorithm is O(1) (or O(n) if the original array is included).

## Performance Testing

Pentomino Tiler can solve 4 tiling problems, corresponding to board dimensions (3, 20), (4, 15), (5, 12) and (6, 10). A board whose shape is close to a square (like (6, 10)) is harder to tile than a more rectangular one (like (3, 20)).

Measured on September 28, the running times of each tiling problem were as follows:
* (3, 20) board: 0.042 s
* (4, 15) board: 1.265 s
* (5, 12) board: 4.551 s
* (6, 10) board: 26.186 s

The search algorithm uses a set (MyHashSet) to prune search branches where a mirrored and/or rotated version of the same partially filled board has already been discovered. In the more computationally demanding problems the set grows quite large (13'377'366 entries in the (6, 10) problem). The speed at which the set can be updated and accessed is critical for performance. Using a fill factor of 0.75 for the set causes very poor performance as the internal array grows too large. Currently a fill factor of 3.0 is used for the (6, 10) problem, leading to greatly enhanced performance. In the (6, 10) problem, rehashing the set requires a tremendous amount of memory; it is therefore desirable to set the initial size large enough that rehashing is not required. On the more rectangular boards this is not essential.

### Comparing Heuristics

By default, Pentomino Tiler tiles the board starting from the origin and proceeding in order of increasing Euclidian distance (sqrt(row^2 + column^2)). In addition to this Euclidian heuristic, Pentomino Tiler supports running performance tests using a Manhattan heuristic (i.e. the board is filled in order of increasing Manhattan distance from the origin (row + column)).

The Euclidian heuristic performs better on all board shapes. On 12.10.2018 the following results were obtained:
* (3, 20) board: 20.01 ms (Euclidian) vs 33.48 ms (Manhattan) – average of 100 tests
* (4, 15) board: 534.25 ms (Euclidian) vs 1106.0 ms (Manhattan) – average of 20 tests
* (5, 12) board: 3067.8 ms (Euclidian) vs 9620.65 ms (Manhattan) – average of 20 tests
* (6, 10) board: 19303.0 ms (Euclidian) vs 28597.0 ms (Manhattan) – average of 5 tests

The testing method ignores the first results on each testing round as they tend to be significantly larger (i.e. the search tends to be much slower) than in later iterations. This difference is likely a result of JIT compilation.
