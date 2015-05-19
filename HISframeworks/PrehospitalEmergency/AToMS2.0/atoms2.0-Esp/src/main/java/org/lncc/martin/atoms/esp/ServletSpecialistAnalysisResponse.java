/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package org.lncc.martin.atoms.esp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Iuri
 */
public class ServletSpecialistAnalysisResponse extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Parametro da pagina
        Map params = request.getParameterMap();
        String idAnalysis = request.getParameter("idAnalysis");

        //enviar analise
        boolean result = new SpecialistController().sendAnalysisResponse(Long.parseLong(idAnalysis), params);
//        System.out.println(">>"+result);
//        String respostaGUI = "ERRO";
//        if(result != -1){
//            respostaGUI = "Salvo no banco com sucesso! id=" + result;
//        }else{
//            respostaGUI = "Houve um problema ao salvar os dados no banco!";
//        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletSpecialistAnalysisResponse</title>");
            out.println("</head>");
            out.println("<body>");
            if(result){
                out.println("<h1> The analysis response has been successfully sent!</h1>");
                out.println("<button type=\"button\" onclick=\"window.location = '/atoms2.0-Esp/';\">Start new analysis</button>");  
            }else{
                out.println("<h1> Error: sendAnalysisResponse </h1>");
                out.println("<h1>" + result + "</h1>");
                out.println("<button type=\"button\" name=\"back\" onclick=\"history.back()\">back</button>");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
