/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class manage_data_product_type_mobile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("get_option") != null) {
                get_option(request, response);
            } else if (request.getParameter("delete") != null) {
                delete(request, response);
            } else if (request.getParameter("edit") != null) {
                edit(request, response);
            } else if (request.getParameter("show_data_product_type_mobile") != null) {
                show_data_product_type_mobile(request, response);
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

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mobile_type_id = request.getParameter("mobile_type_id");
        String mobile_type = request.getParameter("mobile_type");
        String detail = request.getParameter("detail");
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT count(mobile_type_id) AS count_row FROM tb_manage_data_product_type_mobile WHERE mobile_type_id =" + "'" + mobile_type_id + "'" + "";
            ResultSet rs = c.createStatement().executeQuery(sql);
            Statement stmt = c.createStatement();
            if (rs.next()) {
                int count_row = rs.getInt("count_row");

                if (count_row == 0) {
                    sql = "INSERT INTO tb_manage_data_product_type_mobile(mobile_type,detail,date_save) VALUES('" + mobile_type + "','" + detail + "',NOW())";
                } else if (count_row != 0) {
                    sql = "UPDATE tb_manage_data_product_type_mobile SET mobile_type = '" + mobile_type + "' , detail = '" + detail + "', date_save = NOW() WHERE mobile_type_id = " + mobile_type_id;
                }
            }

            if (mobile_type != "") {
                stmt.executeUpdate(sql);
                response.getWriter().print("complete");
            } else if (mobile_type == "") {
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_option(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_product_type_mobile";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();

            str.append("<select  btn-info' name='mobile_type_id' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + rs.getInt("mobile_type_id") + "'>" + rs.getString("mobile_type"));
                str.append("</option>");
            }
          //  str.append("</optgroup>");
            str.append("</select>");




            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
         try {
            String mobile_type_id = request.getParameter("mobile_type_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_product_type_mobile WHERE mobile_type_id = " + mobile_type_id;
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                String mobile_type = rs.getString("mobile_type");
                String detail =rs.getString("detail");
                response.getWriter().print(mobile_type);
                response.getWriter().print(",");
                response.getWriter().print(detail);
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String mobile_type_id = request.getParameter("mobile_type_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "DELETE FROM tb_manage_data_product_type_mobile WHERE mobile_type_id =" + mobile_type_id;
            Statement stmt = c.createStatement();

            if (stmt.executeUpdate(sql) != -1) {
                response.getWriter().print("complete");
            } else {
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_data_product_type_mobile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_product_type_mobile ORDER BY mobile_type_id ASC";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-hover'>");
            str.append("<thead>");
            str.append("<tr>");
            //str.append("<th style='background-color : #98FB98'>" + "รหัสยี่ห้อ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ยี่ห้อ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รายละเอียด" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "วันที่บันทึก" + "</th>");
            str.append("<th style='background-color : #98FB98'></th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {
                str.append("<tr style='background-color : #E0FFFF'>");
                //str.append("<td>" + rs.getString("mobile_type_id") + "</td>");
                str.append("<td>" + rs.getString("mobile_type") + "</td>");
                str.append("<td>" + rs.getString("detail") + "</td>");
                str.append("<td>" + rs.getString("date_save") + "</td>");
                str.append("<td><a  onclick ='return edit_data_product_type_mobile(" + rs.getString("mobile_type_id") + ")'><img src='img/edit.png' width='25' height='25'></a>&nbsp;&nbsp;&nbsp;&nbsp<a href='#' onclick ='return delete_data_product_type_mobile(" + rs.getString("mobile_type_id") + ")'><img src='img/delete.png' width='25' height='25'></a></td>");
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
