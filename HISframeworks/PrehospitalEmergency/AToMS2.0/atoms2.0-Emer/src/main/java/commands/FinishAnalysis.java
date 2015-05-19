/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package commands;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lncc.martin.atoms.emer.ParamedicController;
import servlets.Controller;

/**
 *
 * @author iuri
 */
public class FinishAnalysis implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Controller controller) {
        String idAnalysis = request.getParameter("idAnalysis");
        
        boolean result = new ParamedicController().finishAnalysis(Long.parseLong(idAnalysis));

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FinishAnalysis</title>");
            out.println("</head>");
            out.println("<body>");
            if (result) {
                out.println("<h1> The analysis was finished!</h1>");
                out.println("<button type=\"button\" onclick=\"window.location = '/atoms2.0-Emer/';\">Start new analysis request</button>");
            } else {
                out.println("<h1>A problem occurred to finish the analysis(id=" + idAnalysis + ")!</h1>");
                out.println("<button type=\"button\" name=\"back\" onclick=\"history.back()\">try again...</button>");
            }

            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }

}
