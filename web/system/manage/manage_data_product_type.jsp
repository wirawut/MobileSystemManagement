<%-- 
    Document   : manage_product_type
    Created on : 18 เม.ย. 2556, 15:03:39
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
            <a onclick="loadContent('system/manage/manage_data_product_type_mobile.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>โทรศัพท์ </strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลประเภทของโทรศัพท์</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/manage/manage_data_product_type_sim.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ซิมการ์ด</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลประเภทของซิมการ์ด</strong></span>   </td>

    </tr>

    <tr> 
        <td style="width: 200px ;text-align: center">
            <a onclick="loadContent('system/manage/manage_data_product_type_other_tool.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>อุปกรณ์เสริม</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลประเภทของอุปกรณ์เสริม</strong></span>    </td>
    </tr>
</table>