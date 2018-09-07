# Project Requirements

Pentomino Tiler will be a Java application that finds all solutions to the problem of tiling a rectangular grid with pentominoes. For a description of pentominoes see [Wikipedia](https://en.wikipedia.org/wiki/Pentomino).

## User Interaction, Inputs and Outputs

The user will be asked to select the shape of the grid to be tiled (6x10 by default). As there are 12 pentominoes of size 5 each, the size of the grid must be a multiple of 60. However, the tiling algorith will likely not terminate on a human-relevant time scale for grids larger than 60.

The user will be able to select among 2–3 predefined search heuristics.

The application will output the number of solutions to the problem. The user will also have the option of viewing example solutions.

## Algorithms

All heavy lifting will be performed by an exhaustive search algorithm. The time (and space) complexity of the algorithm will likely be $O(2^n)$ ($n$ being the number of pentominoes used), as it recurses $O(n)$ times with a branching factor bounded by a constant.

Heuristics used by the search algorithm may require sorting small arrays of integers. For this purpose a trivial sorting algorithm (possibly insertion sort) of time complexity $O(n^2)$ will be implemented.

## Data Structures

In addition to arrays, the application will require associative arrays (implemented as a hash table) and sets (also implemented by hashing).
