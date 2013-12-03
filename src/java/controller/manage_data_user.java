/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Hashtable;
import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;

public class manage_data_user extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("show_data_user") != null) {
                show_data_user(request, response);
            } else if (request.getParameter("delete") != null) {
                delete(request, response);
            } else if (request.getParameter("edit") != null) {
                edit(request, response);
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

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, UploadException, ServletException {
        String user_id = request.getParameter("user_id");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String birthdate = request.getParameter("birthdate");
        String sex = request.getParameter("sex");
        String company_id = request.getParameter("company_id");
        String major_id = request.getParameter("major_id");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String level_id = request.getParameter("level_id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String image = request.getParameter("image");


        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT count(user_id) AS count_row FROM tb_manage_data_user WHERE user_id =" + "'" + user_id + "'";
            ResultSet rs = c.createStatement().executeQuery(sql);
            Statement stmt = c.createStatement();
            if (rs.next()) {
                int count_row = rs.getInt("count_row");

                if (count_row == 0) {
                    sql = "INSERT INTO tb_manage_data_user(user_id,fname,lname,birthdate,sex,company_id,major_id,address,email,tel,level_id,username,password,image,date_save) VALUES('" + user_id + "','" + fname + "','" + lname + "','" + birthdate + "','" + sex + "','" + company_id + "','" + major_id + "','" + address + "','" + email + "','" + tel + "','" + level_id + "','" + username + "','" + password + "','" + image + "',NOW())";
                } else if (count_row != 0) {
                   sql = "UPDATE tb_manage_data_user SET user_id = '" + user_id + "' ,fname ='" + fname+ "',lname = '" + lname + "',birthdate='" + birthdate + "' ,sex = '" + sex+ "',company_id='" + company_id + "',major_id='" + major_id+ "',address='" + address + "',email='" + email+ "',tel='" + tel + "',level_id='" + level_id + "',username='" + username + "',password='" + password+ "',image='" + image + "', date_save = NOW() WHERE user_id = " + "'" + user_id + "'";
                }
            }

            if (user_id != "") {
                stmt.executeUpdate(sql);
                response.getWriter().print("complete");
            } else if (user_id == "") {
                response.getWriter().print("no_complete");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }
    //}

    private void show_data_user(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM "
                    + "(( tb_manage_data_user JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id )"
                    + "JOIN tb_manage_data_level ON tb_manage_data_user.level_id = tb_manage_data_level.level_id ORDER BY user_id";
            ResultSet rs = c.createStatement().executeQuery(sql);
            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-hover'>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th style='background-color : #98FB98'>" + "รหัสผู้ใช้" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ชื่อ-สกุล" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รูปภาพ" + "</th>");
            //str.append("<th style='background-color : #98FB98'>" + "วันเกิด" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "เพศ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "บริษัท" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "สาขา" + "</th>");
            //str.append("<th style='background-color : #98FB98'>" + "ที่อยู่" + "</th>"); 
            str.append("<th style='background-color : #98FB98'>" + "อีเมล์" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "เบอร์" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ระดับ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ชื่อผู้ใช้" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รหัสผ่าน" + "</th>");
            str.append("<th style='background-color : #98FB98'></th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            while (rs.next()) {
                str.append("<tr style='background-color : #E0FFFF'>");
                str.append("<td>" + rs.getString("tb_manage_data_user.user_id") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_user.fname") + "&nbsp;" + rs.getString("tb_manage_data_user.lname") + "</td>");
                str.append("<td>" + "<img src='image/" + rs.getString("tb_manage_data_user.image") + "' style='width:50px ; height: 50px'>" + "</td>");
                //str.append("<td>" + rs.getString("tb_manage_data_user.birthdate") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_user.sex") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_company.company_name") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_major.major_name") + "</td>");
                //str.append("<td>" + rs.getString("tb_manage_data_user.address") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_user.email") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_user.tel") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_level.level") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_user.username") + "</td>");
                str.append("<td>" + rs.getString("tb_manage_data_user.password") + "</td>");

                String user_id_get = rs.getString("tb_manage_data_user.user_id");
                if (user_id_get.length() == 3) {
                    String user_id = (user_id_get.charAt(2)) + "";
                    str.append("<td><a  onclick ='return edit_data_user(" + user_id + ")'><img src='img/edit.png' width='25' height='25'></a>&nbsp;&nbsp;&nbsp;&nbsp<a  onclick ='return delete_data_user(" + user_id + ")'><img src='img/delete.png' width='25' height='25'></a></td>");
                } else if (user_id_get.length() > 3) {
                    int user_length = ((user_id_get.length()));
                    String user_id = user_id_get.substring(2, user_length);
                    str.append("<td><a   onclick ='return edit_data_user(" + user_id + ")'><img src='img/edit.png' width='25' height='25'></a>&nbsp;&nbsp;&nbsp;&nbsp<a  onclick ='return delete_data_user(" + user_id + ")'><img src='img/delete.png' width='25' height='25'></a></td>");
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

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String user_id = request.getParameter("user_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM" + "((tb_manage_data_user "
                    + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id)" + " "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id)" + "JOIN tb_manage_data_level "
                    + "ON tb_manage_data_user.level_id = tb_manage_data_level.level_id " + ""
                    + " WHERE tb_manage_data_user.user_id ='" + user_id + "'";
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                String id = rs.getString("tb_manage_data_user.user_id");
                String fname = rs.getString("tb_manage_data_user.fname");
                String lname = rs.getString("tb_manage_data_user.lname");
                String birthdate = rs.getString("tb_manage_data_user.birthdate");
                String sex = rs.getString("tb_manage_data_user.sex");
                String company_name = rs.getString("tb_manage_data_company.company_name");
                String major_name = rs.getString("tb_manage_data_major.major_name");
                String address = rs.getString("tb_manage_data_user.address");
                String email = rs.getString("tb_manage_data_user.email");
                String tel = rs.getString("tb_manage_data_user.tel");
                String level = rs.getString("tb_manage_data_level.level");
                String username = rs.getString("tb_manage_data_user.username");
                String password = rs.getString("tb_manage_data_user.password");
                String image = rs.getString("tb_manage_data_user.image");
                response.getWriter().print(id);
                response.getWriter().print(",");
                response.getWriter().print(fname);
                response.getWriter().print(",");
                response.getWriter().print(lname);
                response.getWriter().print(",");
                response.getWriter().print(birthdate);
                response.getWriter().print(",");
                response.getWriter().print(sex);
                response.getWriter().print(",");
                response.getWriter().print(company_name);
                response.getWriter().print(",");
                response.getWriter().print(major_name);
                response.getWriter().print(",");
                response.getWriter().print(address);
                response.getWriter().print(",");
                response.getWriter().print(email);
                response.getWriter().print(",");
                response.getWriter().print(tel);
                response.getWriter().print(",");
                response.getWriter().print(level);
                response.getWriter().print(",");
                response.getWriter().print(username);
                response.getWriter().print(",");
                response.getWriter().print(password);
                response.getWriter().print(",");
                response.getWriter().print(image);
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String user_id = request.getParameter("user_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "DELETE FROM tb_manage_data_user WHERE user_id = '" + user_id + "'";
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
}