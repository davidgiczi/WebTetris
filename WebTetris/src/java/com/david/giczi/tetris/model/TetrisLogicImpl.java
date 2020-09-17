package com.david.giczi.tetris.model;

import com.david.giczi.tetris.shape.RightGunShape;
import com.david.giczi.tetris.shape.AbstractShape;
import com.david.giczi.tetris.shape.TShape;
import com.david.giczi.tetris.shape.LeftGunShape;
import com.david.giczi.tetris.shape.PillarShape;
import com.david.giczi.tetris.shape.LeftStairShape;
import com.david.giczi.tetris.shape.RightStairShape;
import com.david.giczi.tetris.shape.SquareShape;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author GicziD
 */
@Stateless
public class TetrisLogicImpl implements TetrisLogic{

    private List<Boolean> logicBoard;
    private List<AbstractShape> shapeStore;
    private int score = 0;

    @Override
    public List<Boolean> getLogicBoard() {
        return logicBoard;
    }

    @Override
    public void setLogicBoard(List<Boolean> logicBoard) {
        this.logicBoard = logicBoard;
    }
    
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public List<AbstractShape> getShapeStore() {
        return shapeStore;
    }

    @Override
    public void setShapeStore(List<AbstractShape> shapeStore) {
        this.shapeStore = shapeStore;
    }
    
   
    @Override
    public void initLogicBoard() {
        for (int i = 0;
                i < ShapePosition.WIDTH_OF_BOARD * ShapePosition.LENGTH_OF_BOARD; i++) {
            logicBoard.add(Boolean.FALSE);
        }
    }

    @Override
    public void clearLogicBoard() {

        for (int i = 0;
                i < ShapePosition.WIDTH_OF_BOARD * ShapePosition.LENGTH_OF_BOARD; i++) {
            if (logicBoard.get(i)) {
                logicBoard.set(i, Boolean.FALSE);
            }
        }
    }

    @Override
    public void addShapeToLogicBoard(AbstractShape shape) {
        
        shape.shapeComponent
                .forEach(component -> logicBoard
                .set(component.getLogicBoardIndex(), Boolean.TRUE));

    }

    @Override
    public void addShapeToStore(AbstractShape shape) {

        shapeStore.add(shape);
    }

    @Override
    public boolean canShapeBeMovedToLeft(AbstractShape shape) {

        if (shape instanceof PillarShape) {

            return canPillarShapeBeMovedToLeft((PillarShape) shape);
        } else if (shape instanceof SquareShape) {

            return canSquareShapeBeMovedToLeft((SquareShape) shape);
        } else if (shape instanceof TShape) {

            return canTShapeBeMovedToLeft((TShape) shape);
        } else if (shape instanceof LeftGunShape) {

            return canLeftGunShapeBeMovedToLeft((LeftGunShape) shape);
        } else if (shape instanceof RightGunShape) {

            return canRightGunShapeBeMovedToLeft((RightGunShape) shape);
        } else if (shape instanceof LeftStairShape) {

            return canLeftStairShapeBeMovedToLeft((LeftStairShape) shape);
        } else if (shape instanceof RightStairShape) {

            return canRightStairShapeBeMovedToLeft((RightStairShape) shape);
        }

        return false;
    }

    private boolean canPillarShapeBeMovedToLeft(PillarShape shape) {

        List<ShapePosition> leftPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            shape.shapeComponent.forEach(component -> leftPosition
                    .add(new ShapePosition(component.getDisplayer_x() - 1, component.getDisplayer_y())));

        }

        for (ShapePosition shapePosition : leftPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }

        }

        return true;
    }

    private boolean canSquareShapeBeMovedToLeft(SquareShape shape) {

        List<ShapePosition> leftPosition = new ArrayList<>();

        leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                shape.shapeComponent.get(0).getDisplayer_y()));
        leftPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() - 1,
                shape.shapeComponent.get(2).getDisplayer_y()));

        for (ShapePosition shapePosition : leftPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }

        }

        return true;
    }

    private boolean canTShapeBeMovedToLeft(TShape shape) {

        List<ShapePosition> leftPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() - 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() - 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(2)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(3)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() - 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : leftPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canLeftGunShapeBeMovedToLeft(LeftGunShape shape) {

        List<ShapePosition> leftPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() - 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() - 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() - 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(2)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(3)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() - 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : leftPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canRightGunShapeBeMovedToLeft(RightGunShape shape) {

        List<ShapePosition> leftPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() - 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() - 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(2)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(3)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() - 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() - 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : leftPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canLeftStairShapeBeMovedToLeft(LeftStairShape shape) {

        List<ShapePosition> leftPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() - 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() - 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : leftPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canRightStairShapeBeMovedToLeft(RightStairShape shape) {

        List<ShapePosition> leftPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() - 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            leftPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            leftPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() - 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : leftPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }

        }

        return true;
    }

    @Override
    public boolean canShapeBeMovedToRight(AbstractShape shape) {

        if (shape instanceof PillarShape) {

            return canPillarShapeBeMovedToRight((PillarShape) shape);
        } else if (shape instanceof SquareShape) {

            return canSquareShapeBeMovedToRight((SquareShape) shape);
        } else if (shape instanceof TShape) {

            return canTShapeBeMovedToRight((TShape) shape);
        } else if (shape instanceof LeftGunShape) {

            return canLeftGunShapeBeMovedToRight((LeftGunShape) shape);
        } else if (shape instanceof RightGunShape) {

            return canRightGunShapeBeMovedToRight((RightGunShape) shape);
        } else if (shape instanceof LeftStairShape) {

            return canLeftStairShapeBeMovedToRight((LeftStairShape) shape);
        } else if (shape instanceof RightStairShape) {

            return canRightStairShapeBeMovedToRight((RightStairShape) shape);
        }

        return false;
    }

    private boolean canPillarShapeBeMovedToRight(PillarShape shape) {

        List<ShapePosition> rightPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            shape.shapeComponent.forEach(component -> rightPosition
                    .add(new ShapePosition(component.getDisplayer_x() + 1, component.getDisplayer_y())));

        }

        for (ShapePosition shapePosition : rightPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }

        }

        return true;
    }

    private boolean canSquareShapeBeMovedToRight(SquareShape shape) {

        List<ShapePosition> rightPosition = new ArrayList<>();

        rightPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() + 1,
                shape.shapeComponent.get(1).getDisplayer_y()));
        rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                shape.shapeComponent.get(3).getDisplayer_y()));

        for (ShapePosition shapePosition : rightPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }

        }

        return true;
    }

    private boolean canTShapeBeMovedToRight(TShape shape) {

        List<ShapePosition> rightPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(2)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(3)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : rightPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canLeftGunShapeBeMovedToRight(LeftGunShape shape) {

        List<ShapePosition> rightPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() + 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(2)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(3)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() + 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : rightPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canRightGunShapeBeMovedToRight(RightGunShape shape) {

        List<ShapePosition> rightPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() + 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(2)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(3)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() + 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : rightPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canLeftStairShapeBeMovedToRight(LeftStairShape shape) {

        List<ShapePosition> rightPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() + 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : rightPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canRightStairShapeBeMovedToRight(RightStairShape shape) {

        List<ShapePosition> rightPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() + 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(1)) {

            rightPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rightPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : rightPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }

        }

        return true;
    }

    @Override
    public boolean canShapeBeMovedToDown(AbstractShape shape) {

        if (shape instanceof PillarShape) {

            return canPillarShapeBeMovedToDown((PillarShape) shape);

        } else if (shape instanceof SquareShape) {

            return canSquareShapeBeMovedToDown((SquareShape) shape);
        } else if (shape instanceof TShape) {

            return canTShapeBeMovedToDown((TShape) shape);
        } else if (shape instanceof LeftGunShape) {

            return canLeftGunShapeBeMovedToDown((LeftGunShape) shape);
        } else if (shape instanceof RightGunShape) {

            return canRightGunShapeBeMovedToDown((RightGunShape) shape);
        } else if (shape instanceof LeftStairShape) {

            return canLeftStairShapeBeMovedToDown((LeftStairShape) shape);
        } else if (shape instanceof RightStairShape) {

            return canRightStairShapeBeMovedToDown((RightStairShape) shape);
        }

        return false;
    }

    private boolean canPillarShapeBeMovedToDown(PillarShape shape) {

        List<ShapePosition> downPosition = getPillarShapeDownPosition(shape);

        for (ShapePosition shapePosition : downPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }

        }

        return true;
    }

    private List<ShapePosition> getPillarShapeDownPosition(PillarShape shape) {

        List<ShapePosition> downPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            shape.shapeComponent.forEach(component -> downPosition
                    .add(new ShapePosition(component.getDisplayer_x(),
                            component.getDisplayer_y() + 1)));

        } else if (shape.shapeRotationPosition.get(1)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        }

        return downPosition;
    }

    private boolean canSquareShapeBeMovedToDown(SquareShape shape) {

        List<ShapePosition> downPosition = getSquareShapeDownPosition(shape);

        for (ShapePosition shapePosition : downPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }

        }

        return true;
    }

    private List<ShapePosition> getSquareShapeDownPosition(SquareShape shape) {

        List<ShapePosition> downPosition = new ArrayList<>();

        downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                shape.shapeComponent.get(2).getDisplayer_y() + 1));
        downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                shape.shapeComponent.get(3).getDisplayer_y() + 1));

        return downPosition;

    }

    private boolean canTShapeBeMovedToDown(TShape shape) {

        List<ShapePosition> downPosition = getTShapeDownPosition(shape);

        for (ShapePosition shapePosition : downPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private List<ShapePosition> getTShapeDownPosition(TShape shape) {

        List<ShapePosition> downPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x(),
                    shape.shapeComponent.get(0).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(1)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(2)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(3)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        }

        return downPosition;
    }

    private boolean canLeftGunShapeBeMovedToDown(LeftGunShape shape) {

        List<ShapePosition> downPosition = getLeftGunShapeDownPosition(shape);

        for (ShapePosition shapePosition : downPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }
        return true;
    }

    private List<ShapePosition> getLeftGunShapeDownPosition(LeftGunShape shape) {

        List<ShapePosition> downPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(1)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x(),
                    shape.shapeComponent.get(0).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(2)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(3)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        }

        return downPosition;
    }

    private boolean canRightGunShapeBeMovedToDown(RightGunShape shape) {

        List<ShapePosition> downPosition = getRightGunShapeDownPosition(shape);

        for (ShapePosition shapePosition : downPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }
        return true;
    }

    private List<ShapePosition> getRightGunShapeDownPosition(RightGunShape shape) {

        List<ShapePosition> downPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x(),
                    shape.shapeComponent.get(0).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(1)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(2)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(3)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        }

        return downPosition;
    }

    private boolean canLeftStairShapeBeMovedToDown(LeftStairShape shape) {

        List<ShapePosition> downPosition = getLeftStairShapeDownPosition(shape);

        for (ShapePosition shapePosition : downPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private List<ShapePosition> getLeftStairShapeDownPosition(LeftStairShape shape) {

        List<ShapePosition> downPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x(),
                    shape.shapeComponent.get(0).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(1)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        }

        return downPosition;
    }

    private boolean canRightStairShapeBeMovedToDown(RightStairShape shape) {

        List<ShapePosition> downPosition = getRightStairShapeDownPosition(shape);

        for (ShapePosition shapePosition : downPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private List<ShapePosition> getRightStairShapeDownPosition(RightStairShape shape) {

        List<ShapePosition> downPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(1)) {

            downPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            downPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        }

        return downPosition;

    }

    @Override
    public boolean canShapeBeRotated(AbstractShape shape) {

        if (shape instanceof PillarShape) {

            return canPillarShapeBeRotated((PillarShape) shape);

        } else if (shape instanceof SquareShape) {

            return true;
        } else if (shape instanceof TShape) {

            return canTShapeBeRotated((TShape) shape);
        } else if (shape instanceof LeftGunShape) {

            return canLeftGunShapeBeRotated((LeftGunShape) shape);
        } else if (shape instanceof RightGunShape) {

            return canRightGunShapeBeRotated((RightGunShape) shape);
        } else if (shape instanceof LeftStairShape) {

            return canLeftStairShapeBeRotated((LeftStairShape) shape);
        } else if (shape instanceof RightStairShape) {

            return canRightStairShapeBeRotated((RightStairShape) shape);
        }

        return false;
    }

    private boolean canPillarShapeBeRotated(PillarShape shape) {

        List<ShapePosition> rotatedPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            for (int i = 1; i < 4; i++) {
                rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x(),
                        shape.shapeComponent.get(0).getDisplayer_y() + i));
            }

        } else if (shape.shapeRotationPosition.get(1)) {

            for (int i = 1; i < 4; i++) {
                rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + i,
                        shape.shapeComponent.get(0).getDisplayer_y()));
            }

        }

        for (ShapePosition shapePosition : rotatedPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }

        }

        return true;
    }

    private boolean canTShapeBeRotated(TShape shape) {

        List<ShapePosition> rotatedPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x(),
                    shape.shapeComponent.get(0).getDisplayer_y() + 1));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(1)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(2)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(3)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : rotatedPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canLeftGunShapeBeRotated(LeftGunShape shape) {

        List<ShapePosition> rotatedPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 2));

        } else if (shape.shapeRotationPosition.get(1)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() - 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x() + 1,
                    shape.shapeComponent.get(2).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() + 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(2)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() - 1));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(3)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : rotatedPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canRightGunShapeBeRotated(RightGunShape shape) {

        List<ShapePosition> rotatedPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y() + 2));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 2));

        } else if (shape.shapeRotationPosition.get(1)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() + 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));

        } else if (shape.shapeRotationPosition.get(2)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 2,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(3)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() - 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() + 1));

        }

        for (ShapePosition shapePosition : rotatedPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canLeftStairShapeBeRotated(LeftStairShape shape) {

        List<ShapePosition> rotatedPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(2).getDisplayer_x(),
                    shape.shapeComponent.get(2).getDisplayer_y() + 1));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x(),
                    shape.shapeComponent.get(3).getDisplayer_y() - 1));

        } else if (shape.shapeRotationPosition.get(1)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y() - 1));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x(),
                    shape.shapeComponent.get(1).getDisplayer_y() - 1));

        }

        for (ShapePosition shapePosition : rotatedPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

    private boolean canRightStairShapeBeRotated(RightStairShape shape) {

        List<ShapePosition> rotatedPosition = new ArrayList<>();

        if (shape.shapeRotationPosition.get(0)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(3).getDisplayer_x() + 1,
                    shape.shapeComponent.get(3).getDisplayer_y() + 1));

        } else if (shape.shapeRotationPosition.get(1)) {

            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(1).getDisplayer_x() - 1,
                    shape.shapeComponent.get(1).getDisplayer_y()));
            rotatedPosition.add(new ShapePosition(shape.shapeComponent.get(0).getDisplayer_x() + 1,
                    shape.shapeComponent.get(0).getDisplayer_y()));

        }

        for (ShapePosition shapePosition : rotatedPosition) {

            if (shapePosition.getLogicBoardIndex() == -1) {
                return false;
            }
            if (logicBoard.get(shapePosition.getLogicBoardIndex())) {
                return false;
            }
        }

        return true;
    }

   
    @Override
    public void addDeletedPositionToLogicBoard(List<ShapePosition> deletedShapePosition) {

        deletedShapePosition.forEach(position -> logicBoard.set(position.getLogicBoardIndex(), Boolean.FALSE));
    }

    @Override
    public void calcScore(AbstractShape shape) {

        if (shape instanceof PillarShape) {

            getPillarShapeScore((PillarShape) shape);
        } else if (shape instanceof SquareShape) {

            getSquareShapeScore((SquareShape) shape);
        } else if (shape instanceof TShape) {

            getTShapeScore((TShape) shape);
        } else if (shape instanceof LeftGunShape) {

            getLeftGunShapeScore((LeftGunShape) shape);
        } else if (shape instanceof RightGunShape) {

            getRightGunShapeScore((RightGunShape) shape);
        } else if (shape instanceof LeftStairShape) {

            getLeftStairShapeScore((LeftStairShape) shape);
        } else if (shape instanceof RightStairShape) {

            getRightStairShapeScore((RightStairShape) shape);
        }

    }

    private void getPillarShapeScore(PillarShape shape) {

        List<ShapePosition> downPosition = getPillarShapeDownPosition(shape);

        downPosition.stream().filter((shapePosition) -> (shapePosition.getLogicBoardIndex() != -1
                && logicBoard.get(shapePosition.getLogicBoardIndex()))).forEachOrdered((value) -> {
            score += 10;
        });

    }

    private void getSquareShapeScore(SquareShape shape) {

        List<ShapePosition> downPosition = getSquareShapeDownPosition(shape);

        downPosition.stream().filter((shapePosition) -> (shapePosition.getLogicBoardIndex() != -1
                && logicBoard.get(shapePosition.getLogicBoardIndex()))).forEachOrdered((value) -> {
            score += 10;
        });

    }

    private void getTShapeScore(TShape shape) {

        List<ShapePosition> downPosition = getTShapeDownPosition(shape);

        downPosition.stream().filter((shapePosition) -> (shapePosition.getLogicBoardIndex() != -1
                && logicBoard.get(shapePosition.getLogicBoardIndex()))).forEachOrdered((value) -> {
            score += 10;
        });
    }

    private void getLeftGunShapeScore(LeftGunShape shape) {

        List<ShapePosition> downPosition = getLeftGunShapeDownPosition(shape);

        downPosition.stream().filter((shapePosition) -> (shapePosition.getLogicBoardIndex() != -1
                && logicBoard.get(shapePosition.getLogicBoardIndex()))).forEachOrdered((value) -> {
            score += 10;
        });
    }

    private void getRightGunShapeScore(RightGunShape shape) {

        List<ShapePosition> downPosition = getRightGunShapeDownPosition(shape);

        downPosition.stream().filter((shapePosition) -> (shapePosition.getLogicBoardIndex() != -1
                && logicBoard.get(shapePosition.getLogicBoardIndex()))).forEachOrdered((value) -> {
            score += 10;
        });

    }

    private void getLeftStairShapeScore(LeftStairShape shape) {

        List<ShapePosition> downPosition = getLeftStairShapeDownPosition(shape);

        downPosition.stream().filter((shapePosition) -> (shapePosition.getLogicBoardIndex() != -1
                && logicBoard.get(shapePosition.getLogicBoardIndex()))).forEachOrdered((value) -> {
            score += 10;
        });
    }

    private void getRightStairShapeScore(RightStairShape shape) {

        List<ShapePosition> downPosition = getRightStairShapeDownPosition(shape);

        downPosition.stream().filter((shapePosition) -> (shapePosition.getLogicBoardIndex() != -1
                && logicBoard.get(shapePosition.getLogicBoardIndex()))).forEachOrdered((value) -> {
            score += 10;
        });
    }

    @Override
    public List<Integer> getCompleteTrueRowsIndex() {

        List<Integer> rowsIndex = new ArrayList<>();

        for (int i = 0; i < ShapePosition.LENGTH_OF_BOARD; i++) {

            int trueCounter = 0;

            for (int j = 0; j < ShapePosition.WIDTH_OF_BOARD; j++) {

                if (logicBoard.get(i * ShapePosition.WIDTH_OF_BOARD + j)) {
                    trueCounter++;
                }

            }

            if (trueCounter == ShapePosition.WIDTH_OF_BOARD) {
                rowsIndex.add(i);
            }

        }

        return rowsIndex;
    }

    @Override
    public void deleteCompleteTrueRowsFromShapeComponent(List<Integer> completeTrueRowsIndex) {

        List<ShapePosition> deletedPosition = new ArrayList<>();

        completeTrueRowsIndex.forEach((x) -> {
            for (int y = 0; y < ShapePosition.WIDTH_OF_BOARD; y++) {
                deletedPosition.add(new ShapePosition(x * ShapePosition.WIDTH_OF_BOARD + y));
            }
        });

        deletedPosition.forEach((component) -> {
            shapeStore.forEach((shape) -> {

                if (shape.shapeComponent.contains(component)) {
                    shape.shapeComponent.remove(component);
                }

            });
        });

    }

    @Override
    public void increaseRowNumberForShapeComponentInShapeStore(List<Integer> completeTrueRowsIndex) {

        completeTrueRowsIndex.forEach((rowIndex) -> {
            shapeStore.forEach((shape) -> {
                for (int i = 0; i < shape.shapeComponent.size(); i++) {

                    if (shape.shapeComponent.get(i).getDisplayer_y() < rowIndex) {
                        shape.shapeComponent.set(i, new ShapePosition(
                                shape.shapeComponent.get(i).getLogicBoardIndex()
                                        + ShapePosition.WIDTH_OF_BOARD));
                    }

                }
            });
        });

    }

    @Override
    public void refreshLogicBoard() {

        clearLogicBoard();

        shapeStore.forEach((shape) -> {
            addShapeToLogicBoard(shape);
        });
    }

    @Override
    public boolean isTheEndOfTheGame() {

        for (int i = 0; i < ShapePosition.WIDTH_OF_BOARD; i++) {
            if (logicBoard.get(i)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String createDisplayableLogicBoard() {
        
        StringBuilder build = new StringBuilder("\n");
        
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        

        for (int i = 0; i < ShapePosition.LENGTH_OF_BOARD; i++) {
            
            for(int j = 0; j < ShapePosition.WIDTH_OF_BOARD; j++){
            
             boolean value = logicBoard.get(i * ShapePosition.WIDTH_OF_BOARD + j);
                
                if (value) {
                    
                 build.append(ANSI_RED)
                      .append("!")
                      .append(String.valueOf(value).toUpperCase())
                      .append(ANSI_RESET)
                      .append(" ");
                     
                      
                } else {

                   build.append(value)
                        .append(" ");
                                              
                }
                
            }   
                    build.append("\n");
        }
        
        build.append("\n")
             .append(ANSI_RED)
             .append("end of the board")
             .append(ANSI_RESET);
        
             
        return build.toString();
    }
}
