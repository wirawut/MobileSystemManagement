<%-- 
    Document   : manage
    Created on : 18 เม.ย. 2556, 12:31:18
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
                <a onclick="loadContent('system/manage/manage_data_company.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                    <i class="icon-search icon-white"></i><br>
                    <span ><strong>บริษัท/ห้างร้าน </strong></span>        	
                </a>
            </td>
            <td style="width: 350px"><span ><strong>เพิ่มและแก้ไขข้อมูลของบริษัท/ห้างร้าน</strong></span>  </td>
            
            <td style="width: 200px; text-align: center">
                <a onclick="loadContent('system/manage/manage_data_major.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                    <i class="icon-search icon-white"></i><br>
                    <span ><strong>สาขา</strong></span>        	
                </a>
            </td>
            <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลสาขา</strong></span>   </td>
           
        </tr>

        <tr> 
            <td style="width: 200px ;text-align: center">
                <a onclick="loadContent('system/manage/manage_data_admin.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                    <i class="icon-search icon-white"></i><br>
                    <span ><strong>ผู้ใช้งานระบบ</strong></span>        	
                </a>
            </td>
            <td style="width: 350px"><span ><strong>เพิ่ม ลบ แก้ไขข้อมูลผู้ใช้งานระบบซึ่งแบ่งระดับของผู้ใช้งานออกเป็นหลายระดับ</strong></span>    </td>
            
            <td style="width: 200px ; text-align: center">
                <a onclick="loadContent('system/manage/manage_data_product_type.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                    <i class="icon-search icon-white"></i><br>
                    <span ><strong>ประเภทสินค้า</strong></span>        	
                </a>
            </td>
            <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลประเภทสินค้าทั้งโทรศัพท์ ซิมการ์ด และอุปกรณ์เสริม</strong></span>        </td>
        </tr>

        <tr>
            <td style="width: 200px; text-align: center" >
                <a onclick="loadContent('system/manage/manage_data_product.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                    <i class="icon-search icon-white"></i><br>
                    <span ><strong>สินค้า</strong></span>        	
                </a>
            </td>
            <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลสินค้า</strong></span>   </td>
            
             <td style="width: 200px; text-align: center" >
                <a onclick="loadContent('system/manage/manage_data_customer.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                    <i class="icon-search icon-white"></i><br>
                    <span ><strong>ลูกค้า</strong></span>        	
                </a>
            </td>
            <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลลูกค้าที่เคยซื้อสินค้ากับทางบริษัท/ร้าน</strong></span>   </td>
        </tr>
        
        <tr>
            <td style="width: 200px; text-align: center" >
                <a onclick="loadContent('system/manage/manage_data_topic_repair.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                    <i class="icon-search icon-white"></i><br>
                    <span ><strong>หัวข้อการซ่อม</strong></span>        	
                </a>
            </td>
            <td style="width: 350px"><span ><strong>เพิ่ม ลบ และแก้ไขข้อมูลหัวข้อการซ่อม</strong></span>   </td>
            <td style="width: 200px; text-align: center" >
                <a onclick="loadContent('system/manage/manage_data_about.jsp')" class="btn btn-primary" style="width:200px; height:60px ; margin: 10px">
                    <i class="icon-search icon-white"></i><br>
                    <span ><strong>เกี่ยวกับโปรแกรม</strong></span>        	
                </a>
            </td>
            <td style="width: 350px"><span ><strong>เพิ่ม และแก้ไขข้อมูลหัวข้อการซ่อม</strong></span>   </td>
             
        </tr>
    </table>
