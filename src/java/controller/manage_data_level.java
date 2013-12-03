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

public class manage_data_level extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("get_option_level") != null) {
                get_option_level(request, response);
            } else if (request.getParameter("show_data_level") != null) {
                show_data_level(request, response);
            } else if (request.getParameter("edit") != null) {
                edit(request, response);
            } else if (request.getParameter("delete") != null) {
                delete(request, response);
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
        String level = request.getParameter("level");
        String level_id = request.getParameter("level_id");
        String[] access = request.getParameterValues("access");
        String acc = "";
        int count = access.length;
        //นับตัวเลขที่รับมาจากcheckboxmี่เลือกมาต่อกัน เพื่อบันทึกลงดาต้าเบส
        if (count == 1) {
            acc = (access[0]).toString();
        } else if (count == 2) {
            acc = (access[0] + access[1]).toString();
        } else if (count == 3) {
            acc = (access[0] + access[1] + access[2]).toString();
        } else if (count == 4) {
            acc = (access[0] + access[1] + access[2] + access[3]).toString();
        }
        try {


            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT count(level_id) AS count_row FROM tb_manage_data_level WHERE level_id =" + level_id;
            ResultSet rs = c.createStatement().executeQuery(sql);
            Statement stmt = c.createStatement();
            if (rs.next()) {
                int count_row = rs.getInt("count_row");

                if (count_row == 0) {
                    sql = "INSERT INTO tb_manage_data_level(level,access,date_save) VALUES('" + level + "','" + acc + "',NOW())";
                } else if (count_row != 0) {
                    sql = "UPDATE tb_manage_data_level SET level = '" + level + "'  ,access = '" + acc + "'  , date_save = NOW() WHERE level_id = " + level_id;
                }
            }

            if (level != "") {
                stmt.executeUpdate(sql);
                response.getWriter().print("complete");
            } else if (level == "") {
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }

    }

    private void get_option_level(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_level";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<select id='level_id' name='level_id'  style='margin: 3px'>");
            // str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + rs.getInt("level_id") + "'>" + rs.getString("level"));
                str.append("</option>");
            }
            //str.append("</optgroup>");
            str.append("</select>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_data_level(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_level ORDER BY level_id ASC";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-hover'>");
            str.append("<thead>");
            str.append("<tr>");
            // str.append("<th style='background-color : #98FB98'>" + "รหัส" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ระดับผู้ใช้งาน" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "วันที่บันทึก" + "</th>");
            str.append("<th style='background-color : #98FB98'></th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {
                str.append("<tr style='background-color : #E0FFFF'>");
                //  str.append("<td>" + rs.getString("level_id") + "</td>");
                str.append("<td>" + rs.getString("level") + "</td>");
                str.append("<td>" + rs.getString("date_save") + "</td>");
                str.append("<td><a  onclick ='return edit_data_level(" + rs.getString("level_id") + ")'><img src='img/edit.png' width='25' height='25'></a>&nbsp;&nbsp;&nbsp;&nbsp; <a href='#' onclick='delete_data_level(" + rs.getString("level_id") + ")'><img src='img/delete.png' width='25' height='25'></a></td>");
                str.append("</tr>");
            }
            str.append("</tbody>");
            str.append("</table>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String level_id = request.getParameter("level_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_level WHERE level_id = " + level_id;
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                String status = rs.getString("level");
                response.getWriter().print(status);
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String level_id = request.getParameter("level_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "DELETE FROM tb_manage_data_level WHERE level_id =" + level_id;

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
}
