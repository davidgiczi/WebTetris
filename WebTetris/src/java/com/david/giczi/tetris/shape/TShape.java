package com.david.giczi.tetris.shape;
import com.david.giczi.tetris.model.ShapePosition;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GicziD
 */
public class TShape extends AbstractShape {

    public TShape(ShapePosition starterPosition, String shapeColor) {
        this.shapeColor = shapeColor;
        createShape(starterPosition);
    }

    @Override
    public final void createShape(ShapePosition starterPosition) {
        this.shapeRotationPosition = super.initShapeRotationPosition();
        shapeComponent.add(new ShapePosition(starterPosition.getDisplayer_x() - 1,
                starterPosition.getDisplayer_y()));
        shapeComponent.add(starterPosition);
        shapeComponent.add(new ShapePosition(starterPosition.getDisplayer_x() + 1,
                starterPosition.getDisplayer_y()));
        shapeComponent.add(new ShapePosition(starterPosition.getDisplayer_x(),
                starterPosition.getDisplayer_y() + 1));
    }

    @Override
    public List<ShapePosition> rotateShape() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        if (shapeRotationPosition.get(0)) {

            shapeComponent.add(temp.get(1));
            shapeComponent.add(new ShapePosition(temp.get(1).getDisplayer_x() - 1,
                    temp.get(1).getDisplayer_y() + 1));
            shapeComponent.add(new ShapePosition(temp.get(1).getDisplayer_x(),
                    temp.get(1).getDisplayer_y() + 1));
            shapeComponent.add(new ShapePosition(temp.get(1).getDisplayer_x(),
                    temp.get(1).getDisplayer_y() + 2));
            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(2));
            deletedShapePosition.add(temp.get(3));

            shapeRotationPosition.set(0, Boolean.FALSE);
            shapeRotationPosition.set(1, Boolean.TRUE);
        } else if (shapeRotationPosition.get(1)) {

            shapeComponent.add(temp.get(0));
            shapeComponent.add(temp.get(1));
            shapeComponent.add(temp.get(2));
            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 1,
                    temp.get(0).getDisplayer_y() + 1));
            deletedShapePosition.add(temp.get(3));

            shapeRotationPosition.set(1, Boolean.FALSE);
            shapeRotationPosition.set(2, Boolean.TRUE);
        } else if (shapeRotationPosition.get(2)) {

            shapeComponent.add(temp.get(0));
            shapeComponent.add(temp.get(2));
            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 1,
                    temp.get(0).getDisplayer_y() + 1));
            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x(),
                    temp.get(0).getDisplayer_y() + 2));

            deletedShapePosition.add(temp.get(1));
            deletedShapePosition.add(temp.get(3));

            shapeRotationPosition.set(2, Boolean.FALSE);
            shapeRotationPosition.set(3, Boolean.TRUE);
        } else if (shapeRotationPosition.get(3)) {

            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() - 1,
                    temp.get(0).getDisplayer_y()));
            shapeComponent.add(temp.get(0));
            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 1,
                    temp.get(0).getDisplayer_y()));

            shapeComponent.add(temp.get(1));

            deletedShapePosition.add(temp.get(2));
            deletedShapePosition.add(temp.get(3));

            shapeRotationPosition.set(3, Boolean.FALSE);
            shapeRotationPosition.set(0, Boolean.TRUE);
        }

        return deletedShapePosition;
    }

    @Override
    public List<ShapePosition> moveToLeft() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        if (shapeRotationPosition.get(0)) {

            shapeComponent.add(new ShapePosition(
                    temp.get(0).getDisplayer_x() - 1,
                    temp.get(0).getDisplayer_y()));
            shapeComponent.add(temp.get(0));
            shapeComponent.add(temp.get(1));
            shapeComponent.add(new ShapePosition(
                    temp.get(0).getDisplayer_x(),
                    temp.get(0).getDisplayer_y() + 1));

            deletedShapePosition.add(temp.get(2));
            deletedShapePosition.add(temp.get(3));
        } else if (shapeRotationPosition.get(1)) {

            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() - 1,
                    temp.get(0).getDisplayer_y()));
            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() - 2,
                    temp.get(0).getDisplayer_y() + 1));
            shapeComponent.add(temp.get(1));
            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() - 1,
                    temp.get(0).getDisplayer_y() + 2));

            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(2));
            deletedShapePosition.add(temp.get(3));

        } else if (shapeRotationPosition.get(2)) {

            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() - 1,
                    temp.get(0).getDisplayer_y()));
            shapeComponent.add(new ShapePosition(temp.get(1).getDisplayer_x() - 1,
                    temp.get(1).getDisplayer_y()));
            shapeComponent.add(temp.get(1));
            shapeComponent.add(temp.get(2));

            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(3));

        } else if (shapeRotationPosition.get(3)) {

            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() - 1,
                    temp.get(0).getDisplayer_y()));
            shapeComponent.add(new ShapePosition(temp.get(1).getDisplayer_x() - 1,
                    temp.get(1).getDisplayer_y()));
            shapeComponent.add(temp.get(1));
            shapeComponent.add(new ShapePosition(temp.get(3).getDisplayer_x() - 1,
                    temp.get(3).getDisplayer_y()));

            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(2));
            deletedShapePosition.add(temp.get(3));
        }

        return deletedShapePosition;
    }

    @Override
    public List<ShapePosition> moveToRight() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        if (shapeRotationPosition.get(0)) {

            shapeComponent.add(temp.get(1));
            shapeComponent.add(temp.get(2));
            shapeComponent.add(new ShapePosition(
                    temp.get(0).getDisplayer_x() + 3,
                    temp.get(0).getDisplayer_y()));
            shapeComponent.add(new ShapePosition(
                    temp.get(0).getDisplayer_x() + 2,
                    temp.get(0).getDisplayer_y() + 1));

            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(3));

        } else if (shapeRotationPosition.get(1)) {

            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 1,
                    temp.get(0).getDisplayer_y()));
            shapeComponent.add(temp.get(2));
            shapeComponent.add(new ShapePosition(temp.get(2).getDisplayer_x() + 1,
                    temp.get(2).getDisplayer_y()));
            shapeComponent.add(new ShapePosition(temp.get(3).getDisplayer_x() + 1,
                    temp.get(3).getDisplayer_y()));

            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(1));
            deletedShapePosition.add(temp.get(3));

        } else if (shapeRotationPosition.get(2)) {

            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 1,
                    temp.get(0).getDisplayer_y()));
            shapeComponent.add(temp.get(2));
            shapeComponent.add(temp.get(3));

            shapeComponent.add(new ShapePosition(temp.get(3).getDisplayer_x() + 1,
                    temp.get(3).getDisplayer_y()));

            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(1));

        } else if (shapeRotationPosition.get(3)) {

            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 1,
                    temp.get(0).getDisplayer_y()));
            shapeComponent.add(temp.get(2));
            shapeComponent.add(new ShapePosition(temp.get(2).getDisplayer_x() + 1,
                    temp.get(2).getDisplayer_y()));
            shapeComponent.add(new ShapePosition(temp.get(3).getDisplayer_x() + 1,
                    temp.get(3).getDisplayer_y()));

            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(1));
            deletedShapePosition.add(temp.get(3));

        }

        return deletedShapePosition;
    }

    @Override
    public List<ShapePosition> moveToDown() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        if (shapeRotationPosition.get(0)) {

            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x(),
                    temp.get(0).getDisplayer_y() + 1));
            shapeComponent.add(temp.get(3));
            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 2,
                    temp.get(0).getDisplayer_y() + 1));
            shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 1,
                    temp.get(0).getDisplayer_y() + 2));

            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(1));
            deletedShapePosition.add(temp.get(2));

        } else if (shapeRotationPosition.get(1)) {

            shapeComponent.add(temp.get(2));
            shapeComponent.add(new ShapePosition(temp.get(2).getDisplayer_x() - 1,
                    temp.get(2).getDisplayer_y() + 1));
            shapeComponent.add(temp.get(3));
            shapeComponent.add(new ShapePosition(temp.get(3).getDisplayer_x(),
                    temp.get(3).getDisplayer_y() + 1));
            deletedShapePosition.add(temp.get(1));
            deletedShapePosition.add(temp.get(0));
           

        } else if (shapeRotationPosition.get(2)) {

            shapeComponent.add(temp.get(2));

            shapeComponent.add(new ShapePosition(temp.get(1).getDisplayer_x(),
                    temp.get(1).getDisplayer_y() + 1));
            shapeComponent.add(new ShapePosition(temp.get(2).getDisplayer_x(),
                    temp.get(2).getDisplayer_y() + 1));
            shapeComponent.add(new ShapePosition(temp.get(3).getDisplayer_x(),
                    temp.get(3).getDisplayer_y() + 1));

            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(1));
            deletedShapePosition.add(temp.get(3));

        } else if (shapeRotationPosition.get(3)) {

            shapeComponent.add(temp.get(1));
            shapeComponent.add(temp.get(3));
            shapeComponent.add(new ShapePosition(temp.get(3).getDisplayer_x() + 1,
                    temp.get(3).getDisplayer_y()));
            shapeComponent.add(new ShapePosition(temp.get(3).getDisplayer_x(),
                    temp.get(3).getDisplayer_y() + 1));
            deletedShapePosition.add(temp.get(0));
            deletedShapePosition.add(temp.get(2));
        }

        return deletedShapePosition;
    }

}
