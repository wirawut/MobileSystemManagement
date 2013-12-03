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

public class manage_data_product_mobile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("get_option") != null) {
                get_option(request, response);
            } else if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("show_data_product_mobile") != null) {
                show_data_product_mobile(request, response);
            } else if (request.getParameter("delete") != null) {
                delete(request, response);
            } else if (request.getParameter("edit") != null) {
                edit(request, response);
            } else if (request.getParameter("show_mobile") != null) {
                show_mobile(request, response);
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

    private void get_option(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_product_mobile";
            ResultSet rs = c.createStatement().executeQuery(sql);

            StringBuffer str = new StringBuffer();
            str.append("<select class='selectpicker btn-info' name='mobile_id' style='margin: 3px'>");
            str.append("<optgroup>");
            str.append("<option value = '' >-----กรุณาเลือก-----</option>");
            while (rs.next()) {
                str.append("<option class='special' value='" + rs.getInt("mobile_id") + "'>" + rs.getString("mobile"));
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
        String mobile_id = request.getParameter("mobile_id");
        String mobile_type_id = request.getParameter("mobile_type_id");
        String mobile = request.getParameter("mobile");
        String image = request.getParameter("image");
        String price_buy = request.getParameter("price_buy");
        String price_sale = request.getParameter("price_sale");
        String unit_id = request.getParameter("unit_id");
        String quantity = request.getParameter("quantity");
        String date_import = request.getParameter("date_import");
        String detail = request.getParameter("detail");

        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT count(mobile_id) AS count_row FROM "
                    + "( tb_manage_data_product_mobile JOIN tb_manage_data_product_type_mobile ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id) "
                    + "JOIN tb_manage_data_unit ON tb_manage_data_product_mobile.unit_id = tb_manage_data_unit.unit_id "
                    + "WHERE tb_manage_data_product_mobile.mobile_id = '" + mobile_id + "'";
            ResultSet rs = c.createStatement().executeQuery(sql);
            Statement stmt = c.createStatement();
            if (rs.next()) {
                int count_row = rs.getInt("count_row");
                if (count_row == 0) {
                    sql = "INSERT INTO tb_manage_data_product_mobile(mobile_id,mobile_type_id,mobile,image,price_buy,price_sale,unit_id,quantity,date_import,detail) VALUES('" + mobile_id + "'," + mobile_type_id + ",'" + mobile + "','" + image + "','" + price_buy + "','" + price_sale + "'," + unit_id + ",'" + quantity + "','" + date_import + "','" + detail + "')";
                } else if (count_row != 0) {
                    sql = "UPDATE tb_manage_data_product_mobile SET mobile_id='" + mobile_id + "',mobile_type_id = " + mobile_type_id + " , mobile= '" + mobile + "', image = '" + image + "',price_buy = '" + price_buy + "',price_sale = '" + price_sale + "',unit_id = " + unit_id + ",quantity = '" + quantity + "',date_import = '" + date_import + "',detail = '" + detail + "'WHERE mobile_id = '" + mobile_id + "'";
                }
            }

            if (mobile != "") {
                stmt.executeUpdate(sql);
                response.getWriter().print("complete");
            } else if (mobile == "") {
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_data_product_mobile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM "
                    + "( tb_manage_data_product_mobile JOIN tb_manage_data_product_type_mobile ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id) "
                    + "JOIN tb_manage_data_unit ON tb_manage_data_product_mobile.unit_id = tb_manage_data_unit.unit_id "
                    + "ORDER BY tb_manage_data_product_mobile.mobile_id ASC";
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
            str.append("<th style='background-color : #98FB98'></th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {
                str.append("<tr style='background-color : #E0FFFF'>");
                str.append("<td>" + rs.getString("tb_manage_data_product_mobile.mobile_id") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_type_mobile.mobile_type") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_mobile.mobile") + "</td>");
                str.append("<td>" + "<img src='image/" + rs.getString("tb_manage_data_product_mobile.image") + "' style='width:50px ; height: 50px'>" + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_mobile.price_buy") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_mobile.price_sale") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_unit.unit") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_mobile.quantity") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_mobile.date_import") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_product_mobile.detail") + "</td>");
                String mobile_id_get = rs.getString("tb_manage_data_product_mobile.mobile_id");
                if (mobile_id_get.length() == 3) {
                    String mobile_id = (mobile_id_get.charAt(2)) + "";
                    str.append("<td><a  onclick ='return edit_data_product_mobile(" + mobile_id + ")'><img src='img/edit.png' width='25' height='25'></a>&nbsp;&nbsp;&nbsp;&nbsp<a  onclick ='return delete_data_product_mobile(" + mobile_id + ")'><img src='img/delete.png' width='25' height='25'></a></td>");
                } else if (mobile_id_get.length() > 3) {
                    int mobile_length = ((mobile_id_get.length()));
                    String mobile_id = mobile_id_get.substring(2, mobile_length);
                    str.append("<td><a onclick ='return edit_data_product_mobile(" + mobile_id + ")'><img src='img/edit.png' width='25' height='25'></a>&nbsp;&nbsp;&nbsp;&nbsp<a  onclick ='return delete_data_product_mobile(" + mobile_id + ")'><img src='img/delete.png' width='25' height='25'></a></td>");
                }
                str.append("</tr>");
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
            String mobile_id = request.getParameter("mobile_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "DELETE FROM tb_manage_data_product_mobile WHERE mobile_id = '" + mobile_id + "'";
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
            String mobile_id = request.getParameter("mobile_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM "
                    + "( tb_manage_data_product_mobile JOIN tb_manage_data_product_type_mobile ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id) "
                    + "JOIN tb_manage_data_unit ON tb_manage_data_product_mobile.unit_id = tb_manage_data_unit.unit_id "
                    + "WHERE tb_manage_data_product_mobile.mobile_id = '" + mobile_id + "' ";
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                String id = rs.getString("tb_manage_data_product_mobile.mobile_id");
                String mobile_type = rs.getString("tb_manage_data_product_type_mobile.mobile_type");
                String mobile = rs.getString("tb_manage_data_product_mobile.mobile");
                String image = rs.getString("tb_manage_data_product_mobile.image");
                String price_buy = rs.getString("tb_manage_data_product_mobile.price_buy");
                String price_sale = rs.getString("tb_manage_data_product_mobile.price_sale");
                String unit = rs.getString("tb_manage_data_unit.unit");
                String quantity = rs.getString("tb_manage_data_product_mobile.quantity");
                String date_import = rs.getString("tb_manage_data_product_mobile.date_import");
                String detail = rs.getString("tb_manage_data_product_mobile.detail");

                response.getWriter().print(id);
                response.getWriter().print(",");
                response.getWriter().print(mobile_type);
                response.getWriter().print(",");
                response.getWriter().print(mobile);
                response.getWriter().print(",");
                response.getWriter().print(image);
                response.getWriter().print(",");
                response.getWriter().print(price_buy);
                response.getWriter().print(",");
                response.getWriter().print(price_sale);
                response.getWriter().print(",");
                response.getWriter().print(unit);
                response.getWriter().print(",");
                response.getWriter().print(quantity);
                response.getWriter().print(",");
                response.getWriter().print(date_import);
                response.getWriter().print(",");
                response.getWriter().print(detail);
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_mobile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM "
                    + "( tb_manage_data_product_mobile JOIN tb_manage_data_product_type_mobile ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id) "
                    + "JOIN tb_manage_data_unit ON tb_manage_data_product_mobile.unit_id = tb_manage_data_unit.unit_id "
                    + "ORDER BY tb_manage_data_product_mobile.mobile_id ASC";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            //str.append("<frame style='width: 500px ; height : 300px'>");
            str.append("<table class='table table-hover'>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th style='background-color : #98FB98'>" + "รายการ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รหัสสินค้า" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ยี่ห้อ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รุ่น" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รูปภาพ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ราคาต่อหน่วย" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "จำนวน" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "คงเหลือ" + "</th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");

            while (rs.next()) {
                String mobile_id_get = rs.getString("tb_manage_data_product_mobile.mobile_id");
                if (mobile_id_get.length() == 3) {
                    String mobile_id = (mobile_id_get.charAt(2)) + "";
                    str.append("<tr style='background-color : #ffffff'>");
                    str.append("<td><div class='switch demo1'><input type = 'checkbox' id='select' name='select' value ='" + rs.getString("tb_manage_data_product_mobile.mobile_id") + "'><label></label></div></td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_mobile.mobile_id") + "</td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_type_mobile.mobile_type") + "</td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_mobile.mobile") + "</td>");
                    str.append("<td>" + "<img src='image/" + rs.getString("tb_manage_data_product_mobile.image") + "' style='width:50px ; height: 50px'>" + "</td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_mobile.price_sale") + "</td>");
                    str.append("<td><input type = 'text' class='input-mini' value='1' name='number" + mobile_id + "' style='background-color : LightBlue ; color : black;width: 100px ; height: 38px;'></td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_mobile.quantity") + "</td>");
                    str.append("</tr>");
                } else if (mobile_id_get.length() > 3) {
                    int mobile_length = ((mobile_id_get.length()));
                    String mobile_id = mobile_id_get.substring(2, mobile_length);

                    str.append("<tr style='background-color : #E0FFFF'>");
                   str.append("<td><div class='switch demo1'><input type = 'checkbox' id='select' name='select' value ='" + rs.getString("tb_manage_data_product_mobile.mobile_id") + "'><label></label></div></td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_mobile.mobile_id") + "</td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_type_mobile.mobile_type") + "</td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_mobile.mobile") + "</td>");
                    str.append("<td>" + "<img src='image/" + rs.getString("tb_manage_data_product_mobile.image") + "' style='width:50px ; height: 50px'>" + "</td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_mobile.price_sale") + "</td>");
                    str.append("<td><input type = 'text' class='input-mini' value='1' name='number" + mobile_id + "' style='background-color : LightBlue ; color : black ; width: 100px ; height: 38px;'></td>");
                    str.append("<td>" + rs.getString("tb_manage_data_product_mobile.quantity") + "</td>");
                    str.append("</tr>");
                }
            }
            str.append("</tbody>");
            str.append("</table>");
            // str.append("</frame>");

            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }
}
