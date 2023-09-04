$('.follow').on('click', function() {
	var mymnum = $(this).data('mymnum');
	var othermnum = $(this).data('othermnum');
	// 서버에 AJAX 요청 보내기
	$.ajax({
		url: '/follow/follow.do',
		method: 'POST',
		dataType: "JSON",
		data: { myMNum: mymnum, otherMNum: othermnum },
		success: function(response) {
			console.log(response);
			location.reload();
			alert("팔로우 했습니다!");
		},
		error: function(error) {
			console.log('Error:', error);

		}
	});
});
$('.unfollow').on('click', function() {
	var mymnum = $(this).data('mymnum');
	var othermnum = $(this).data('othermnum');
	// 서버에 AJAX 요청 보내기
	$.ajax({
		url: '/follow/unfollow.do',
		method: 'POST',
		dataType: "JSON",
		data: { myMNum: mymnum, otherMNum: othermnum },
		success: function(response) {
			console.log(response);
			location.reload();
			alert("팔로우 취소 했습니다ㅠ");
		},
		error: function(error) {
			console.log('Error:', error);
		}
	});
});
