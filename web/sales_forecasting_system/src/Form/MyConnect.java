package Form;
import java.sql.*;

public class MyConnect {
    //jdbcเชื่อมต่อกับmysql 
    //เก็บข้อมูลชื่อดาต้าเบส ชื่อuserและpassword เข้าmysql ชื่อโฮส 
    //ชื่อแหล่งไดรเวอร์ที่addเข้ามาใช้งาน
    private String db_name = "sales_forecasting_system";
    private String db_user = "root";
    private String db_pass = "1234";
    private String db_host = "localhost";
    private String db_driver = "com.mysql.jdbc.Driver";
    
    //สร้างเมธอดในการเชื่มต่อเเล้วรีเทินค่าที่ได้กลับมาเป็นคลาสConnection 
    //เพื่อนำไปเรียกใช้งานในหน้าอื่น
    public Connection getConnection() {
        try {
            //เชื่อมต่อเเละรีเทินค่ากลับ
            Class.forName(db_driver);
            String url = "jdbc:mysql://" + db_host + "/" + db_name;
            return DriverManager.getConnection(url,db_user, db_pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
