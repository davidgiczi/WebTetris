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
        <style>
            table{
                border: 3px solid lightgray;
                width:  500px;
                height: 700px;
                background-color: white;
            }

            td{
                border: 1px solid white;
                background-color: white;
                border-collapse: collapse;
            }

        </style>

    </head>
    <body>

        <br><br><br><br> <table align="center">

            <c:forEach begin="0" end="${row - 1}" varStatus="i">
                
                <tr>
                
                <c:forEach begin="0" end="${col - 1}" varStatus="j">

                     <td id="${i.index*col+j.index}"></td>

                </c:forEach>
                    
            </tr>
        </c:forEach>
    </table>
     
        <div class="nextShape">
            <table style="width: 200px;height: 75px;border: none;background-color: powderblue" align="center">
                <c:forEach begin="0" end="1" varStatus="i">
                <tr style="background-color: powderblue;border: none">
                    <c:forEach begin="0" end="3" varStatus="j">
                     
                        <td id="next${i.index * col + j.index}" style="border:none;background-color: powderblue"></td>
                    
                </c:forEach>
                </tr>
        </c:forEach>
            </table>
        </div>   
        
    <input type="hidden" id="act" value="${actualshape}">
    <input type="hidden" id="actcolor" value="${actualshapecolor}">
    <input type="hidden" id="next" value="${nextshape}">
    <input type="hidden" id="nextcolor" value="${nextshapecolor}">

    <script src="script/tetrisboard.js"></script>

</body>
</html>
