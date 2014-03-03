/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import JDBC.AccesoJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
@WebServlet(name = "ServletSession2", urlPatterns = {"/ServletSession2"})
public class ServletSession2 extends HttpServlet {

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
        HttpSession sesion = request.getSession();
        String[] inputs = (String[])sesion.getAttribute("form2inputs");
        String[] values = new String[inputs.length+1];
        String nss = (String)sesion.getAttribute("NSS");
        values[inputs.length] = nss;
        for (int i = 0; i < inputs.length; i++){
            values[i] = request.getParameter(inputs[i]);
        }
        int res = jdbc.insertarEnfermo(
            values[0], values[1], values[2], values[3], values[4], values[5]);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletSession2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=\"2\" bgcolor=\"#00AA33\" align=\"center\">");
            out.println("<tr>");
            out.println("<td>");
            out.println("<h1>"+(res==0?"Fallo registrando":"Alta registrada")+" - NSS:"+nss+"</h1>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td bgcolor=\"#DDDDDD\">");
            out.println("<table align=\"center\">");
            for (int i = 1; i < values.length; i++){
                out.println("<tr>");
                out.println("<td>");
                out.println("<b>"+(i==inputs.length?"NSS":inputs[i])+":</b>");
                out.println("</td>");
                out.println("<td>");
                out.println(values[i]);
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}
