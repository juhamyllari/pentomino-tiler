# Implementation Document

This document is under construction.

## General Application Structure

### Setting Up the Search

The parameters of a search problem are contained in a Search object. The search object has methods for running the search and retrieving the results. The Search constructor takes as its sole parameter a Board object which represents the pentomino board to be tiled.

Pentomino pieces are represented by Piece objects, which in turn contain 5 Block objects, each representing one square of the piece. The Board class has a method for placing a Piece into a position (row, column) on the board.

### Running the Search

Pentomino Tiler finds all unique solutions to the tiling problem through an exhaustive recursive search. Each iteration takes as its argument a board and finds the first empty square of that board. It then fits each unused piece in all of its orientations into that square. If the placement is legal, the search method calls itself on the resulting board. The recursion bottoms out in the following cases:
* all pieces are used up, meaning that a solution has been found
* none of the remaining pieces can be placed into the first empty square.

#### Pruning Searches Leading to Duplicate Solutions

If tiling B can be derived from tiling A by rotation or mirroring, A and B are not considered unique solutions to the tiling problem. Pentomino Tiler therefore prunes all search branches where the (partially filled) board is a rotated and/or mirrored version of a board that has already been found by the search.

#### Preplacement of the X

Of the 12 pentomino pieces, the "X" piece (shaped like '+') has only one orientation; it is symmetrical with respect to rotation and mirroring. The tiling problem can therefore be significantly constrained by placing the X in each of its legal positions and running the search separately on each placement. Pentomino Tiler automatically does this, placing the X into each legal position in the quadrant closest to the origin of the board (or next to said quadrant, if the number of rows or columns is odd).

#### Euclidian Heuristic

It was stated above that each step of the recursive search attempts to fill the first empty square of the (possibly partially filled) board. However, we did not specify what "first" means in this context. Pentomino Tiler numbers the squares of the board according to their Euclidian distance from the origin. The board is therefore filled starting from the origin and proceeding away from it.

## Time and Space Complexity

The search is exponential (i.e. O(2^n)) in both time and space complexity.

## Limitations

## Sources
