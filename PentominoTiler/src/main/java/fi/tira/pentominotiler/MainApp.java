package fi.tira.pentominotiler;

import fi.tira.pentominotiler.logic.Board;
import fi.tira.pentominotiler.logic.Search;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);

        /*
        The GUI does not do anything interesting yet. This is a testing ground for elements of the program logic.
         */
        Search mySearch = new Search(new Board(6, 10, Board.LETTER_SYMBOLS));
        mySearch.runSearch();
        
        //Uncomment the line below to show all solutions.
//        mySearch.getSolutions().forEach(b -> System.out.print(b.printableBoard()));
        
        /*
        The testing ground ends here.
        
         */
        // Uncomment the line below to activate the GUI
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
