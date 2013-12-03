/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

public class convert {

    public String convert_date(String date) {
        String[] array_date = date.split("/");
        return array_date[2] + "-" + array_date[1] + "-" + array_date[0];
    }
}