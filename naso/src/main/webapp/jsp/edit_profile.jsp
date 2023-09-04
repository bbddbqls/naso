<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.naso.member.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/register.css">
</head>


<body>
	<div class="container">
		<form action="/member/profileUpload.do" method="post">
			<div class="row">
				<div class="col-3"></div>
				<div class="col-3 shadow mt-5 me-3">


					<div class="d-flex flex-column align-items-center pt-2">
						<h3>회원정보 수정</h3>
<!-- 						<div class="d-flex flex-column align-items-center pt-2">
							<label for="uploadInput">프로필 사진 업로드</label> <input type="file"
								id="uploadInput" style="display: none;" name="memberImg">
							<img id="uploadedImage" src="../images/icon/person-circle.svg"
								alt="" class="mb-3" style="width: 50px; height: 50px;">
							<button class="btn btn-outline-secondary" type="button"
								id="uploadButton">Upload</button>
						</div> -->

						<script>
							document.getElementById('uploadButton')
									.addEventListener(
											'click',
											function() {
												document.getElementById(
														'uploadInput').click();
											});

							document
									.getElementById('uploadInput')
									.addEventListener(
											'change',
											function(e) {
												var file = e.target.files[0];
												var reader = new FileReader();

												reader.onload = function(event) {
													document
															.getElementById('uploadedImage').src = event.target.result;
												};

												reader.readAsDataURL(file);
											});
						</script>
					</div>

					<div class="">
						<div class="mt-2">

							<c:choose>
								<c:when test="${userList.getmGender() eq ('남자' or 'M') }">
									<span>Gender</span>
									<input type="radio" name="gender" value="남자" id="male" checked>
									<label for="male" class="form-label">Male</label>
									<input type="radio" name="gender" value="여자" id="female">
									<label for="female" class="form-label">Female</label>
								</c:when>
								<c:otherwise>
									<span>Gender</span>
									<input type="radio" name="gender" value="남자" id="male">
									<label for="male" class="form-label">Male</label>
									<input type="radio" name="gender" value="여자" id="female"
										checked>
									<label for="female" class="form-label">Female</label>
								</c:otherwise>
							</c:choose>



						</div>
					</div>
					<div class="input-info">
						<div class="form-floating">
							<!-- 수정 -->
							<input class="form-control" type="text" name="memberId" disabled
								id="signup-id" placeholder="id" value="${userList.getmUserId()}" /><label
								for="signup-id">UserId</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="password" name="memberPwd"
								id="signup-password" placeholder="password" /> <label
								for="signup-password">Password</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="password"
								id="signup-password-check" placeholder="password check"
								onchange="validatePW()" /> <label for="signup-password-check"
								id="signup-password-check-lab">Password check</label>
						</div>
						<script>
							// register.html, edit_profile3.html 비밀번호 확인
							function validatePW() {
								var pw1 = document
										.getElementById("signup-password").value
										.trim();
								var pw2 = document
										.getElementById("signup-password-check").value
										.trim();
								var lab = document
										.getElementById("signup-password-check-lab"); // 비밀번호 확인 레이블에 아이디 부여

								if (pw1 !== pw2) {
									lab.innerHTML = "Password check : 비밀번호가 일치하지 않습니다.";
									lab.style.color = "red";
								} else {
									lab.innerHTML = "Password check";
									lab.style.color = "";
								}
							}
						</script>
						<br>
						<div class="form-floating">
							<input class="form-control" type="text" name="memberName"
								id="signup-name" placeholder="name"
								value="${userList.getmName()}" /> <label for="signup-name">Name</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="email" name="memberEmail"
								id="signup-email" placeholder="email"
								value="${userList.getmEmail()}" /> <label for="signup-email">Email</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="text" name="memberPhone"
								id="signup-phone" placeholder="phone number"
								value="${userList.getmPhone()}" /> <label for="signup-phone">Phone
								number</label>
						</div>
						<p>Date of Birth</p>
						<input class="form-control" name="dateOfBirth" type="date"
							id="signup-birth" value="${userList.getmDateBirth()}" /><br>
					</div>
				</div>

				<div class="col-3 shadow mt-5 ms-3">
					<div class="d-flex flex-column align-items-center pt-2">
						<h3>mk</h3>
						<br>
					</div>
					<div class="input-info">
						<div class="form-floating">
							<input class="form-control" type="text" name="memberInstagram"
								id="Instagram-domain" placeholder="Instagram domain"
								value="${userList.getmInstagram()}" /> <label
								for="Instagram-domain">Instagram domain</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="text" name="memberKakao"
								id="Kakao-domain" placeholder="Kakao domain"
								value="${userList.getmKakao()}" /> <label for="Kakao-domain">Kakao
								domain</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="text" name="memberTiktok"
								id="Tiktok-domain" placeholder="Tiktok domain"
								value="${userList.getmTiktok()}" /> <label for="Tiktok-domain">Tiktok
								domain</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="text" name="memberYoutube"
								id="Youtube-domain" placeholder="Youtube domain"
								value="${userList.getmYoutube()}" /> <label
								for="Youtube-domain">Youtube domain</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="text" name="memberAfrica"
								id="Aftv-domain" placeholder="Aftv domain"
								value="${userList.getmAfrica()}" /> <label for="Aftv-domain">Aftv
								domain</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="text" name="memberNaver"
								id="Naver-domain" placeholder="Naver domain"
								value="${userList.getmNaver()}" /> <label for="Naver-domain">Naver
								domain</label>
						</div>
						<br>
						<div class="form-floating">
							<input class="form-control" type="text" name="memberBlog"
								id="blog-domain" placeholder="blog domain"
								value="${userList.getmBlog()}" /> <label for="Shopping-domain">blog
								domain</label>
						</div>
						<br>
					</div>
					<!-- 제출버튼 -->
					<div class="pt-4" style="text-align: center;">
						<button class="btn btn-outline-secondary" type="reset"
							onclick="javascript:history.back();">Cancel</button>
						<button class="btn btn-outline-secondary" type="submit"
							onclick="updateInfoConfirm();">Submit</button>
					</div>

				</div>

				<div class="col-3"></div>

			</div>
		</form>
	</div>
	<script src="../js/register.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>

</body>
</html>
