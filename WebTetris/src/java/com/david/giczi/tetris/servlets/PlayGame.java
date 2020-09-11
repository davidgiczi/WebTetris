
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
        
         switch(req){
           
             case "left":
                 moveLeftShape(request, response);
                 break;
             case "right":
                 moveRightShape(request, response);
                 break;
             case "down":
                 moveDownShape(request, response);
                 break;
             case "rotate":
                 rotateShape(request, response);
                 break;
         }  
        
    }
    
    
    private synchronized void moveLeftShape(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        
        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("actual");
        
        if(logic.canShapeBeMovedToLeft(actualShape)){
        List<ShapePosition> deletedPosition = actualShape.moveToLeft();
        response.getWriter().append(createResponseString(actualShape.shapeComponent, deletedPosition));
        request.getSession().setAttribute("actual", actualShape);
        }
    }
    private synchronized void moveRightShape(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        
        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("actual");
        
        if(logic.canShapeBeMovedToRight(actualShape)){
        List<ShapePosition> deletedPosition = actualShape.moveToRight();
        response.getWriter().append(createResponseString(actualShape.shapeComponent, deletedPosition));
        request.getSession().setAttribute("actual", actualShape);
        }
    }
    
    private synchronized void moveDownShape(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        
        AbstractShape actualShape = (AbstractShape) request.getSession().getAttribute("actual");
        
        if(logic.canShapeBeMovedToDown(actualShape)){
        List<ShapePosition> deletedPosition = actualShape.moveToDown();
        response.getWriter().append(createResponseString(actualShape.shapeComponent, deletedPosition));
        request.getSession().setAttribute("actual", actualShape);
        }
        
    }
    
    
    private synchronized void rotateShape(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        
        AbstractShape shape = (AbstractShape) request.getSession().getAttribute("actual");
        
        if(logic.canShapeBeRotated(shape)){
        List<ShapePosition> deletedPosition = shape.rotateShape();
        response.getWriter().append(createResponseString(shape.shapeComponent, deletedPosition));
        request.getSession().setAttribute("actual", shape);
        }
    }
    
    private String createResponseString(List<ShapePosition> shapeComponent, List<ShapePosition> deletedPosition){
        
       StringBuilder build = new StringBuilder();
       shapeComponent.forEach(component -> build.append(component.getLogicBoardIndex()).append(","));
       deletedPosition.forEach(position -> build.append(position.getLogicBoardIndex()).append(","));
       
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
