var actualShape = JSON.parse(document.getElementById("act").value);
var actualShapeColor = document.getElementById("actcolor").value;
var nextShape = JSON.parse(document.getElementById("next").value);
var nextShapeColor = document.getElementById("nextcolor").value;
document.addEventListener("keydown", pressButton);
var responseData;


for (var i = 0; i < actualShape.length; i++) {
    document.getElementById(actualShape[i]).style.backgroundColor = actualShapeColor;
    document.getElementById("next"+nextShape[i]).style.backgroundColor = nextShapeColor;
}


setInterval(downRequest, 500);

function sendRequest(req) {

    var xmlHTTP = new XMLHttpRequest();

    xmlHTTP.onreadystatechange = function () {

        if (xmlHTTP.readyState === 4 && xmlHTTP.status === 200) {

            var response = xmlHTTP.responseText;
            
            responseData = response.split(",");
            displayShape();    
            
        }

    };

    var url = document.location.protocol + "//" + document.location.host +
            document.location.pathname + "play?move=" + req;


    xmlHTTP.open("GET", url, true);
    xmlHTTP.send();


}

function pressButton(e) {

    e = e || window.event;

    if (e.keyCode === 37) {

        leftRequest();
    } else if (e.keyCode === 38) {

        rotateRequest();

    } else if (e.keyCode === 39) {

        rightRequest();
    } else if (e.keyCode === 40) {

        downRequest();

    }

}

function leftRequest() {
    sendRequest("left");

}

function rightRequest() {
    sendRequest("right");
}

function rotateRequest() {
    sendRequest("rotate");
}

function downRequest() {
    sendRequest("down");
}

function displayShape() {

    for (var i = 0; i < responseData.length; i++) {

        if (i < 4) {
            document.getElementById(responseData[i]).style.backgroundColor = actualShapeColor;
        } else {
            document.getElementById(responseData[i]).style.backgroundColor = "white";
        }
    }
}