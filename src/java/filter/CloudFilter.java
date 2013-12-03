/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CloudFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            ServletContext sc = filterConfig.getServletContext();
            String host = sc.getInitParameter("host");
            String user = sc.getInitParameter("user");
            String pass = sc.getInitParameter("pass");
            String driver = sc.getInitParameter("driver");
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(host,user,pass);
            
            sc.setAttribute("conn", conn);
            
        } catch (Exception ex){
            ex.printStackTrace();
        }
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       
    }

    public void destroy() {
        
    }
}
 