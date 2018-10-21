package fi.tira.pentominotiler.gui;

import fi.tira.pentominotiler.logic.Board;
import fi.tira.pentominotiler.logic.Search;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * The controller class for the GUI's sole scene.
 * Most of the action happens in the method "handleRunButton", which is called
 * when the "Run search" button is pressed.
 * 
 * @author juha
 */
public class FXMLController implements Initializable {

    private Search search;
    private final Group blockGroup = new Group();
    private final IntegerProperty solutionIndex = new SimpleIntegerProperty(0);

    // See the initialize method for a comment on the RadioButtons.
    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb4;
    
    @FXML
    private Button runButton;
    
    @FXML
    private Button nextSolutionButton;
    
    @FXML
    private Button previousSolutionButton;
    
    @FXML
    private Button randomSolutionButton;

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
        enableButtons(false);
        int rows = (Integer) boardDims.getSelectedToggle().getUserData();
        int cols = 60 / rows;
        Board board = new Board(rows, cols, Board.LETTER_SYMBOLS);
        search = new Search(board);

        // Run the search as a separate Task.
        Task<Void> searchTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                search.foundProperty().addListener(
                        (arg, oldVal, newVal)
                        -> updateMessage("Searching.\nFound " + newVal.toString() + " solutions."));
                search.runSearch();
                int solutions = search.getSolutions().size();
                updateMessage("Done.\nFound " + solutions + " solutions in " + search.getDuration() / 1000.0 + " seconds.");
                return null;
            }
        };

        // Show solutions once search terminates.
        searchTask.setOnSucceeded(e -> {
            drawBoard(search.getSolutions().get(0));
            solutionIndex.set(0);
            updateBoardText();
            solutionsBox.setVisible(true);
            enableButtons(true);
        });

        // Display the number of solutions found so far.
        searchLabel.textProperty().bind(searchTask.messageProperty());

        Thread th = new Thread(searchTask);
        th.setDaemon(true);
        th.start();
    }
    
    private void enableButtons(boolean enable) {
        boolean disable = !enable;
        runButton.setDisable(disable);
        nextSolutionButton.setDisable(disable);
        previousSolutionButton.setDisable(disable);
        randomSolutionButton.setDisable(disable);
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
        // To ensure compliance with course requirements, Math.random() is
        // not used. System.nanoTime() should be sufficiently random for the
        // current purpose as security is not an issue with this method.
        long time = System.nanoTime();
        int preIndex = (int) (time % search.getSolutions().size());
        int index = preIndex >= 0 ? preIndex : -preIndex;
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
