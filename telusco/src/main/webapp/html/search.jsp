<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.naso.search.SearchDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Naso</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="../css/main.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Caveat&display=swap" rel="stylesheet">
  <script src="https://kit.fontawesome.com/0cf27f7ac1.js" crossorigin="anonymous"></script>
</head>
<body class="position-relative">  <!-- 채팅버튼 위치 잡기 위한 클래스 지정 -->
  <!-- 헤더 -->
  <header class="container-fluid mb-1">
    <div class="row">
      <div class="col-sm-2 ps-0 pt-0">
        <img src="../images/main/mainlogo.png" alt="로고" width="150px" height="165px">
      </div>
      <!-- 검색창 -->
      <div class="col-sm-8 mt-3" style="padding-top: 20px; text-align: center;">
        <div class="d-flex justify-content-center align-items-center">
          <div class="position-relative" style="width: 450px;">
          	<form action="/schCtrl">
	            <span class="position-absolute top-50 start-0 translate-middle-y" style="color: #ff7f00;">&nbsp<i class="fa fa-search"></i></span>
	            <input type="text" id="search" placeholder="Search..." name="keyword"/>
	            <input type="submit" style="display:none;">
            </form>
          </div>
         </div>
      </div>
      <!-- 로그인, 알림창 버튼 -->
      <div class="col-sm-2 mt-3" style="padding-top: 20px; padding-right: 50px; text-align: right;">
        <button type="button" class="btn btn-basic p-1" data-bs-toggle="popover" data-bs-placement="bottom" data-bs-content="Bottom popover" style="margin-bottom: 3px;">
          <i class="fa-regular fa-bell fa-2xl"></i>
        </button>
        <button type="button" class="btn btn-basic p-1" data-bs-toggle="popover" data-bs-placement="bottom" data-bs-content="Bottom popover" style="margin-bottom: 3px;">
          <i class="fa-regular fa-circle-user fa-2xl"></i>
        </button>
      </div>
  </header>
  <!-- 메인 컨텐츠 영역 -->
  <main class="container-fluid">
    <div class="row" style="text-align: center; ;">
      <span style="color: black; font-family: 'Caveat', cursive; font-size: 50px; font-weight: bold;">Naso: rami</span>
      <!-- 사이드바 -->
      <div class="col-lg-2 text-lg-start">
        <ul class="bg-white p-0">
          <!-- 모달(photo, video, diary), 팝오버(chat) 트리거 버튼-->
          <li>
            <button type="button" class="btn btn-basic py-4 ps-2 w-50 text-lg-start" data-bs-toggle="modal" data-bs-target="#modalPhoto">
              <i class="fa-solid fa-image fa-xl"></i> Photo
            </button>
          </li>
          <li>
            <button type="button" class="btn btn-basic py-4 ps-2 w-50 text-lg-start" data-bs-toggle="modal" data-bs-target="#modalVideo">
              <i class="fa-solid fa-video fa-xl"></i> Video
            </button>
          </li>
          <li>
            <button type="button" class="btn btn-basic py-4 ps-2 w-50 text-lg-start" data-bs-toggle="modal" data-bs-target="#modalDiary">
              <i class="fa-solid fa-book fa-xl"></i> Diary
            </button>
          </li>
          <!-- 연동-->
          <li class="dropdown dropend">
            <button type="button" class="btn btn-basic py-4 ps-2 w-50 text-lg-start" data-bs-toggle="dropdown" style="width: 200px;">
              <i class="fa-solid fa-envelopes-bulk fa-xl"></i> Marketing
            </button>
            <ul class="dropdown-menu">
              <!-- Dropdown menu items -->
              <!-- 카톡 -->
              <li><a href="https://www.kakaocorp.com/page/service/service/KakaoTalk" class="dropdown-item text-center px-0"><img src="../images/main/kakao.png" alt="" style="width: 20px; height: 20px;"></a></li>
              <!-- 인스타 -->
              <li><a href="https://www.instagram.com/" class="dropdown-item text-center px-0"><img src="../images/main/insta.png" alt="" style="width: 20px; height: 20px;"></a></li>
              <!-- 유튜브-->
              <li><a href="https://www.youtube.com/" class="dropdown-item text-center px-0"><img src="../images/main/youtube.png" alt="" style="width: 20px; height: 20px;"></a></li>
              <!-- 틱톡-->
              <li><a href="https://www.tiktok.com/ko-KR/" class="dropdown-item text-center px-0"><img src="../images/main/tiktok.png" alt="" style="width: 20px; height: 20px;"></a></li>
              <!-- 아프리카-->
              <li><a href="https://www.afreecatv.com/" class="dropdown-item text-center px-0"><img src="../images/main/aftv.png" alt="" style="width: 20px; height: 20px;"></a></li>
              <!-- 네이버-->
              <li><a href="https://www.naver.com/" class="dropdown-item text-center px-0"><img src="../images/main/naver.png" alt="" style="width: 20px; height: 20px;"></a></li>
              <!-- 쇼핑몰-->
              <li><a href="https://shopping.naver.com/home" class="dropdown-item text-center px-0"><img src="../images/main/show.png" alt="" style="width: 20px; height: 20px;"></a></li>
            </ul>
          </li>
          <li>
            <button type="button" class="btn btn-basic py-4 ps-2 w-50 text-lg-start" data-bs-toggle="popover" title="Popover Header" data-bs-content="
            <input type='text' class='form-control' placeholder='Type your text here'>
            <button type='button' class='btn btn-primary mt-2' onclick='retrieveInputData()'>Submit</button>
            ">
            <i class="fa-solid fa-comments fa-xl"></i> Chat
            </button>
          </li>
        </ul>
        <!-- 포토 모달 -->
        <div class="modal fade" id="modalPhoto" tabindex="-1" aria-labelledby="postDiaryLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-scrollable modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="postDiaryLabel">Photo Gallery</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="container-fluid">
                  <div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xl-4">
                    <div class="col photo-item" data-bs-toggle="modal" data-bs-target="#detailPhoto">
                      <img class="" src="../images/celeb/11.jpg" alt="">
                    </div>
                    <div class="col photo-item">
                      <img src="../images/celeb/12.jpg" alt="">
                    </div>
                    <div class="col photo-item">
                      <img src="../images/celeb/13.jpg" alt="">
                    </div>
                    <div class="col photo-item">
                      <img src="../images/celeb/14.jpg" alt="">
                    </div>
                    <div class="col photo-item">
                      <img src="../images/celeb/15.jpg" alt="">
                    </div>
                  </div>
               </div>
              </div>
              <div class="modal-footer">
                <button class="my-button" type="button" data-bs-toggle="modal" data-bs-target="#postPhoto">Post</button>
              </div>
            </div>
          </div>
        </div>
        <!-- 포토 갤러리 글쓰기 모달 -->
        <div class="modal fade" id="postPhoto" aria-hidden="true" aria-labelledby="postPhotoLabel" tabindex="-1">
          <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="postPhotoLabel">글쓰기</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="container">
                  <div class="row">
                    <div class="col-lg-6 d-flex justify-content-center" style="aspect-ratio: auto 1/1;">
                      <div class="upload-preview">
                        <label class="my-button">업로드<input class="d-none" type="file" id="input-photo" accept="image/*"
                          onchange="previewImage(event)"/></label>
                      </div>
                    </div>
                    <div class="col-lg-6 ">
                      <div>
                        <textarea name="text" id="text" cols="30" rows="10" placeholder="내용을 입력해 주세요..."></textarea>
                        <label><input type="radio" name="option" value="1" checked>모두 공개</label>
                        <label><input type="radio" name="option" value="2">친구만 공개</label>
                      </div>
                      <div style="height: 300px;"></div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-target="#modalPhoto" data-bs-toggle="modal" role="button">Return</button>
                <button class="my-button" type="submit">Submit</button>
              </div>
            </div>
          </div>
        </div>
        <!-- from yubin 포토모달에서 상세보기-->
        <div class="modal fade" id="detailPhoto" tabindex="-1" aria-labelledby="detailPhotoLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-scrollable modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="detailPhotoLabel">food photo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body row select-modal">
                <div class="container col-lg-6 select-modal-img">
                  <div class="img-box">
                    <img src="../images/celeb/11.jpg" alt="">
                  </div>
                </div>
                <div class="container col-lg-6 select-modal-information" >
                  <div class="container-fluid select-modal-user">
                    <div class="user-profile-img">
                      <img src="../images/icon/person-circle.svg" alt="">
                    </div>
                    <div class="user-profile-name">
                      <p>유저이름</p>
                    </div>
                  </div>
                  <div class="container-fluid select-modal-text">
                    본문내용
                  </div>
                  <div class="container-fluid select-modal-buttons">
                    <img src="../images/main/heart.png" alt="">
                    <p>0</p>
                    <img src="../images/main/talk.png" alt="">
                    <p>0</p>
                  </div>
                  <div class="container-fluid select-modal-comment">
                    <ul>
                      <li>
                        <div class="comment-list">
                          <div class="user-profile-img">
                            <img src="../images/icon/person-circle.svg" alt="">
                          </div>
                          <div class="user-profile-name">
                            <p><strong>유저이름</strong></p>
                            <p><span class="comment">아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...</span></p>
                          </div>
                        </div>
                      </li>
                        <div class="comment-list">
                          <div class="user-profile-img">
                            <img src="../images/icon/person-circle.svg" alt="">
                          </div>
                          <div class="user-profile-name">
                            <p><strong>유저이름</strong></p>
                            <p><span class="comment">아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...</span></p>
                          </div>
                        </div>
                      </li>
                      </li>
                        <div class="comment-list">
                          <div class="user-profile-img">
                            <img src="../images/icon/person-circle.svg" alt="">
                          </div>
                          <div class="user-profile-name">
                            <p><strong>유저이름</strong></p>
                            <p><span class="comment">아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...</span></p>
                          </div>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="input-group mb-3 select-modal-entercomment">
                    <input type="text" class="form-control" placeholder="댓글 달기..." aria-label="댓글 달기..." aria-describedby="button-addon2">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2">게시</button>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <!-- <button type="button" class="btn btn-primary" data-bs-target="#modalPhoto">Previous</button> -->
                <a class="btn btn-primary" data-bs-toggle="modal" href="#modalPhoto" role="button">Return</a>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <!-- 비디오 모달 -->
        <div class="modal fade" id="modalVideo" tabindex="-1" aria-labelledby="modalVideoLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-scrollable modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="modalVideoLabel">Video Gallery</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="container-fluid">
                  <div class="row">
                    <img src="https://img.youtube.com/vi/f0eQbs0oP8Q/0.jpg"  data-bs-toggle="modal" data-bs-target="#detailVideo" alt="" style="width: 300px; height: 300px; margin-bottom: 5px;">
                    <img src="https://img.youtube.com/vi/y1ApSB0FpAM/0.jpg"  data-bs-toggle="modal" data-bs-target="#detailVideo" alt="" style="width: 300px; height: 300px;">
                    <img src="https://img.youtube.com/vi/f0eQbs0oP8Q/0.jpg"  data-bs-toggle="modal" data-bs-target="#detailVideo" alt="" style="width: 300px; height: 300px;">
                  </div>
                  <div class="row">
                    <iframe src="https://www.youtube.com/embed/y1ApSB0FpAM" frameborder="0" allowfullscreen style="width: 300px; height: 300px; margin-bottom: 5px;"></iframe>
                    <iframe src="https://www.youtube.com/embed/f0eQbs0oP8Q" frameborder="0" allowfullscreen style="width: 300px; height: 300px;"></iframe>
                    <iframe src="https://www.youtube.com/embed/y1ApSB0FpAM" frameborder="0" allowfullscreen style="width: 300px; height: 300px;"></iframe>
                  </div>
                  <div class="row">
                    <iframe src="https://www.youtube.com/embed/y1ApSB0FpAM" frameborder="0" allowfullscreen style="width: 300px; height: 300px; margin-bottom: 5px;"></iframe>
                    <iframe src="https://www.youtube.com/embed/f0eQbs0oP8Q" frameborder="0" allowfullscreen style="width: 300px; height: 300px;"></iframe>
                    <iframe src="https://www.youtube.com/embed/y1ApSB0FpAM" frameborder="0" allowfullscreen style="width: 300px; height: 300px;"></iframe>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button class="my-button" type="button" data-bs-toggle="modal" data-bs-target="#postVideo">Post</button>
              </div>
            </div>
          </div>
        </div>
        <!-- 비디오 갤러리 글쓰기 모달 -->
        <div class="modal fade" id="postVideo" aria-hidden="true" aria-labelledby="postVideoLabel" tabindex="-1">
          <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="postVideoLabel">글쓰기</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="container">
                  <div class="row">
                    <div class="col-lg-6 d-flex justify-content-center" style="aspect-ratio: auto 1/1;">
                      <div class="upload-preview">
                        <label class="my-button">업로드<input class="d-none" type="file" id="input-Video" accept="image/*"
                          onchange="previewImage(event)"/></label>
                      </div>
                    </div>
                    <div class="col-lg-6 ">
                      <div>
                        <textarea name="text" id="text" cols="30" rows="10" placeholder="내용을 입력해 주세요..."></textarea>
                        <label><input type="radio" name="option" value="1" checked>모두 공개</label>
                        <label><input type="radio" name="option" value="2">친구만 공개</label>
                      </div>
                      <div style="height: 300px;"></div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-target="#modalVideo" data-bs-toggle="modal" role="button">Return</button>
                <button class="my-button" type="submit">Submit</button>
              </div>
            </div>
          </div>
        </div>
        <!-- from yubin 비디오 모달에서 상세보기-->
        <div class="modal fade" id="detailVideo" tabindex="-1" aria-labelledby="detailVideoLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-scrollable modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="detailVideoLabel">video</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body row select-modal">
                <div class="container col-lg-6 select-modal-img">
                  <div class="img-box">
                    <video controls autoplay muted loop>
                      <source src="../video/3.mp4" type="video/mp4">
                    </video>
                  </div>
                </div>
                <div class="container col-lg-6 select-modal-information" >
                  <div class="container-fluid select-modal-user">
                    <div class="user-profile-img">
                      <img src="../images/icon/person-circle.svg" alt="">
                    </div>
                    <div class="user-profile-name">
                      <p>유저이름</p>
                    </div>
                  </div>
                  <div class="container-fluid select-modal-text">
                    본문내용
                  </div>
                  <div class="container-fluid select-modal-buttons">
                    <img src="../images/main/heart.png" alt="">
                    <p>0</p>
                    <img src="../images/main/talk.png" alt="">
                    <p>0</p>
                  </div>
                  <div class="container-fluid select-modal-comment">
                    <ul>
                      <li>
                        <div class="comment-list">
                          <div class="user-profile-img">
                            <img src="../images/icon/person-circle.svg" alt="">
                          </div>
                          <div class="user-profile-name">
                            <p><strong>유저이름</strong></p>
                            <p><span class="comment">아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...</span></p>
                          </div>
                        </div>
                      </li>
                        <div class="comment-list">
                          <div class="user-profile-img">
                            <img src="../images/icon/person-circle.svg" alt="">
                          </div>
                          <div class="user-profile-name">
                            <p><strong>유저이름</strong></p>
                            <p><span class="comment">아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...</span></p>
                          </div>
                        </div>
                      </li>
                      </li>
                        <div class="comment-list">
                          <div class="user-profile-img">
                            <img src="../images/icon/person-circle.svg" alt="">
                          </div>
                          <div class="user-profile-name">
                            <p><strong>유저이름</strong></p>
                            <p><span class="comment">아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...아아아 너무 어렵다...</span></p>
                          </div>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="input-group mb-3 select-modal-entercomment">
                    <input type="text" class="form-control" placeholder="댓글 달기..." aria-label="댓글 달기..." aria-describedby="button-addon2">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2">게시</button>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <!-- <button type="button" class="btn btn-primary" data-bs-target="#modalPhoto">Previous</button> -->
                <a class="btn btn-primary" data-bs-toggle="modal" href="#modalVideo" role="button">Return</a>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <!-- 다이어리 모달 -->
        <div class="modal fade" id="modalDiary" tabindex="-1" aria-labelledby="modalDiaryLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-scrollable modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="modalDiaryLabel">Photo Gallery</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body" style="width: 100%; height: 60vh;">
                <div class="container-fluid">
                  <div class="row">
                    <div class="col-md-4" style="width: 100%; height: 300px;" id="diary-board">
                      <p style="color:#b4b2b2">다이어리</p>
                    </div>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <label class="my-button">글쓰기
                  <button type="button" id="show-footer-button" data-bs-toggle="modal" data-bs-target="#diary-write"></button>
                </label>
              </div>
            </div>
          </div>
        </div>
        <!-- 다이어리 글쓰기 모달 -->
        <div class="modal fade" id="diary-write" tabindex="-1" aria-labelledby="postDiaryLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-scrollable modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="postDiaryLabel">Photo Gallery</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body" style="width: 100%; height: 60vh;">
                <div id="footer">
                  <form id="diary" onsubmit="addPost(); return false;">
                    <input type="text" id="title-input" placeholder="제목을 입력해 주세요..." style="border-color: #b4b2b2;" />
                    <br>
                    <textarea id="content-input" placeholder="내용을 입력해 주세요..." style="border-color: #b4b2b2;"></textarea>
                    <br>
                  </form>
                </div>
              </div>
              <div class="modal-footer">
                <label class="my-button">목록<button type="button" data-bs-toggle="modal" data-bs-target="#modalDiary"></button></label>
                <label class="my-button">게시하기<button type="submit" form="diary" data-bs-toggle="modal" data-bs-target="#modalDiary"></button></label>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 검색결과 -->
      <div class="col-lg-8">
        <h2>Search Results</h2>
        <hr>
		<c:choose>
			<c:when test="${empty searchResult }">
				<p>검색 결과가 없습니다.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="searchResult" items="${searchResult }">
			        <img src="../images/icon/person-circle.svg" alt="프사" width="30px" height="30px" style="border-radius: 50%;">
			        <!-- 아래 a 태그는 클릭시 해당 계정 아이디 값을 "userId"에 담아서 prfCtrl(ProfileController)로 넘김 -->
			        <a style="text-decoration:none; color:black;" href="/prfCtrl?userId=${searchResult.userId}"><span>${searchResult.name }</span><span> (${searchResult.userId})</span></a><br><br>
				</c:forEach>
			</c:otherwise>
		</c:choose>
      </div>
      <div class="col-lg-2"></div>
    </div>
  </main>
  <!-- 좌측 하단 메뉴 -->
  <div class="btn-group dropup position-fixed bottom-0 start-0">
    <!-- 메뉴버튼 -->
    <button type="button" class="btn btn-basic dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="false">
      <i class="fas fa-bars"></i>
    </button>
    <!-- data-bs-container="body" data-bs-toggle="popover" data-bs-placement="right" data-bs-content="Right popover" -->
    <!-- 메뉴목록 -->
    <ul class="dropdown-menu shadow-sm">
        <!-- 프로필-->
        <li><a href="#" class="dropdown-item text-center px-0"><i class="fa-solid fa-user"></i></a></li>
        <!-- 고객센터-->
        <li><a href="#" class="dropdown-item text-center px-0"><img src="../images/main/cs.png" alt="" style="width: 20px; height: 20px;"></a></li>
        <!-- 설정-->
        <li><a href="#" class="dropdown-item text-center px-0"><i class="fa-solid fa-gear"></i></a></li>
        <!-- 로그아웃-->
        <li><a href="#" class="dropdown-item text-center px-0"><i class="fa-solid fa-right-from-bracket"></i></a></li>
    </ul>
  </div>
  <!-- Javascript============================================================================================================ -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <script src="../js/main.js"></script>
</body>
</html>