<%-- 
    Document   : repair
    Created on : 22 เม.ย. 2556, 17:59:14
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
            <a onclick="loadContent('system/repair/repair_mobile.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>โทรศัพท์</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ดำเนินการซ่อมโทรศัพท์</strong></span>  </td>

        <td style="width: 200px ;text-align: center">
            <a onclick="loadContent('system/repair/repair_other_tool.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>อุปกรณ์เสริม</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ดำเนินการซ่อมและเคลมอุปกรณ์เสริม</strong></span>   </td>
    </tr>


</table>

