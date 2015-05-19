<!-- 
 Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 Read file LICENSE.txt for conditions on the use of this software.
--> 

<%-- 
    Document   : AnalysisRequest
    Created on : 16/02/2014, 13:08:06
    Author     : Iuri
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Specialist Client</title>
    </head>
    <body>
        <h1>Especialista</h1>
        <form action="ServletSpecialistAnalysisResponse?idAnalysis=${idAnalysis}">
            
            <table border="1">
                <thead>
                    <tr>
                        <th>Pedido de análise</th>
                        <th>Resposta da análise</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td valign="top">
                            <%--<%@ include file="TemplateObservationSpecialist.html" %>--%>
                            <%@ include file="CardiologistComponentTemapleParamedicObservation-v2.html" %>                            
                        </td>
                        <td valign="top">
                            <%@ include file="AnalysisResponse.jsp" %>
                        </td>
                    </tr>
                    
                </tbody>
            </table>      
            						
        </form>	
    </body>
</html>
