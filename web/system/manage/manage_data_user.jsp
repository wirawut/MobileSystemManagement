<%-- 
    Document   : manage_data_user
    Created on : 27 พ.ค. 2556, 17:22:34
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript" src="../../js/my_ajax.js"></script>
<script type="text/javascript">
    $(function() {
        $("#birthdate").datepicker({dateFormat: 'yy-mm-dd'});
        $("#get_option_company").load("controller.manage_data_company?get_option_company");
        $("#get_option_major").load("controller.manage_data_major?get_option_major");
        $("#get_option_level").load("controller.manage_data_level?get_option_level");

        $("#hide").click(function() {
            $("#show_data_user").slideUp("slow");
        });
        $("#show").click(function() {
            bind_data_user();
            $("#show_data_user").slideDown("slow");
        });

        $("#cmd_save").click(function() {
            $.ajax({
                url: "controller.manage_data_user?save",
                type: "post",
                data: $("#manage_data_user").serialize(),
                success: function(data) {
                    if (data == "complete") {

                        alert("บันทึกข้อมูลแล้ว");
                        $("#content").load('system/manage/manage_data_user.jsp');
                        $("#user_id").val("1");
                        // clear_data();
                    }
                    else if (data == "no_complete") {
                        alert("คุณกรอกไม่ได้กรอกข้อมูล หรือข้อมูลที่คุณกรอกซ้ำกัน");
                        clear_data();
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

        $("#fname").val("");
        $("#lname").val("");
        $("#birthdate").val("");
        $("#address").val("");
        $("#email").val("");
        $("#tel").val("");
        $("#username").val("");
        $("#password").val("");
        $("#image").val("");
    }
    function bind_data_user() {
        $("#show_data_user").load("controller.manage_data_user?show_data_user");
    }

    function delete_data_user(id) {

        var user_id = "EM" + id;
        if (confirm("กรุณายืนยันการลบอีกครั้ง !!!")) {
            $.ajax({
                url: "controller.manage_data_user?delete",
                type: "post",
                data: {user_id: user_id},
                success: function(data) {
                    if (data == "complete") {
                        bind_data_user();
                        $("#content").load('system/manage/manage_data_user.jsp');
                    }

                    else {
                        alert(data);
                    }

                }
            });
            return true;
        } else {
            return false;
        }
    }

    function edit_data_user(id) {
        var user_id = "EM" + id;
        $.ajax({
            url: "controller.manage_data_user?edit",
            type: "post",
            data: {user_id: user_id},
            success: function(data) {
                var arr = data.split(",");

                $("#user_id").val(arr[0]);
                $("#fname").val(arr[1]);
                $("#lname").val(arr[2]);
                $("#birthdate").val(arr[3]);
                $("#sex option[value=" + arr[4] + "]").attr("selected", "selected");
                $("#id  option[value=" + arr[5] + "]").attr("selected", "selected");
                // $("#major_id option[value=" +arr[6]+ "]").attr("selected","selected");
                $("#address").val(arr[7]);
                $("#email").val(arr[8]);
                $("#tel").val(arr[9]);
                //$("#level_id option[value=" +arr[10]+ "]").attr("selected","selected");
                $("#username").val(arr[11]);
                $("#password").val(arr[12]);
                $("#image").val(arr[13]);
            }
        });
    }

    function open_window_upload() {
        var style_dialog = "dialogWidth:400px;dialogHeight:300px;dialogLeft:450px;";
        var w = window.showModalDialog("choose_file_dialog.jsp", null, style_dialog);
        //ถ้ามีการเปิดหน้าต่างใหม่จริงๆ

        //alert(w);
        if (w != null) {
            $("#image").val(w);
            //var image_select = getControlId("image_select");
            //image_select.innerHTML = w ;
        } else {
            alert("ไม่มีรูปภาพ");
        }

        //$("#image_select").val(w);
    }
</script>
<%


    //เพิ่มค่าตัวเลข repair_id บวกเข้ากับสตริง
    Connection c = (Connection) getServletContext().getAttribute("conn");
    String sql = " SELECT MAX( user_id ) AS user_id FROM ((tb_manage_data_user "
            + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
            + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
            + "JOIN tb_manage_data_level ON tb_manage_data_user.level_id = tb_manage_data_level.level_id "
            + "ORDER BY user_id ";//PM3
    ResultSet rs = c.createStatement().executeQuery(sql);
    if (rs.next()) {
        String user = rs.getString("user_id"); //PM30
        if (user.length() == 3) { // = 2 เพราะ 0 , 1 , 2  ก็คือ ถ้ามันมี3ตัวอักษร
            session.setAttribute("i", (Integer.parseInt(user.substring(2, user.length()))) + 1); //ได้3  เอาไปเก็บไว้ใน i
        } else if (user.length() > 3) { //ถ้ามันมี 3ตัวอักษรขึ้นไป
            session.setAttribute("i", (Integer.parseInt(user.substring(2, user.length()))) + 1); //ต้องตัดสตริงตามำเเหน่งที่ต้องการ คือ  2 - 3 เช่น PM30
        }
    } else {
        Integer i = 1;
        session.setAttribute("i", i);
    }
%>
<form id="manage_data_user">
    <legend>จัดการข้อมูลผู้ใช้งานระบบ</legend>
    <input type="button" class="btn-info btn-medium" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-medium" value="ซ่อน" id="hide">
    <div id="show_data_user">
    </div>
    รหัสผู้ใช้งาน&nbsp;<input type="text" name="user_id" id="user_id" class="input-mini" style="padding: 5px;width: 40px;font-size: 15px ;margin: 3px ; background-color: orange ; color: #000 ;  height: 38px"  value="EM<%=session.getAttribute("i")%>">
    &nbsp;&nbsp;&nbsp;&nbsp;ชื่อ&nbsp;<input type="text" name="fname" id="fname" class="input-medium" style="margin: 3px ;  height: 38px" >
    &nbsp;&nbsp;&nbsp;&nbsp;สกุล&nbsp;<input type="text" name="lname" id="lname" class="input-medium" style="margin: 3px ;  height: 38px">
    &nbsp;&nbsp;&nbsp;&nbsp;วันเกิด&nbsp;<input type="text" name="birthdate" id="birthdate" class="input-small" style="margin: 3px ;  height: 38px">
    &nbsp;&nbsp;&nbsp;&nbsp;เพศ&nbsp;<select name="sex" id="sex" style='margin: 3px ;  height: 38px'>
        <option value = "" >-----กรุณาเลือก-----</option>
        <option class = "special" value="ชาย">ชาย</option>
        <option class = "special" value="หญิง">หญิง</option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;บริษัท/ห้างร้าน&nbsp;<span id="get_option_company" class="input-large" ></span>
    &nbsp;&nbsp;&nbsp;&nbsp; สาขา&nbsp;<span id="get_option_major" class="input-large" ></span>
    &nbsp;&nbsp;&nbsp;&nbsp;ที่อยู่&nbsp;<textarea class="field span12" name ="address" id="address" cols ="14" rows="3" style="resize:none ; margin: 3px ; width: 300px"></textarea>
    &nbsp;&nbsp;&nbsp;&nbsp; อีเมล์&nbsp;<input type="text" name="email" id="email" class="input-medium" style="margin: 3px ;  height: 38px">
    &nbsp;&nbsp;&nbsp;&nbsp; เบอร์โทร&nbsp;<input type="text" name="tel" id="tel" class="input-medium" style="margin: 3px ;  height: 38px">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="get_option_level" class="input-large"  ></span>
    &nbsp;&nbsp;&nbsp;&nbsp; ชื่อผู้ใช้งาน&nbsp;<input type="text" name="username" id="username" class="input-medium" style="margin: 3px ;  height: 38px">
    &nbsp;&nbsp;&nbsp;&nbsp; รหัสผ่าน&nbsp;<input type="text" name="password" id="password" class="input-medium" style="margin: 3px ;  height: 38px">
    &nbsp;&nbsp;&nbsp;&nbsp;รูปภาพ&nbsp;<input type="text" class="input-medium" id="image" name="image" style="margin: 3px;  height: 38px">&nbsp;<input type="button" value="เลือกรูปภาพ" class="btn btn-info" onClick="open_window_upload()" style="margin: 3px;padding: 6px ">
    <br><input type="button" id="cmd_save" class="btn btn-medium btn-info" value="บันทึกข้อมูล"  style ="margin-left: 3px; margin-right: 3px ;margin-top: 3px ; margin-bottom: 3px ;padding: 5px;width: 100px;height: 45px ">
</form>



