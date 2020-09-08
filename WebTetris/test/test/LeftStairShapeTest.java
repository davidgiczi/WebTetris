
package test;

import com.david.giczi.tetris.model.GameLogic;
import com.david.giczi.tetris.model.ShapePosition;
import com.david.giczi.tetris.shape.AbstractShape;
import com.david.giczi.tetris.shape.LeftStairShape;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GicziD
 */
public class LeftStairShapeTest {
    
    @Test
    public void testCreateLeftStairShape() {
        
        GameLogic logic = new GameLogic();
        logic.initGame();
        
        for(int y = 0; y < ShapePosition.LENGTH_OF_BOARD - 1; y++){
           
            for(int x = 1; x < ShapePosition.WIDTH_OF_BOARD - 1; x++){
                
                AbstractShape leftStair = new LeftStairShape(new ShapePosition(x, y), "black");
                logic.addShapeToLogicBoard(leftStair);
                assertTrue(logic.getLogicBoard().get(y * ShapePosition.WIDTH_OF_BOARD + x - 1) &&
                           logic.getLogicBoard().get(y * ShapePosition.WIDTH_OF_BOARD + x) &&
                           logic.getLogicBoard().get((y + 1) * ShapePosition.WIDTH_OF_BOARD + x) &&
                           logic.getLogicBoard().get((y + 1) * ShapePosition.WIDTH_OF_BOARD + x + 1));
                logic.displayLogicBoard();
                logic.initGame();
            }
        }
        
    }
    
}
