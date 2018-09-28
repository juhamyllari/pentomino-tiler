# Testing Document

This document is under construction.

## Unit Testing

Each class has been unit tested using JUnit.

## Performance Testing

Pentomino Tiler can solve 4 tiling problems, corresponding to board dimensions (3, 20), (4, 15), (5, 12) and (6, 10). A board whose shape is close to a square (like (6, 10)) is harder to tile than a more rectangular one (like (3, 20)).

Measured on September 28, the running times of each tiling problem were as follows:
* (3, 20) board: 0.042 s
* (4, 15) board: 1.265 s
* (5, 12) board: 4.551 s
* (6, 10) board: 26.186 s

The search algorithm uses a set (MyHashSet) to prune search branches where a mirrored and/or rotated version of the same partially filled board has already been discovered. In the more computationally demanding problems the set grows quite large (13'377'366 entries in the (6, 10) problem). The speed at which the set can be updated and accessed is critical for performance. Using a fill factor of 0.75 for the set causes very poor performance as the internal array grows too large. Currently a fill factor of 3.0 is used for the (6, 10) problem, leading to greatly enhanced performance. In the (6, 10) problem, rehashing the set requires a tremendous amount of memory; it is therefore desirable to set the initial size large enough that rehashing is not required. On the more rectangular boards this is not essential.
