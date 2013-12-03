/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class report_product_import_other_tool_summary extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("today") != null) {
                today(request, response);
            } else if (request.getParameter("day") != null) {
                day(request, response);
            } else if (request.getParameter("month") != null) {
                month(request, response);
            } else if (request.getParameter("year") != null) {
                year(request, response);
            } else if (request.getParameter("other_tool_type") != null) {
                other_tool_type(request, response);
            } else if (request.getParameter("other_tool") != null) {
                other_tool(request, response);
            } else if (request.getParameter("employee") != null) {
                employee(request, response);
            } else if (request.getParameter("supplier") != null) {
                supplier(request, response);
            }
        } catch (Exception e) {
            out.print(e);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   
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

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void today(HttpServletRequest request, HttpServletResponse response) {
       
    }

    private void day(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void month(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void year(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void other_tool_type(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void other_tool(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void employee(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void supplier(HttpServletRequest request, HttpServletResponse response) {
        
    }

}
