/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class repair_mobile_order extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("check") != null) {
                check(request, response);
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
        try {
            String sale_mobile_id = request.getParameter("sale_mobile_id");
            if (sale_mobile_id != "") {
                Connection c = (Connection) getServletContext().getAttribute("conn");
                String sql = "SELECT * FROM tb_sale_product_mobile WHERE sale_mobile_id = '" + sale_mobile_id + "'";
                ResultSet rs = c.createStatement().executeQuery(sql);

                if (rs.next()) {
                    //เซสชั่น
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    Date date_insurance_end = rs.getDate("date_insurance_end");
                    HttpSession session = request.getSession();
                    session.setAttribute("fname", fname);
                    session.setAttribute("lname", lname);
                    session.setAttribute("date_insurance_end", date_insurance_end);
                    Calendar calendar = new GregorianCalendar();
                    int year = calendar.get(calendar.YEAR);
                    int month = calendar.get(calendar.MONTH);
                    int date = calendar.get(calendar.DATE);

                    //montต้อง+1เพราะว่ามันนับเดือนย้อนหลัง เช่น มิถุนายนนับเป็น5 เเละต้อง+0เข้าไปกรณีที่น้อยกว่าหรือเท่ากับ9มันจะไม่ใส่0ข้างหน้าให้
                    if (month <= 9) {
                        String today = year + "-" + ("0" + (month + 1)) + "-" + date;
                        session.setAttribute("today", today);
                    } else {
                        String today = year + "-" + (month + 1) + "-" + date;
                        session.setAttribute("today", today);
                    }
                    response.getWriter().print("complete");
                } else {
                    response.getWriter().print("no_complete");
                }
            } else {
                response.getWriter().print("data_null");
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String repair_mobile_id = request.getParameter("repair_mobile_id");
        String sale_mobile_id = request.getParameter("sale_mobile_id");
        String mobile_type_id = request.getParameter("mobile_type_id");
        String mobile_id = request.getParameter("mobile_id");
        String topic_repair_mobile_id = request.getParameter("topic_repair_mobile_id");
        String detail = request.getParameter("detail");
        String price = request.getParameter("price");
        String status_id = request.getParameter("status_id");
        String date_get_product = request.getParameter("date_get_product");
        try {

            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_sale_product_mobile WHERE sale_mobile_id = '" + sale_mobile_id + "'";
            ResultSet rs = c.createStatement().executeQuery(sql);

            if (rs.next()) {

                //วันปัจจุบัน
                Calendar calendar = new GregorianCalendar();
                String today = calendar.get(calendar.YEAR) + "-" + calendar.get(calendar.MONTH) + "-" + calendar.get(calendar.DATE);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                //วันหมดอายุ
                String date_insurance_end = rs.getString("date_insurance_end");
                Date d1 = (Date) df.parse(today);
                Date d2 = (Date) df.parse(date_insurance_end);

                //กรณีอาการอยู่ในประกันเพราะไม่ได้กรอกอาการนอกประกันเลยไม่ได้คิดราคา
                //ตรวจสอบด้วยว่าวันปัจจุบันนี้ก่อนหรือเท่ากับวันหมดประกันหรือไม่ถ้าเป็นจริงเเสดงว่ายังไม่หมดประกันจึงซ่อมให้ฟรี
                if ((detail == "") || (d1.before(d2)) || (d1.equals(d2))) {

                    //คิดเงิน0บาท
                    price = "0";
                    sql = "INSERT INTO tb_repair_mobile_order(repair_mobile_id,sale_mobile_id,mobile_type_id,mobile_id,topic_repair_mobile_id,detail,price,status_id,date_get_product,date_save) VALUES(?,?,?,?,?,?,?,?,?,NOW())";
                    PreparedStatement pre = c.prepareStatement(sql);
                    pre.setString(1, repair_mobile_id);
                    pre.setString(2, sale_mobile_id);
                    pre.setString(3, mobile_type_id);
                    pre.setString(4, mobile_id);
                    pre.setString(5, topic_repair_mobile_id);
                    pre.setString(6, detail);
                    pre.setString(7, price);
                    pre.setString(8, status_id);
                    pre.setString(9, date_get_product);

                    if ((repair_mobile_id != "") && (sale_mobile_id != "") && (mobile_type_id != "") && (mobile_id != "") && (topic_repair_mobile_id != "") && (status_id != "")) {
                        pre.executeUpdate();
                        response.getWriter().print("complete");
                    } else {
                        response.getWriter().print("no_complete");
                    }
                    //ถ้ากรอกอาการนอกประกันมา และ
                } else if ((detail != "") || ((d1.after(d2)))) {
                    //อาการอยู่นอกประกันคิดเงินตามราคาที่กำหนด
                    sql = "INSERT INTO tb_repair_mobile_order(repair_mobile_id,sale_mobile_id,mobile_type_id,mobile_id,topic_repair_mobile_id,detail,price,status_id,date_get_product,date_save) VALUES(?,?,?,?,?,?,?,?,?,NOW())";
                    PreparedStatement pre = c.prepareStatement(sql);
                    pre.setString(1, repair_mobile_id);
                    pre.setString(2, sale_mobile_id);
                    pre.setString(3, mobile_type_id);
                    pre.setString(4, mobile_id);
                    pre.setString(5, topic_repair_mobile_id);
                    pre.setString(6, detail);
                    pre.setString(7, price);
                    pre.setString(8, status_id);
                    pre.setString(9, date_get_product);
                    if ((repair_mobile_id != "") && (sale_mobile_id != "") && (mobile_type_id != "") && (mobile_id != "") && (price != "") && (status_id != "") && (topic_repair_mobile_id != "") && (date_get_product != "")) {
                        pre.executeUpdate();
                        response.getWriter().print("complete");
                    } else {
                        response.getWriter().print("no_complete");
                    }
                }
            } else {
                sale_mobile_id = "0";
                sql = "INSERT INTO tb_repair_mobile_order(repair_mobile_id,sale_mobile_id,mobile_type_id,mobile_id,topic_repair_mobile_id,detail,price,status_id,date_get_product,date_save) VALUES(?,?,?,?,?,?,?,?,?,NOW())";
                PreparedStatement pre = c.prepareStatement(sql);
                pre.setString(1, repair_mobile_id);
                pre.setString(2, sale_mobile_id);
                pre.setString(3, mobile_type_id);
                pre.setString(4, mobile_id);
                pre.setString(5, topic_repair_mobile_id);
                pre.setString(6, detail);
                pre.setString(7, price);
                pre.setString(8, status_id);
                pre.setString(9, date_get_product);
                if ((repair_mobile_id != "") && (sale_mobile_id != "") && (mobile_type_id != "") && (mobile_id != "") && (price != "") && (status_id != "") && (topic_repair_mobile_id != "") && (date_get_product != "")) {
                    pre.executeUpdate();
                    response.getWriter().print("complete");
                } else {
                    response.getWriter().print("no_complete");
                }
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }


    }
}
