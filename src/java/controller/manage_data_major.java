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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class manage_data_major extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("get_data") != null) {
                get_data(request, response);
            } else if (request.getParameter("get_option_major") != null) {
                get_option_major(request, response);
            } else if (request.getParameter("get_option_type") != null) {
                get_option_type(request, response);
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
        String major_name = request.getParameter("major_name");
        String company_id = request.getParameter("company_id");//nameของselect
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String fax = request.getParameter("fax");

        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT count(major_id) AS count_row FROM tb_manage_data_major";
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                int count_row = rs.getInt("count_row");
                if (count_row == 0) {
                    sql = "INSERT INTO tb_manage_data_major(major_name,company_id,major_address,tel,email,fax,date_save) VALUES(?,?,?,?,?,?,NOW())";
                } else {
                    sql = "UPDATE tb_manage_data_major SET major_name = ? ,company_id = ? , major_address = ? , tel = ? , email = ? , fax = ?, date_save = NOW()";
                }
            }

            PreparedStatement pre = c.prepareStatement(sql);

            pre.setString(1, major_name);
            pre.setString(2, company_id);
            pre.setString(3, address);
            pre.setString(4, tel);
            pre.setString(5, email);
            pre.setString(6, fax);
            if (major_name != "") {
                pre.executeUpdate();
                response.getWriter().print("complete");
            } else if (major_name == "") {
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_data(HttpServletRequest request, HttpServletResponse response) {
    }

    private void get_option_major(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_major";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();

            str.append("<select name='major_id' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + rs.getInt("major_id") + "'>" + rs.getString("major_name"));
                str.append("</option>");
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
            String sql = "SELECT * FROM tb_manage_data_product_type_mobile";
            ResultSet rs = c.createStatement().executeQuery(sql);
            int i = 1;
            StringBuffer str = new StringBuffer();

            str.append("<select name='major_id' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + i + "'>" + rs.getString("mobile_type"));
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
}
