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

public class showData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("show_data_mobile") != null) {
                show_data_mobile(request, response);
            } else if (request.getParameter("show_data_sim") != null) {
                show_data_sim(request, response);
            } else if (request.getParameter("show_data_other_tool") != null) {
                show_data_other_tool(request, response);
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

    private void show_data_mobile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_product_mobile JOIN tb_manage_data_product_type_mobile ON tb_manage_data_product_mobile.mobile_type_id =  tb_manage_data_product_type_mobile.mobile_type_id";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-bordered table-striped table-hover'>");
            //str.append("<caption>" + "ข้อมูลสินค้าที่มีอยู่ในสต๊อก(โทรศัพท์)" + "</caption>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th>" + "รหัสรุ่น" + "</th>");
            str.append("<th>" + "ยี่ห้อ" + "</th>");
            str.append("<th>" + "รุ่น" + "</th>");
            str.append("<th>" + "รูปภาพ" + "</th>");
            str.append("<th>" + "ราคาซื้อ" + "</th>");
            str.append("<th>" + "ราคาขาย" + "</th>");
            str.append("<th>" + "จำนวน" + "</th>");
            str.append("<th>" + "วันรับเข้า" + "</th>");
            str.append("<th>" + "คู่ค้า" + "</th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {
                str.append("<tr>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_mobile.mobile_id") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_type_mobile.mobile_type") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_mobile.mobile") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_mobile.image") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_mobile.price_buy") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_mobile.price_sale") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_mobile.quantity") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_mobile.date_import") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_mobile.supplier") + "'" + "</td>");
                str.append("</tr>");
            }
            str.append("</tbody>");
            str.append("</table>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_data_sim(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_product_sim JOIN tb_manage_data_product_type_sim ON tb_manage_data_product_sim.sim_type_id =  tb_manage_data_product_type_sim.sim_type_id";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-bordered'>");
            str.append("<caption>" + "ข้อมูลสินค้าที่มีอยู่ในสต๊อก(ซิม)" + "</caption>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th>" + "รหัสรุ่น" + "</th>");
            str.append("<th>" + "เครือข่าย" + "</th>");
            str.append("<th>" + "รุ่น" + "</th>");
            str.append("<th>" + "รูปภาพ" + "</th>");
            str.append("<th>" + "เบอร์โทร" + "</th>");
            str.append("<th>" + "ราคาซื้อ" + "</th>");
            str.append("<th>" + "ราคาขาย" + "</th>");
            str.append("<th>" + "จำนวน" + "</th>");
            str.append("<th>" + "วันรับเข้า" + "</th>");

            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {
                str.append("<tr>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_sim.sim_id") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_type_sim.sim_type") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_sim.sim") + "'" + "</td>");
                 str.append("<td>" + "'" + rs.getString("tb_manage_data_product_sim.image") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_sim.tel") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_sim.price_buy") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_sim.price_sale") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_sim.quantity") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_sim.date_import") + "'" + "</td>");

                str.append("</tr>");
            }
            str.append("</tbody>");
            str.append("</table>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_data_other_tool(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_product_other_tool JOIN tb_manage_data_product_type_other_tool ON tb_manage_data_product_other_tool.other_tool_type_id =  tb_manage_data_product_type_other_tool.other_tool_type_id";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-bordered'>");
            str.append("<caption>" + "ข้อมูลสินค้าที่มีอยู่ในสต๊อก(โทรศัพท์)" + "</caption>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th>" + "รหัสรุ่น" + "</th>");
            str.append("<th>" + "ประเภทสินค้า" + "</th>");
            str.append("<th>" + "รุ่น" + "</th>");
            str.append("<th>" + "รูปภาพ" + "</th>");
            str.append("<th>" + "ราคาซื้อ" + "</th>");
            str.append("<th>" + "ราคาขาย" + "</th>");
            str.append("<th>" + "จำนวน" + "</th>");
            str.append("<th>" + "วันรับเข้า" + "</th>");
            str.append("<th>" + "คู่ค้า" + "</th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {
                str.append("<tr>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_other_tool.other_tool_id") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_type_other_tool.other_tool_type") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_other_tool.other_tool") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_other_tool.image") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_other_tool.price_buy") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_other_tool.price_sale") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_other_tool.quantity") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_other_tool.date_import") + "'" + "</td>");
                str.append("<td>" + "'" + rs.getString("tb_manage_data_product_other_tool.supplier") + "'" + "</td>");
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
