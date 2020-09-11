
package com.david.giczi.tetris.servlets;

import com.david.giczi.tetris.model.ShapeFactory;
import com.david.giczi.tetris.model.ShapePosition;
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
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        AbstractShape actualShape = shapeFactory.getShape();
        AbstractShape nextShape = shapeFactory.getShape();
        actualShape.addShapeToGameBoard();
        request.setAttribute("actualshape", getShapeComponentPosition(actualShape.shapeComponent));
        request.setAttribute("nextshape", getShapeComponentPosition(nextShape.shapeComponent));
        request.getSession().setAttribute("actual", actualShape);
        request.getSession().setAttribute("next", nextShape);
        request.setAttribute("actualshapecolor", actualShape.shapeColor);
        request.setAttribute("nextshapecolor", nextShape.shapeColor);
        request.setAttribute("row", ShapePosition.LENGTH_OF_BOARD);
        request.setAttribute("col", ShapePosition.WIDTH_OF_BOARD);
        request.getRequestDispatcher("gameboard.jsp").forward(request, response);
              
    }

    private List<Integer> getShapeComponentPosition(List<ShapePosition> shapeComponent){
        List<Integer> shapePosition = new ArrayList<>();
        
        shapeComponent.forEach(component -> shapePosition.add(component.getLogicBoardIndex()));
        
        return shapePosition;
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
