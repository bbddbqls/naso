<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page
	import="org.json.simple.JSONObject, org.json.simple.parser.JSONParser, org.json.simple.parser.ParseException"%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Naso</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/main.css?s">
<link rel="stylesheet" href="../css/profile.css?ss">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Caveat&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/0cf27f7ac1.js"
	crossorigin="anonymous"></script>
</head>
<body class="position-relative">
	<!-- 채팅버튼 위치 잡기 위한 클래스 지정 -->
	<!-- 헤더 -->
	<header class="container-fluid mb-1">
		<div class="row">
			<div class="col-sm-2 ps-0 pt-0">
				<img src="../images/main4.png" alt="로고" width="150px" height="165px"
					onclick="goMain()">
				<script>
					function goMain(){
						location="/post/my.do";
					}
				</script>
			</div>
			<!-- 검색창 -->
			<div class="col-sm-8 mt-3"
				style="padding-top: 20px; text-align: center;">
				<div class="d-flex justify-content-center align-items-center">
					<div class="position-relative" style="width: 450px;">
						<form action="/schCtrl">
							<span class="position-absolute top-50 start-0 translate-middle-y"
								style="color: #ff7f00;">&nbsp<i class="fa fa-search"></i></span>
							<input type="text" id="search" placeholder="Search..."
								name="keyword" /> <input type="submit" style="display: none;">
						</form>
					</div>
				</div>
			</div>
			<!-- 로그인, 알림창 버튼 -->
			<div class="col-sm-2 mt-3"
				style="padding-top: 20px; padding-right: 50px; text-align: right;">
				<button type="button" class="btn btn-basic p-1 goProfile"
					onclick="goProfile(${sessionScope.mNum})" data-bs-toggle="popover"
					data-bs-placement="bottom" data-bs-content="Bottom popover"
					style="margin-bottom: 3px;">
					<i class="fa-regular fa-circle-user fa-2xl"></i>
				</button>
				<button type="button" class="btn btn-basic p-1"
					data-bs-toggle="popover" data-bs-placement="bottom"
					onclick="logout()" data-bs-content="Bottom popover"
					style="margin-bottom: 3px;">
					<i class="fa-solid fa-right-from-bracket fa-2xl"></i>
				</button>
			</div>
	</header>
	<!-- 메인 컨텐츠 영역 -->
	<main class="container-fluid">
		<div class="row" style="text-align: center;">
			<div class="col-lg-2 text-lg-start"></div>



			<!-- 프로필 & 게시물 영역 -->
			<div class="col-lg-8 container">
				<!-- 프로필 -->
				<div class="row container">
					<div class="col-md-10 container">
						<div class="row text-lg-start">
							<!-- ProfileController로부터 넘겨받은 데이터가 DTO 형식(필드들이 private임)으로 되어있으므로 게터를 사용해서 데이터를 가져온다. -->
							<h2 class="col-lg-6 fw-bold">${targetProfile[0].getName()}<span
									class="user-id">(${targetProfile[0].getUserId()})</span>
							</h2>
							<div class="col-lg-6"></div>
						</div>
						<div class="row text-lg-start">
							<p class="col-lg-6">사용자 소개</p>
							<div class="col-lg-6"></div>
						</div>
						<div class="row text-lg-start">

							<div class="col-lg-6"></div>
						</div>
						<div class="row">
							<div class="col-lg-4">
								<span class="fw-bold">게시물</span>
								<p>${postCount }</p>
							</div>
							<div class="col-lg-4">
								<span class="fw-bold">팔로워</span>
								<p>${followerCount}</p>
							</div>

							<div class="col-lg-4">
								<span class="fw-bold">팔로우</span>
								<p>${followingCount}</p>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-8"></div>
							<c:set var="sessionNum" value="${ sessionScope.mNum}" />
							<script>
	function redirectToOther(num, sessionNum) {
		var mNum = num;
		var url = null;
		if (num === sessionNum) {
			url = 'post/my.do';
		} else {
			url = 'post/other.do?mNum=' + encodeURIComponent(mNum);
		}
		window.location.href = url;
	}
	function profileUpdate(){
		window.location.href = '/member/editProfile.do';
	}

</script>
							<button type="button" class="btn btn-light col-lg-1 px-0"
								onclick="redirectToOther(${targetProfile[0].getMNum()},${sessionNum})">홈</button>
							<c:choose>
								<c:when test="${sessionNum == targetProfile[0].getMNum() }">
									<button type="button" class="btn btn-light col-lg-2 px-0 ms-1"
										onclick="profileUpdate()">프로필 수정</button>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${checkFollow}">
											<button type="button"
												class="btn btn-light col-lg-2 px-0 ms-1 unfollow"
												data-mymnum="${sessionNum}"
												data-othermnum="${targetProfile[0].getMNum()}">팔로우
												취소</button>
										</c:when>
										<c:otherwise>
											<button type="button"
												class="btn btn-light col-lg-2 px-0 ms-1 follow"
												data-mymnum="${sessionNum}"
												data-othermnum="${targetProfile[0].getMNum()}">팔로우</button>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>



						</div>
					</div>
				</div>
				<hr>
				<!-- 게시물 -->
				<div class="item-list">
					<p>게시물</p>
					<div class="container-fluid">
						<div
							class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5">
							<!-- ========================================================================================== -->
							<c:choose>
								<c:when test="${empty targetProfile }">
									<p>검색 결과가 없습니다.</p>
								</c:when>
								<c:otherwise>
									<!-- 아래 a 태그는 클릭시 해당 계정 아이디 값을 "userId"에 담아서 prfCtrl(ProfileController)로 넘김 -->
									<c:forEach var="itemLists" items="${itemList}">
										<c:choose>
											<c:when test="${itemLists.getCategory() eq 'photo'}">
												<div class="col item" data-bs-toggle="modal">
													<img
														src="https://naso-main.s3.ap-northeast-2.amazonaws.com/${itemLists.mediaS3}"
														class="click" data-pnum="${itemLists.getPNum()}"
														data-userid="<c:out value="${itemLists.getPNum()}" />">
												</div>
											</c:when>
											<c:otherwise>
												<div class="col item" data-bs-toggle="modal">
													<video
														src="https://naso-main.s3.ap-northeast-2.amazonaws.com/${itemLists.mediaS3}"
														class="click" data-pnum="${itemLists.getPNum()}"
														data-userid="<c:out value="${itemLists.getPNum()}" />">
													</video>
												</div>
											</c:otherwise>
										</c:choose>
									</c:forEach>

								</c:otherwise>
							</c:choose>
							<!-- ========================================================================================== -->
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
		<c:forEach var="itemLists" items="${itemList}">
			<div class="modal fade" id="detail${itemLists.getPNum()}"
				tabindex="-1" aria-labelledby="detailPhotoLabel" aria-hidden="true">
				<div
					class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">목록</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body row select-modal" style="height: 60vh">
							<c:choose>
								<c:when test="${itemLists.getCategory() eq 'photo'}">
									<div class="container col-lg-6 select-modal-img">
										<div class="img-box">
											<img
												src="https://naso-main.s3.ap-northeast-2.amazonaws.com/${itemLists.mediaS3}"
												alt="">
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="container col-lg-6 select-modal-img">
										<div class="img-box">
											<video class="img-fluid click" autoplay muted loop 
												data-pnum="${itemLists.getPNum()}" data-bs-toggle="modal"
												data-bs-target="#detail${itemLists.getPNum()}"
												src="https://naso-main.s3.ap-northeast-2.amazonaws.com/${itemLists.mediaS3}"
												style="max-width: 100%; max-height: 100%; object-fit: contain;"></video>
										</div>
									</div>
								</c:otherwise>
							</c:choose>



							<div class="container col-lg-6 select-modal-information">
								<div class="container-fluid select-modal-user">
									<div class="user-profile-img">
										<img src="../images/icon/person-circle.svg" alt="">
									</div>
									<div class="user-profile-name">
										<h4>
											<strong>&nbsp;${itemLists.userId}</strong>
										</h4>
									</div>
								</div>
								<div class="container-fluid select-modal-text">${itemLists.content}</div>
								<div class="container-fluid select-modal-buttons">
									<img src="../images/yubin/heart.png" alt="">
									<p>${itemLists.likeCount}</p>
									<img src="../images/yubin/talk.png" alt="">
									<p id="commentCount${itemLists.getPNum()}">0</p>
									<fmt:parseDate var="parsedDate"
										value="${itemLists.datetimeCreated}"
										pattern="yyyy-MM-dd HH:mm:ss.S" />
									<span class="post-date"><fmt:formatDate
											value="${parsedDate}" pattern="yyyy년 MM월 dd일" /></span>
								</div>
								<div class="container-fluid select-modal-comment"
									id="modalComments${itemLists.getPNum()}">
									<ul>
									
									</ul>
								</div>
								<div class="input-group mb-3 select-modal-entercomment"
									id="modalAddComments${itemLists.getPNum()}">
									<input type="text" class="form-control" id="reply"
										placeholder="댓글 달기..." aria-label="댓글 달기..."
										aria-describedby="button-addon2">
									<button class="btn btn-outline-secondary addon" type="button"
										id="button-addon2" data-pnum="${itemLists.getPNum()}">게시</button>
								</div>
							</div>
						</div>
						<div class="modal-footer">

							<label class="my-button">Close
								<button type="button" class="my-button org-btn"
									data-bs-dismiss="modal"
									onmouseover="this.style.backgroundColor='#FFA500';"
									onmouseout="this.style.backgroundColor='#FF7F00';">Close</button>
							</label>
							<c:if test="${sessionNum == targetProfile[0].getMNum()}">
								<label class="my-button">삭제
									<button onclick="postDelete(${videoItem.getPNum()})">
									</button>
								</label>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</main>
	<!-- 좌측 하단 메뉴 -->
	<div class="btn-group dropup position-fixed bottom-0 start-0">
		<!-- 메뉴버튼 -->
		<button type="button" class="btn btn-basic dropdown-toggle-split"
			data-bs-toggle="dropdown" aria-expanded="false"
			data-bs-auto-close="false">
			<i class="fas fa-bars"></i>
		</button>
		<!-- data-bs-container="body" data-bs-toggle="popover" data-bs-placement="right" data-bs-content="Right popover" -->
		<!-- 메뉴목록 -->
		<ul class="dropdown-menu shadow-sm">
			<!-- 프로필-->
			<li><a href="#" class="dropdown-item text-center px-0"><i
					class="fa-solid fa-user"></i></a></li>
			<!-- 고객센터-->
			<li><a href="#" class="dropdown-item text-center px-0"><img
					src="../images/cs.png" alt="" style="width: 20px; height: 20px;"></a></li>
			<!-- 설정-->
			<li><a href="#" class="dropdown-item text-center px-0"><i
					class="fa-solid fa-gear"></i></a></li>
			<!-- 로그아웃-->
			<li><a href="#" class="dropdown-item text-center px-0"><i
					class="fa-solid fa-right-from-bracket"></i></a></li>
		</ul>
	</div>
	<!-- Javascript============================================================================================================ -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="../js/main.js?s"></script>
	<script src="../js/reply.js"></script>
	<script src="../js/follow.js?sㄴsq"></script>
</body>
</html>