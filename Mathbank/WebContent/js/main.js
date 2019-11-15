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

$("textarea[name='comment'").on("input", function(e) {
	$("#charactersRemaining").html(1000 - $(this).val().length);
});

function translateMarkdown() {
	var markdown = $(".markdown");
	var converter = new showdown.Converter();
	converter.setFlavor("github");
	var text = markdown.html();
	var html = converter.makeHtml(text);
	markdown.html(html);
}

$(function() {
	translateMarkdown();
});