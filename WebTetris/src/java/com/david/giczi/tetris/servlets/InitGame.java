
package com.david.giczi.tetris.servlets;

import com.david.giczi.tetris.model.ShapePosition;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GicziD
 */
@WebServlet(name = "InitGame", urlPatterns = {"/startGame"})
public class InitGame extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        request.setAttribute("row", ShapePosition.LENGTH_OF_BOARD - 1);
        request.setAttribute("col", ShapePosition.WIDTH_OF_BOARD - 1);
        request.getRequestDispatcher("gameboard.jsp").forward(request, response);
              
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
