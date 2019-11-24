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

$("#profileEdit").click(function() {
	$("input[name='profileImage'").trigger("click");
});

$uploadCrop = $('#upload-demo').croppie({
	enableExif : true,
	viewport : {
		width : 200,
		height : 200,
		type : 'circle'
	},
	boundary : {
		width : 300,
		height : 300
	}
});

var profileImage = document.getElementsByName("profileImage")[0];
if (profileImage) {
	profileImage.onchange = function(event) {
		var target = event.target || window.event.srcElement;
		var files = target.files;

		if (FileReader && files && files.length) {
			var fr = new FileReader();
			fr.onload = function() {
				document.getElementById("profilePicture").src = fr.result;
			};
			fr.readAsDataURL(files[0]);
		} else {
		}
	};
}

$(document).ready(function() {
	$('header').visibility({
		once : false,
		onBottomPassed : function() {
			$('.fixed.menu').transition('fade in');
		},
		onBottomPassedReverse : function() {
			$('.fixed.menu').transition('fade out');
		}
	});

	$('.ui.sidebar').sidebar('attach events', '.toc.item');

});
