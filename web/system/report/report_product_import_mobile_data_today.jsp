<%-- 
    Document   : report_product_import_mobile_data_today
    Created on : 27 เม.ย. 2556, 13:10:29
    Author     : Admin
--%>

<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.Map"%>
<%@page import="net.sf.jasperreports.engine.JRParameter"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.view.JasperViewer"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    try {
        /*Connection c = (Connection) getServletContext().getAttribute("conn");
         String report = "report\\report_product_import_mobile_data_day.jrxml";
         JasperReport jr = JasperCompileManager.compileReport(report);
         JasperPrint jp = JasperFillManager.fillReport(jr, null, c);
         JasperViewer.viewReport(jp); */

       

    } catch (Exception e) {
        out.print(e);

    }
%>

