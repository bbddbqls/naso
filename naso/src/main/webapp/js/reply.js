//댓글 달기
// $(document).ready(function() {
$('.click').on('click', function() {
	var pNum = $(this).data('pnum');
	// 서버에 AJAX 요청 보내기
	$.ajax({
		url: '/post/replyShow.do', // 댓글을 가져오는 엔드포인트 주소
		method: 'POST', // 혹은 POST 등 요청 방식에 맞게 지정
		dataType: "JSON",
		data: { pNum: pNum }, // 요청에 필요한 데이터 전달
		success: function(response) {
			// 서버로부터의 응답을 받았을 때 실행되는 콜백 함수
			console.log(response);
			// 댓글 목록 채우기
			var commentsHtml = '';
			var commentCount;
			for (var i = 0; i < response.length; i++) {
				var rDatetimeCreated = new Date(response[i].rDatetimeCreated); // DB에서 가져온 타임스탬프
				var currentTime = new Date(); // 현재 시간

				var timeDiff = currentTime - rDatetimeCreated; // 현재 시간과의 차이(ms)
				var seconds = Math.floor(timeDiff / 1000); // 초 단위로 변환

				var timeAgo;
				if (seconds < 60) {
					timeAgo = seconds + '초 전';
				} else if (seconds < 3600) {
					var minutes = Math.floor(seconds / 60);
					timeAgo = minutes + '분 전';
				} else if (seconds < 86400) {
					var hours = Math.floor(seconds / 3600);
					timeAgo = hours + '시간 전';
				} else {
					var days = Math.floor(seconds / 86400);
					timeAgo = days + '일 전';
				}
				commentsHtml += '<li>' +
					'<div class="comment-list">' +
					'<div class="user-profile-img">' +
					'<img src="../images/icon/person-circle.svg" alt="">' +
					'</div>' +
					'<div class="user-profile-name">' +
					'<p><strong><a style="text-decoration:none; color:black;" href="/prfCtrl?mNum=' + response[i].mNum + '">' + response[i].mUserId
					+ '</a></strong>' + '<span class="time" style="font-size: 60%; color: #ff7f00;">(' + timeAgo + ')</span>' + '</p>' +
					'<p class="comment">' + response[i].rContent + '</p>' +
					'</div>' +
					'</div>' +
					'</li>';
				commentCount = i + 1;

			}
			$('#commentCount' + pNum).text(commentCount);
			$('#modalComments' + pNum + ' ul').html(commentsHtml);

			// 모달 창 열기
			$('#detail' + pNum).modal('show');
		},
		error: function(error) {
			console.log('Error:', error);
		}
	});
});

$('.addon').on('click', function() {
	var pNum = $(this).data('pnum');
	var userId = $(this).data('userid');
	var commentText = $('#modalAddComments' + pNum + ' input').val();
	if (!commentText) {
		window.alert("댓글을 입력해 주세요!!!!");
	}
	else {
		// 서버에 AJAX 요청 보내기
		$.ajax({
			url: '/post/replyUpload.do', // 댓글을 생성하는 엔드포인트 주소
			method: 'POST', // POST 방식으로 변경
			data: { pNum: pNum, commentText: commentText, userId: userId }, // 요청에 필요한 데이터 전달
			success: function(response) {
				// 서버로부터의 응답을 받았을 때 실행되는 콜백 함수
				// 새로운 댓글 생성
				var newComment = '<li>' +
					'<div class="comment-list">' +
					'<div class="user-profile-img">' +
					'<img src="../images/icon/person-circle.svg" alt="">' +
					'</div>' +
					'<div class="user-profile-name">' +
					'<p><strong><a style="text-decoration:none; color:black;" href="/prfCtrl?mNum=' + response.mNum + '">' + response.userId + '</a></strong><span class="time" style="font-size: 60%; color: #ff7f00;">(지금)</span>' + '</p>' +
					'<p class="comment">' + response.commentText + '</p>' +
					'</div>' +
					'</div>' +
					'</li>';
				// 기존 댓글 목록에 새로운 댓글 추가
				$('#modalComments' + pNum + ' ul').append(newComment);

				// 댓글 입력 필드 초기화
				$('#modalAddComments' + pNum + ' input').val('');
				var commentCountElement = $('#commentCount' + pNum);
				var currentCommentCount = parseInt(commentCountElement.text(), 10);
				var newCommentCount = currentCommentCount + 1;
				commentCountElement.text(newCommentCount);

				// 새로운 댓글 생성 후 스크롤 아래로 내리기
				var commentContainer = document.getElementById('modalComments' + pNum); // 댓글 컨테이너 요소
				commentContainer.scrollTop = commentContainer.scrollHeight;
			},
			error: function(error) {
				console.log('Error:', error);
			}
		});
	}

});
// });

//댓글 보기