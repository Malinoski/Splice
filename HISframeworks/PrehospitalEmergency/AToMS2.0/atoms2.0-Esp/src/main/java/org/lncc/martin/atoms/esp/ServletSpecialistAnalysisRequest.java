/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package org.lncc.martin.atoms.esp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lncc.martin.ct.ws.AnalysisModel;

/**
 *
 * @author Iuri
 */
public class ServletSpecialistAnalysisRequest extends HttpServlet {

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

        String idAnalysis = request.getParameter("idAnalysis");
        String idEsp = request.getParameter("idEsp");
        String ehrFile = request.getParameter("ehrFile");
        
        System.out.println("# ESP - ServletAnalysisRequest");
        System.out.println("# idAnalysis " + idAnalysis);
        System.out.println("# idEsp " + idEsp);
        System.out.println("# ehrFile " + ehrFile);
       
//        HashMap<String,String[]> map = new HashMap<String,String[]>();        
        
        MessageAnalysisRequest message = new SpecialistController().receiveAnalysisRequest(Long.parseLong(idAnalysis), Long.parseLong(idEsp));                
        
        request.setAttribute("map", message.getEHRmap());
        request.setAttribute("idAnalysis", idAnalysis);
        request.setAttribute("idEsp", idEsp);
        
        RequestDispatcher reqDispatcher = request.getRequestDispatcher("AnalysisRequest.jsp");
        reqDispatcher.forward(request, response);

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
