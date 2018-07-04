/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.dbuitl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Naman Khurpia
 */
public class DBConnection {
    
    private static Connection conn=null;
    
    static
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","naman","naman");
            JOptionPane.showMessageDialog(null,"Successfully loaded","Sucess!",JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null,"class not found exception"+ e.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"SQL Exception"+ ex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        
        
    }
    public static Connection getConnection()
        {
            return(conn);
        }
    
    public static void closeConnection()
    {
        if(conn!=null)
            {
                try{
                    conn.close();
        
                }
                catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,"Error while disconnecting","Exception",JOptionPane.ERROR_MESSAGE);
                    
                }
            }
    }
    
    
    
}
