<!-- 
Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
Read file LICENSE.txt for conditions on the use of this software.
--> 
<%-- 
    Document   : index
    Created on : 05/02/2014, 18:25:22
    Author     : Iuri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Paramedic Client</title>
    </head>
    <body>

        <h1>Specialist Client</h1>
        <br/>
        <table border="1">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%@ include file="AnalysisRequest.jsp" %></td>
                    <td><!--%@ include file="AnalysisResponseDWRTest.jsp" %--></td>                    
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>       

    </body>
</html>
