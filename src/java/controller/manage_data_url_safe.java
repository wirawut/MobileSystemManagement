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


public class manage_data_url_safe extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if(request.getParameter("save") != null){
            save(request,response);
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

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url_safe = request.getParameter("url_safe");
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT count(url_safe_id) AS count_row FROM tb_manage_data_url_safe";
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                int count_row = rs.getInt("count_row");
                if (count_row == 0) {
                    sql = "INSERT INTO tb_manage_data_url_safe(url_safe,date_save) VALUES(?,NOW())";
                } else {
                    sql = "UPDATE tb_manage_data_url_safe SET url_safe = ? , date_save = NOW()";
                }
            }

            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1, url_safe);

            if (url_safe != "") {
                pre.executeUpdate();
                response.getWriter().print("complete");
            } else if (url_safe == ""){
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

}
