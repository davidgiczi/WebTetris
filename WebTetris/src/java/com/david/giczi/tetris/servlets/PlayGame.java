package com.david.giczi.tetris.servlets;

import com.david.giczi.tetris.model.ShapeFactory;
import com.david.giczi.tetris.model.ShapePosition;
import com.david.giczi.tetris.model.TetrisLogic;
import com.david.giczi.tetris.shape.AbstractShape;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GicziD
 */
@WebServlet(name = "PlayGame", urlPatterns = {"/play"})
public class PlayGame extends HttpServlet {

    @EJB
    private TetrisLogic logic;
    @EJB
    private ShapeFactory shapeFactory;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String req = request.getParameter("move");

        switch (req) {

            case "left":
                moveLeftShape(request, response);
                break;
            case "right":
                moveRightShape(request, response);
                break;
            case "step":
                playGame(request, response);
                break;
            case "rotate":
                rotateShape(request, response);
                break;
        }

    }

    private void playGame(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("actual");
        List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
        logic.setLogicBoard(logicBoard);

        if (logic.canShapeBeMovedToDown(actualShape)) {

            moveDownShape(actualShape, request, response);

        } else {

            logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
            logic.setLogicBoard(logicBoard);
            List<Integer> fullRowIndex = logic.getCompleteTrueRowsIndex();

            if (!fullRowIndex.isEmpty()) {

                runFullRowProcess(request, response);
                return;
            }

            if (logic.isTheEndOfTheGame()) {

                sendTheEndOfTheGame(request, response);
            } else {

                turnNextShapeIntoActualShape(request, response);

            }

        }

    }

    private void sendTheEndOfTheGame(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("actual");
        response.getWriter().append(createResponseString(actualShape, null, "endofgame"));
    }

    private synchronized void moveLeftShape(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("actual");

        if (logic.canShapeBeMovedToLeft(actualShape)) {
            List<ShapePosition> deletedPosition = actualShape.moveToLeft();
            List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
            logic.setLogicBoard(logicBoard);
            logic.addDeletedPositionToLogicBoard(deletedPosition);
            logic.addShapeToLogicBoard(actualShape);
            request.getSession().setAttribute("logicboard", logic.getLogicBoard());
            request.getSession().setAttribute("actual", actualShape);
            response.getWriter().append(createResponseString(actualShape, deletedPosition, "actual"));
        }
    }

    private synchronized void moveRightShape(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("actual");

        if (logic.canShapeBeMovedToRight(actualShape)) {
            List<ShapePosition> deletedPosition = actualShape.moveToRight();
            List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
            logic.setLogicBoard(logicBoard);
            logic.addDeletedPositionToLogicBoard(deletedPosition);
            logic.addShapeToLogicBoard(actualShape);
            request.getSession().setAttribute("logicboard", logic.getLogicBoard());
            request.getSession().setAttribute("actual", actualShape);
            response.getWriter().append(createResponseString(actualShape, deletedPosition, "actual"));
        }
    }

    private synchronized void moveDownShape(AbstractShape actualShape, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<ShapePosition> deletedPosition = actualShape.moveToDown();
        List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
        logic.setLogicBoard(logicBoard);
        logic.addDeletedPositionToLogicBoard(deletedPosition);
        logic.addShapeToLogicBoard(actualShape);
        request.getSession().setAttribute("logicboard", logic.getLogicBoard());
        request.getSession().setAttribute("actual", actualShape);
        response.getWriter().append(createResponseString(actualShape, deletedPosition, "actual"));
    }

    private synchronized void rotateShape(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("actual");

        if (logic.canShapeBeRotated(actualShape)) {
            List<ShapePosition> deletedPosition = actualShape.rotateShape();
            List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
            logic.setLogicBoard(logicBoard);
            logic.addDeletedPositionToLogicBoard(deletedPosition);
            logic.addShapeToLogicBoard(actualShape);
            request.getSession().setAttribute("logicboard", logic.getLogicBoard());
            request.getSession().setAttribute("actual", actualShape);
            response.getWriter().append(createResponseString(actualShape, deletedPosition, "actual"));
        }
    }

    private void calcScore(HttpServletRequest request) {

        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("actual");
        List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
        int score = (int) request.getSession().getAttribute("score");
        logic.setScore(score);
        logic.setLogicBoard(logicBoard);
        logic.calcScore(actualShape);
        request.getSession().setAttribute("score", logic.getScore());

    }

    private synchronized void turnNextShapeIntoActualShape(HttpServletRequest request, HttpServletResponse response) throws IOException {

        calcScore(request);
        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("next");
        List<AbstractShape> shapeStore = (List<AbstractShape>) request.getSession().getAttribute("shapestore");
        shapeStore.add(actualShape);
        request.getSession().setAttribute("shapestore", shapeStore);
        List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
        logic.setLogicBoard(logicBoard);
        actualShape.addShapeToGameBoard();
        logic.addShapeToLogicBoard(actualShape);
        AbstractShape nextShape = shapeFactory.getShape();
        request.getSession().setAttribute("next", nextShape);
        request.getSession().setAttribute("logicboard", logic.getLogicBoard());
        request.getSession().setAttribute("actual", actualShape);
        int score = (int) request.getSession().getAttribute("score");
        String responseString = createResponseString(nextShape, null, "next")
                + "," + createResponseString(actualShape, null, "actual") + "," + score;

        response.getWriter().append(responseString);
    }

    private synchronized void runFullRowProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
        List<AbstractShape> shapeStore = (List<AbstractShape>) request.getSession().getAttribute("shapestore");
        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("next");
        AbstractShape nextShape = shapeFactory.getShape();
        int score = (int) request.getSession().getAttribute("score");

        logic.setLogicBoard(logicBoard);
        logic.setShapeStore(shapeStore);
        List<Integer> fullRowIndex = logic.getCompleteTrueRowsIndex();
        logic.setScore(score + 100 * fullRowIndex.size());
        logic.deleteCompleteTrueRowsFromShapeComponent(fullRowIndex);
        logic.increaseRowNumberForShapeComponentInShapeStore(fullRowIndex);
        logic.refreshLogicBoard();

        shapeStore.add(actualShape);
        actualShape.addShapeToGameBoard();
        logic.addShapeToLogicBoard(actualShape);

        request.getSession().setAttribute("score", logic.getScore());
        request.getSession().setAttribute("logicboard", logic.getLogicBoard());
        request.getSession().setAttribute("shapestore", logic.getShapeStore());
        request.getSession().setAttribute("actual", actualShape);
        request.getSession().setAttribute("next", nextShape);

        String responseString = createResponseString(nextShape, null, "fullrow")
                + "," + createResponseString(actualShape, null, "actual") + "," + logic.getScore();
        responseString = fullRowIndex.stream().map((index) -> "," + index).reduce(responseString, String::concat);

        response.getWriter().append(responseString);

    }

    private String createResponseString(AbstractShape shape, List<ShapePosition> deletedPosition, String instruction) {

        StringBuilder build = new StringBuilder();
        if (!instruction.isEmpty()) {
            build.append(instruction);
            build.append(",");
        }
        build.append(shape.shapeColor)
                .append(",");
        shape.shapeComponent.forEach(component -> build.append(component.getLogicBoardIndex()).append(","));
        if (deletedPosition != null) {
            deletedPosition.forEach(component -> build.append(component.getLogicBoardIndex()).append(","));
        }

        return build.toString().substring(0, build.toString().length() - 1);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
