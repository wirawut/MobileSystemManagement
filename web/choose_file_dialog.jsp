<%-- 
    Document   : choose_file_dialog
    Created on : 19 มิ.ย. 2556, 2:17:03
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap.css" rel="stylesheet" media="screen">

    </head>
    <body style="background-color: darkcyan">
        <form method="post" action="uploadfile.jsp" enctype="multipart/form-data">

            <input type="file" name="file_upload" style="display: none;"><br>
            <input type="button" value="เลือกรูปภาพ" class="btn btn-inverse btn-block" onClick="file_upload.click();" style="margin: 3px ; height: 100px"><br>
            <input type="submit" value="ยืนยันการเลือก" class="btn btn-danger btn-block" style="margin: 3px ; height: 100px">

        </form>
    </body>
</html>
