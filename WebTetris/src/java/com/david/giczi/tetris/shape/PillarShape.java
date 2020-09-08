package com.david.giczi.tetris.shape;

import com.david.giczi.tetris.model.ShapePosition;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GicziD
 */
public class PillarShape extends AbstractShape {

    public PillarShape(ShapePosition starterPosition, String shapeColor) {
        this.shapeColor = shapeColor;
        createShape(starterPosition);
    }

    @Override
    public final void createShape(ShapePosition starterPosition) {
        this.shapeRotationPosition = super.initShapeRotationPosition();
        shapeComponent.add(new ShapePosition(starterPosition.getDisplayer_x() - 2, starterPosition.getDisplayer_y()));
        shapeComponent.add(new ShapePosition(starterPosition.getDisplayer_x() - 1, starterPosition.getDisplayer_y()));
        shapeComponent.add(starterPosition);
        shapeComponent.add(new ShapePosition(starterPosition.getDisplayer_x() + 1, starterPosition.getDisplayer_y()));
    }

    @Override
    public List<ShapePosition> moveToLeft() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        if (shapeRotationPosition.get(0)) {

            shapeComponent.add(new ShapePosition(
                    temp.get(0).getDisplayer_x() - 1, temp.get(0).getDisplayer_y()));
            for (int i = 0; i < temp.size(); i++) {

                if (i < temp.size() - 1) {
                    shapeComponent.add(temp.get(i));
                } else {
                    deletedShapePosition.add(temp.get(i));
                }

            }

        } else if (shapeRotationPosition.get(1)) {

            temp.forEach(component -> shapeComponent.add(
                    new ShapePosition(component.getDisplayer_x() - 1,
                            component.getDisplayer_y())));
            deletedShapePosition = temp;

        }

        return deletedShapePosition;
    }

    @Override
    public List<ShapePosition> moveToRight() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        if (shapeRotationPosition.get(0)) {

            for (int i = 0; i < temp.size(); i++) {

                if (i == 0) {

                    deletedShapePosition.add(temp.get(i));
                } else {

                    shapeComponent.add(temp.get(i));
                }

            }
            shapeComponent.add(new ShapePosition(
                    temp.get(temp.size() - 1).getDisplayer_x() + 1,
                    temp.get(temp.size() - 1).getDisplayer_y()));

        } else if (shapeRotationPosition.get(1)) {

            temp.forEach(component -> shapeComponent.add(
                    new ShapePosition(component.getDisplayer_x() + 1,
                            component.getDisplayer_y())));
            deletedShapePosition = temp;

        }

        return deletedShapePosition;
    }

    @Override
    public List<ShapePosition> moveToDown() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        if (shapeRotationPosition.get(0)) {

            temp.forEach(component -> shapeComponent.add(
                    new ShapePosition(component.getDisplayer_x(),
                            component.getDisplayer_y() + 1)));
            deletedShapePosition = temp;
        } else if (shapeRotationPosition.get(1)) {

            for (int i = 0; i < temp.size(); i++) {

                if (i == 0) {

                    deletedShapePosition.add(temp.get(i));
                } else {

                    shapeComponent.add(temp.get(i));
                }
            }

            shapeComponent.add(new ShapePosition(
                    temp.get(temp.size() - 1).getDisplayer_x(),
                    temp.get(temp.size() - 1).getDisplayer_y() + 1));

        }

        return deletedShapePosition;
    }

    @Override
    public List<ShapePosition> rotateShape() {

        List<ShapePosition> deletedShapePosition = new ArrayList<>();
        List<ShapePosition> temp = new ArrayList<>(shapeComponent);
        shapeComponent.clear();

        if (shapeRotationPosition.get(0)) {

            for (int i = 0; i < temp.size(); i++) {

                if (i == 0) {
                    shapeComponent.add(temp.get(i));
                } else {
                    int y = temp.get(0).getDisplayer_y() + i;
                    shapeComponent.add(new ShapePosition(temp.get(0).getDisplayer_x(), y));
                }
            }

            for (int i = 0; i < temp.size(); i++) {
                if (i != 0) {
                    deletedShapePosition.add(temp.get(i));
                }
            }

            shapeRotationPosition.set(0, Boolean.FALSE);
            shapeRotationPosition.set(1, Boolean.TRUE);

        } else if (shapeRotationPosition.get(1)) {

            for (int i = 0; i < temp.size(); i++) {

                if (i == 0) {
                    shapeComponent.add(temp.get(i));
                } else {
                    int x = temp.get(0).getDisplayer_x() + i;
                    shapeComponent.add(new ShapePosition(x, temp.get(0).getDisplayer_y()));
                }

            }

            for (int i = 0; i < temp.size(); i++) {
                if (i != 0) {
                    deletedShapePosition.add(temp.get(i));
                }
            }

            shapeRotationPosition.set(0, Boolean.TRUE);
            shapeRotationPosition.set(1, Boolean.FALSE);

        }

        return deletedShapePosition;
    }

}
