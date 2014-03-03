/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet(name = "ServletCookies2", urlPatterns = {"/ServletCookies2"})
public class ServletCookies2 extends HttpServlet {
    static Cookie[] galletas;
    static int expiracion = 60*60*24*2; //2 dias
    
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
        
        galletas = request.getCookies();
        String usuario = null;
        String aficion = null;
        String color = null;
        String ultVisita = null;
        String nVisitas = null;
        String urlimagen = null;
        Cookie c;
        if (galletas!=null){
            for (Cookie galleta : galletas){
                String nom = galleta.getName();
                if (nom.equals("usuario")){
                    usuario = galleta.getValue();
                } else if (nom.equals("aficion")) {
                    aficion = galleta.getValue();
                } else if (nom.equals("color")) {
                    color = galleta.getValue();
                } else if (nom.equals("nVisitas")) {
                    nVisitas = galleta.getValue();
                    galleta.setValue(String.valueOf(Integer.parseInt(nVisitas)+1));
                } else if (nom.equals("ultVisita")) {
                    ultVisita = galleta.getValue();
                    galleta.setValue(new Date().toString());
                }
                galleta.setMaxAge(expiracion);
                response.addCookie(galleta);
            }
        } else {
            usuario = (String)request.getParameter("usuario");
            aficion = (String)request.getParameter("aficion");
            color = (String)request.getParameter("color");
            
            c = new Cookie("usuario", usuario);
            c.setMaxAge(expiracion);
            response.addCookie(c);
            
            c = new Cookie("aficion", aficion);
            c.setMaxAge(expiracion);
            response.addCookie(c);
            
            c = new Cookie("color", color);
            c.setMaxAge(expiracion);
            response.addCookie(c);
            
            nVisitas = "1";
            c = new Cookie("nVisitas", "2");
            c.setMaxAge(expiracion);
            response.addCookie(c);
            
            ultVisita = new Date().toString();
            c = new Cookie("ultVisita", ultVisita);
            c.setMaxAge(expiracion);
            response.addCookie(c);
        }
        
        switch (Integer.parseInt(aficion)){
            case 1: //lectura
                urlimagen = "http://st-listas.20minutos.es/images/2013-04/359955/list_640px.jpg?1367624909";
                break;
            case 2: //viajes
                urlimagen = "http://blogs.elpais.com/.a/6a00d8341bfb1653ef01761656bab5970c-pi";
                break;
            case 3: //deportes
                urlimagen = "http://www.almoradi.es/images/secciones/1369031775deportesacti.jpg";
                break;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Portada</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<table bgcolor=\""+color+"\">");
            out.println("<tr>");
            out.println("<td>");
            out.println("Bienvenido "+usuario);
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>");
            out.println("Esta es su visita: "+nVisitas);
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>");
            out.println("Ultima visita: "+ultVisita);
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<img src=\""+urlimagen+"\" height=\"100\"/>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        galletas = request.getCookies();
        if (galletas!=null){
            this.doPost(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Preferencias</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<table>");
                out.println("<form name=\"form\" action=\"ServletCookies2\" method=\"Post\">");
                out.println("<tr>");
                out.println("<td>");
                out.println("Nombre de Usuario:");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type=\"text\" name=\"usuario\"/>");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                
                out.println("<table>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<b>Aficiones:</b>");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"radio\" name=\"aficion\" value=\"1\"/>Lectura");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"radio\" name=\"aficion\" value=\"2\"/>Viajes");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"radio\" name=\"aficion\" value=\"3\"/>Deportes");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
                
                out.println("</td>");
                out.println("<td>");
                
                out.println("<table>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<b>Color:</b>");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"radio\" name=\"color\" value=\"yellow\"/>Amarillo");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"radio\" name=\"color\" value=\"blue\"/>Azul");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"radio\" name=\"color\" value=\"green\"/>Verde");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
                
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td colspan=\"2\">");
                out.println("<input type=\"submit\" value=\"Enviar Datos\"/>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</form>");
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }
    }
}
