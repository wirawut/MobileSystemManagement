package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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

public class option_report extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("get_option_year") != null) {
                get_option_year(request, response);
            } else if (request.getParameter("get_option_month") != null) {
                get_option_month(request, response);
            } else if (request.getParameter("get_option_employee") != null) {
                get_option_employee(request, response);
            } else if (request.getParameter("get_option_type") != null) {
                get_option_type(request, response);
            } else if (request.getParameter("get_option_product") != null) {
                get_option_product(request, response);
            } else if (request.getParameter("get_option_customer") != null) {
                get_option_customer(request, response);
            } else if (request.getParameter("get_option_forecast_start") != null) {
                get_option_forecast_start(request, response);
            } else if (request.getParameter("get_option_forecast_end") != null) {
                get_option_forecast_end(request, response);
            }
        } catch (Exception e) {
            response.getWriter().print(e);
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

    private void get_option_year(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String[] arr_year = {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
            StringBuffer str = new StringBuffer();
            int j = (arr_year.length) - 1;

            str.append("<select name='year' id='year' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = ''>-----กรุณาเลือก-----</option>");
            for (int i = 0; i <= j; i++) {
                str.append("<option class = 'special' value='" + (1990 + i) + "'>" + arr_year[i]);
                str.append("</option>");
            }
            //str.append("</optgroup>");
            str.append("</select>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_option_month(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String[] arr_year = {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
            StringBuffer str = new StringBuffer();
            int j = (arr_year.length) - 1;

            str.append("<select name='month' id='month' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = ''>-----กรุณาเลือก-----</option>");
            for (int i = 0; i <= j; i++) {
                str.append("<option class = 'special'  value='" + (i + 1) + "'>" + arr_year[i]);
                str.append("</option>");
            }
            //str.append("</optgroup>");
            str.append("</select>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_option_employee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT DISTINCT tb_manage_data_user.fname, tb_manage_data_user.lname,tb_manage_data_user.user_id "
                    + "FROM tb_sale_product "
                    + "JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();
            int i = 1;
            str.append("<select name='employee' id='employee' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + rs.getString("user_id") + "'>" + rs.getString("fname") + " " + rs.getString("lname"));
                str.append("</option>");
                i++;
            }
            //str.append("</optgroup>");
            str.append("</select>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_option_type(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT DISTINCT * "
                    + "FROM ( "
                    + "tb_manage_data_product_mobile "
                    + "JOIN tb_manage_data_product_type_mobile "
                    + "ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id "
                    + ") "
                    + "JOIN tb_manage_data_unit "
                    + "ON tb_manage_data_product_mobile.unit_id = tb_manage_data_unit.unit_id "
                    + "UNION ALL SELECT DISTINCT  * "
                    + "FROM ( "
                    + "tb_manage_data_product_sim "
                    + "JOIN tb_manage_data_product_type_sim ON tb_manage_data_product_sim.sim_type_id = tb_manage_data_product_type_sim.sim_type_id "
                    + ") "
                    + "JOIN tb_manage_data_unit "
                    + "ON tb_manage_data_product_sim.unit_id = tb_manage_data_unit.unit_id "
                    + "UNION ALL SELECT DISTINCT * "
                    + "FROM ( "
                    + "tb_manage_data_product_other_tool "
                    + "JOIN tb_manage_data_product_type_other_tool "
                    + "ON tb_manage_data_product_other_tool.other_tool_type_id = tb_manage_data_product_type_other_tool.other_tool_type_id "
                    + ")"
                    + "JOIN tb_manage_data_unit "
                    + "ON tb_manage_data_product_other_tool.unit_id = tb_manage_data_unit.unit_id "
                    + " ";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();
            int i = 1;
            str.append("<select name='type' id='type' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + rs.getString("mobile_type") + "'>" + rs.getString("mobile_type"));
                str.append("</option>");
                i++;
            }
            //str.append("</optgroup>");
            str.append("</select>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_option_product(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * "
                    + "FROM ( "
                    + "tb_manage_data_product_mobile "
                    + "JOIN tb_manage_data_product_type_mobile "
                    + "ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id "
                    + ") "
                    + "JOIN tb_manage_data_unit "
                    + "ON tb_manage_data_product_mobile.unit_id = tb_manage_data_unit.unit_id "
                    + "UNION ALL SELECT * "
                    + "FROM ( "
                    + "tb_manage_data_product_sim "
                    + "JOIN tb_manage_data_product_type_sim ON tb_manage_data_product_sim.sim_type_id = tb_manage_data_product_type_sim.sim_type_id "
                    + ") "
                    + "JOIN tb_manage_data_unit "
                    + "ON tb_manage_data_product_sim.unit_id = tb_manage_data_unit.unit_id "
                    + "UNION ALL SELECT * "
                    + "FROM ( "
                    + "tb_manage_data_product_other_tool "
                    + "JOIN tb_manage_data_product_type_other_tool "
                    + "ON tb_manage_data_product_other_tool.other_tool_type_id = tb_manage_data_product_type_other_tool.other_tool_type_id "
                    + ")"
                    + "JOIN tb_manage_data_unit "
                    + "ON tb_manage_data_product_other_tool.unit_id = tb_manage_data_unit.unit_id "
                    + "ORDER BY mobile_id ASC";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();
            int i = 1;
            str.append("<select name='product' id='product' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + rs.getString("mobile_id") + "'>" + rs.getString("mobile"));
                str.append("</option>");
                i++;
            }
            //str.append("</optgroup>");
            str.append("</select>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_option_customer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT DISTINCT tb_manage_data_customer.customer_fname, tb_manage_data_customer.customer_lname,tb_manage_data_customer.customer_id "
                    + "FROM tb_sale_product "
                    + "JOIN tb_manage_data_customer ON tb_sale_product.customer_id = tb_manage_data_customer.customer_id "
                    + "WHERE tb_sale_product.customer_id LIKE 'CM%'";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();
            int i = 1;
            str.append("<select name='customer' id='customer' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + rs.getString("customer_id") + "'>" + rs.getString("customer_fname") + " " + rs.getString("customer_lname"));
                str.append("</option>");
                i++;
            }
            //str.append("</optgroup>");
            str.append("</select>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_option_forecast_start(HttpServletRequest request, HttpServletResponse response) throws IOException {
         try {
            String[] arr_year = {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
            StringBuffer str = new StringBuffer();
            int j = (arr_year.length) - 1;

            str.append("<select name='year_start' id='year_start' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = ''>-----กรุณาเลือก-----</option>");
            for (int i = 0; i <= j; i++) {
                str.append("<option class = 'special' value='" + (1990 + i) + "'>" + arr_year[i]);
                str.append("</option>");
            }
            //str.append("</optgroup>");
            str.append("</select>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_option_forecast_end(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String[] arr_year = {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
            StringBuffer str = new StringBuffer();
            int j = (arr_year.length) - 1;

            str.append("<select name='year_end' id='year_end' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = ''>-----กรุณาเลือก-----</option>");
            for (int i = 0; i <= j; i++) {
                str.append("<option class = 'special' value='" + (1990 + i) + "'>" + arr_year[i]);
                str.append("</option>");
            }
            //str.append("</optgroup>");
            str.append("</select>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }
}
