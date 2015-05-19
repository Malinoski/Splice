<!-- 
Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
Read file LICENSE.txt for conditions on the use of this software.
--> 
<%-- 
    Document   : AnalysisResponse
    Created on : 26/03/2014, 11:51:24
    Author     : Iuri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/interface/AjaxAnalysisCommands.js'></script>        
        <script type='text/javascript' src='dwr/util.js'></script>

        <script type='text/javascript'>
            
            var idAnalysis = ${idAnalysis};
            function searchAnalysisResponse() {
                
                //alert(idAnalysis);
                if(idAnalysis == null){
                    dwr.util.setValue("analysisRequestLog", "A análise não foi enviada ainda!", {escapeHtml: false});                    
                }else{
                    AjaxAnalysisCommands.searchAnalysisResponse(idAnalysis, gotMessages);                                
                }
                
            }

            function gotMessages(message) {
                var idAnalysis = ${idAnalysis};
                var analysisRequestLog = "Houve um erro.";                
                if ( (message == null) || (message == "")) {
                    analysisRequestLog = "A análise não foi respondida ainda!";
                } else{
                    analysisRequestLog = "A analise foi respondida:";
                    var uri = "Controller?command=ReceiveAnalysisResponse&idAnalysis="+${idAnalysis}+"";
                    //var uri = "ServletAnalysisResponse?idAnalysis=63";
                    analysislog = '<div>' +
                            '<a href="' + uri + '" onkeydown="noway(event)">' + message + '</a>' +
                            '</div><br/>';
                    dwr.util.setValue("analysislog", analysislog, {escapeHtml: false});                    
                }
                dwr.util.setValue("analysisRequestLog", analysisRequestLog, {escapeHtml: false});                
            }
        </script>
    </head>
    <body>
        <h3>${requestScope["message"]}</h3><br/>
        <h3>Analysis id: ${idAnalysis}</h3><br/>
        <button onclick="searchAnalysisResponse()">Search analysis response</button>
        <div id="analysisRequestLog"></div>
        <div id="analysislog"></div>
    </body>
</html>
