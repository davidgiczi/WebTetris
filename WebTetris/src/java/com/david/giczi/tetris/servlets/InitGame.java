
package com.david.giczi.tetris.servlets;

import com.david.giczi.tetris.model.ShapeFactory;
import com.david.giczi.tetris.model.ShapePosition;
import com.david.giczi.tetris.model.TetrisLogic;
import com.david.giczi.tetris.shape.AbstractShape;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "InitGame", urlPatterns = {"/start"})
public class InitGame extends HttpServlet {
    
    @EJB
    private ShapeFactory shapeFactory;
    @EJB
    private TetrisLogic logic;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        request.getSession().invalidate();
        
        initLogicBoard();
        
        AbstractShape actualShape = shapeFactory.getShape();
        actualShape.addShapeToGameBoard();
        request.getSession().setAttribute("actual", actualShape);
       
        logic.addShapeToLogicBoard(actualShape);
        request.getSession().setAttribute("logicboard", logic.getLogicBoard());
        
        List<AbstractShape> shapeStore = new ArrayList<>();
        shapeStore.add(actualShape);
        request.getSession().setAttribute("shapestore", shapeStore);
        
        AbstractShape nextShape = shapeFactory.getShape();
        request.getSession().setAttribute("next", nextShape);
        
        logic.setScore(0);
        request.getSession().setAttribute("score", logic.getScore());
         
        request.setAttribute("actual", createResponseString(actualShape, "actual"));
        request.setAttribute("next", createResponseString(nextShape, "next"));
        request.setAttribute("row", ShapePosition.LENGTH_OF_BOARD);
        request.setAttribute("col", ShapePosition.WIDTH_OF_BOARD);
           
        request.getRequestDispatcher("gameboard.jsp").forward(request, response);
              
    }
   
    private void initLogicBoard(){
        List<Boolean> logicBoard = new ArrayList<>();
        logic.setLogicBoard(logicBoard);
        logic.initLogicBoard();
    }
     
    private String createResponseString(AbstractShape shape, String instruction){
        
       StringBuilder build = new StringBuilder(instruction+",");
       build.append(shape.shapeColor)
            .append(",");
       shape.shapeComponent.forEach(component -> build.append(component.getLogicBoardIndex()).append(","));
     
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
