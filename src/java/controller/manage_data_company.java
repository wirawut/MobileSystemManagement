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

public class manage_data_company extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("get_data") != null) {
                get_data(request, response);
            } else if (request.getParameter("get_option_company") != null) {
                get_option_company(request, response);
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
        String company_name = request.getParameter("company_name");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String fax = request.getParameter("fax");
        String tax_number = request.getParameter("tax_number");
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT count(company_id) AS count_row FROM tb_manage_data_company";
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                int count_row = rs.getInt("count_row");
                if (count_row == 0) {
                    sql = "INSERT INTO tb_manage_data_company(company_name,company_address,tel,fax,tax_number,date_save) VALUES(?,?,?,?,?,NOW())";
                } else {
                    sql = "UPDATE tb_manage_data_company SET company_name = ? , company_address = ? , tel = ? , fax = ? , tax_number = ? , date_save = NOW()";
                }
            }
            PreparedStatement pre = c.prepareStatement(sql);

            pre.setString(1, company_name);
            pre.setString(2, address);
            pre.setString(3, tel);
            pre.setString(4, fax);
            pre.setString(5, tax_number);

            if (company_name != "") {
                pre.executeUpdate();
                response.getWriter().print("complete");
            } else if (company_name == "") {
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_data(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_company";
            ResultSet rs = c.createStatement().executeQuery(sql);
            //while (rs.next()) {
            //String str = "{"+"company_name:'"+rs.getString("company_name")+"'"+"}";
            //StringBuffer str = new StringBuffer();
            //str.append("{");
            //str.append("company_name:'" + rs.getString("company_name") + "'");
            //str.append("address:'" + rs.getString("address") + "',");
            //str.append("tel:'" + rs.getString("tel") + "',");
            //str.append("fax:'" + rs.getString("fax") + "',");
            //str.append("tax_number : '" + rs.getString("tax_number") + "'");
            //str.append("}");

            //response.getWriter().print(str);
            //}
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void get_option_company(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_company";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();

            str.append("<select name='company_id' id='company_id' style='margin: 3px'>");
            //str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class = 'special' value='" + rs.getInt("company_id") + "'>" + rs.getString("company_name"));
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
