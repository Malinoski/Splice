<!-- 
 Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 Read file LICENSE.txt for conditions on the use of this software.
--> 
<%-- 
    Document   : AnalysisRequestSearch
    Created on : 09/05/2014, 10:23:49
    Author     : Iuri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/interface/AjaxSpecialistAnalysisRequest.js'></script>        
        <script type='text/javascript' src='dwr/util.js'></script>

        <script type='text/javascript'>

            function checkMessages() {
                //getAllAnalysisRequestPending(idSpecialist, id Area)
                AjaxSpecialistAnalysisRequest.getAllAnalysisRequestPending(9, 9, gotMessages);
            }

            function gotMessages(messages) {
                //alert(messages);
                var href = "Não há analises...";

                if (messages != null) {

                    var cont = messages.length;
                    var analysislog = "";

                    for (var data in messages)
                    {
                        href = "ServletSpecialistAnalysisRequest?ehrFile=" + messages[data].observation.fileName + "&idAnalysis=" + messages[data].id + "&idEsp=" + "2" + "&step=" + "N/A";

                        analysislog = '<div>' + cont + ':  '+
                                '<a href="' + href + '" target="_blank" onkeydown="noway(event)">' +
                                messages[data].observation.fileName + '</a>' +
                                '</div><br/>' + analysislog;
                        cont--;
                    }
                }
                dwr.util.setValue("analysislog", analysislog, {escapeHtml: false});
            }

        </script>
    </head>
    <body>
        <button onclick="checkMessages()">Search pending analysis</button>
        <br/><br/>
        <div id="analysislog"></div>        
        <!--a href="ServletAnalysisRequest?ehrFile=analysisRequest001.xml">mylink</a-->
    </body>
</html>
