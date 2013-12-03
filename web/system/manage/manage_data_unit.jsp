<%-- 
    Document   : manage_data_unit
    Created on : Jul 29, 2013, 6:27:22 PM
    Author     : Hippo-G8
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){
        $("#hide").click(function(){
            $("#show_data_unit").slideUp("slow");
        });
        $("#show").click(function(){
            bind_data_unit();
            $("#show_data_unit").slideDown("slow");
        });
        
        $("#cmd_save").click(function(){
            $.ajax({
                url:"controller.manage_data_unit?save",
                type:"post",
                data: $("#manage_data_unit").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                        $("#unit_id").val("1");
                        bind_data_unit();
                        clear_data();
                    }
                    else if(data == "no_complete"){
                        alert("คุณกรอกไม่ได้กรอกข้อมูล หรือข้อมูลที่คุณกรอกซ้ำกัน");
                        clear_data();
                    }
                    else{
                        alert("ไม่สามารถบันทึกข้อมูลได้");
                    }
                }
            });
            return false;
        });
    });
    function clear_data(){
        $("#unit").val("");
        $("#detail").val("");
    }
    function bind_data_unit(){
        $("#show_data_unit").load("controller.manage_data_unit?show_data_unit");
    }
    
    function edit_data_unit(unit_id){
        $.ajax({
            url:"controller.manage_data_unit?edit",
            type:"post",
            data: {unit_id : unit_id},
            success:function(data){
                //แยกคำที่รับค่ามาจากการresponse จากservletออกจากกันเพื่อนำมาใช้งาน
                var arr = data.split ( "," );
                $("#unit_id").val(unit_id); 
                $("#unit").val(arr[0]); //ส่งไปเเบบhiddenเพื่อเก็บค่านี้ไว้ใช้งาน
                $("#detail").val(arr[1]);
            }
        });
    }
    function delete_data_unit(unit_id){
        if(confirm("กรุณายืนยันการลบอีกครั้ง !!!")){

            $.ajax({
                url: "controller.manage_data_unit?delete",
                type: "post",
                data: {unit_id : unit_id},
                success:function(data){
                    if(data == "complete"){
                        bind_data_unit();
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
</script>
<form id="manage_data_unit">
    <legend>จัดการข้อมูลหน่วยนับสินค้า</legend>
    <input type="button" class="btn-info btn-medium" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-medium" value="ซ่อน" id="hide">
    <div id="show_data_unit">
    </div>
    <table>
        <tr>
            <td><input type="hidden" name="unit_id" id="unit_id" class="input-large" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>หน่วยนับสินค้า</td>
            <td><input type="text" name="unit" id="unit" class="input-large" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>รายละเอียด</td>
            <td><textarea class="field span12" name ="detail" id="detail" cols ="14" rows="4" style="resize:none ; margin: 3px ; width: 300px"></textarea></td>
        </tr>
        <tr>
            <td colspan="8"><input type="button" id="cmd_save" class="btn btn-medium btn-info" value="บันทึกข้อมูล"  style ="margin-left: 90px; margin-right: 3px ;margin-top: 3px ; margin-bottom: 3px ;padding: 5px;width: 100px;height: 45px "></td>
        </tr>
    </table>
</form>



