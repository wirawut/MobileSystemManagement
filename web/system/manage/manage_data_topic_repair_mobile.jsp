<%-- 
    Document   : manage_data_topic_repair_mobile
    Created on : 23 เม.ย. 2556, 0:50:58
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
     $(function(){
        
        
        $("#hide").click(function(){
            $("#show_data_topic_repair_mobile").slideUp("slow");
        });
        $("#show").click(function(){
            bind_data_topic_repair_mobile();
            $("#show_data_topic_repair_mobile").slideDown("slow");
        });
        
         $("#cmd_save").click(function(){
            $.ajax({
                url:"controller.manage_data_topic_repair_mobile?save",
                type:"post",
                data: $("#manage_data_topic_repair_mobile").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                        //เซ็ต1เป็นค่าdefault เพื่อไม่ให้มันเป้นค่าว่าง ไม่เช่นนั้นจะบันทึกไม่ได้
                        $("#topic_repair_mobile_id").val("1");
                        bind_data_topic_repair_mobile()
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
        $("#topic_repair_mobile").val("");
        $("#detail").val("");
    }
    function bind_data_topic_repair_mobile(){
        $("#show_data_topic_repair_mobile").load("controller.manage_data_topic_repair_mobile?show_data_topic_repair_mobile");
    }
    
    function delete_data_topic_repair_mobile(topic_repair_mobile_id){
        if(confirm("กรุณายืนยันการลบอีกครั้ง !!!")){
            $.ajax({
                url:"controller.manage_data_topic_repair_mobile?delete",
                type:"post",
                data: {topic_repair_mobile_id : topic_repair_mobile_id},
                success:function(data){
                    if(data == "complete"){
                        bind_data_topic_repair_mobile();
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
    function edit_data_topic_repair_mobile(topic_repair_mobile_id){    
        $.ajax({
            url:"controller.manage_data_topic_repair_mobile?edit",
            type:"post",
            data: {topic_repair_mobile_id : topic_repair_mobile_id},
            success:function(data){
                //แยกคำที่รับค่ามาจากการresponse จากservletออกจากกันเพื่อนำมาใช้งาน
                var arr = data.split ( "," );
                $("#topic_repair_mobile_id").val(topic_repair_mobile_id); 
                $("#topic_repair_mobile").val(arr[0]); //ส่งไปเเบบhiddenเพื่อเก็บค่านี้ไว้ใช้งาน
                $("#detail").val(arr[1]);
            }
        });
    }
    
    
</script>
<form id="manage_data_topic_repair_mobile">
    <legend>จัดการข้อมูลหัวข้อการซ่อมโทรศัพท์</legend>
    <input type="button" class="btn-info btn-medium" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-medium" value="ซ่อน" id="hide">
    <div id="show_data_topic_repair_mobile">
    </div>
    <table>
        <tr>
            <td><input type="hidden" name="topic_repair_mobile_id" id="topic_repair_mobile_id" class="input-large" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>หัวข้อการซ่อม</td>
            <td><input type="text" name="topic_repair_mobile" id="topic_repair_mobile" class="input-xlarge" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>รายละเอียดเพิ่มเติม</td>
            <td><textarea class="field span12" name ="detail" id="detail" cols ="14" rows="4" style="resize:none ; margin: 3px ; width: 300px"></textarea></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" id="cmd_save" class="btn btn-success btn-large" value="บันทึกข้อมูล" style ="margin-left:115px ; margin-bottom: 3px; margin-right: 3px ; margin-top: 3px" ></td>
        </tr>
    </table>
</form>


