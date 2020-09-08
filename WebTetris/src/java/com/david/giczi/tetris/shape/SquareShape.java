package com.david.giczi.tetris.shape;

import com.david.giczi.tetris.model.ShapePosition;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GicziD
 */
public class SquareShape extends AbstractShape {

    public SquareShape(ShapePosition starterPosition, String shapeColor) {
        this.shapeColor = shapeColor;
        createShape(starterPosition);
    }

    @Override
    public final void createShape(ShapePosition starterPosition) {
        this.shapeRotationPosition = super.initShapeRotationPosition();
        shapeComponent.add(starterPosition);
        shapeComponent.add(new ShapePosition(
                starterPosition.getDisplayer_x() + 1,
                starterPosition.getDisplayer_y()));
        shapeComponent.add(new ShapePosition(
                starterPosition.getDisplayer_x(),
                starterPosition.getDisplayer_y() + 1));
        shapeComponent.add(new ShapePosition(
                starterPosition.getDisplayer_x() + 1,
                starterPosition.getDisplayer_y() + 1));
    }

    @Override
    public List<ShapePosition> rotateShape() {
        return new ArrayList<>();
    }

    @Override
    public List<ShapePosition> moveToLeft() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() - 1,
                temp.get(0).getDisplayer_y()));
        shapeComponent.add(temp.get(0));
        shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() - 1,
                temp.get(0).getDisplayer_y() + 1));
        shapeComponent.add(temp.get(2));

        deletedShapePosition.add(temp.get(1));
        deletedShapePosition.add(temp.get(3));

        return deletedShapePosition;
    }

    @Override
    public List<ShapePosition> moveToRight() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        shapeComponent.add(temp.get(1));
        shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 2,
                temp.get(0).getDisplayer_y()));
        shapeComponent.add(temp.get(3));
        shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 2,
                temp.get(0).getDisplayer_y() + 1));

        deletedShapePosition.add(temp.get(0));
        deletedShapePosition.add(temp.get(2));

        return deletedShapePosition;
    }

    @Override
    public List<ShapePosition> moveToDown() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        shapeComponent.add(temp.get(2));
        shapeComponent.add(temp.get(3));
        shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x(),
                temp.get(0).getDisplayer_y() + 2));
        shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x() + 1,
                temp.get(0).getDisplayer_y() + 2));

        deletedShapePosition.add(temp.get(0));
        deletedShapePosition.add(temp.get(1));

        return deletedShapePosition;
    }


}
