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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class report_data extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("today") != null) {
                today(request, response);
            } else if (request.getParameter("show_today") != null) {
                show_today(request, response);
            } else if (request.getParameter("day") != null) {
                day(request, response);
            } else if (request.getParameter("month") != null) {
                month(request, response);
            } else if (request.getParameter("year") != null) {
                year(request, response);
            } else if (request.getParameter("employee") != null) {
                employee(request, response);
            } else if (request.getParameter("product") != null) {
                product(request, response);
            } else if (request.getParameter("type") != null) {
                type(request, response);
            } else if (request.getParameter("customer") != null) {
                customer(request, response);
            } else if (request.getParameter("forecast") != null) {
                forecast(request, response);
            } else if (request.getParameter("show_print_today") != null) {
                show_print_today(request, response);
            } else if (request.getParameter("show_print_day") != null) {
                show_print_day(request, response);
            } else if (request.getParameter("show_print_month") != null) {
                show_print_month(request, response);
            } else if (request.getParameter("show_print_year") != null) {
                show_print_year(request, response);
            } else if (request.getParameter("show_print_employee") != null) {
                show_print_employee(request, response);
            } else if (request.getParameter("show_print_product") != null) {
                show_print_product(request, response);
            } else if (request.getParameter("show_print_type") != null) {
                show_print_type(request, response);
            } else if (request.getParameter("show_print_customer") != null) {
                show_print_customer(request, response);
            }else if (request.getParameter("show_print_forecast") != null) {
                show_print_forecast(request, response);
            }


        } catch (Exception e) {
            out.print(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void today(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            //String sale_id = request.getParameter("sale_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\report\\report_data_today.jrxml";
            Map parameters = new HashMap();

            JasperDesign jd = JRXmlLoader.load(reportFileName);
            String sql = "SELECT * , ROUND(( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale = curdate() "
                    + "),2) AS price_total, ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.date_sale = curdate() ) + ( (SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.date_sale = curdate() ) * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ))) , 2) AS price_end, ROUND((( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale = curdate() "
                    + ") * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_tax ,ROUND(( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_of_tax ,ROUND((tb_sale_detail.price_sum +( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ))),2) AS price_all_row "
                    + "FROM (((((tb_sale_product "
                    + "JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id) "
                    + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                    + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                    + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id) "
                    + "WHERE tb_sale_detail.date_sale = curdate() ORDER BY tb_sale_product.user_id ASC";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\show_report\\data_today.pdf");
            // Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/รายงานข้อมูลยอดขายประจำวัน.pdf");
            //response.getWriter().print("complete");
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }

    private void day(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String date_start = request.getParameter("date_start");
            String date_end = request.getParameter("date_end");
            //String sale_id = request.getParameter("sale_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\report\\report_data_day.jrxml";
            Map parameters = new HashMap();

            JasperDesign jd = JRXmlLoader.load(reportFileName);
            String sql = "SELECT * ,ROUND(( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale BETWEEN '" + date_start + "' AND  '" + date_end + "' "
                    + "),2) AS price_total, ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.date_sale BETWEEN '" + date_start + "' AND  '" + date_end + "') + ( (SELECT SUM( price_sum ) FROM tb_sale_detail  WHERE tb_sale_detail.date_sale BETWEEN '" + date_start + "' AND  '" + date_end + "') * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ))) , 2) AS price_end, ROUND((( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale BETWEEN '" + date_start + "' AND  '" + date_end + "' "
                    + ") * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_tax ,ROUND(( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_of_tax ,ROUND((tb_sale_detail.price_sum +( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ))),2) AS price_all_row "
                    + "FROM (((((tb_sale_product "
                    + "JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id) "
                    + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                    + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                    + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id) "
                    + "WHERE tb_sale_detail.date_sale BETWEEN '" + date_start + "' AND  '" + date_end + "' "
                    + "ORDER BY tb_sale_detail.date_sale ";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\show_report\\data_day.pdf");
            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/รายงานข้อมูลยอดขายตามวัน.pdf");
            //response.getWriter().print("complete");
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }

    private void month(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String month = request.getParameter("m");
            int m = Integer.parseInt(month);
            //response.getWriter().print("ค่าที่ส่งมา"+month);
            //String sale_id = request.getParameter("sale_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\report\\report_data_month.jrxml";
            Map parameters = new HashMap();

            JasperDesign jd = JRXmlLoader.load(reportFileName);
            String sql = "";

            if ((m >= 0) && (m <= 9)) {
                String mm = "0" + m;
                sql = "SELECT * ,ROUND(( "
                        + "SELECT SUM( price_sum ) "
                        + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%' "
                        + "),2) AS price_total, ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%') + ( (SELECT SUM( price_sum ) FROM tb_sale_detail  WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%') * ( "
                        + "SELECT tb_sale_detail.tax_rate /100 ))) , 2) AS price_end, ROUND((( "
                        + "SELECT SUM( price_sum ) "
                        + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%' "
                        + ") * ( "
                        + "SELECT tb_sale_detail.tax_rate /100 ) "
                        + "),2) AS price_tax ,ROUND(( "
                        + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ) "
                        + "),2) AS price_of_tax ,ROUND((tb_sale_detail.price_sum +( "
                        + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ))),2) AS price_all_row "
                        + "FROM (((((tb_sale_product "
                        + "JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id) "
                        + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                        + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                        + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                        + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id) "
                        + "WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%'";
                response.getWriter().print(sql);
            } else if ((m >= 10)) {
                String mm = "" + m;

                sql = "SELECT * ,ROUND(( "
                        + "SELECT SUM( price_sum ) "
                        + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%'"
                        + "),2) AS price_total, ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%') + ( (SELECT SUM( price_sum ) FROM tb_sale_detail  WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%') * ( "
                        + "SELECT tb_sale_detail.tax_rate /100 ))) , 2) AS price_end, ROUND((( "
                        + "SELECT SUM( price_sum ) "
                        + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%' "
                        + ") * ( "
                        + "SELECT tb_sale_detail.tax_rate /100 ) "
                        + "),2) AS price_tax ,ROUND(( "
                        + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ) "
                        + "),2) AS price_of_tax ,ROUND((tb_sale_detail.price_sum +( "
                        + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ))),2) AS price_all_row "
                        + "FROM (((((tb_sale_product "
                        + "JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id) "
                        + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                        + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                        + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                        + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id) "
                        + "WHERE tb_sale_detail.date_sale LIKE '%-" + mm + "-%'";
                response.getWriter().print(sql);
            }




            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\show_report\\data_month.pdf");
            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/รายงานข้อมูลยอดขายตามเดือน.pdf");
            //response.getWriter().print("complete");
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }

    private void year(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String year = request.getParameter("y");
            int yy = Integer.parseInt(year);
            //response.getWriter().print("ค่าที่ส่งมา"+month);
            //String sale_id = request.getParameter("sale_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\report\\report_data_year.jrxml";
            Map parameters = new HashMap();

            JasperDesign jd = JRXmlLoader.load(reportFileName);
            String sql = "SELECT * ,ROUND(( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale LIKE '" + yy + "-%' "
                    + "),2) AS price_total, ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.date_sale LIKE '" + yy + "-%') + ( (SELECT SUM( price_sum ) FROM tb_sale_detail  WHERE tb_sale_detail.date_sale LIKE '" + yy + "-%') * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ))) , 2) AS price_end, ROUND((( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail WHERE tb_sale_detail.date_sale LIKE '" + yy + "-%' "
                    + ") * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_tax ,ROUND(( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_of_tax ,ROUND((tb_sale_detail.price_sum +( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ))),2) AS price_all_row "
                    + "FROM (((((tb_sale_product "
                    + "JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id) "
                    + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                    + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                    + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id) "
                    + "WHERE tb_sale_detail.date_sale LIKE '" + yy + "-%'";
            response.getWriter().print(sql);





            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\show_report\\data_year.pdf");
            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/รายงานข้อมูลยอดขายตามปี.pdf");
            //response.getWriter().print("complete");
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }

    private void employee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String employee = request.getParameter("e");
            // int ee = Integer.parseInt( employee);
            // response.getWriter().print("ค่าที่ส่งมา"+employee);
            //String sale_id = request.getParameter("sale_id");




            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\report\\report_data_employee.jrxml";
            Map parameters = new HashMap();

            JasperDesign jd = JRXmlLoader.load(reportFileName);
            String sql = "SELECT * ,ROUND(( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail JOIN tb_sale_product ON tb_sale_detail.sale_id = tb_sale_product.sale_id WHERE tb_sale_product.user_id = '" + employee + "' "
                    + "),2) AS price_total, ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail JOIN tb_sale_product ON tb_sale_detail.sale_id = tb_sale_product.sale_id WHERE tb_sale_product.user_id = '" + employee + "') + ( (SELECT SUM( price_sum ) FROM tb_sale_detail JOIN tb_sale_product ON tb_sale_detail.sale_id = tb_sale_product.sale_id WHERE tb_sale_product.user_id = '" + employee + "') * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ))) , 2) AS price_end, ROUND((( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail JOIN tb_sale_product ON tb_sale_detail.sale_id = tb_sale_product.sale_id WHERE tb_sale_product.user_id = '" + employee + "' "
                    + ") * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_tax ,ROUND(( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_of_tax ,ROUND((tb_sale_detail.price_sum +( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ))),2) AS price_all_row "
                    + "FROM (((((tb_sale_product "
                    + "JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id) "
                    + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                    + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                    + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id) "
                    + "WHERE tb_sale_product.user_id = '" + employee + "'";
            response.getWriter().print(sql);

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\show_report\\data_employee.pdf");
            // Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/รายงานข้อมูลยอดขายตามพนักงาน.pdf");
            //response.getWriter().print("complete");
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }

    private void product(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String product = request.getParameter("p");
            // int ee = Integer.parseInt( employee);
            //response.getWriter().print("ค่าที่ส่งมา"+product);
            //String sale_id = request.getParameter("sale_id");


            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\report\\report_data_product.jrxml";
            Map parameters = new HashMap();

            JasperDesign jd = JRXmlLoader.load(reportFileName);
            String sql = "SELECT * , ROUND(( "
                    + "SELECT SUM( price_sum )FROM tb_sale_detail WHERE tb_sale_detail.mobile_id = '" + product + "'"
                    + "),2) AS price_total, ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.mobile_id = '" + product + "') + ( (SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.mobile_id = '" + product + "') * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ))) , 2) AS price_end, ROUND((( "
                    + "SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.mobile_id = '" + product + "') * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 )),2) AS price_tax ,ROUND((tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_of_tax ,ROUND((tb_sale_detail.price_sum +( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ))),2) AS price_all_row "
                    + "FROM (((((tb_sale_product JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id) "
                    + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                    + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                    + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id) "
                    + "WHERE tb_sale_detail.mobile_id = '" + product + "' ";
            response.getWriter().print(sql);

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\show_report\\data_product.pdf");
            // Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/รายงานข้อมูลยอดขายตามสินค้า.pdf");
            //response.getWriter().print("complete");
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }

    private void type(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String type = request.getParameter("t");
            // int ee = Integer.parseInt( employee);
            response.getWriter().print("ค่าที่ส่งมา" + type);
            //String sale_id = request.getParameter("sale_id");


            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\report\\report_data_type.jrxml";
            Map parameters = new HashMap();

            JasperDesign jd = JRXmlLoader.load(reportFileName);
            String sql = "SELECT * , ROUND(( "
                    + "SELECT SUM( price_sum )FROM tb_sale_detail WHERE tb_sale_detail.mobile_type = '" + type + "'"
                    + "),2) AS price_total, ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.mobile_type= '" + type + "') + ( (SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.mobile_type = '" + type + "') * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ))) , 2) AS price_end, ROUND((( "
                    + "SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.mobile_type = '" + type + "') * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 )),2) AS price_tax ,ROUND((tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_of_tax ,ROUND((tb_sale_detail.price_sum +( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ))),2) AS price_all_row "
                    + "FROM (((((tb_sale_product JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id) "
                    + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                    + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                    + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id) "
                    + "WHERE tb_sale_detail.mobile_type = '" + type + "' ";
            response.getWriter().print(sql);

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\show_report\\data_type.pdf");
            // Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/รายงานข้อมูลยอดขายตามประเภทสินค้า.pdf");
            //response.getWriter().print("complete");
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }

    private void show_today(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/today.pdf' type='application/pdf' width='750' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_print_today(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/data_today.pdf' type='application/pdf' width='1260' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_print_day(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/data_day.pdf' type='application/pdf' width='1260' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_print_month(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/data_month.pdf' type='application/pdf' width='1260' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_print_year(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/data_year.pdf' type='application/pdf' width='1260' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_print_employee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/data_employee.pdf' type='application/pdf' width='1260' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_print_product(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/data_product.pdf' type='application/pdf' width='1260' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_print_type(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/data_type.pdf' type='application/pdf' width='1260' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void customer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String customer = request.getParameter("c");
            // int ee = Integer.parseInt( employee);
            // response.getWriter().print("ค่าที่ส่งมา"+employee);
            //String sale_id = request.getParameter("sale_id");




            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\report\\report_data_customer.jrxml";
            Map parameters = new HashMap();

            JasperDesign jd = JRXmlLoader.load(reportFileName);
            String sql = "SELECT * ,ROUND(( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail JOIN tb_sale_product ON tb_sale_detail.sale_id = tb_sale_product.sale_id WHERE tb_sale_product.customer_id = '" + customer + "' "
                    + "),2) AS price_total, ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail JOIN tb_sale_product ON tb_sale_detail.sale_id = tb_sale_product.sale_id WHERE tb_sale_product.customer_id = '" + customer + "') + ( (SELECT SUM( price_sum ) FROM tb_sale_detail JOIN tb_sale_product ON tb_sale_detail.sale_id = tb_sale_product.sale_id WHERE tb_sale_product.customer_id = '" + customer + "') * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ))) , 2) AS price_end, ROUND((( "
                    + "SELECT SUM( price_sum ) "
                    + "FROM tb_sale_detail JOIN tb_sale_product ON tb_sale_detail.sale_id = tb_sale_product.sale_id WHERE tb_sale_product.customer_id = '" + customer + "' "
                    + ") * ( "
                    + "SELECT tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_tax ,ROUND(( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ) "
                    + "),2) AS price_of_tax ,ROUND((tb_sale_detail.price_sum +( "
                    + "tb_sale_detail.price_sum * ( tb_sale_detail.tax_rate /100 ))),2) AS price_all_row "
                    + "FROM (((((tb_sale_product "
                    + "JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id) "
                    + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                    + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                    + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id) "
                    + "WHERE tb_sale_product.customer_id = '" + customer + "'";
            response.getWriter().print(sql);

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\show_report\\data_customer.pdf");
            // Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/รายงานข้อมูลยอดขายตามพนักงาน.pdf");
            //response.getWriter().print("complete");
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }

    private void show_print_customer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/data_customer.pdf' type='application/pdf' width='1260' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void forecast(HttpServletRequest request, HttpServletResponse response) throws IOException {
         Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\sales_forecasting_system\\dist\\sales_forecasting_system.jar");
    }

    private void show_print_forecast(HttpServletRequest request, HttpServletResponse response) throws IOException {
         try {
            StringBuffer str = new StringBuffer();
      
             str.append("<center>");
            str.append("<object data='sales_forecasting_system/dist/sales_forecasting_system.jar' type='application/pdf' width='1260' height='750'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
            str.append("</center>");
           
            
           

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }
}
