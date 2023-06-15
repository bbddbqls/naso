//댓글 달기
$(document).ready(function() {
	$('#button-addon2').on('click', function() {
		var pNum = $(this).data('pnum');
		var commentText = $('#modalPhotoAddComments' + pNum + ' input').val();
		if (!commentText) {
			window.alert("댓글을 입력해 주세요!!!!");
		}
		else {
			// 서버에 AJAX 요청 보내기
			$.ajax({
				url: '/post/upReply.do', // 댓글을 생성하는 엔드포인트 주소
				method: 'POST', // POST 방식으로 변경
				data: { pNum: pNum, commentText: commentText }, // 요청에 필요한 데이터 전달
				success: function(response) {
					// 서버로부터의 응답을 받았을 때 실행되는 콜백 함수

					// 새로운 댓글 생성
					var newComment = '<li>' +
						'<div class="comment-list">' +
						'<div class="user-profile-img">' +
						'<img src="../images/yubin/person.png" alt="">' +
						'</div>' +
						'<div class="user-profile-name">' +
						'<p><strong>' + response.userName + '</strong></p>' +
						'<p class="comment">' + response.commentText + '</p>' +
						'</div>' +
						'</div>' +
						'</li>';

					// 기존 댓글 목록에 새로운 댓글 추가
					$('#modalPhotoComments' + pNum + ' ul').append(newComment);

					// 댓글 입력 필드 초기화
					$('#modalPhotoAddComments' + pNum + ' input').val('');
					// 새로운 댓글 생성 후 스크롤 아래로 내리기
					var commentContainer = document.getElementById('modalPhotoComments' + pNum); // 댓글 컨테이너 요소
					commentContainer.scrollTop = commentContainer.scrollHeight;
				},
				error: function(error) {
					console.log('Error:', error);
				}
			});
		}

	});
});

//댓글 보기