package JDBC;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.StringTokenizer;
import oracle.jdbc.driver.OracleTypes;

/**
 *
 * @author alumno
 */
public class AccesoJDBC {
    
    private Connection conn;
    
    public AccesoJDBC(){
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            this.conn = DriverManager.getConnection
            ("jdbc:oracle:thin:@localhost:1521:XE","system","javaoracle");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet consNSS(String nss){
        ResultSet res = null;
        try{
            PreparedStatement ps = conn.prepareStatement(
                "Select * from enfermo where nss = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            ps.setString(1, nss);
            res = ps.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    
    public int insertarEnfermo(String ins, String ape, String dir, String fx,
            String sexo, String nss){
        int res = 0;
        try{
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Enfermo VALUES (?,?,?,to_date(?,'dd/mm/yyyy'),?,?)"    
            );
            ps.setString(1, ins);
            ps.setString(2, ape);
            ps.setString(3, dir);
            ps.setString(4, fx);
            ps.setString(5, sexo);
            ps.setString(6, nss);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}