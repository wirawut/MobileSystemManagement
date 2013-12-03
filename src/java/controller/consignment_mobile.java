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

public class consignment_mobile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("save") != null) {
                save(request, response);
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
        String company_consignment = request.getParameter("company_consignment");
        String tel = request.getParameter("tel");
        String mobile_type_id = request.getParameter("mobile_type_id");
        String mobile_id = request.getParameter("mobile_id");
        String quantity = request.getParameter("quantity");
        String price_sale = request.getParameter("price_sale");
        String rate_consignment = request.getParameter("rate_consignment");
        String price_consignment = request.getParameter("price_consignment");
        try{
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "INSERT INTO tb_consignment_mobile(company_consignment,tel,mobile_type_id,mobile_id,quantity,price_sale,rate_consignment,price_consignment,date_save) VALUES(?,?,?,?,?,?,?,?,NOW())";
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1,company_consignment);
            pre.setString(2,tel);
            pre.setString(3,mobile_type_id);
            pre.setString(4,mobile_id);
            pre.setString(5,quantity);
            pre.setString(6,price_sale);
            pre.setString(7,rate_consignment);
            pre.setString(8,price_consignment);
            //ถ้ามีการบันทึกลงดาต้าเบส ไม่เท็จ==จริง
            if(pre.executeUpdate() != -1){
                response.getWriter().print("complete");
            }else{
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }

    }
}
