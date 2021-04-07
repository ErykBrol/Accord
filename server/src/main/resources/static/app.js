var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/room', function (greeting) {
            showMessage(JSON.stringify(greeting.body));
            console.log(JSON.parse(greeting.body).roomCode);
            // Subscribe to status updates
            stompClient.subscribe('/topic/room/' + JSON.parse(greeting.body).roomCode + "/status", function (status) {
                showMessage(JSON.stringify(status.body));
            });
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function createRoom() {
    stompClient.send("/app/room/create", {}, JSON.stringify({'topic': $("#topic").val(), 'duration': $("#duration").val()}));
}

function joinRoom() {
    // Subscribe to room for status updates (i.e. another user joins/leaves)
    stompClient.subscribe('/topic/room/' + $("#code").val() + "/status", function (status) {
        showMessage(JSON.stringify(status.body));
    });

    stompClient.send("/app/room/join", {}, JSON.stringify({'roomCode': $("#code").val()}));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function submission() {
    stompClient.send("/app/room/submission", {}, JSON.stringify({'name': $("#submission").val()}));
}

function closeRoom() {
    stompClient.send("/app/room/close-room", {}, "");
}

function startTournament() {
    stompClient.send("/app/room/setup-tournament", {}, "");
}

function vote() {
    stompClient.send("/app/room/vote", {}, $("#vote").val());
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { createRoom(); });
    $( "#join" ).click(function() { joinRoom(); });
    $( "#submit" ).click(function() { submission(); });
    $( "#close" ).click(function() { closeRoom(); });
    $( "#start-tournament" ).click(function() { startTournament(); });
    $( "#submit-vote" ).click(function() { vote(); });
});