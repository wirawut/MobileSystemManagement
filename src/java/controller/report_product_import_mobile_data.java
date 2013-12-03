/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class report_product_import_mobile_data extends HttpServlet {
    
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
            } else if (request.getParameter("mobile_type") != null) {
                mobile_type(request, response);
            } else if (request.getParameter("mobile") != null) {
                mobile(request, response);
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

    private void supplier(HttpServletRequest request, HttpServletResponse response) {
    }
 
    private void mobile(HttpServletRequest request, HttpServletResponse response) {
    }
    
    private void mobile_type(HttpServletRequest request, HttpServletResponse response) {
    }
    
    private void year(HttpServletRequest request, HttpServletResponse response) {
    }
    
    private void month(HttpServletRequest request, HttpServletResponse response) {
    }
    
    private void day(HttpServletRequest request, HttpServletResponse response) {
    }
    private void today(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\project\\cloud\\web\\report\\report_product_import_mobile_data_day.jrxml";
            Map parameters = new HashMap();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Admin/Desktop/รายงาน.pdf");
            //JasperViewer.viewReport(jasperPrint);
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/รายงาน.pdf");
            response.getWriter().print("complete");
            //c.close();
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }
}
