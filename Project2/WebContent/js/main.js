function incrementCategoryPage(category, direction) {
	var page = 0;

	if (direction > 0) {
		page++;
	} else if (direction < 0) {
		page--;
	}

	document.location.href = "/?category=" + category + "&page=" + page;
}

function gotoCategoryPage(category, pageIndex) {
	document.location.href = "/?category=" + category + "&page=" + pageIndex;
}

$(function() {
});

$(".problem-card").click(function() {
	var id = $(this).attr("data-id");
	document.location.href = "problem?id=" + id;
});