package org.javaee7.servlet.simple;

import org.omg.DynamicAny.NameValuePair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Arun Gupta
 */
@WebServlet("/paste")
public class PasteServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        //Put your answer here

//        PrintWriter out = response.getWriter();
//        Map<String, Paste> mape = (Map<String, Paste>) request.getServletContext().getAttribute("mapa");
//        Paste wklejka = mape.get(request.getParameter("id"));
//        out.print(wklejka.getTitle()+wklejka.getContent());


        //End of answer
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        //Put your answer her
//        Map<String, Paste> mapOfPaste = new HashMap<String, Paste>();
//        String key;
//
//        PrintWriter out = response.getWriter();
//        String title = request.getParameter("title");
//        String content = request.getParameter("content");
//
//        Paste tmp = new Paste(title,content);
//        Date data = new Date();
//        key = "paste-"+data.getTime();
//
//        mapOfPaste.put(key,tmp);
//        request.getServletContext().setAttribute("mapa",mapOfPaste);
//        out.println("<a href=\"paste?id="+key+"\">"+key+"</a>");

        //End of answer
    }



}