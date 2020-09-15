
package com.david.giczi.tetris.model;

import com.david.giczi.tetris.shape.AbstractShape;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author GicziD
 */
@Local
public interface TetrisLogic {
    
    List<Boolean> getLogicBoard();
    void setLogicBoard(List<Boolean> logicBoard);
    int getScore();
    List<AbstractShape> getShapeStore();
    void setShapeStore(List<AbstractShape> shapeStore);
    void setScore(int score);
    void initLogicBoard();
    void clearLogicBoard();
    void addShapeToLogicBoard(AbstractShape shape);
    void addShapeToStore(AbstractShape shape);
    boolean canShapeBeMovedToLeft(AbstractShape shape);
    boolean canShapeBeMovedToRight(AbstractShape shape);
    boolean canShapeBeMovedToDown(AbstractShape shape);
    boolean canShapeBeRotated(AbstractShape shape);
    void moveLogicShape(AbstractShape shape);
    void clearLogicBoard(List<ShapePosition> deletedShapePosition);
    void calcScore(AbstractShape shape);
    List<Integer> getCompleteTrueRowsIndex();
    void deleteCompleteTrueRowsFromShapeComponent(List<Integer> completeTrueRowsIndex);
    void increaseRowNumberForShapeComponentInShapeStore(List<Integer> completeTrueRowsIndex);
    void refreshLogicBoard();
    boolean isTheEndOfTheGame();
    void displayLogicBoard();
}
