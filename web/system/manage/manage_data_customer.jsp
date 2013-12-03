<%-- 
    Document   : manage_data_customer
    Created on : 18 เม.ย. 2556, 16:28:14
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function() {
        $("#birthdate").datepicker({dateFormat: 'yy-mm-dd'});
        $("#hide").click(function() {
            $("#show_data_customer").slideUp("slow");
        });
        $("#show").click(function() {
            bind_data_customer();
            $("#show_data_customer").slideDown("slow");
        });

        $("#cmd_save").click(function() {
            $.ajax({
                url: "controller.manage_data_customer?save",
                type: "post",
                data: $("#manage_data_customer").serialize(),
                success: function(data) {
                    alert(data);
                    if (data == "complete") {
                        alert("บันทึกข้อมูลแล้ว");
                        $("#content").load('system/manage/manage_data_customer.jsp');
                        bind_data_customer();

                        //clear_data();
                    }
                    else if (data == "no_complete") {
                        alert("กรุณากรอกข้อมูลให้ครบ");
                        //clear_data();
                    }
                    else {
                        alert("ไม่สามารถบันทึกข้อมูลได้");
                    }
                }
            });
            return false;
        });
    });
    function clear_data() {
        $("#customer_id").val("");
        $("#fname").val("");
        $("#lname").val("");
        $("#sex").val("");
        $("#birthdate").val("");
        $("#email").val("");
        $("#tel").val("");
        $("#address").val("");

    }
    function bind_data_customer() {
        $("#show_data_customer").load("controller.manage_data_customer?show_data_customer");
    }
    function delete_data_customer(customer_id) {
        var customer_id = "CM" + customer_id;
        if (confirm("กรุณายืนยันการลบอีกครั้ง !!!")) {
            $.ajax({
                url: "controller.manage_data_customer?delete",
                type: "post",
                data: {customer_id: customer_id},
                success: function(data) {
                    if (data == "complete") {
                        bind_data_customer();
                        $("#content").load('system/manage/manage_data_customer.jsp');
                    }
                    else if (data = "no_complete") {
                        alert(data);
                    }
                }
            });
            return true;
        } else {
            return false;
        }
    }
    function edit_data_customer(customer_id) {
        var customer_id = "CM" + customer_id;
        $.ajax({
            url: "controller.manage_data_customer?edit",
            type: "post",
            data: {customer_id: customer_id},
            success: function(data) {

                var arr = data.split(",");

                $("#customer_id").val(arr[0]);
                $("#fname").val(arr[1]);
                $("#lname").val(arr[2]);
                $("#sex").val(arr[3]);
                $("#birthdate").val(arr[4]);
                // $("#get_option_unit").val(arr[6]);
                $("#email").val(arr[5]);
                $("#tel").val(arr[6]);
                $("#card_number").val(arr[7]);
                $("#address").val(arr[8]);
            }
        });
    }
</script>
<%


    //เพิ่มค่าตัวเลข repair_id บวกเข้ากับสตริง
    Connection c = (Connection) getServletContext().getAttribute("conn");
    String sql = " SELECT MAX( customer_id ) AS customer_id FROM tb_manage_data_customer ORDER BY customer_id ";//PM3
    ResultSet rs = c.createStatement().executeQuery(sql);
    if (rs.next()) {
        String customer = rs.getString("customer_id"); //PM30
        if (customer.length() == 3) { // = 2 เพราะ 0 , 1 , 2  ก็คือ ถ้ามันมี3ตัวอักษร
            session.setAttribute("i", (Integer.parseInt(customer.substring(2, customer.length()))) + 1); //ได้3  เอาไปเก็บไว้ใน i
        } else if (customer.length() > 3) { //ถ้ามันมี 3ตัวอักษรขึ้นไป
            session.setAttribute("i", (Integer.parseInt(customer.substring(2, customer.length()))) + 1); //ต้องตัดสตริงตามำเเหน่งที่ต้องการ คือ  2 - 3 เช่น PM30
        }
    } else {
        Integer i = 1;
        session.setAttribute("i", i);
    }
%>
<form id="manage_data_customer">
    <legend>ข้อมูลลูกค้า</legend>
    <input type="button" class="btn-info btn-medium" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-medium" value="ซ่อน" id="hide">
    <div id="show_data_customer">
    </div>
    <table>

        <tr>
            <td>รหัสลูกค้า</td>
            <td colspan="2"><input type="text" name="customer_id" id="customer_id" class="input-mini" style="padding: 5px;width: 60px;font-size: 15px ;margin: 3px ; background-color: orange ; color: #000 ;  height: 38px"  value="CM<%=session.getAttribute("i")%>">&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>ชื่อ</td>
            <td><input type="text" name="fname" id="fname" class="input-medium" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>สกุล</td>
            <td><input type="text" name="lname" id="lname" class="input-medium" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>เพศ</td>
            <td><select name="sex" id="sex" style='margin: 3px'>
                    <option value = "" >-----กรุณาเลือก-----</option>
                    <option class = "special" value="ชาย">ชาย</option>
                    <option class = "special" value="หญิง">หญิง</option>
                </select></td>
        </tr>
        <tr>
            <td>วันเกิด</td>
            <td><input type="text" name="birthdate" id="birthdate" class="input-medium" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>อีเมล์</td>
            <td><input type="text" name="email" id="email" class="input-medium" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>เบอร์</td>
            <td><input type="text" name="tel" id="tel" class="input-medium" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>เลขที่บัตรประชาชน</td>
            <td><input type="text" name="card_number" id="card_number" class="input-large" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>ที่อยู่</td>
            <td><textarea class="field span12" name ="address" id="address" cols ="14" rows="2" style="resize:none ; margin: 3px ; width: 300px"></textarea></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" id="cmd_save" class="btn btn-medium btn-info" value="บันทึกข้อมูล"  style ="margin-left: 116px ; margin-bottom: 3px; margin-right: 3px ; margin-top: 3px ;padding: 5px;width: 100px;height: 45px "></td>
        </tr>
    </table>
</form>
