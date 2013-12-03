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


public class repair_other_tool_check_status extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if(request.getParameter("check") != null){
            check(request,response);
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

    private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //ตรวจสอบว่ามีรหัสที่ส่งมาหรือไม่ ถ้ามีก็ดึงstatusการซ่อมออกมาเเสดง (ต้องสร้างตารางใหม่ขึ้นมา)
        //ตรวจสอบว่ามีรหัสที่ส่งมาหรือไม่ ถ้ามีก็ดึงstatusการซ่อมออกมาเเสดง (ต้องสร้างตารางใหม่ขึ้นมา)
        String repair_other_tool_id = request.getParameter("repair_other_tool_id");
        Connection c = (Connection) getServletContext().getAttribute("conn");
        String sql = "SELECT tb_manage_data_status.status FROM tb_repair_other_tool_order "
                + "JOIN tb_manage_data_status "
                + "ON tb_manage_data_status.status_id = tb_repair_other_tool_order.status_id "
                + "WHERE tb_repair_other_tool_order.repair_other_tool_id = '" + repair_other_tool_id + "'";
        try {
            ResultSet rs = c.createStatement().executeQuery(sql);
            
            if (rs.next()) {
                //HttpSession session = request.getSession();
                //String status = rs.getString("tb_manage_data_status.status")
                //session.setAttribute("status", rs.getString("tb_manage_data_status.status"));
                response.getWriter().print(rs.getString("tb_manage_data_status.status"));
            } 
            else {
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

}
