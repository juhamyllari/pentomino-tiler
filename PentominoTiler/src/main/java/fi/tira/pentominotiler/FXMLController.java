package fi.tira.pentominotiler;

import fi.tira.pentominotiler.logic.Board;
import fi.tira.pentominotiler.logic.Search;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class FXMLController implements Initializable {
    
    private Board board;
    private Search search;
    
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
        
        label.textProperty().bind(searchTask.messageProperty());
        
        Thread th = new Thread(searchTask);
        th.setDaemon(true);
        th.start();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb1.setUserData(3);
        rb2.setUserData(4);
        rb3.setUserData(5);
        rb4.setUserData(6);
    }    
  
}
