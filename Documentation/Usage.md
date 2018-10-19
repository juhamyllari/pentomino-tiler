# Using Pentomino Tiler

Pentomino Tiler is a Java application that finds all solutions to the pentomino board tiling problem and allows the user to browse the solutions. The supported board shapes are 3×20, 4×15, 5×12 and 6×10.

## Starting the Application

Pentomino Tiler version 1.0 has been released on GitHub as a jar file. After downloading the file issue the command
```
java -jar PentominoTiler-1.0.jar
```
to run it.

Pentomino Tiler requires about 1.5 GB of memory. If your Java memory settings are very conservative, the application may stall when tiling the most demanding (6×10) board. In this case consider adjusting the heap size manually:
```
java -jar -Xmx2g PentominoTiler-1.0.jar
```

Alternatively you may compile and run the project on the command line. In the directory `<project directory>/PentominoTiler` (containing pom.xml) run the following:
```
mvn compile
java -jar target/PentominoTiler-1.0-SNAPSHOT.jar
```

Yet another way to run Pentomino Tiler is to import it into NetBeans as a Maven project and run (F6) the project.

## Using the Application

Click on the radio button corresponding to the board dimensions of your choice. Then click "Run search". The search may take some time; the number of solutions found so far is displayed and continuously updated. Once the search terminates, the first result is displayed. Click on "Next" to see the next solution, "Previous" to see the previous solution or "Random" to see a random solution.

A solution actually corresponds to four tilings which are rotated and/or mirrored versions of each other. For each solution, Pentomino Tiler displays the tiling in which the "X" piece is closest to the upper left corner.

To run a new search, simply select the board dimensions using the radio buttons and click on "Run search" again.

## Exiting the Application

To exit Pentomino Tiler, close the window.
