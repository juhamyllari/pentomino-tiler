package fi.tira.pentominotiler;

import fi.tira.pentominotiler.logic.ArrayPiece;
import fi.tira.pentominotiler.logic.PieceUtils;
import java.util.List;
import java.util.stream.Collectors;
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
        List<ArrayPiece> pieces = PieceUtils.allPieces();
        
        List<List<ArrayPiece>> orientations = pieces
                .stream()
                .map(p -> PieceUtils.nonRedundant(p))
                .collect(Collectors.toList());
        
        orientations.forEach(l -> l.forEach(p -> p.printPiece()));
        
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
