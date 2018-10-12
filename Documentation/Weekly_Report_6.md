# Week 6

## Areas of Focus

This week I focused on the following:
* Performance testing
* Small tweaks in the code and in the Javadoc in the interest of code legibility and maintainability.

### The Two Heuristics

I implemented a Manhattan heuristic for the search and created methods for testing and comparing the performance of each heuristic. I then simplified the sorting procedure for improved code legibility.

The Euclidean heuristic performs better across the board (pun intended), but the Manhattan heuristic is not untenable either. The Testing Document contains a summary of the results.

### The Performance Test

At the moment, the performance tests are implemented as follows. The methods used are in a utility class of their own; this class provides no functionality for the application proper. The test are in a Test Package and are therefore run together with JUnit tests. This is probably less than optimal, but I did not want to include the performance tests in the GUI.

## Next Steps

As far as algorithms and data structures are concerned, the project is nearly finished. I will probably tweak the GUI a little and then focus on the rest of the required documentation (Usage and Testing).

## Time Spent on the Project This Week

Approximately 14 hours.
