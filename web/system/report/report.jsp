<%-- 
    Document   : report
    Created on : 23 เม.ย. 2556, 18:36:07
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
            <a onclick="loadContent('system/report/report_product_import.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>นำเข้า</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ดูรายงานข้อมูลและรายงานสรุปของสินค้าที่นำเข้าทั้งรายวัน ตามวัน ตามเดือน ตามปี ตามประเภท ยี่ห้อ เครือข่าย และตามรุ่น </strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>จ่ายออก</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ดูรายงานข้อมูลและรายงานสรุปของสินค้าที่จ่ายออกทั้งรายวัน ตามวัน ตามเดือน ตามปี ตามประเภท ยี่ห้อ เครือข่าย และตามรุ่น</strong></span>   </td>

    </tr>

    <tr> 
        <td style="width: 200px ;text-align: center">
            <a onclick="loadContent('system/report/report_creditor.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>เจ้าหนี้</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ดูรายงานข้อมูลและรายงานสรุปของลูกหนี้</strong></span>   </td>
      <td style="width: 200px ;text-align: center">
            <a onclick="loadContent('system/report/report_debtor.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ลูกหนี้</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ดูรายงานข้อมูลและรายงานสรุปของลูกหนี้</strong></span>   </td>
    
    </tr>


</table>

