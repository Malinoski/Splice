<!-- 
 Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 Read file LICENSE.txt for conditions on the use of this software.
--> 
<%-- 
    Document   : AnalysisResponse
    Created on : 08/05/2014, 15:04:50
    Author     : Iuri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
    </head>
    <body>
        <form action="ServletSpecialistAnalysisFinish?idAnalysis=${idAnalysis}">
            <!-- TROCAR PARA INSTRUCAO!!!!-->
            <%--<%@ include file="TemplateObservationParamedic.html" %>--%>
            <!--com ecg-->
            <%--<%@ include file="ParamedicComponentTemapleParamedicObservation-v2.html" %>--%>
            <%@ include file="CardiologistComponentprescricao.html" %>            
            <input type="hidden" name="idAnalysis" value="${idAnalysis}"/>            
            <input type="submit" value="submit">
        </form>
    </body>
</html>
