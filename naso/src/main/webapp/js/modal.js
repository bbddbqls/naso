$(document).ready(function() {
  $('.clickable-image').on('click', function() {
    var pNum = $(this).data('pnum');
    
    // 서버에 AJAX 요청 보내기
    $.ajax({
      url: '/your-dao-endpoint', // DAO의 메서드를 호출하는 엔드포인트 주소
      method: 'POST', // 혹은 GET 등 요청 방식에 맞게 지정
      data: { pNum: pNum }, // 요청에 필요한 데이터 전달
      success: function(response) {
        // 서버로부터의 응답을 받았을 때 실행되는 콜백 함수
        
        // 모달 창에 데이터 채우기
        $('#detailPhotoLabel').text(response.title);
        $('#modalPhotoImage').attr('src', response.mediaS3);
        $('#modalPhotoUserId').text(response.userId);
        $('#modalPhotoContent').text(response.content);
        $('#modalPhotoLikeCount').text(response.likeCount);
        
        // 댓글 목록 채우기
        var commentsHtml = '';
        for (var i = 0; i < response.comments.length; i++) {
          var comment = response.comments[i];
          commentsHtml += '<li>' +
                          '<div class="comment-list">' +
                          '<div class="user-profile-img">' +
                          '<img src="../images/yubin/person.png" alt="">' +
                          '</div>' +
                          '<div class="user-profile-name">' +
                          '<p><strong>' + comment.userName + '</strong></p>' +
                          '<p><span class="comment">' + comment.commentText + '</span></p>' +
                          '</div>' +
                          '</div>' +
                          '</li>';
        }
        $('#modalPhotoComments').html(commentsHtml);
        
        // 모달 창 열기
        $('#detailPhotoModal').modal('show');
      },
      error: function(error) {
        console.log('Error:', error);
      }
    });
  });
});