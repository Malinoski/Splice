<!-- 
Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
Read file LICENSE.txt for conditions on the use of this software.
--> 
<%--
    Document   : AnalysisRequest
    Created on : 07/04/2014, 15:22:21
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
        <form action="Controller?command=SendAnalysisRequest" method="post" enctype="multipart/form-data">

            <%--<%@ include file="TemplateObservationParamedic.html" %>--%>
            
            <!--com ECG-->
            <%@ include file="ParamedicComponentTemapleParamedicObservation-v2.html"%>
            
            <input type="submit" value="submit">						
        </form>	
    </body>
</html>
