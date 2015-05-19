<!-- 
Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
Read file LICENSE.txt for conditions on the use of this software.
--> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Problem</title>
    </head>

    <body> 
        <div id="problem">
            <h3>${requestScope["message"]}</h3>
            <button type="button" name="back" onclick="history.back()">back</button>
        </div>

    </body>
</html>
