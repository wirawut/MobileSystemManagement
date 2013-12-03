<%-- 
    Document   : uploadfile
    Created on : 20 มิ.ย. 2556, 13:42:58
    Author     : Admin
--%>

<%@page import="javazoom.upload.UploadBean"%>
<%@page import="javazoom.upload.UploadFile"%>
<%@page import="javazoom.upload.MultipartFormDataRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
    MultipartFormDataRequest mul = new MultipartFormDataRequest(request);
    UploadFile uploadfile = (UploadFile) mul.getFiles().get("file_upload");
    UploadBean upload = new UploadBean();
    upload.setFolderstore("C:/Users/Hippo-G8/Desktop/cloud/web/image");
    upload.store(mul);
    String image_select = uploadfile.getFileName().toString();
    //HttpSession s = request.getSession();
    //s.setAttribute("image_select", image_select);
   
%>

<base target="_self" />
<script type="text/javascript">
    //rerurn ชื่อไฟล์กลับไปเป็นค่าของ w ของไฟล์ manage_data_user.jsp
    window.returnValue = "<%=image_select%>";
    window.close();
</script>
