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
                border: 3px solid #bfbebe;
                width:  500px;
                height: 700px;
                background-color: lightgray;
            }

            td{
                border: 1px solid white;
                background-color: lightgray;
                border-collapse: collapse;
            }

        </style>

    </head>
    <body>
        <br>
        <h1 id="timer">0:00</h1>
        <a id="stop" style="position: relative;right: 190px;cursor: pointer;color: white">Stop/Start</a>
        <a id="newgame" style="position: relative;right: 170px;cursor: pointer;color: white">Új játék</a>
        <br> <table align="center">

            <c:forEach begin="0" end="${row - 1}" varStatus="i">
                
                <tr>
                
                <c:forEach begin="0" end="${col - 1}" varStatus="j">

                     <td id="${i.index*col+j.index}"></td>

                </c:forEach>
                    
            </tr>
        </c:forEach>
    </table>
     
        <div class="nextShape">
            <table style="width: 200px;height: 75px;border: none;background-color: #dfe5c9" align="center">
                <c:forEach begin="0" end="1" varStatus="i">
                <tr style="background-color: #dfe5c9;border: none">
                    <c:forEach begin="0" end="3" varStatus="j">
                     
                        <td id="next${i.index * col + j.index}" style="border:none;background-color: #dfe5c9"></td>
                    
                </c:forEach>
                </tr>
        </c:forEach>
            </table>
        </div>
        
       <div class="level">
       <font style="position: relative;top: -150px;font-size: 20px">Tempó:</font>     
       <font id="levelValue" style="font-size: 150px;">1</font>
       </div> 
        
       <div class="score">
       <font style="position: relative;top: -150px;font-size: 20px">Pontszám:</font>     
       <font id="scoreValue" style="font-size: 150px;">0</font>
       </div> 
        
        <input id="act" type="hidden" name="actualshape" value="${actual}">
        <input id="next" type="hidden" name="nextshape" value="${next}">
        
    <script src="script/tetrisboard.js"></script>

</body>
</html>
