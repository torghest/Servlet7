/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import JDBC.AccesoJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alumno
 */
@WebServlet(name = "ServletSession1", urlPatterns = {"/ServletSession1"})
public class ServletSession1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccesoJDBC jdbc = new AccesoJDBC();
        String nss = request.getParameter("nss").trim();
        ResultSet rs = jdbc.consNSS(nss);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("NSS", nss);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletSession1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=\"2\" bgcolor=\"#00AA33\" align=\"center\">");
            out.println("<tr>");
            out.println("<td>");
            if (rs.next()){
                out.println("<h1>Datos del NSS:"+nss+"</h1>");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td bgcolor=\"#DDDDDD\">");
                out.println("<table align=\"center\">");
                for (int i = 1; i <= rsmd.getColumnCount(); i++){
                    out.println("<tr>");
                    out.println("<td>");
                    out.println("<b>"+rsmd.getColumnName(i)+":</b>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println(rs.getString(i));
                    out.println("</td>");
                    out.println("</tr>");
                }
            } else {
                out.println("<h1>Alta del NSS:"+nss+"</h1>");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td bgcolor=\"#DDDDDD\">");
                out.println("<table align=\"center\">");
                out.println("<form name=\"form2\" action=\"ServletSession2\" method=\"Post\">");
                String[] inputs = new String[rsmd.getColumnCount()-1];
                for (int i = 1; i < rsmd.getColumnCount(); i++){
                    out.println("<tr>");
                    out.println("<td>");
                    inputs[i-1] = rsmd.getColumnName(i);
                    out.println("<b>"+inputs[i-1]+":</b>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<input type=\"text\" name=\""+inputs[i-1]+"\"/>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                sesion.setAttribute("form2inputs", inputs);
                out.println("<tr>");
                out.println("<td>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"submit\" value=\"Insertar\"/>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</form>");
            }
            out.println("</table>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
