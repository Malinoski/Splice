/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lncc.martin.atoms.emer.MessageAnalysisRequest;
import org.lncc.martin.atoms.emer.ParamedicController;
import servlets.Controller;

/**
 *
 * @author iuri
 */
public class ReceiveAnalysisResponse implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Controller controller) {
        String stringIdAnalysis = request.getParameter("idAnalysis");
        MessageAnalysisRequest message = new ParamedicController().receiveAnalysisResponse(Long.parseLong(stringIdAnalysis));
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ReceiveAnalysisResponse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ReceiveAnalysisResponse " + request.getContextPath() + "</h1>");
            if(message != null){
                request.setAttribute("idAnalysis", message.getId());
                request.setAttribute("map", message.getEHRmap());
                RequestDispatcher reqDispatcher = request.getRequestDispatcher("AnalysisResponse.jsp");
                reqDispatcher.forward(request, response);
            }else{
                out.println("<h1> Error: getAnalysisResponse </h1>");
                out.println("<h1>" + message + "</h1>");
                out.println("<button type=\"button\" name=\"back\" onclick=\"history.back()\">back</button>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            Logger.getLogger(ReceiveAnalysisResponse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ReceiveAnalysisResponse.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
}
