<%-- 
    Document   : show_mobile
    Created on : Aug 12, 2013, 1:27:22 AM
    Author     : Hippo-G8
--%>

<%@page import="sun.security.util.BigInt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.sale_mobile_model"%>
<%@page import="java.util.ArrayList"%>
<script type="text/javascript">
    function DeleteMemory(index){
        $("#list_select").css("height","373px");
        $.ajax({
            url:"controller.sale_mobile?delete_memory",
            //type:"post",
            data: {index : index},
            success:function(data){
                if(data == "complete"){
                    $("#list_select").css("height","373px");
                    $("#list_select").load("system/sale/show_mobile.jsp"); 
                    
                }else{
                    alert(data);
                }
            }
        
        });  
 
    }
     function DeleteAll(){
            $("#list_select").css("height","248px");
            $.ajax({
                url:"controller.sale_mobile?delete_all_memory",
                type:"post",
                data: $("#sale_mobile").serialize(),
                success:function(){
                    $("#list_select").load("system/sale/show_mobile.jsp"); 
                }
            }); 
        
        }

</script>
<table  class="table table-hover">
    <thead>
        <tr>
            <th style='background-color : #98FB98' align="left"></th>
            <th style='background-color : #98FB98' align="left">ลำดับ</th>
            <th style='background-color : #98FB98' align="left">รหัสสินค้า</th>
            <th style='background-color : #98FB98'>ชื่อสินค้า</th>
            <th style='background-color : #98FB98'>ราคาต่อหน่วย</th>
            <th style='background-color : #98FB98'>จำนวน</th>
            <th style='background-color : #98FB98'>รวม</th>

        </tr>
    </thead>
    <tbody>
        <%
            try {
                int count = 0;
                ArrayList<sale_mobile_model> arr_sale_mobile = (ArrayList) session.getAttribute("arr_sale_mobile");
                if (arr_sale_mobile.isEmpty()) {
        %>
        <tr style='background-color : #E0FFFF'>
            <td colspan="7"><center>ยังไม่มีข้อมูล</center></td>
</tr>
<%    } else {
    for (sale_mobile_model sale_mobile : arr_sale_mobile) {
        //เชคว่าmobile ในarraylistมันว่าไม๊ เชคเเค่อันเดียวก็พอเพราะตอนบันทึกเรานำเข้ามาทั้งหมดพร้อมกันอยู่เเล้ว
        //เช็คสินค้าที่ิลอกเเล้วเอาเปรียบเทียบกับสินค้าในคลังว่าหมดรึยัง 
        if ((sale_mobile.getNumber() > sale_mobile.getQuantity()) || (sale_mobile.getQuantity() == 0)) {
%>
<script type="text/javascript">
    alert("ไม่สามารถเพิ่มรายการได้เนื่องจากท่านเลือกสินค้าบางตัวหมด หรือสินค้าที่เลือกมามีจำนวนมากกว่าสินค้าที่มีอยู่ในคลังสินค้า !");
    DeleteAll();
</script>
<%                } else {
%>


<tr style='background-color : #E0FFFF'>                    
    <td><a onclick="return DeleteMemory(<%=count%>)"><img src="img/delete.png" width="25" height="25"></a></td>
    <td><%=++count%></td>
    <td><%=sale_mobile.getMobile_id()%></td>
    <td><%=sale_mobile.getMobile_type()%>&nbsp;<%=sale_mobile.getMobile()%></td>
    <td><%=sale_mobile.getPrice_sale()%></td>
    <td><%=sale_mobile.getNumber()%></td>
    <td><%=sale_mobile.getSum()%></td>

</tr>

<%
        }
    }
%>
<tr><td colspan="5"><td align="right"><strong>รวมทั้งสิ้น</strong></td><td><strong>
            <%
                int t = 0;
                for (sale_mobile_model total : arr_sale_mobile) {
                    int b = (int) total.getSum();
                    t = t + b;

                }
                out.print(t);
            %></strong></td></tr> 
            <% }
            } catch (java.lang.NullPointerException el) {
            %>

<tr style='background-color : #E0FFFF'>
    <td colspan="9"><center>ยังไม่มีข้อมูล</center></td>
</tr>
<%    } catch (Exception ex) {
        out.print(ex);
    }
%>
</tbody>
</table>
