<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인등록</title>
<link rel="stylesheet" href="../css/style.css?ssssf">

</head>
<body>
	<div class="container right-panel-active">
		<!-- Sign Up -->
		<div class="container__form container--signup">
			<form action="/member/join.do" class="form" id="form1" method="post">
				<h2 class="form__title">Sign Up</h2>
				<input type="text" placeholder="ID" name="memberId" class="input" />
				<input type="Password" placeholder="Password" name="memberPwd"
					class="input" /> <input type="text" placeholder="Name"
					name="memberName" class="input" /> <label for="gender"
					class="form-label"> <input type="radio" name="memberGender"
					value="남자" id="male">male <input type="radio"
					name="memberGender" value="여자" id="female">female
				</label>

				<button class="btn" type="submit">Sign Up</button>
			</form>
		</div>

		<!-- Sign In -->
		<div class="container__form container--signin">
			<form action="/member/login.do" class="form" id="form2" method="post">
				<h2 class="form__title">Sign In</h2>
				<input type="text" placeholder="ID" name="id" class="input" /> <input
					type="password" placeholder="Password" name="pw" class="input" />
				<button type="submit" class="btn">Sign In</button>
			</form>
		</div>

		<!-- Overlay -->
		<div class="container__overlay">
			<div class="overlay">
				<div class="overlay__panel overlay--left">
					<button class="btn" id="signIn">Sign In</button>
				</div>
				<div class="overlay__panel overlay--right">
					<button class="btn" id="signUp">Sign Up</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="../js/script.js?s"></script>
</html>
