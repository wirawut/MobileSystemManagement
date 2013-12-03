/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hippo-G8
 */
public class manage_data_product_all extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("show_data_product_all") != null) {
                show_data_product_all(request, response);
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

    private void show_data_product_all(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql ="SELECT * "
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
                    + "ORDER BY mobile_id ASC" ;
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-hover'>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th style='background-color : #98FB98'>" + "รายการ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รหัสสินค้า" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ยี่ห้อ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รุ่น" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รูปภาพ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ราคา" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "จำนวน" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "คงเหลือ" + "</th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {

                str.append("<tr style='background-color : #ffffff'>");
                str.append("<td><input type = 'button' value ='เลือก'></td>");
                str.append("<td>" + rs.getString("mobile_id") + "</td>");
                str.append("<td>" + rs.getString("mobile_type") + "</td>");
                str.append("<td>" + rs.getString("mobile") + "</td>");
                str.append("<td>" + "<img src='image/" + rs.getString("image") + "' style='width:50px ; height: 50px'>" + "</td>");
                str.append("<td>" + rs.getString("price_sale") + "</td>");
                str.append("<td><input type = 'text' class='input-mini' value='1' name='number' style='background-color : LightBlue ; color : black'></td>");
                str.append("<td>" + rs.getString("quantity") + "</td>");
                str.append("</tr>");
            }
            str.append("</tbody>");
            str.append("</table>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }
}
