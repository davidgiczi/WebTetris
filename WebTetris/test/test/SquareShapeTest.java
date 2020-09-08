
package test;

import com.david.giczi.tetris.model.GameLogic;
import com.david.giczi.tetris.model.ShapePosition;
import com.david.giczi.tetris.shape.AbstractShape;
import com.david.giczi.tetris.shape.SquareShape;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Judut
 */
public class SquareShapeTest {
    
   
    @Test
    public void testCreateSquareShape() {
             
        GameLogic logic = new GameLogic();
        logic.initGame();
        
         for(int y = 0; y < ShapePosition.LENGTH_OF_BOARD - 1; y++){
           
            for(int x = 0; x < ShapePosition.WIDTH_OF_BOARD - 1; x++){
                
            AbstractShape square = new SquareShape(new ShapePosition(x, y), "yellow");
            logic.addShapeToLogicBoard(square);
            assertTrue(logic.getLogicBoard().get(y * ShapePosition.WIDTH_OF_BOARD + x) &&
                        logic.getLogicBoard().get(y * ShapePosition.WIDTH_OF_BOARD + x + 1) &&
                        logic.getLogicBoard().get((y + 1) * ShapePosition.WIDTH_OF_BOARD + x) &&
                        logic.getLogicBoard().get((y + 1) * ShapePosition.WIDTH_OF_BOARD + x + 1));
            //logic.displayLogicBoard();
            logic.initGame();
           
            }    
         }    
       
    }
    
    @Test
    public void testMoveRightSquareShape() {
             
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape square = new SquareShape(new ShapePosition(0, 0), "yellow");
        logic.addShapeToLogicBoard(square);
       
        for(int i = 0; i < ShapePosition.WIDTH_OF_BOARD - 1; i++){
            assertTrue(logic.getLogicBoard().get(i) &&
                        logic.getLogicBoard().get(i + 1) &&
                        logic.getLogicBoard().get(i + ShapePosition.WIDTH_OF_BOARD) &&
                        logic.getLogicBoard().get(i + ShapePosition.WIDTH_OF_BOARD + 1));
            //logic.displayLogicBoard();
            logic.clearLogicBoard(square.moveToRight());
            logic.addShapeToLogicBoard(square);
        }
        
    }
    
    @Test
    public void testMoveLeftSquareShape() {
             
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape square = new SquareShape(new ShapePosition(8, 0), "yellow");
        logic.addShapeToLogicBoard(square);
        //logic.displayLogicBoard();
              
        for(int i = 8; 0 <= i; i--){
            assertTrue(logic.getLogicBoard().get(i) &&
                        logic.getLogicBoard().get(i + 1) &&
                        logic.getLogicBoard().get(i + ShapePosition.WIDTH_OF_BOARD) &&
                        logic.getLogicBoard().get(i + ShapePosition.WIDTH_OF_BOARD + 1));
            //logic.displayLogicBoard();
            logic.clearLogicBoard(square.moveToLeft());
            logic.addShapeToLogicBoard(square);
        }
        
    }
    
     @Test
    public void testMoveDownSquareShape() {
             
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape square = new SquareShape(new ShapePosition(0, 0), "yellow");
        logic.addShapeToLogicBoard(square);
        //logic.displayLogicBoard();
              
        for(int i = 0; i < ShapePosition.LENGTH_OF_BOARD - 1; i++){
            assertTrue(logic.getLogicBoard().get(i * ShapePosition.WIDTH_OF_BOARD) &&
                        logic.getLogicBoard().get(i * ShapePosition.WIDTH_OF_BOARD + 1) &&
                        logic.getLogicBoard().get((i + 1) * ShapePosition.WIDTH_OF_BOARD) &&
                        logic.getLogicBoard().get((i + 1) *  ShapePosition.WIDTH_OF_BOARD + 1));
            //logic.displayLogicBoard();
            logic.clearLogicBoard(square.moveToDown());
            logic.addShapeToLogicBoard(square);
        }
        
    }
    
    
}
