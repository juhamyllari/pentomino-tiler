package fi.tira.pentominotiler.gui;

import fi.tira.pentominotiler.logic.Board;
import fi.tira.pentominotiler.logic.Search;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.shape.*;

public class FXMLController implements Initializable {
    
    private Board board;
    private Search search;
    private Group blockGroup = new Group();
    
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
    private Label label;
    
    @FXML
    private Pane boardPane;
    
    @FXML
    private void handleRunButton(ActionEvent event) {
        int rows = (Integer) boardDims.getSelectedToggle().getUserData();
        int cols = 60 / rows;
        board = new Board(rows, cols, Board.LETTER_SYMBOLS);
        search = new Search(board);
        
        Task<Integer> searchTask = new Task<Integer>() {
            // The return value of the call method is currently not used for anything.
            // This may change.
            @Override
            protected Integer call() throws Exception {
                search.foundProperty().addListener(
                        (arg, oldVal, newVal) ->
                        updateMessage("Searching. Found " + newVal.toString() + " solutions."));
                search.runSearch();
                int solutions = search.getSolutions().size();
                updateMessage("Done. Found " + solutions + " solutions.");
                return solutions;
            }
        };
        
        searchTask.setOnSucceeded(e -> drawBoard(search.getSolutions().get(0)));
        
        label.textProperty().bind(searchTask.messageProperty());
        
        Thread th = new Thread(searchTask);
        th.setDaemon(true);
        th.start();
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
        boardPane.setPrefSize(200,200);
    }    
  
}
