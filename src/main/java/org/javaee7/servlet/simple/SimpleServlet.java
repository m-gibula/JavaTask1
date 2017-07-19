package org.javaee7.servlet.simple;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Arun Gupta
 */
@WebServlet("/add")
public class SimpleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        //Put your answer here

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("   <head>");
        out.println("       <title></title>");
        out.println("   </head>");
        out.println("   <body>" +
                "");
        out.println("       <form name = \"form\" action = \"add\" method = \"POST\">");
        out.println("           <input name = \"title\" type = \"text\"/>");
        out.println("           <textarea name = \"content\"></textarea>");
        out.println("           <input name = \"submit\" type = \"submit\"/>");
        out.println("       </form>");
        out.println("   </body>");
        out.println("</html>");


        //End of answer
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        //Put your answer here

        RequestDispatcher rd = request.getRequestDispatcher("/paste");
        rd.forward(request,response);


        //End of answer
    }



}