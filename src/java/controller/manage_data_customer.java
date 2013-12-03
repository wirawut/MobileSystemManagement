/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class manage_data_customer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("show_data_customer") != null) {
                show_data_customer(request, response);
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
        String customer_id = request.getParameter("customer_id");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String sex = request.getParameter("sex");
        String birthdate = request.getParameter("birthdate");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String card_number = request.getParameter("card_number");
        String address = request.getParameter("address");
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT count(customer_id) AS count_row FROM tb_manage_data_customer WHERE customer_id =" + "'" + customer_id + "'";
            ResultSet rs = c.createStatement().executeQuery(sql);
            Statement stmt = c.createStatement();
            if (rs.next()) {
                int count_row = rs.getInt("count_row");

                if (count_row == 0) {
                    sql = "INSERT INTO tb_manage_data_customer(customer_id,customer_fname,customer_lname,sex,birthdate,email,tel,card_number,address,date_save) VALUES('" + customer_id + "','" + fname + "','" + lname + "','" + sex + "','" + birthdate + "','" + email + "','" + tel + "','" + card_number + "','" + address + "',NOW())";
                } else if (count_row != 0) {
                    sql = "UPDATE tb_manage_data_customer SET customer_id = '" + customer_id + "',customer_fname = '" + fname + "',customer_lname = '" + lname + "',sex = '" + sex + "',birthdate = '" + birthdate + "',email = '" + email + "',tel = '" + tel + "',card_number = '" + card_number + "',address = '" + address + "'  , date_save = NOW() WHERE customer_id = " + "'" + customer_id + "'";
                }
            }

            if ((customer_id != "") || (fname != "") || (lname != "") || (sex != "") || (birthdate != "") || (email != "") || (tel != "") || (card_number != "")) {
                stmt.executeUpdate(sql);
                response.getWriter().print("complete");
            } else{
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_data_customer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_customer WHERE customer_id LIKE 'CM%' ORDER BY customer_id ASC";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-hover'>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th style='background-color : #98FB98'>" + "รหัสลูกค้า" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ชื่อ-สกุล" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "เพศ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "อายุ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "อีเมล์" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "เบอร์" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "เลขที่บัตรฯ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ที่อยู่" + "</th>");
            str.append("<th style='background-color : #98FB98'></th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {
                str.append("<td>" + rs.getString("customer_id") + "</td>");
                str.append("<td>" + rs.getString("customer_fname") + "&nbsp;&nbsp;" + rs.getString("customer_lname") + "</td>");
                str.append("<td>" + rs.getString("sex") + "</td>");
                str.append("<td>" + rs.getString("birthdate") + "</td>");
                str.append("<td>" + rs.getString("email") + "</td>");
                str.append("<td>" + rs.getString("tel") + "</td>");
                str.append("<td>" + rs.getString("card_number") + "</td>");
                str.append("<td>" + rs.getString("address") + "</td>");
                String customer_id_get = rs.getString("tb_manage_data_customer.customer_id");

                //เนื่องจากส่งข้อมูลกลับไปเป็นสตริงยากจึงต้องทำ
                if (customer_id_get.length() == 3) {
                    String customer_id = (customer_id_get.charAt(2)) + "";
                    str.append("<td><a   onclick ='return edit_data_customer(" + customer_id + ")'><img src='img/edit.png' width='25' height='25'></a>&nbsp;&nbsp;&nbsp;&nbsp<a href='#' onclick ='return delete_data_customer(" + customer_id + ")'><img src='img/delete.png' width='25' height='25'></a></td>");
                } else if (customer_id_get.length() > 3) {
                    int customer_length = ((customer_id_get.length()));
                    String customer_id = customer_id_get.substring(2, customer_length);
                    str.append("<td><a   onclick ='return edit_data_customer(" + customer_id + ")'><img src='img/edit.png' width='25' height='25'></a>&nbsp;&nbsp;&nbsp;&nbsp<a href='#' onclick ='return delete_data_customer(" + customer_id + ")'><img src='img/delete.png' width='25' height='25'></a></td>");
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
            String customer_id = request.getParameter("customer_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "DELETE FROM tb_manage_data_customer WHERE customer_id = '" + customer_id + "'";
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
            String customer_id = request.getParameter("customer_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_manage_data_customer WHERE customer_id = '" + customer_id + "' ";
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                String id = rs.getString("customer_id");
                String fname = rs.getString("customer_fname");
                String lname = rs.getString("customer_lname");
                String sex = rs.getString("sex");
                String birthdate = rs.getString("birthdate");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String card_number = rs.getString("card_number");
                String address = rs.getString("address");


                response.getWriter().print(id);
                response.getWriter().print(",");
                response.getWriter().print(fname);
                response.getWriter().print(",");
                response.getWriter().print(lname);
                response.getWriter().print(",");
                response.getWriter().print(sex);
                response.getWriter().print(",");
                response.getWriter().print(birthdate);
                response.getWriter().print(",");
                response.getWriter().print(email);
                response.getWriter().print(",");
                response.getWriter().print(tel);
                response.getWriter().print(",");
                response.getWriter().print(card_number);
                response.getWriter().print(",");
                response.getWriter().print(address);
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }
}
