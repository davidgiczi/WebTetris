
package test;

import com.david.giczi.tetris.model.ShapePosition;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author GicziD
 */
public class ShapePositionTest {
    
   
 
    @Test
    public void testCreateShapePositionByDisplayerCoords1() {
        
        for(int y = 0; y < ShapePosition.LENGTH_OF_BOARD; y++){
            
             for(int x = 0; x < ShapePosition.WIDTH_OF_BOARD; x++){
                 
                 ShapePosition posi = new ShapePosition(x, y);
                 //System.out.println(posi);
                 assertEquals(y * ShapePosition.WIDTH_OF_BOARD + x, posi.getLogicBoardIndex());
                 
             }
               
        }
        
    }
    
    
    @Test
    public void testCreateShapePositionByDisplayerCoords2() {
        
        for(int y =  ShapePosition.LENGTH_OF_BOARD; y < 100; y++){
            
             for(int x = ShapePosition.WIDTH_OF_BOARD; x < 100; x++){
                 
                 ShapePosition posi = new ShapePosition(x, y);
                 //System.out.println(posi);
                 assertEquals(-1, posi.getLogicBoardIndex());
                 
             }
               
        }
        
    }
    
    @Test
    public void testCreateShapePositionByLogicBoardIndex1() {
              
        for(int index = 0; index < ShapePosition.LENGTH_OF_BOARD * ShapePosition.WIDTH_OF_BOARD; index++){
            ShapePosition posi = new ShapePosition(index);
            //System.out.println(posi);
            assertEquals(index % ShapePosition.WIDTH_OF_BOARD, posi.getDisplayer_x());
            assertEquals(index / ShapePosition.WIDTH_OF_BOARD, posi.getDisplayer_y());
        }
       
    }
    
    @Test
    public void testCreateShapePositionByLogicBoardIndex2() {
              
        for(int index = ShapePosition.LENGTH_OF_BOARD * ShapePosition.WIDTH_OF_BOARD; index < 300; index++){
            ShapePosition posi = new ShapePosition(index);
            //System.out.println(posi);
            assertEquals(-1, posi.getDisplayer_x());
            assertEquals(-1, posi.getDisplayer_y());
        }
       
    }
    
    
}
