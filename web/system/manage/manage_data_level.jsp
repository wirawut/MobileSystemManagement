<%-- 
    Document   : manage_data_level
    Created on : 26 พ.ค. 2556, 21:16:26
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function() {
        $("#hide").click(function() {
            $("#show_data_level").slideUp("slow");
        });
        $("#show").click(function() {
            bind_data_level();
            $("#show_data_level").slideDown("slow");
        });

        $("#cmd_save").click(function() {
            $.ajax({
                url: "controller.manage_data_level?save",
                type: "post",
                data: $("#manage_data_level").serialize(),
                success: function(data) {
                    if (data == "complete") {
                        alert("บันทึกข้อมูลแล้ว");
                        $("#level_id").val("1");
                        bind_data_level();
                        clear_data();
                    }
                    else if (data == "no_complete") {
                        alert("คุณกรอกไม่ได้กรอกระดับผู้ใช้งาน");
                        clear_data();
                    } else if (data == "java.lang.NullPointerException") {
                        alert("กรุณาเลือกสิทธิการเข้าถึงอย่างน้อย1สิทธิ");

                    } else {
                        alert("ไม่สามารถบันทึกข้อมูลได้");
                    }

                }
            });
            return false;
        });
    });
    function clear_data() {
        $("#level").val("");
    }
    function bind_data_level() {
        $("#show_data_level").load("controller.manage_data_level?show_data_level");
    }
    function edit_data_level(level_id) {
        $.ajax({
            url: "controller.manage_data_level?edit",
            type: "post",
            data: {level_id: level_id},
            success: function(data) {
                if (data != "") {
                    $("#level_id").val(level_id); //ส่งไปเเบบhiddenเพื่อเก็บค่านี้ไว้ใช้งาน
                    $("#level").val(data);
                    bind_data_status();
                }

            }
        });
    }
    function delete_data_level(level_id) {
        if (confirm("กรุณายืนยันการลบอีกครั้ง !!!")) {

            $.ajax({
                url: "controller.manage_data_level?delete",
                type: "post",
                data: {level_id: level_id},
                success: function(data) {
                    if (data == "complete") {
                        bind_data_level();
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
</script>
<form id="manage_data_level">
    <legend>ข้อมูลระดับผู้ใช้งาน</legend>
    <input type="button" class="btn-info btn-medium" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-medium" value="ซ่อน" id="hide">
    <div id="show_data_level">
    </div>
    <table>
        <tr>
            <td><input type="hidden" name="level_id" id="level_id" class="input-large" value="1" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>ระดับผู้ใช้งาน</td>
            <td colspan="8"><input type="text" name="level" id="level" class="input-large" style="margin: 3px;  height: 38px" ></td>
        </tr>
        <tr>

            <td>สิทธิการเข้าถึง</td>
            <td><div class='switch demo1'><input type="checkbox" name="access" id="access" value="1"><label></label></div></td> <td>ขาย &nbsp; &nbsp;</td>
            <td><div class='switch demo1'><input type="checkbox" name="access" id="access" value="3"><label></label></div></td><td>รายงาน &nbsp; &nbsp;</td> 
            <td><div class='switch demo1'><input type="checkbox" name="access" id="access" value="2"><label></label></div></td><td>คลังสินค้า &nbsp; &nbsp;</td>
            <td><div class='switch demo1'><input type="checkbox" name="access" id="access" value="4"><label></label></div></td><td>ตั้งค่าพื้นฐาน &nbsp; &nbsp;</td>
        </tr>
        <tr>
            <td colspan="8"><input type="button" id="cmd_save" class="btn btn-medium btn-info" value="บันทึกข้อมูล"  style ="margin-left: 90px; margin-right: 3px ;margin-top: 3px ; margin-bottom: 3px ;padding: 5px;width: 100px;height: 45px "></td>
        </tr>
    </table>
</form>


