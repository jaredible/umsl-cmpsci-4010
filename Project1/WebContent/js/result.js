$(function() {
	var city = $("#city").html().split(":")[1];
	var number = $("#number").html().split(":")[1];
	var result = $("#result").html().split(":")[1];
	var invoice = $("#invoice").html().split(":")[1];

	console.log("City: " + city);
	console.log("Number: " + number);
	console.log("Result: " + result);
	console.log("Invoice: " + invoice);

	if (result.toLowerCase().indexOf("prime") >= 0) {
		// $("#result").attr("title", "The only factors of " + number + " are 1 and itself.");
	} else {
		// TODO
	}

	$('[data-toggle="tooltip"]').tooltip();
});