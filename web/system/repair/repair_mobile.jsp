<%-- 
    Document   : repair_mobile
    Created on : 22 เม.ย. 2556, 18:02:44
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
            <a onclick="loadContent('system/repair/repair_mobile_check_status.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตรวจสอบสถานะการสั่งซ่อม</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ตรวจสอบสถานะการซ่อมโทรศัพท์</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/repair/repair_mobile_order.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>แจ้งสั่งซ่อม</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>แจ้งสั่งซ่อมโทรศัพท์</strong></span>   </td>

    </tr>

    
</table>


