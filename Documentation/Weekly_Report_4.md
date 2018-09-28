# Week 4

## Areas of Focus

This week I focused on the following:
* Search performance
* Implementing my own data structures.

### Performance

Last weed I was unable to make the most demanding search problem (the (6, 10) board) run to completion. The problem was that some unnecessary lookups were performed on the pruning HashSet (now MyHashSet). Removing these improved performance drastically and made it possible for the search to terminate correctly.

The parameters of the set object have a significant impact on search performance. (More on this in the Testing Document.) Discovering this was quite informative as I had previously not given the issue much thought.

### Implementing Two Data Structures

I implemented, documented and unit tested an array-based list (MyArrayList) and a hash table based set (MyHashSet).

## Questions

There is no explicit parallelization in the application code, but monitoring the Java process one often sees CPU usage of several hundred (200–500) percent. I found this somewhat surprising. Are the other threads performing garbage collection or something else? Clearly I have much to explore in this space.

## Next Steps

Pentomino Tiler does not yet have a GUI, so I will start working on one next week. I may also carry out some more performance tests.

## Time Spent on the Project This Week

Approximately 20 hours.
