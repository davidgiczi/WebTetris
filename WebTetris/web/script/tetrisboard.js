var actualShape = JSON.parse(document.getElementById("act").value);
var actualShapeColor = document.getElementById("actcolor").value;
var nextShape = JSON.parse(document.getElementById("next").value);
var nextShapeColor = document.getElementById("nextcolor").value;
document.getElementById("stop").addEventListener("click", stopStartGame);
document.getElementById("newgame").addEventListener("click", function() {
    location.reload();
});
document.addEventListener("keydown", pressButton);
var responseData;
var timer = 1;
var leftArrow = "\u2BC7";
var rightArrow = "\u2BC8";
var upArrow = "\u2BC5";
var downArrow = "\u2BC6";
var play = true;
var level = 1;
var score = 0;
var tempo = 1000;

alert("Nyomógombok:\n\n" +
        leftArrow + "  :  Balra húz\n" +
        rightArrow + "  :  Jobbra húz\n" +
        upArrow + "  :  Forgatás\n" +
        downArrow + "  :  Lefelé húz\n" + 
        "+   :  tempó fel\n" + 
        " -   :  tempó le");

setInterval(displayTimer, 1000);
var run = setInterval(downRequest, tempo);

for (var i = 0; i < actualShape.length; i++) {
    document.getElementById(actualShape[i]).style.backgroundColor = actualShapeColor;
    document.getElementById("next" + nextShape[i]).style.backgroundColor = nextShapeColor;
}

function displayTimer() {

    var min = Math.floor(timer / 60);
    var sec = Math.floor(timer % 60);

    var disp = sec < 10 ? min + ":0" + sec : min + ":" + sec;

    document.getElementById("timer").innerHTML = disp;
    timer++;
}

function sendRequest(req) {

    var xmlHTTP = new XMLHttpRequest();

    xmlHTTP.onreadystatechange = function () {

        if (xmlHTTP.readyState === 4 && xmlHTTP.status === 200) {

            var response = xmlHTTP.responseText;

            responseData = response.split(",");
            clearDisplayer();
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

    if (e.keyCode === 37 && play) {

        leftRequest();
    } else if (e.keyCode === 38 && play) {

        rotateRequest();
    } else if (e.keyCode === 39 && play) {

        rightRequest();
    } else if (e.keyCode === 40 && play) {

        downRequest();
    }
    else if(e.keyCode === 107 && play){
        increaseLevelValue();
        getDelay();
        displayLevelValue();
    }
    else if(e.keyCode === 109 && play){
        decreaseLevelValue();
        getDelay();
        displayLevelValue();
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
        
     document.getElementById(responseData[i]).style.backgroundColor = actualShapeColor;
        
    }
}

function clearDisplayer() {
    
     for (var i = 0; i < 200; i++) {
         document.getElementById(i).style.backgroundColor = "lightgray";
     }
    
}

function stopStartGame() {
    
    if(play){
         clearInterval(run);
    }
    else{
        run = setInterval(downRequest, tempo);
    }
   play = !play;
}
 
    function  increaseLevelValue() {
        if (level < 10) {
            level++; 
        }
    }
    
  
    function  decreaseLevelValue() {
        if (1 < level) {
            level--; 
        }
    }

    function  getDelay() {

    clearInterval(run);
    
        switch (level) {

            case 1:    
                tempo = 800;
                break;
            case 2:
               tempo = 600;
               break;
            case 3:
                tempo = 400;
                break;
            case 4:
                tempo = 200;
                break;
            case 5:
               tempo = 150;
                break;
            case 6:
                tempo = 100;
                break;
            case 7:
               tempo = 80;
               break;
            case 8:
                tempo = 60;
                break;
            case 9:
               tempo = 50;
               break;
            
        }
            
            run = setInterval(downRequest,tempo);
    }
    
    function displayLevelValue() {
    document.getElementById("levelValue").innerHTML = level;
  }