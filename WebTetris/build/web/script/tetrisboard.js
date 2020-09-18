var actualShapeString = document.getElementById("act").value;
var nextShapeString = document.getElementById("next").value;
document.getElementById("stop").addEventListener("click", stopStartGame);
document.getElementById("newgame").addEventListener("click", function () {
    location.reload();
});
document.addEventListener("keydown", pressButton);
var actualShape = actualShapeString.split(",");
var actualShapeColor = actualShape[1];
var nextShape = nextShapeString.split(",");
var nextShapeColor = nextShape[1];
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

for (var i = 2; i < actualShape.length; i++) {
    document.getElementById(actualShape[i]).style.backgroundColor = actualShapeColor;
    document.getElementById("next" + nextShape[i]).style.backgroundColor = nextShapeColor;
}

var duration = setInterval(displayTimer, 1000);
var run = setInterval(stepRequest, tempo);

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
           
            displayerController(response);

        }

    };

    var url = document.location.protocol + "//" + document.location.host +
            document.location.pathname + "play?move=" + req;


    xmlHTTP.open("POST", url, true);
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

        stepRequest();
    } else if (e.keyCode === 107 && play) {
        increaseLevelValue();
        getDelay();
        displayLevelValue();
    } else if (e.keyCode === 109 && play) {
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

function stepRequest() {
    sendRequest("step");
}


function displayerController(resp) {

     if (resp.startsWith("endofgame")) {
         
        actualShape = resp.split(",");
        actualShapeColor = actualShape[1];
        theEndOfTheGameProcess();

    } else if (resp.startsWith("actual")) {
        
        actualShape = resp.split(",");
        actualShapeColor = actualShape[1];
        clearActualShape();
        displayActualShape();
        
    } else if (resp.startsWith("next")) {
        
        nextShape = resp.split(",");
        displayScore(nextShape[12]);
        nextShapeColor = nextShape[1];
        clearNextShape();
        displayNextShape();
        actualShape = [];
        for(var i = 6; i < nextShape.length - 1; i++){
            actualShape.push(nextShape[i]);
        }
        actualShapeColor = actualShape[1];
        displayActualShape();
        

    } else if (resp.startsWith("fullrow")) {

        alert(resp);
    }
}

function clearActualShape() {
    
    for (var i = 6; i < actualShape.length; i++) {
        
        document.getElementById(actualShape[i]).style.backgroundColor = "lightgray";
    }
}

function displayActualShape() {
    
    for (var i = 2; i < 6; i++) {

        document.getElementById(actualShape[i]).style.backgroundColor = actualShapeColor;

    }
}


function clearNextShape() {

    for (var i = 0; i < 4; i++) {
        document.getElementById("next" + i).style.backgroundColor = "#dfe5c9";
        document.getElementById("next" + (i + 10)).style.backgroundColor = "#dfe5c9";
    }
}

function displayNextShape() {

    for (var i = 2; i < 6; i++) {
        document.getElementById("next" + nextShape[i]).style.backgroundColor = nextShapeColor;
    }
}

function displayScore(score) {
    document.getElementById("scoreValue").innerHTML = score;
}

function theEndOfTheGameProcess() {
    
    displayActualShape();
    clearInterval(run);
    clearInterval(duration);
    play = false;
    alert("Vége a játéknak!");
}
function stopStartGame() {

    if (play) {
        clearInterval(run);
    } else {
        run = setInterval(stepRequest, tempo);
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

    run = setInterval(stepRequest, tempo);
}

function displayLevelValue() {
    document.getElementById("levelValue").innerHTML = level;
}