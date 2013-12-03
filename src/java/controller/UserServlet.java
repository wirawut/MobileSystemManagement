/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("checkLogin") != null) {
                checkLogin(request, response);
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

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        try {
            ServletContext sc = getServletContext();
            Connection c = (Connection) sc.getAttribute("conn");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String sql = "SELECT * FROM tb_manage_data_user "
                    + "JOIN tb_manage_data_level "
                    + "ON tb_manage_data_user.level_id = tb_manage_data_level.level_id "                    
                    + "WHERE tb_manage_data_user.username='" + username + "' "
                    + "AND tb_manage_data_user.password = '" + password + "' ";
            ResultSet rs = c.createStatement().executeQuery(sql);

            while (rs.next()) {
                /*
                 request.setAttribute("fname", rs.getString("fname"));
                 request.setAttribute("lname", rs.getString("lname"));
                 request.setAttribute("level", rs.getString("level"));
                 request.setAttribute("image", rs.getString("image"));
                 */
                HttpSession s = request.getSession();
                s.setAttribute("user_id", rs.getString("tb_manage_data_user.user_id"));
                s.setAttribute("fname", rs.getString("tb_manage_data_user.fname"));
                s.setAttribute("lname", rs.getString("tb_manage_data_user.lname"));
                s.setAttribute("email", rs.getString("tb_manage_data_user.email"));
                s.setAttribute("tel", rs.getString("tb_manage_data_user.tel"));
                s.setAttribute("username", rs.getString("tb_manage_data_user.username"));
                s.setAttribute("password", rs.getString("tb_manage_data_user.password"));
                //s.setAttribute("password_confirm", rs.getString("password_confirm"));
                s.setAttribute("level_id", rs.getString("tb_manage_data_user.level_id"));
                s.setAttribute("level", rs.getString("tb_manage_data_level.level"));
                s.setAttribute("access", rs.getString("tb_manage_data_level.access"));
                s.setAttribute("image", rs.getString("tb_manage_data_user.image"));
                //s.setAttribute("company_name", rs.getString("tb_manage_data_company.company_name"));
                //s.setAttribute("company_tel", rs.getString("tb_manage_data_company.tel"));
                //s.setAttribute("company_fax", rs.getString("tb_manage_data_company.fax"));
               //s.setAttribute("company_tax_number", rs.getString("tb_manage_data_company.tax_number"));
                response.getWriter().print("pass");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }
}
