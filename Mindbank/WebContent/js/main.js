window.MathJax = {
	tex2jax : {
		inlineMath : [ [ '$', '$' ], [ "\\(", "\\)" ] ],
		processEscapes : true
	}
};

$(".ui.dropdown").dropdown();

$("#tag .ui.dropdown").dropdown({
	maxSelections : 3
});

$('#rangeStart').calendar({
	type : 'date',
	endCalendar : $('#rangeEnd')
});
$('#rangeEnd').calendar({
	type : 'date',
	startCalendar : $('#rangeStart')
});

$(document).ready(function() {

	// fix menu when passed
	$('.masthead').visibility({
		once : false,
		onBottomPassed : function() {
			$('.fixed.menu').transition('fade in');
		},
		onBottomPassedReverse : function() {
			$('.fixed.menu').transition('fade out');
		}
	});

	// create sidebar and attach to menu open
	$('.ui.sidebar').sidebar('attach events', '.toc.item');

});
