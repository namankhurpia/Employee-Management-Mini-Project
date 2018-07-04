/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp.dao;

import emp.dbuitl.DBConnection;
import emp.pojo.Emp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Naman Khurpia
 */
//data access objects
public class EmpDao {

    
    public static boolean AddEmployee(Emp e)throws SQLException
    {
        
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into emp_table values(?,?,?)");
       
        ps.setInt(1,e.getEmpno());
        ps.setString(2,e.getEname());
        ps.setDouble(3,e.getSal());
        
        int count=ps.executeUpdate();
        if(count==0)
        {
            return false;
        }
        else
        {
            return true;
        }
        

    }
    
    public static ArrayList<Emp> getAllEmp()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        ArrayList<Emp> empList=new ArrayList<>();
        
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select empno,empname,salary from emp_table");
        
        while(rs.next())
        {
            
            Emp e=new Emp();
            
            e.setEmpno(rs.getInt("empno"));          
            e.setEname(rs.getString("empname"));
            e.setSal(rs.getDouble("salary"));
            
            empList.add(e);
            
        }
        return empList; 
    }
    
    
    public static boolean UpdateEmployee(Emp e) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update emp_table set empname=? , salary=? where empno=?");
        
        ps.setString(1,e.getEname());
        ps.setDouble(2,e.getSal());
        ps.setInt(3,e.getEmpno());
                   
        int c=ps.executeUpdate();      
        return c != 0;//returns true if c is 1
        
    }
    
    public static boolean DeleteEmp(int enumber)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Delete from emp_table where empno=?");
        ps.setInt(1,enumber);
        int c=ps.executeUpdate();
        
        return c!=0;
    }
    
    public static Emp SearchEmp(int enumber)throws SQLException,NullPointerException
    {
        Emp e=null;
        Connection conn=DBConnection.getConnection();
        //PreparedStatement ps=conn.prepareStatement("select empname , salary,empno from emp_table where empno=?");
        //ps.setInt(1, enumber);
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select empname , salary,empno from emp_table where empno="+enumber);
        
        
        
        while(rs.next())
        {
            e=new Emp();
            e.setEname(rs.getString("empname"));
            e.setSal(rs.getDouble("salary"));
        }
        
        return e;
        
    }

}
