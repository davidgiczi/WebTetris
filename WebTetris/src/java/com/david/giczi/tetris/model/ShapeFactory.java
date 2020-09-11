
package com.david.giczi.tetris.model;

import com.david.giczi.tetris.shape.AbstractShape;
import javax.ejb.Local;

/**
 *
 * @author GicziD
 */
@Local
public interface ShapeFactory {
    
    AbstractShape getShape();
    
}
