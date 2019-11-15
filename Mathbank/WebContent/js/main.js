var deleteTitle = $("#deleteTitle");
var deleteButton = $("#deleteButton");

deleteTitle.on("input", function(e) {
	var ele = $(this);
	
	var dataVal = ele.attr("data-title");
	var inputVal = ele.val();
	
	if (inputVal === dataVal) {
		deleteButton.removeAttr("disabled");
		deleteTitle.removeClass("is-invalid");
	} else {
		deleteButton.attr("disabled", "disabled");
		deleteTitle.addClass("is-invalid");
	}
});