package test;


import com.david.giczi.tetris.model.GameLogic;
import com.david.giczi.tetris.model.ShapePosition;
import com.david.giczi.tetris.shape.AbstractShape;
import com.david.giczi.tetris.shape.PillarShape;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author GicziD
 */
public class PillarShapeTest {
    
    
    
    @Test
    public void testCreatePillarShape() {
             
        GameLogic logic = new GameLogic();
        logic.initGame();
        
        for(int y = 0; y < ShapePosition.LENGTH_OF_BOARD; y++){
           
            for(int x = 2; x < ShapePosition.WIDTH_OF_BOARD - 1; x++){
                 AbstractShape pillar = new PillarShape(new ShapePosition(x, y), "red");
                 logic.addShapeToLogicBoard(pillar);
                 assertTrue(logic.getLogicBoard().get(ShapePosition.WIDTH_OF_BOARD * y + x - 2) && 
                            logic.getLogicBoard().get(ShapePosition.WIDTH_OF_BOARD * y + x - 1) &&
                            logic.getLogicBoard().get(ShapePosition.WIDTH_OF_BOARD * y + x) &&
                            logic.getLogicBoard().get(ShapePosition.WIDTH_OF_BOARD * y + x + 1));
                 logic.initGame();
            }
            
        }
        
    }
    
    @Test
    public void testAddPillarShapeToLogicBoard() {
        
         GameLogic logic = new GameLogic();
         logic.initGame();
         AbstractShape pillar = new PillarShape(new ShapePosition(0,0), "green");
         pillar.addShapeToGameBoard();
         logic.addShapeToLogicBoard(pillar);
         //logic.displayLogicBoard();
         
    }
    
    @Test
    public void testMoveRightShapePillar1() {
        
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape pillar = new PillarShape(new ShapePosition(2,0), "green");
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        
        for(int i = 0; i < 6; i++ ){
        assertTrue(logic.getLogicBoard().get(i) && logic.getLogicBoard().get(i + 1) &&
                   logic.getLogicBoard().get(i + 2) && logic.getLogicBoard().get(i + 3));
        logic.clearLogicBoard(pillar.moveToRight());
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        }
        
    }
    
     @Test
    public void testMoveRightShapePillar2() {
        
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape pillar = new PillarShape(new ShapePosition(2,0), "green");
        pillar.rotateShape();
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        
        for(int i = 0; i < ShapePosition.WIDTH_OF_BOARD - 1; i++ ){
        assertTrue(logic.getLogicBoard().get(i) && 
                   logic.getLogicBoard().get(i + ShapePosition.WIDTH_OF_BOARD) &&
                   logic.getLogicBoard().get(i + 2 * ShapePosition.WIDTH_OF_BOARD) && 
                   logic.getLogicBoard().get(i + 3 * ShapePosition.WIDTH_OF_BOARD));
        logic.clearLogicBoard(pillar.moveToRight());
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        }
        
    }
    
    @Test
    public void testMoveLeftShapePillar1() {
        
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape pillar = new PillarShape(new ShapePosition(8,0), "green");
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        
        for(int i = 9; 3 < i; i-- ){
        assertTrue(logic.getLogicBoard().get(i) && logic.getLogicBoard().get(i - 1) &&
                   logic.getLogicBoard().get(i - 2) && logic.getLogicBoard().get(i - 3));
        logic.clearLogicBoard(pillar.moveToLeft());
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        }
    
   }
    
    @Test
    public void testMoveLeftShapePillar2() {
        
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape pillar = new PillarShape(new ShapePosition(8,0), "green");
        pillar.rotateShape();
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        
        for(int i = 6; 0 < i; i-- ){
        assertTrue(logic.getLogicBoard().get(i) &&
                   logic.getLogicBoard().get(i + ShapePosition.WIDTH_OF_BOARD) &&
                   logic.getLogicBoard().get(i + 2 * ShapePosition.WIDTH_OF_BOARD) &&
                   logic.getLogicBoard().get(i + 3 * ShapePosition.WIDTH_OF_BOARD));
        logic.clearLogicBoard(pillar.moveToLeft());
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        }
    
   } 
   
    @Test
    public void testMoveDownShapePillar1() {
        
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape pillar = new PillarShape(new ShapePosition(2,0), "green");
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        
        for(int i = 0; i < ShapePosition.LENGTH_OF_BOARD - 1; i++){
        assertTrue(logic.getLogicBoard().get(i * ShapePosition.WIDTH_OF_BOARD) &&
                   logic.getLogicBoard().get(1 + i * ShapePosition.WIDTH_OF_BOARD) &&
                   logic.getLogicBoard().get(2 + i * ShapePosition.WIDTH_OF_BOARD) &&
                   logic.getLogicBoard().get(3 + i * ShapePosition.WIDTH_OF_BOARD));
        logic.clearLogicBoard(pillar.moveToDown());
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        }
        
        
    }
    
     @Test
    public void testMoveDownShapePillar2() {
        
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape pillar = new PillarShape(new ShapePosition(2,0), "green");
        pillar.rotateShape();
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        
        for(int i = 0; i < ShapePosition.LENGTH_OF_BOARD - 4; i++){
        assertTrue(logic.getLogicBoard().get(i * ShapePosition.WIDTH_OF_BOARD) &&
                   logic.getLogicBoard().get((i + 1) * ShapePosition.WIDTH_OF_BOARD) &&
                   logic.getLogicBoard().get((i + 2) * ShapePosition.WIDTH_OF_BOARD) &&
                   logic.getLogicBoard().get((i + 3) * ShapePosition.WIDTH_OF_BOARD));
        logic.clearLogicBoard(pillar.moveToDown());
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        }
              
    }
    
    @Test
    public void testRotateShapePillar1() {
        
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape pillar = new PillarShape(new ShapePosition(2,0), "green");
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        logic.clearLogicBoard(pillar.rotateShape());
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        assertTrue(logic.getLogicBoard().get(0) && logic.getLogicBoard().get(10) &&
        logic.getLogicBoard().get(20) && logic.getLogicBoard().get(30));
      
    }
    
    @Test
    public void testRotateShapePillar2() {
        
        GameLogic logic = new GameLogic();
        logic.initGame();
        AbstractShape pillar = new PillarShape(new ShapePosition(2,0), "green");
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        logic.clearLogicBoard(pillar.rotateShape());
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        logic.clearLogicBoard(pillar.rotateShape());
        logic.addShapeToLogicBoard(pillar);
        //logic.displayLogicBoard();
        assertTrue(logic.getLogicBoard().get(0) && logic.getLogicBoard().get(1) &&
        logic.getLogicBoard().get(2) && logic.getLogicBoard().get(3));
        
        
    }
    
}