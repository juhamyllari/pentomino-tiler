# Week 5

## Areas of Focus

This week I focused on:
* Creating a GUI for Pentomino Tiler
* Making MyArrayList implement the Collection interface rather than extending the AbstractList class.

### Creating a JavaFX GUI

This was my second JavaFX GUI, the first being that of my JavaLabra course project [Arithmetic Visualizer](https://github.com/juhamyllari/arithmetic-visualizer). JavaFX is finally starting to make a little bit of sense, althought I definitely still consider myself a beginner. I learned how to create and run Tasks and how to use Properties to automatically update Label texts when variable values change.

While the GUI could be prettier, it basically does everything I intended it to do. The user can run the search on the board of their choice and  view as many or as few solutions they desire.

### Implementing Collection Instead of Extending the AbstractList Class

When implementing MyArrayList, I had originally extended the AbstractList Class. However, in order to make sure that the class conforms to the course requirements (and partially out of curiosity) I decided to implement the Collection interface instead. This turned out to be an interesting exercise and gave insight into how iteration is implemented in Java Collections.

## Next Steps

I initially intended for the user to have some choice over the heuristic used in the search. Currently the search proceeds from the origin in order of increasing Euclidian distance. An alternative heuristic might be Manhattan distance, but I am not yet sure if this is interesting enough to include in the GUI. In any case I will probably try out Manhattan distance too and comment on it in the testing documentation.

## Time Spent on the Project This Week

Approximately 20 hours.
