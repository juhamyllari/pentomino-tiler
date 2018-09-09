# Project Requirements

Pentomino Tiler will be a Java application that finds all solutions to the problem of tiling a rectangular grid with pentominoes. For a description of pentominoes see [Wikipedia](https://en.wikipedia.org/wiki/Pentomino).

## User Interaction, Inputs and Outputs

The user will be asked to select the shape of the grid to be tiled (6x10 by default). As there are 12 pentominoes of size 5 each, the size of the grid must be 60. Grids larger than 60 are disallowed for two reasons:
* The algorithm would likely not terminate on a human-relevant time scale.
* The algorithm may contain optimizations that would not work on a larger grid.

The user will be able to select among 2–3 predefined search heuristics.

The application will output the number of solutions to the problem. The user will also have the option of viewing example solutions.

## Algorithms

All heavy lifting will be performed by an exhaustive search algorithm. The time (and space) complexity of the algorithm will likely be O(2^n) (n being the number of pentominoes used), as it recurses O(n) times with a branching factor bounded by a constant.

Heuristics used by the search algorithm may require sorting small arrays of integers. For this purpose a trivial sorting algorithm (possibly insertion sort) of time complexity O(n^2) and space complexity O(n) will be implemented.

## Data Structures

In addition to arrays, the application will require implementing a hash table which will likely do double duty as a hash map and as a hash set. The hash set will store intermediate solutions for the purpose of pruning search tree branches leading to duplicate solutions. As each pentomino piece (with one exception) can be placed on the board in a number of orientations, a hash map may be used to associate each oriented piece to its abstract identity.
