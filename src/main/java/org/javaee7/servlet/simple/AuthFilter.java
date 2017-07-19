package org.javaee7.servlet.simple;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by student on 17.07.17.
 */
//Put your answer here
@WebFilter(filterName = "Filter", urlPatterns = { "/add" })
//End of answer
public class AuthFilter  implements Filter{
    //Put your answer here
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out=servletResponse.getWriter();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html");

        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length<=1){
            if(request.getParameter("pw")==null){
                out.print("Missing password");
            }
            else if(request.getParameter("pw").equals("passwd")){
                Cookie tmp = new Cookie("password","passwd");
                response.addCookie(tmp);
                filterChain.doFilter(request,response);
            }else{
                out.print("Wrong password");
            }

        }else{
            for (Cookie ck : cookies) {
                if ("password".equals(ck.getName()) && "passwd".equals(ck.getValue())) {
                    filterChain.doFilter(request, response);
                }
            }
        }


    }

    @Override
    public void destroy() {

    }
//End of answer
}
