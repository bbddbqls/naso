<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="../css/main.css?ss">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Caveat&display=swap"
	rel="stylesheet">
<script src="https://sdk.amazonaws.com/js/aws-sdk-2.809.0.min.js"></script>
<script src="https://kit.fontawesome.com/0cf27f7ac1.js"
	crossorigin="anonymous"></script>

</head>
<body class="position-relative">
	<!-- 채팅버튼 위치 잡기 위한 클래스 지정 -->
	<!-- 헤더 -->
	<header class="container-fluid mb-1">
		<div class="row">
			<div class="col-sm-2 ps-0 pt-0">
				<img src="../images/main4.png" alt="로고" width="150px" height="165px">
			</div>
			<!-- 검색창 -->
			<div class="col-sm-8 mt-3"
				style="padding-top: 20px; text-align: center;">
				<div class="d-flex justify-content-center align-items-center">
					<div class="position-relative" style="width: 450px;">
						<span class="position-absolute top-50 start-0 translate-middle-y"
							style="color: #ff7f00;">&nbsp<i class="fa fa-search"></i></span>
						<input type="search" id="search" placeholder="Search..." />
					</div>
				</div>
			</div>
			<!-- 로그인, 알림창 버튼 -->
			<div class="col-sm-2 mt-3"
				style="padding-top: 20px; padding-right: 50px; text-align: right;">
				<button type="button" class="btn btn-basic p-1"
					data-bs-toggle="popover" data-bs-placement="bottom"
					data-bs-content="Bottom popover" style="margin-bottom: 3px;">
					<i class="fa-regular fa-bell fa-2xl"></i>
				</button>
				<button type="button" class="btn btn-basic p-1"
					data-bs-toggle="popover" data-bs-placement="bottom"
					data-bs-content="Bottom popover" style="margin-bottom: 3px;">
					<i class="fa-regular fa-circle-user fa-2xl"></i>
				</button>
			</div>
	</header>
	<!-- 메인 컨텐츠 영역 -->
	<main class="container-fluid">
		<div class="row" style="text-align: center;">
			<span
				style="color: black; font-family: 'Caveat', cursive; font-size: 50px; font-weight: bold;">
				Naso:<c:out value="${photoList[0].userId}" />
			</span>
			<!-- 사이드바 -->
			<div class="col-lg-2 text-lg-start">
				<ul class="bg-white p-0">
					<!-- 모달(photo, video, diary), 팝오버(chat) 트리거 버튼-->
					<li>
						<button type="button"
							class="btn btn-basic py-4 ps-2 w-50 text-lg-start"
							data-bs-toggle="modal" data-bs-target="#modalPhoto">
							<i class="fa-solid fa-image fa-xl"></i> Photo
						</button>
					</li>
					<li>
						<button type="button"
							class="btn btn-basic py-4 ps-2 w-50 text-lg-start"
							data-bs-toggle="modal" data-bs-target="#modalVideo">
							<i class="fa-solid fa-video fa-xl"></i> Video
						</button>
					</li>
					<li>
						<button type="button"
							class="btn btn-basic py-4 ps-2 w-50 text-lg-start"
							data-bs-toggle="modal" data-bs-target="#modalDiary">
							<i class="fa-solid fa-book fa-xl"></i> Diary
						</button>
					</li>
					<!-- 연동-->
					<li class="dropdown dropend">
						<button type="button"
							class="btn btn-basic py-4 ps-2 w-50 text-lg-start"
							data-bs-toggle="dropdown" data-bs-target="#modalDiary"
							style="width: 200px;">
							<i class="fa-solid fa-envelopes-bulk fa-xl"></i> Marketing
						</button> <!-- 카톡, 인스타 등등... -->
						<ul class="dropdown-menu">
							<!-- Dropdown menu items -->
							<!-- 카톡 -->
							<li><a
								href="https://www.kakaocorp.com/page/service/service/KakaoTalk"
								class="dropdown-item text-center px-0"><img
									src="../images/kakao.png" alt=""
									style="width: 20px; height: 20px;"></a></li>
							<!-- 인스타 -->
							<li><a href="https://www.instagram.com/"
								class="dropdown-item text-center px-0"><img
									src="../images/insta.png" alt=""
									style="width: 20px; height: 20px;"></a></li>
							<!-- 유튜브-->
							<li><a href="https://www.youtube.com/"
								class="dropdown-item text-center px-0"><img
									src="../images/youtube.png" alt=""
									style="width: 20px; height: 20px;"></a></li>
							<!-- 틱톡-->
							<li><a href="https://www.tiktok.com/ko-KR/"
								class="dropdown-item text-center px-0"><img
									src="../images/tiktok.png" alt=""
									style="width: 20px; height: 20px;"></a></li>
							<!-- 아프리카-->
							<li><a href="https://www.afreecatv.com/"
								class="dropdown-item text-center px-0"><img
									src="../images/aftv.png" alt=""
									style="width: 20px; height: 20px;"></a></li>
							<!-- 네이버-->
							<li><a href="https://www.naver.com/"
								class="dropdown-item text-center px-0"><img
									src="../images/naver.png" alt=""
									style="width: 20px; height: 20px;"></a></li>
							<!-- 쇼핑몰-->
							<li><a href="https://shopping.naver.com/home"
								class="dropdown-item text-center px-0"><img
									src="../images/show.png" alt=""
									style="width: 20px; height: 20px;"></a></li>
						</ul>
					</li>
					<li>
						<button type="button"
							class="btn btn-basic py-4 ps-2 w-50 text-lg-start"
							data-bs-toggle="popover" title="Popover Header"
							data-bs-content="
            <input type='text' class='form-control' placeholder='Type your text here'>
            <button type='button' class='btn btn-primary mt-2' onclick='retrieveInputData()'>Submit</button>
            ">
							<i class="fa-solid fa-comments fa-xl"></i> Chat
						</button>
					</li>
				</ul>
				<!-- 포토 모달 -->
				<div class="modal fade" id="modalPhoto" tabindex="-1"
					aria-labelledby="modalPhotoLabel1" aria-hidden="true">
					<div
						class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="modalPhotoLabel1">Photo Gallery</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body" style="width: 100%; height: 60vh">
								<c:choose>
									<c:when test="${empty photoList}">
										<div class="col-12 text-center">게시물이 없습니다.</div>
									</c:when>
									<c:otherwise>
										<div class="container-fluid">
											<div
												class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xl-4">
												<c:forEach var="photoItem" items="${photoList}">
													<div class="col photo-item" data-bs-toggle="modal"
														>
														<img
															src="https://naso-bucket.s3.ap-northeast-2.amazonaws.com/${photoItem.mediaS3}" class="image-click" data-pnum="${photoItem.getPNum()}">
													</div>
												</c:forEach>
											</div>
										</div>
									</c:otherwise>
								</c:choose>

							</div>
							<div class="modal-footer">
								<button class="my-button org-btn" type="button"
									data-bs-toggle="modal" data-bs-target="#postPhoto"
									onmouseover="this.style.backgroundColor='#FFA500';"
									onmouseout="this.style.backgroundColor='#FF7F00';">Post</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 포토 갤러리 글쓰기 모달 -->
				<div class="modal fade" id="postPhoto" aria-hidden="true"
					aria-labelledby="postPhotoLabel2" tabindex="-1">
					<div
						class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="postPhotoLabel2">글쓰기</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body" style="height: 60vh">
								<form action="imageUpload.do" method="post" id="upload-image"
									enctype="multpart/form-data">
									<div class="container">
										<div class="row">
											<div class="col-lg-6 d-flex justify-content-center"
												style="waspect-ratio: auto 1/1;">
												<div class="upload-preview">
													<!-- 													<label class="my-button" id="upimg-btn" for="input-photo">업로드 <input
														class="d-none" type="file" id="input-photo" name="image"
														accept="image/*" onchange="previewImage(event)" />
													</label> -->
													<input id="input-photo" name="input-photo" type="file" />
												</div>
											</div>
											<div class="col-lg-6 ">
												<div>
													<textarea name="content" id="text" cols="30" rows="10"
														placeholder="내용을 입력해 주세요..."></textarea>
													<label><input type="radio" name="exposure"
														value="1" checked>모두 공개</label> <label><input
														type="radio" name="exposure" value="2">친구만 공개</label>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button class="btn btn-secondary" data-bs-target="#modalPhoto"
									data-bs-toggle="modal" role="button">Return</button>
								<button class="my-button" id="upload-button"
									onmouseover="this.style.backgroundColor='#FFA500';"
									onmouseout="this.style.backgroundColor='#FF7F00';">Submit</button>
								<!-- type="submit" form="upload-image" -->
							</div>
						</div>
					</div>
				</div>

				<!-- from yubin 포토모달에서 상세보기-->
				<c:forEach var="photoItem" items="${photoList}">
					<div class="modal fade" id="detailPhoto${photoItem.getPNum()}"
						tabindex="-1" aria-labelledby="detailPhotoLabel"
						aria-hidden="true">
						<div
							class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Photo</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body row select-modal" style="height: 60vh">
									<div class="container col-lg-6 select-modal-img">
										<div class="img-box">
											<img
												src="https://naso-bucket.s3.ap-northeast-2.amazonaws.com/${photoItem.mediaS3}"
												alt="">
										</div>
									</div>
									<div class="container col-lg-6 select-modal-information">
										<div class="container-fluid select-modal-user">
											<div class="user-profile-img">
												<img src="../images/yubin/person.png" alt="">
											</div>
											<div class="user-profile-name">
												<p>${photoItem.userId}</p>
											</div>
										</div>
										<div class="container-fluid select-modal-text">${photoItem.content}</div>
										<div class="container-fluid select-modal-buttons">
											<img src="../images/yubin/heart.png" alt="">
											<p>${photoItem.likeCount}</p>
											<img src="../images/yubin/talk.png" alt="">
											<p id="commentCount${photoItem.getPNum()}">0</p>
										</div>
										<div class="container-fluid select-modal-comment"
											id="modalPhotoComments${photoItem.getPNum()}">
											<ul>
												
											</ul>
										</div>
										<div class="input-group mb-3 select-modal-entercomment"
											id="modalPhotoAddComments${photoItem.getPNum()}">
											<input type="text" class="form-control "
												placeholder="댓글 달기..." aria-label="댓글 달기..."
												aria-describedby="button-addon2">
											<button class="btn btn-outline-secondary addon" type="button"
												id="button-addon2" data-pnum="${photoItem.getPNum()}">게시</button>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<a class="btn btn-secondary" data-bs-toggle="modal"
										href="#modalPhoto" role="button">Return</a>
									<button type="button" class="my-button org-btn"
										data-bs-dismiss="modal"
										onmouseover="this.style.backgroundColor='#FFA500';"
										onmouseout="this.style.backgroundColor='#FF7F00';">Close</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>


				<!-- 비디오 모달 -->
				<div class="modal fade" id="modalVideo" tabindex="-1"
					aria-labelledby="modalVideoLabel1" aria-hidden="true">
					<div
						class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="modalVideoLabel1">Video Gallery</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body" style="height: 60vh">
								<c:choose>
									<c:when test="${empty videoList}">
										<div class="col-12 text-center">게시물이 없습니다.</div>
									</c:when>
									<c:otherwise>
										<div class="container-fluid">
											<div
												class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xl-4">
												<c:forEach var="videoItem" items="${videoList}">
													<div class="col video-item" data-bs-toggle="modal"
														data-bs-target="#detailVideo${videoItem.getPNum()}">
														<img
															src="https://naso-bucket.s3.ap-northeast-2.amazonaws.com/${videoItem.mediaS3}">
													</div>
												</c:forEach>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="modal-footer">
								<button class="my-button" type="button" data-bs-toggle="modal"
									data-bs-target="#write">Post</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 비디오 갤러리 글쓰기 모달 -->
				<div class="modal fade" id="postVideo" aria-hidden="true"
					aria-labelledby="postVideoLabel2" tabindex="-1">
					<div
						class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="postVideoLabel2">글쓰기</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body" style="width: height: 60vh">
								<label for="exampleFormControlTextarea1" class="form-label">Example
									textarea</label>
								<textarea class="form-control" id="exampleFormControlTextarea1"
									rows="3"></textarea>
								<input class="form-control" type="file" id="formFile">
							</div>
							<div class="modal-footer">
								<button class="btn btn-secondary" data-bs-target="#modalVideo"
									data-bs-toggle="modal" role="button">Return</button>
								<button class="my-button" type="submit">Submit</button>
							</div>
						</div>
					</div>
				</div>
				<!-- from yubin 비디오 모달에서 상세보기-->
				<div class="modal fade" id="detailVideo" tabindex="-1"
					aria-labelledby="detailVideoLabel" aria-hidden="true">
					<div
						class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="detailVideoLabel">video</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body row select-modal" style="height: 60vh;">
								<div class="container col-lg-6 select-modal-img">
									<div class="img-box">
										<video controls autoplay muted loop>
											<source src="../video/3.mp4" type="video/mp4">
										</video>
									</div>
								</div>
								<div class="container col-lg-6 select-modal-information">
									<div class="container-fluid select-modal-user">
										<div class="user-profile-img">
											<img src="../images/yubin/person.png" alt="">
										</div>
										<div class="user-profile-name">
											<p>유저이름</p>
										</div>
									</div>
									<div class="container-fluid select-modal-text">본문내용</div>
									<div class="container-fluid select-modal-buttons">
										<img src="../images/yubin/heart.png" alt="">
										<p>0</p>
										<img src="../images/yubin/talk.png" alt="">
										<p>0</p>
									</div>
									<div class="container-fluid select-modal-comment">
										<ul>
											<li>
												<div class="comment-list">
													<div class="user-profile-img">
														<img src="../images/yubin/person.png" alt="">
													</div>
													<div class="user-profile-name">
														<p>
															<strong>유저이름</strong>
														</p>
														<p>
															<span class="comment">아아아 너무 어렵다...아아아 너무
																어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...</span>
														</p>
													</div>
												</div>
											</li>
										</ul>
									</div>
									<div class="input-group mb-3 select-modal-entercomment">
										<input type="text" class="form-control" placeholder="댓글 달기..."
											aria-label="댓글 달기..." aria-describedby="button-addon2">
										<button class="btn btn-outline-secondary" type="button"
											id="button-addon2">게시</button>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<!-- <button type="button" class="btn btn-primary" data-bs-target="#modalPhoto">Previous</button> -->
								<a class="btn btn-primary" data-bs-toggle="modal"
									href="#modalVideo" role="button">Return</a>
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 다이어리 모달 -->
				<div class="modal fade" id="modalDiary" tabindex="-1"
					aria-labelledby="modalDiaryLabel" aria-hidden="true">
					<div
						class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="modalDiaryLabel">Photo Gallery</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body" style="height: 60vh;">
								<div class="container-fluid">
									<div class="row">
										<div class="col-md-4" style="width: 100%; height: 300px;"
											id="diary-board">
											<p style="color: #b4b2b2">다이어리</p>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<label class="my-button">글쓰기
									<button type="button" id="show-footer-button"
										data-bs-toggle="modal" data-bs-target="#diary-write"></button>
								</label>
							</div>
						</div>
					</div>
				</div>
				<!-- 다이어리 글쓰기 모달 -->
				<div class="modal fade" id="diary-write" tabindex="-1"
					aria-labelledby="modalPhotoLabel" aria-hidden="true">
					<div
						class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="modalPhotoLabel">Photo Gallery</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body" style="height: 60vh;">
								<div id="footer">
									<form id="diary" onsubmit="addPost(); return false; action="diaryPost.do">
										<input type="text" id="title-input" name="diaryText"
											placeholder="제목을 입력해 주세요..." style="border-color: #b4b2b2;" />
										<br>
										<textarea id="content-input" placeholder="내용을 입력해 주세요..."
											style="border-color: #b4b2b2;"></textarea>
										<br>
									</form>
								</div>
							</div>
							<div class="modal-footer">
								<label class="my-button">목록
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#modalDiary"></button>
								</label> <label class="my-button">게시하기
									<button type="submit" form="diary" data-bs-toggle="modal"
										data-bs-target="#modalDiary"></button>
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 포토갤러리 & 비디오갤러리 -->
			<!-- 포토갤러리 -->
			<div class="col-lg-4 mt-4">
				<div id="photoCarousel" class="carousel slide"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<c:choose>
							<c:when test="${empty photoList}">
								<div class="no-item">게시물이 없습니다.</div>
							</c:when>
							<c:otherwise>
								<c:forEach var="photoItem" items="${photoList}"
									varStatus="status">
									<c:choose>
										<c:when test="${status.index == 0}">
											<div class="carousel-item active">
												<div class="ratio ratio-1x1"
													style="background-color: black; display: flex; align-items: center; justify-content: center;">
													<img class="img-fluid image-click" data-pnum="${photoItem.getPNum()}" data-bs-toggle="modal" 
														data-bs-target="#detailPhoto${photoItem.getPNum()}"
														src="https://naso-bucket.s3.ap-northeast-2.amazonaws.com/${photoItem.mediaS3} "
														alt="
												Image 1"
														style="max-width: 100%; max-height: 100%; object-fit: contain;">
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="carousel-item">
												<div class="ratio ratio-1x1"
													style="background-color: black; display: flex; align-items: center; justify-content: center;">
													<img class="img-fluid image-click" data-pnum="${photoItem.getPNum()}" data-bs-toggle="modal"
														data-bs-target="#detailPhoto${photoItem.getPNum()}"
														src="https://naso-bucket.s3.ap-northeast-2.amazonaws.com/${photoItem.mediaS3}"
														alt="
												Image 1"
														style="max-width: 100%; max-height: 100%; object-fit: contain;">
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<!-- 추가적인 이미지 태그를 원하는 만큼 추가 가능 -->
					</div>
					<!-- 포토 갤러리 슬라이드 버튼 -->
					<button class="carousel-control-prev" type="button"
						data-bs-target="#photoCarousel" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#photoCarousel" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
			<!-- 비디오 갤러리 -->
			<div class="col-lg-4 mt-4">
				<div id="videoCarousel" class="carousel slide"
					data-bs-ride="carousel">
					<div class="carousel-inner">

						<c:choose>
							<c:when test="${empty videoList}">
								<div class="no-item">게시물이 없습니다.</div>
							</c:when>
							<c:otherwise>
								<c:forEach var="videoItem" items="${videoList}">
									<div class="carousel-item active">
										<div class="ratio ratio-1x1"
											style="background-color: black; display: flex; align-items: center; justify-content: center;">
											<img class="img-fluid" type="button" data-bs-toggle="modal"
												data-bs-target="#detailPhoto${videoItem.getPNum()}"
												src="https://naso-bucket.s3.ap-northeast-2.amazonaws.com/${videoItem.mediaS3}"
												alt="
												Image 1"
												style="max-width: 100%; max-height: 100%; object-fit: contain;">
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- 비디오 갤러리 슬라이드 버튼 -->
					<button class="carousel-control-prev" type="button"
						data-bs-target="#videoCarousel" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#videoCarousel" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
	</main>
	<!-- 좌측 하단 메뉴 -->
	<div class="btn-group dropup position-fixed bottom-0 start-0">
		<!-- 메뉴버튼 -->
		<button type="button" class="btn btn-basic dropdown-toggle-split"
			data-bs-toggle="dropdown" aria-expanded="false"
			data-bs-auto-close="false">
			<i class="fas fa-bars"></i>
		</button>
		<!-- data-bs-container="
									body" data-bs-toggle="popover" data-bs-placement="right"
									data-bs-content="Right popover"-->
		<!-- 메뉴목록 -->
		<ul class="dropdown-menu shadow-sm">
			<!-- 프로필-->
			<li><a href="#" class="dropdown-item text-center px-0"><i
					class="fa-solid fa-user"></i></a></li>
			<!-- 고객센터-->
			<li><a href="#" class="dropdown-item text-center px-0"><img
					src="../images/cs11.png" alt="" style="width: 20px; height: 20px;"></a></li>
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
	<script src="../js/main.js"></script>
	<script src="../js/reply.js"></script>
	<script src="../js/fileupload.js"></script>
	<script src="../js/putFile.js"></script>
</body>
</html>