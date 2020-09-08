<%-- 
    Document   : gameboard
    Created on : 2020.09.08., 11:31:03
    Author     : GicziD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>T E T R I S</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/tetrisboard.css" />
    </head>
    <body>

        <br><br><br><br> <table align="center">

            <c:forEach begin="0" end="${row}" varStatus="i">

                <tr>

                    <c:forEach begin="0" end="${col}" varStatus="j">

                        <td id="${i.index*col+j.index}" style="background: white"></td>

                    </c:forEach>

                </tr>
            </c:forEach>
        </table>

        <script src="script/tetrisboard.js"></script>

    </body>
</html>
