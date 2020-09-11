
package com.david.giczi.tetris.model;

import com.david.giczi.tetris.shape.AbstractShape;
import javax.ejb.Local;

/**
 *
 * @author GicziD
 */
@Local
public interface TetrisLogic {
    
    boolean canShapeBeMovedToLeft(AbstractShape shape);
    boolean canShapeBeMovedToRight(AbstractShape shape);
    boolean canShapeBeMovedToDown(AbstractShape shape);
    boolean canShapeBeRotated(AbstractShape shape);
}
