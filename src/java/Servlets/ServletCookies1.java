/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet(name = "ServletCookies", urlPatterns = {"/ServletCookies"})
public class ServletCookies1 extends HttpServlet {
    static Cookie[] galletas;

    /**
     *
     * @param peticion
     * @param respuesta
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doPost (HttpServletRequest peticion, HttpServletResponse respuesta) throws IOException, ServletException{

            boolean nuevo = true;
            String usuario = peticion.getParameter ("user");
            String contra = peticion.getParameter ("key");

            galletas = peticion.getCookies();
            if (galletas!=null){
                for (Cookie galleta : galletas) {
                    if (galleta.getName().equals(usuario) && galleta.getValue().equals(contra)) {
                        nuevo=false;
                        break;
                    }
                }
            }

            ServletOutputStream flujo = respuesta.getOutputStream ();
            respuesta.setContentType("text/html");
            Cookie galleta = new Cookie (usuario, contra);
            galleta.setMaxAge(30);
            respuesta.addCookie(galleta);

            flujo.println("<html>");
            flujo.println("<head><title>RESPUESTA</TITLE></HEAD>");
            flujo.println("<BODY TEXT=#FFFFFF BGCOLOR=#000080><CENTER><H1>");
            if (nuevo){
                flujo.println("GRACIAS POR REGISTRARSE CON NOSOTROS");
            }else{
                flujo.println("BIENVENIDO A TU SITIO FAVORITO");
            }
            flujo.println("</H1></CENTER></BODY></HTML>");
            flujo.close();

    }

}
