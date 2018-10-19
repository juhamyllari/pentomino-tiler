package fi.tira.pentominotiler.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLANCHEDALMOND;
import static javafx.scene.paint.Color.BURLYWOOD;
import static javafx.scene.paint.Color.CHARTREUSE;
import static javafx.scene.paint.Color.CORAL;
import static javafx.scene.paint.Color.CRIMSON;
import static javafx.scene.paint.Color.DARKGOLDENROD;
import static javafx.scene.paint.Color.FORESTGREEN;
import static javafx.scene.paint.Color.FUCHSIA;
import static javafx.scene.paint.Color.GAINSBORO;
import static javafx.scene.paint.Color.INDIANRED;
import static javafx.scene.paint.Color.MEDIUMBLUE;
import static javafx.scene.paint.Color.MEDIUMSEAGREEN;
import javafx.scene.shape.Rectangle;

/**
 * A GraphicBlock is the graphical representation of one Block.
 * A Piece is therefore represented as five contiguous GraphicBlocks.
 * 
 * @author juha
 */
public class GraphicBlock extends StackPane {
    
    public static final double BLOCK_SIZE = 30.0;
    
    private Rectangle border;

    public GraphicBlock() {
        border = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        this.getChildren().add(border);
    }
    
    /**
     * Set the colour of the block according to the piece to which it belongs.
     * 
     * @param symbol the letter symbol of the piece
     */
    public void setColourBySymbol(char symbol) {
        border.setFill(getColour(symbol));
    }
    
    private Color getColour(char symbol) {
        // The pieces are 'X', 'I', 'T', 'V', 'W', 'Z', 'U', 'P', 'N', 'L', 'F', and 'Y'.
        Color colour;
        switch (symbol) {
            case 'X': colour = MEDIUMBLUE;
            break;
            
            case 'I': colour = MEDIUMSEAGREEN;
            break;
            
            case 'T': colour = BLANCHEDALMOND;
            break;
            
            case 'V': colour = BURLYWOOD;
            break;
            
            case 'W': colour = CHARTREUSE;
            break;
            
            case 'Z': colour = CORAL;
            break;
            
            case 'U': colour = CRIMSON;
            break;
            
            case 'P': colour = DARKGOLDENROD;
            break;
            
            case 'N': colour = FORESTGREEN;
            break;
            
            case 'L': colour = FUCHSIA;
            break;
            
            case 'F': colour = GAINSBORO;
            break;
            
            case 'Y': colour = INDIANRED;
            break;
            
            default: colour = BLACK;
            break;
            
        }
        return colour;
               
    }
    
    
}
