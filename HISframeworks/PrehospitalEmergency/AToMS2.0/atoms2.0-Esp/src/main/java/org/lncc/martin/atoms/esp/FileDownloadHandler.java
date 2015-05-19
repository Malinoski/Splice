/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package org.lncc.martin.atoms.esp;

import com.martin.spliceexistdb.ExistdbDAO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.lncc.martin.ct.ws.AnalysisService_Service;

/**
 *
 * @author iuri
 */
public class FileDownloadHandler extends HttpServlet {

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
        //tell browser program going to return an application file 
        //instead of html page
        String fileName = request.getParameter("fileName");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        try {
            
            byte[] result = new SpecialistController().getMultimedia(fileName);
            
            ServletOutputStream out = response.getOutputStream();
//            StringBuffer sb = generateCsvFileBuffer();
            //InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            InputStream in = new ByteArrayInputStream(result);
            byte[] outputByte = new byte[result.length];
            //copy binary contect to output stream
            while (in.read(outputByte, 0, result.length) != -1) {
                out.write(outputByte, 0, result.length);
            }
            in.close();
            out.flush();
            out.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return null;
    }

    private byte[] getByteArray(String fileName) {
        try {
//            byte[] buffer = new byte[1024];

//            File file = new File("src/main/java/trecho.TEP");
//            InputStream is = new BufferedInputStream(new FileInputStream(file));
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//            while (is.read(buffer) != -1) {
//                out.write(buffer);
//            }
//            byte[] bytes = out.toByteArray();
            byte[] bytes = new ExistdbDAO().getBinaryResource(fileName);
            System.out.println("bytes length: " + bytes.length);

            return bytes;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
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
