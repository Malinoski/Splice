<!-- 
Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
Read file LICENSE.txt for conditions on the use of this software.
--> 
<%-- 
    Document   : AnalysisResponse
    Created on : 09/04/2014, 19:46:19
    Author     : Iuri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ServletSpecialistAnalysisFinish?idAnalysis=${idAnalysis}">
            <!--%@ include file="TemplateInstructionSpecialist.html" %-->
            <!--<form action="Controller?command=FinishAnalysis&idAnalysis=${idAnalysis}">-->
            <%--<%@ include file="CardiologistComponentTemapleParamedicObservation-v2.html" %>--%>
            <%@ include file="ParamedicComponentprescricao.html" %>
            <input type="hidden" name="idAnalysis" value="${idAnalysis}"/>            
            <input type="submit" value="Finish the analysis">
        </form>
    </body>
</html>
