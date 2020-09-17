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

        if (logic.canShapeBeMovedToDown(actualShape)) {

            moveDownShape(actualShape, request, response);

        } else {

            if (logic.isTheEndOfTheGame()) {

                sendTheEndOfTheGame(request, response);
            } else {

                sendResponseData(request, response);

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
            response.getWriter().append(createResponseString(actualShape, deletedPosition, "actual"));
            request.getSession().setAttribute("actual", actualShape);
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
            response.getWriter().append(createResponseString(actualShape, deletedPosition, "actual"));
            request.getSession().setAttribute("actual", actualShape);
        }
    }

    private synchronized void moveDownShape(AbstractShape actualShape, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<ShapePosition> deletedPosition = actualShape.moveToDown();
        List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
        logic.setLogicBoard(logicBoard);
        logic.addDeletedPositionToLogicBoard(deletedPosition);
        logic.addShapeToLogicBoard(actualShape);
        request.getSession().setAttribute("logicboard", logic.getLogicBoard());
        response.getWriter().append(createResponseString(actualShape, deletedPosition, "actual"));
        request.getSession().setAttribute("actual", actualShape);

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
            response.getWriter().append(createResponseString(actualShape, deletedPosition, "actual"));
            request.getSession().setAttribute("actual", actualShape);
        }
    }

    private synchronized void sendResponseData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        AbstractShape shape = (AbstractShape) request.getSession().getAttribute("actual");
        List<Boolean> logicBoard = (List<Boolean>) request.getSession().getAttribute("logicboard");
        logic.setLogicBoard(logicBoard);
        logic.calcScore(shape);
        request.getSession().setAttribute("score", logic.getScore());
        shape = (AbstractShape) request.getSession().getAttribute("next");
        shape.addShapeToGameBoard();
        logic.addShapeToLogicBoard(shape);
        request.getSession().setAttribute("actual", shape);
        String actualShapeString = createResponseString(shape, null, "actual");
        shape = shapeFactory.getShape();
        request.getSession().setAttribute("next", shape);
        String nextShapeString = createResponseString(shape, null, "next");
        int score = (int) request.getSession().getAttribute("score");
        String responseString = nextShapeString + "," + actualShapeString + "," + score;
        response.getWriter().append(responseString);
    }

    private String createResponseString(AbstractShape shape, List<ShapePosition> deletedPosition, String instruction) {

        StringBuilder build = new StringBuilder(instruction + ",");
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
