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
var levelController = [true,false,false,false,false,false,false,false,false,false];

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

        resp = resp.split(",");
        createNextShape(resp);
        clearNextShape();
        displayNextShape();
        createActualShape(resp);
        displayActualShape();
        setLevel(resp[resp.length - 1]);
        getDelay();
        displayScore(resp[resp.length - 1]);
        
       
    } else if (resp.startsWith("fullrow")) {

        resp = resp.split(",");
        createNextShape(resp);
        clearNextShape();
        displayNextShape();
        createActualShape(resp);
        setLevel(resp[12]);
        getDelay();
        displayScore(resp[12]);
        clearFullRow(resp);
        increaseRows(resp);
        displayActualShape();
        
    }
}

function createNextShape(respString) {

    nextShape = [];
    for (var i = 0; i < 6; i++) {
        nextShape.push(respString[i]);
    }
    nextShapeColor = nextShape[1];
}

function createActualShape(respString) {

    actualShape = [];
    for (var i = 6; i < 12; i++) {
        actualShape.push(respString[i]);
    }
    actualShapeColor = actualShape[1];
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

function clearFullRow(fullRowIndex) {

    for (var i = 13; i < fullRowIndex.length; i++) {

        for (var j = 0; j < 10; j++) {

            document.getElementById(parseInt(fullRowIndex[i] * 10 + j)).style.backgroundColor = "lightgray";

        }

    }

}

function increaseRows(fullRowIndex) {

    for (var i = 13; i < fullRowIndex.length; i++) {

        for (var index = 199; 0 < index; index--) {

            var shapeColor = document.getElementById(index).style.backgroundColor;

            if (shapeColor !== "lightgray" && parseInt(fullRowIndex[i]) > Math.floor(index / 10)) {

                document.getElementById(index).style.backgroundColor = "lightgray";
                document.getElementById((index + 10)).style.backgroundColor = shapeColor;
            }
        }
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

function setLevel(scoreValue) {
    
    score = parseInt(scoreValue);
    
    if (1000 <= score && levelController[0]) {
        increaseLevelValue();
        setLevelController(1);
    } else if (2000 <= score && levelController[1]) {
        increaseLevelValue();
        setLevelController(2);
    } else if (5000 <= score && levelController[2]) {
        increaseLevelValue();
        setLevelController(3);
    } else if (10000 <= score && levelController[3]) {
        increaseLevelValue();
        setLevelController(4);
    } else if (20000 <= score && levelController[4]) {
        increaseLevelValue();
        setLevelController(5);
    } else if (25000 <= score && levelController[5]) {
        increaseLevelValue();
        setLevelController(6);
    } else if (30000 <= score && levelController[6]) {
        increaseLevelValue();
        setLevelController(7);
    } else if (35000 <= score && levelController[7]) {
        increaseLevelValue();
        setLevelController(8);
    } else if (40000 <= score && levelController[8]) {
        increaseLevelValue();
        setLevelController(9);
    } else if (45000 <= score && levelController[9]) {
        increaseLevelValue();
        setLevelController(10);
    }

    displayLevelValue();
}

function setLevelController(index) {

    levelController = [];

    for (var i = 0; i < 10; i++) {

        if (i !== index) {
            levelController.push(false);
        } else {
            levelController.push(true);
        }

    }
}

function displayLevelValue() {
    document.getElementById("levelValue").innerHTML = level;
}