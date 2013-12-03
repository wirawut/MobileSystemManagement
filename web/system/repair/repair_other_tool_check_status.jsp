<%-- 
    Document   : repair_other_tool_check_status
    Created on : 23 เม.ย. 2556, 0:13:09
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){
        //เรียกใช้งานฟังก์ชั่นbindManageDataCompany()
        //bindData();
        
        $("#cmd_check").click(function(){
            $.ajax({
                url:"controller.repair_other_tool_check_status?check",
                type:"post",
                data: $("#repair_other_tool_check_status").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert(data);
                        clearData();
                    }
                    else if(data == "no_complete"){
                        alert("สินค้านี้ไม่ได้ซ่อมกับทางร้านหรือท่านกรอกข้อมูลเท็จ");
                        clearData();
                    }
                    else{
                        alert(data);
                    }
                }
            }); 
        });
    });
    
    
    
</script>
<form id="repair_other_tool_check_status">
    <legend>ตรวจสอบสถานะการซ่อมอุปกรณ์เสริม</legend>
    <p class="text-info">รหัสการซ่อม
        <input type="text" name="repair_other_tool_id" id="repair_other_tool_id" class="input-small" style="margin: 3px">
        <input type="button" id="cmd_check" class="btn btn-warning btn-large" value="ตรวจสอบสถานะ" ></p>
</form>
