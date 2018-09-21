# Week 3
## Areas of Focus

This week I focused on the following:
* Creating the 12 pentomino pieces.
* Implementing an early version of the search. (Not fully functional yet.)

### Creating the Pentominoes

I created the pentominoes as ArrayPiece objects from String representations corresponding to each piece. I also created functionality for generating all the symmetry variants of the pieces.

### Implementing the Search

I implemented an early version of the search. The algorithm preplaces the "X" pentomino (which can only be oriented one way due to symmetry) before starting the search. During the search, placements leading to mirrored (i.e. not truly unique) solutions are pruned. The board is filled in order of increasing Euclidian distance from the origin (row 0, column 0).

At this stage the search produces correct solutions but stalls or runs out of memory before the last 200 or so solutions to the 6x10 problem are discovered. The problem has 2339 solutions; the algorithm stalls after finding 2150 or so.

## Things I Learned This Week

The "X" pentomino can be preplaced into seven different locations in the first quadrant, leading to seven subsearches. The performance of the algorithm is somewhat affected by the order of the subsearches. As described above, a HashSet is maintained for pruning placements that would lead to mirrored solutions. The initial size of the HashSet appears to affect performance.

## Questions, Difficulties and Next Steps

I do not yet know why the search stalls a little short of the finish line. I intend to diagnose the problem and/or find ways around it. This will probably be the focus of next week's work.

## Time Spent on the Project This Week

Approximately 14 hours.
