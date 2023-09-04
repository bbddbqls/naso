<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.naso.search.SearchDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="../css/main.css">
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
					function goMain() {
						location = "/post/my.do";
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
								style="color: #ff7f00;">&nbsp;<i class="fa fa-search"></i></span>
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

			<!-- 사이드바 -->
			<div class="col-lg-2 text-lg-start"></div>

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
							<img src="../images/icon/person-circle.svg" alt="프사" width="30px"
								height="30px" style="border-radius: 50%;">
							<!-- 아래 a 태그는 클릭시 해당 계정 아이디 값을 "userId"에 담아서 prfCtrl(ProfileController)로 넘김 -->
							<a style="text-decoration: none; color: black;"
								href="/prfCtrl?mNum=${searchResult.getMNum()}"><span>${searchResult.name }</span><span>
									(${searchResult.userId})</span></a>
							<br>
							<br>
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
					src="../images/main/cs.png" alt=""
					style="width: 20px; height: 20px;"></a></li>
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
	<script src="../js/main.js?s"></script>
</body>
</html>