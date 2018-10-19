# Using Pentomino Tiler

## Starting the Application

Pentomino Tiler will be released as a .jar file later. At the moment the recommended way to run the application is to click "Run Project" in NetBeans (or press F6). Alternatively one may complile and run the project on the command line. In the directory `<project directory>/PentominoTiler` (containing pom.xml) run the following:
```
mvn compile
java -jar target/PentominoTiler-1.0-SNAPSHOT.jar
```
Pentomino Tiler requires about 1.5 GB of memory. If your Java memory settings are very conservative, the application may stall when tiling the most demanding (6×10) board. In this case consider adjusting the heap size manually:
```
java -jar -Xmx2g target/PentominoTiler-1.0-SNAPSHOT.jar
```

## Using the Application

Click on the radio button corresponding to the board dimensions of your choice. Then click "Run search". The search may take some time; the number of solutions found so far is displayed and continuously updated. Once the search terminates, the first result is displayed. Click on "Next" to see the next solution, "Previous" to see the previous solution or "Random" to see a random solution.

To run a new search, simply select the board dimensions using the radio buttons and click on "Run search" again.

## Exiting the Application

To exit Pentomino Tiler, close the window.
