package fi.tira.pentominotiler.gui;

import fi.tira.pentominotiler.logic.Board;
import fi.tira.pentominotiler.logic.Search;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;

public class FXMLController implements Initializable {

    private Search search;
    private Group blockGroup = new Group();
    private IntegerProperty solutionIndex = new SimpleIntegerProperty(0);

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb4;

    @FXML
    private ToggleGroup boardDims;

    @FXML
    private VBox solutionsBox;

    @FXML
    private Label searchLabel;

    @FXML
    private Label boardLabel;

    @FXML
    private Pane boardPane;

    @FXML
    private void handleRunButton(ActionEvent event) {
        int rows = (Integer) boardDims.getSelectedToggle().getUserData();
        int cols = 60 / rows;
        Board board = new Board(rows, cols, Board.LETTER_SYMBOLS);
        search = new Search(board);

        Task<Integer> searchTask = new Task<Integer>() {
            // The return value of the call method is currently not used for anything.
            // This may change.
            @Override
            protected Integer call() throws Exception {
                search.foundProperty().addListener(
                        (arg, oldVal, newVal)
                        -> updateMessage("Searching. Found " + newVal.toString() + " solutions."));
                search.runSearch();
                int solutions = search.getSolutions().size();
                updateMessage("Done. Found " + solutions + " solutions.");
                return solutions;
            }
        };

        searchTask.setOnSucceeded(e -> {
            drawBoard(search.getSolutions().get(0));
            solutionIndex.set(0);
            updateBoardText();
            solutionsBox.setVisible(true);
        });

        searchLabel.textProperty().bind(searchTask.messageProperty());

        Thread th = new Thread(searchTask);
        th.setDaemon(true);
        th.start();
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
        if (solutionIndex.get() == search.getSolutions().size() - 1) {
            solutionIndex.set(0);
        } else {
            solutionIndex.set(solutionIndex.get() + 1);
        }
        updateBoardText();
        drawBoard(search.getSolutions().get(solutionIndex.get()));
    }
    
    @FXML
    private void handlePreviousButton(ActionEvent event) {
        if (solutionIndex.get() == 0) {
            solutionIndex.set(search.getSolutions().size() - 1);
        } else {
            solutionIndex.set(solutionIndex.get() - 1);
        }
        updateBoardText();
        drawBoard(search.getSolutions().get(solutionIndex.get()));
    }
    
    @FXML
    private void handleRandomButton(ActionEvent event) {
        int index = new Random().nextInt(search.getSolutions().size());
        solutionIndex.set(index);
        updateBoardText();
        drawBoard(search.getSolutions().get(solutionIndex.get()));
    }

    private void updateBoardText() {
        boardLabel.setText("Showing solution "
                + (solutionIndex.get() + 1)
                + " of "
                + search.getSolutions().size()
                + ".");
    }

    private void drawBoard(Board board) {
        int rows = board.getRows();
        int cols = board.getCols();
        blockGroup.getChildren().clear();
        boardPane.getChildren().clear();
        boardPane.getChildren().add(blockGroup);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                GraphicBlock gb = new GraphicBlock();
                gb.setTranslateY(i * GraphicBlock.BLOCK_SIZE);
                gb.setTranslateX(j * GraphicBlock.BLOCK_SIZE);
                gb.setColourBySymbol(board.charAtLinearIndex(i * cols + j));
                blockGroup.getChildren().add(gb);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // The radio buttons contain as their data
        // the number of rows on the board.
        rb1.setUserData(3);
        rb2.setUserData(4);
        rb3.setUserData(5);
        rb4.setUserData(6);
    }

}
