/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package commands;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.lncc.martin.atoms.emer.ParamedicController;
import servlets.Controller;

/**
 *
 * @author iuri
 */
public class SendAnalysisRequest implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Controller controller) {
        //http://commons.apache.org/proper/commons-fileupload/using.html
        //process only if its multipart content
        String page = "Problem.jsp";
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                // Create a factory for disk-based file items
                DiskFileItemFactory factory = new DiskFileItemFactory();
                
                // Configure a repository (to ensure a secure temp location is used)
                ServletContext servletContext = controller.getServletConfig().getServletContext();
                File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                System.out.println("File repository absolute path: " + repository.getAbsolutePath());

                factory.setRepository(repository);

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                //parametros chave-valor (path valor)
                HashMap<String, String[]> params = new HashMap<String, String[]>();
                //parametros chave-valor para multimedia (path e array de bytes)
                HashMap<String, byte[]> mediaParams = new HashMap<String, byte[]>();

                // Tratando todos os parametros/itens da pagina (arquivos e não-arquivos)
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    //key (path)
                    FileItem item = iter.next();
                    String key = item.getFieldName(); 
                    
                    if (item.isFormField()) {
                        //printFormField(item);
                        //value
                        String[] value = new String[1];
                        value[0] = item.getString();
                        params.put(key, value);
                    } else {
                        //printUploadedFile(item);
                        byte[] value = item.get();
                        mediaParams.put(key, value);
                    }
                }

                //File uploaded successfully
//              request.setAttribute("message", "File Uploaded Successfully");
                long result = new ParamedicController().sendAnalysisRequest(params, 1, mediaParams);

                if (result == -1) {
                    request.setAttribute("message", "Occurred a problem to sending analysis request");
                } else {
                    request.setAttribute("idAnalysis", result);
                    request.setAttribute("message", "Analysis request sent successfully");
                    page = "AnalysisResponseSearch.jsp";
                    //RequestDispatcher reqDispatcher = request.getRequestDispatcher("AnalysisResponseSearch.jsp");
                    //reqDispatcher.forward(request, response);
                }

//                request.getRequestDispatcher("AnalysisResponseSearch.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
                ex.printStackTrace();
            }

        } else {
            request.setAttribute("message", "Sorry this Servlet only handles file upload request");
        }
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
