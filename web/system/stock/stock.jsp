<%-- 
    Document   : stock
    Created on : 21 เม.ย. 2556, 21:16:56
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
            <a onclick="loadContent('system/stock/stock_mobile.jsp')" class="btn btn-info" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>โทรศัพท์ </strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ตรวจสอบยอดสินค้าคงคลังโทรศัพท์</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/stock/stock_sim.jsp')" class="btn btn-info" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ซิมการ์ด</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ตรวจสอบยอดสินค้าคงคลังซิมการ์ด</strong></span>   </td>

    </tr>

    <tr> 
        <td style="width: 200px ;text-align: center">
            <a onclick="loadContent('system/stock/stock_other_tool.jsp')" class="btn btn-info" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>อุปกรณ์เสริม</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>ตรวจสอบยอดสินค้าคงคลังอุปกรณ์เสริม</strong></span>    </td>
    </tr>
</table>

