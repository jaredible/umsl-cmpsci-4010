$(function() {
	$("#problem-edit").click(function() {
		var button = $(this);
		var problemId = button.attr("data-id");
		console.log(problemId);
		$.ajax({
			type : "POST",
			url : "problem",
			data : {
				id : problemId,
				edit: true
			},
			success : function(data, status) {
				console.log(data);
			},
			dataType : "json"
		});
	});

	$("#problem-delete").click(function() {
		var button = $(this);
		var problemId = button.attr("data-id");
		console.log(problemId);
	});
});