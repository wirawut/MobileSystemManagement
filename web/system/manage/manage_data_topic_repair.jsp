<%-- 
    Document   : manage_data_topic_repair
    Created on : 23 เม.ย. 2556, 0:51:35
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script type="text/javascript">
    function loadContent(url){
        $("#content").load(url);
    }
        
</script>

<table>

    <tr>
 
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/manage/manage_data_topic_repair_mobile.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>โทรศัพท์</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลหัวข้อการซ่อมโทรศัพท์</strong></span>   </td>

    </tr>

    <tr> 
        <td style="width: 200px ;text-align: center">
            <a onclick="loadContent('system/manage/manage_data_topic_repair_other_tool.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>อุปกรณ์เสริม</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลหัวข้อการซ่อมอุปกรณ์เสริม</strong></span>    </td>
    </tr>
</table>