<%-- 
    Document   : sale_mobile
    Created on : 22 เม.ย. 2556, 15:33:35
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){
        //เรียกใช้งานฟังก์ชั่นbindManageDataCompany()
        $("#show_data_mobile").load("controller.showData?show_data_mobile");
        $("#get_option_type_mobile").load("controller.manage_data_product_type_mobile?get_option");
        $("#get_option_mobile").load("controller.manage_data_product_mobile?get_option");

        //ซ่อน
        $("#hide").click(function(){
            $("#show_data_product_mobile").slideUp("slow");
        });
        
        //โชว
        $("#show").click(function(){
            bind_data_product_mobile();
            $("#show_data_product_mobile").slideDown("slow");
        });
     
        
        $("#cmdSave").click(function(){
            $.ajax({
                url:"controller.sale_mobile?save",
                type:"post",
                data: $("#sale_mobile").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                      
                    }
                    else if(data == "no_complete"){
                        alert("ท่านต้องกรอกชื่อบริษัท/ห้างร้าน");
                        
                    }
                    else{
                        alert(data);
                    }
                }
            });
        });
    });
    function bind_data_product_mobile(){
        $("#show_data_product_mobile").load("controller.manage_data_product_mobile?show_data_product_mobile");
    }
    
    
</script>
<%
    //เพิ่มค่าตัวเลข repair_id บวกเข้ากับสตริง
    Connection c = (Connection) getServletContext().getAttribute("conn");
    String sql = "SELECT count(sale_mobile_id) AS count_row FROM tb_sale_product_mobile";
    ResultSet rs = c.createStatement().executeQuery(sql);
    if (rs.next()) {
        Integer i = rs.getInt("count_row") + 1;
        session.setAttribute("i", i);
    } else {
        Integer i = 1;
        session.setAttribute("i", i);
    }
%>
<form id="sale_mobile">
    <legend>ขายโทรศัพท์</legend>
    <input type="button" class="btn-info btn-medium" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-medium" value="ซ่อน" id="hide">
    <div id="show_data_product_mobile">
    </div>
    <br>
    <table>
        
        <tr>
            <td>เลขที่ขาย</td>
            <td colspan="2"><input type="text" name="sale_id" id="sale_id" class="input-mini" style="padding: 5px;width: 40px;font-size: 15px ;margin: 3px ; background-color: orange ; color: #000"  value="S<%=session.getAttribute("i")%>"></td>
        </tr>
        <tr>
            <td>รหัสลูกค้า</td>
            <td colspan="2"><input type="text" name="customer_id" id="customer_id" class="input-small" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>อัตราภาษี</td>
            <td colspan="2"><input type="text" name="tax_rate" id="tax_rate" class="input-small" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>สถานะการจ่ายเงิน</td>
                <td class="radio"><label style="margin: 3px"><input id="payment_status" type="radio" checked="" value="option1" name="payment_status">ยังไม่ชำระ</label></td>
                <td class="radio"><label style="margin: 3px"><input id="payment_status" type="radio" value="option2" name="payment_status">ชำระแล้ว</label></td>
        </tr>
        <tr>
            <td>สถานะการจัดส่ง</td>
            <td class="radio"><label style="margin: 3px"><input id="shipping_status" type="radio" checked="" value="option1" name="shipping_status" >ยังไม่จัดส่ง</label></td>
            <td class="radio"><label style="margin: 3px"><input id="shipping_status" type="radio" value="option2" name="shipping_status">อยู่ในระหว่างการจัดส่ง</label></td>
            <td class="radio"><label style="margin: 3px"><input id="shipping_status" type="radio" value="option3" name="shipping_status">จัดส่งเรียบร้อย</label></td>
        </tr>
        <tr>
            <td>ส่วนลด</td>
            <td colspan="2"><input type="text" name="discount" id="discount" class="input-mini" style="margin: 3px"><td>
        <tr>
        <tr>
            <td>รหัสพนักงาน</td>
            <td colspan="2"><input type="text" name="user_id" id="user_id" class="input-small" style="margin: 3px"><td>
        </tr>
        <tr>
            <td>วันที่ขาย</td>
            <td colspan="2"><input type="text" name="date_sale" id="date_sale" class="input-medium" style="margin: 3px"></td>
        </tr>
        <tr>
            <td colspan="3"><input type="button" id="cmdSave" class="btn btn-success btn-small" value="บันทึกการขาย"  style ="margin-left: 108px ; margin-bottom: 3px; margin-right: 3px ; margin-top: 3px"></td>
        </tr>
    </table>
</form>


