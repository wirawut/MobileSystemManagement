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
import javax.servlet.http.HttpSession;
//import org.hibernate.Session;

public class manage_data_status extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("get_option") != null) {
                get_option(request, response);
            } else if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("show_data_status") != null) {
                show_data_status(request, response);
            } else if (request.getParameter("delete") != null) {
                delete(request, response);
            } else if (request.getParameter("edit") != null) {
                edit(request, response);
            }
        } catch (Exception e) {
            response.getWriter().print(e);
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

    private void get_option(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_status";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<select class='selectpicker btn-info' name='status_id' style='margin: 3px'>");
            str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + rs.getInt("status_id") + "'>" + rs.getString("status"));
                str.append("</option>");
            }
            str.append("</optgroup>");
            str.append("</select>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String status = request.getParameter("status");
        String status_id = request.getParameter("status_id");
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT count(status_id) AS count_row FROM tb_manage_data_status WHERE status_id =" + status_id;
            ResultSet rs = c.createStatement().executeQuery(sql);
            Statement stmt = c.createStatement();
            if (rs.next()) {
                int count_row = rs.getInt("count_row");

                if (count_row == 0) {
                    sql = "INSERT INTO tb_manage_data_status(status,date_save) VALUES('" + status + "',NOW())";
                } else if (count_row != 0) {
                    sql = "UPDATE tb_manage_data_status SET status = '" + status + "'  , date_save = NOW() WHERE status_id = " + status_id;
                }
            }

            if (status != "") {
                stmt.executeUpdate(sql);
                response.getWriter().print("complete");
            } else if (status == "") {
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_data_status(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_status ORDER BY status_id ASC";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-hover'>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th style='background-color : #98FB98'>" + "รหัส" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "สถานะการซ่อม" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "วันที่การซ่อม" + "</th>");
            str.append("<th style='background-color : #98FB98'></th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {

                str.append("<tr style='background-color : #E0FFFF'>");
                str.append("<td>" + rs.getString("status_id") + "</td>");
                str.append("<td>" + rs.getString("status") + "</td>");
                str.append("<td>" + rs.getString("date_save") + "</td>");
                str.append("<td><a onclick ='return edit_data_status(" + rs.getString("status_id") + ")'><img src='img/edit.png' width='25' height='25'></a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick ='return delete_data_status(" + rs.getString("status_id") + ")'><img src='img/delete.png' width='25' height='25'></a></td>");
                str.append("</tr>");
                HttpSession s = request.getSession();
                s.setAttribute("status", rs.getString("status"));
            }
            str.append("</tbody>");
            str.append("</table>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String status_id = request.getParameter("status_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "DELETE FROM tb_manage_data_status WHERE status_id =" + status_id;
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

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String status_id = request.getParameter("status_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_status WHERE status_id = " + status_id;
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                String status = rs.getString("status");
                response.getWriter().print(status);
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }

    }
}
