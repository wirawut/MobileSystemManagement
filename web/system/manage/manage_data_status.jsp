<%-- 
    Document   : manage_data_status.jsp
    Created on : 10 มิ.ย. 2556, 22:00:11
    Author     : Admin
--%>

<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){
        $("#status_id").val("1");
        $("#hide").click(function(){
            $("#show_data_status").slideUp("slow");
        });
        $("#show").click(function(){
            bind_data_status();
            $("#show_data_status").slideDown("slow");
        });
        
        $("#cmd_save").click(function(){
            $.ajax({
                url:"controller.manage_data_status?save",
                type:"post",
                data: $("#manage_data_status").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                        $("#status_id").val("1");
                        bind_data_status();
                        clear_data();
                    }
                    else if(data == "no_complete"){
                        alert("คุณกรอกไม่ได้กรอกข้อมูล หรือข้อมูลที่คุณกรอกซ้ำกัน");
                        clear_data();
                    }
                    else{
                        alert(data);
                    }
                }
            });
        });
    });
    function clear_data(){
        $("#status").val("");

    }
    function bind_data_status(){
        $("#show_data_status").load("controller.manage_data_status?show_data_status");
    }
    function delete_data_status(status_id){
        if(confirm("กรุณายืนยันการลบอีกครั้ง !!!")){
            $.ajax({
                url:"controller.manage_data_status?delete",
                type:"post",
                data: {status_id : status_id},
                success:function(data){
                    if(data == "complete"){
                        bind_data_status();
                    }
                    else if(data = "no_complete"){
                        alert(data);
                    }
                }
            });
            return true;
        }else{
            return false;
        }
    }
    function edit_data_status(status_id){    
        $.ajax({
            url:"controller.manage_data_status?edit",
            type:"post",
            data: {status_id : status_id},
            success:function(data){
                if(data != ""){
                    $("#status_id").val(status_id);
                    $("#status").val(data);
                    bind_data_status();
                }
                
            }
        });
    }
</script>
<form id="manage_data_status">
    <legend>จัดการข้อมูลสถานะการซ่อม</legend>
    <input type="button" class="btn-info btn-medium" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-medium" value="ซ่อน" id="hide">
    <div id="show_data_status">
    </div>
    <table>
        <tr>

            <td><input type="hidden" name="status_id" id="status_id" class="input-large" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>สถานะการซ่อม</td>
            <td><input type="text" name="status" id="status" class="input-large" style="margin: 3px"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" id="cmd_save" class="btn btn-success btn-large" value="บันทึกข้อมูล" style ="margin-left: 90px ; margin-bottom: 3px; margin-right: 3px ; margin-top: 3px"></td>
        </tr>
    </table>
</form>
