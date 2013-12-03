<%-- 
    Document   : stock_sim
    Created on : 22 เม.ย. 2556, 15:17:44
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    function loadContent(url){
        $("#content").load(url);
    }
        
</script>

<%
    try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM "
                    + "( tb_manage_data_product_sim JOIN tb_manage_data_product_type_sim ON tb_manage_data_product_sim.sim_type_id = tb_manage_data_product_type_sim.sim_type_id) "
                    + "JOIN tb_manage_data_unit ON tb_manage_data_product_sim.unit_id = tb_manage_data_unit.unit_id "
                    + "ORDER BY tb_manage_data_product_sim.sim_id ASC";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-hover'>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th style='background-color : #98FB98'>" + "รหัสสินค้า" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ยี่ห้อ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รุ่น" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รูปภาพ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ราคาซื้อ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ราคาขาย" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "หน่วยนับ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "จำนวน" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "วันนำเข้า" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รายละเอียด" + "</th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {
                str.append("<tr style='background-color : #E0FFFF'>");
                str.append("<td>" + rs.getString("tb_manage_data_product_sim.sim_id") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_type_sim.sim_type") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_sim.sim") + "</td>");
                str.append("<td>" + "<img src='image/" + rs.getString("tb_manage_data_product_sim.image") + "' style='width:50px ; height: 50px'>" + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_sim.price_buy") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_sim.price_sale") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_unit.unit") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_sim.quantity") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_sim.date_import") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_sim.detail") + "</td>");
                str.append("</tr>");
            }
            str.append("</tbody>");
            str.append("</table>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
%>