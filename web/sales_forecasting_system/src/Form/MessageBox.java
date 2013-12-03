package Form;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MessageBox {
    //ใช้Optionpaneในการเเสดงผลข้อมูลในเมธอดshow เเล้วเก็บไว้ในคลาสMessageBox
    //เวลาเรียกใช้ในหน้าอื่นก็ new MessageBox().show();
    public static void show(Component parent, String message, String title) {
         JOptionPane.showMessageDialog(parent, message, title, 
         JOptionPane.INFORMATION_MESSAGE);
    }
}
