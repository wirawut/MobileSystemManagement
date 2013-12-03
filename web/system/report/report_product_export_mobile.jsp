<%-- 
    Document   : report_product_export_mobile
    Created on : 25 เม.ย. 2556, 15:10:39
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
            <a onclick="loadContent('system/report/report_product_export_mobile_data.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>รายงานข้อมูล</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานข้อมูลการจ่ายออกโทรศัพท์</strong></span>  </td>

        <td style="width: 200px ;text-align: center">
            <a onclick="loadContent('system/report/report_product_export_mobile_summary.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>รายงานสรุป</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปการจ่ายออกโทรศัพท์</strong></span>   </td>
    </tr>

    
</table>

