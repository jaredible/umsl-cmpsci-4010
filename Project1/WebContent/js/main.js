var isProduction = window.location.href.indexOf("localhost"); // remove false
var domainName = "http://localhost:8888";
var socket = io.connect(domainName);

socket.on("message", function(message) {
	console.log(message);
});

socket.on("messages", function(messages) {
	console.log(messages);
});

socket.emit("messages");
socket.emit("message", "Testing");

$(function() {
	UserInfo.getInfo(function(data) {
		console.log(data);
		if (isProduction) {
			$.ajax({
				url: domainName + "/notifier/notify",
				method: "post",
				data: {
					"phone": "3146291836",
					"message": "Hello World!",
					"user-info": data
				},
				success: function(data) {
					console.log(data);
				}
			});
		}
	}, function(err) {
		console.log(err);
	});
});
