<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- DB 연결 -->
<!DOCTYPE html>
<html lang="ko-kr">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/register.css">
  </head>
  
  <body>
    <div class="container">
      <div class="row">
        <div class="col-4"></div>
        <div class="col-4 shadow mt-5 me-3">
          <form action="/member/join.do" method="post" id="joinMember">
            <div class="d-flex flex-column align-items-center pt-2">
              <h3>Sign Up</h3>
            <div class="d-flex flex-column align-items-center pt-2">
    <label for="uploadInput">프로필 사진 업로드</label>
    <input type="file" id="uploadInput" style="display: none;" name="uploadInput">
    <img id="uploadedImage" src="../images/icon/person-circle.svg" alt="" class="mb-3" style="width: 50px; height: 50px;">
    <button class="btn btn-outline-secondary" type="button" id="uploadButton">Upload</button>
</div>

<script>
    document.getElementById('uploadButton').addEventListener('click', function() {
        document.getElementById('uploadInput').click();
    });

    document.getElementById('uploadInput').addEventListener('change', function(e) {
        var file = e.target.files[0];
        var reader = new FileReader();

        reader.onload = function(event) {
            document.getElementById('uploadedImage').src = event.target.result;
        };

        reader.readAsDataURL(file);
    });
</script>
            </div>
            <div class="">
              <div class="mt-2">
                <span>Gender</span>
                <input type="radio" name="memberGender" value="남자" id="male">
                <label for="male" class="form-label">Male</label>
                <input type="radio" name="memberGender" value="여자" id="female">
                <label for="female" class="form-label">Female</label>
              </div>
            </div>
            <div class="input-info">
              <div class="form-floating">
                <input class="form-control" type="text" name="memberId" id="signup-id" placeholder="id"/>
                <label for="signup-id">Username</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="password" name="memberPwd" id="signup-password" placeholder="password"/>
                <label for="signup-password">Password</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="password" id="signup-password-check" placeholder="password check" />
                <label for="signup-password-check">Password check</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="text" name="memberName" id="signup-name" placeholder="name"/>
                <label for="signup-name">Name</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="email" name="memberEmail" id="signup-email" placeholder="email"/>
                <label for="signup-email">Email</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="text" name="memberPhone" id="signup-phone" placeholder="phone number"/>
                <label for="signup-phone">Phone number</label>
              </div>
              <p>Date of Birth</p><input class="form-control" type="date" name="dateOfBirth" id="signup-birth"/><br> 
            </div>
            <div class="text-center mb-4">
            	<button class="btn btn-outline-secondary" id="ProfileImg-upload-button">Submit</button>
            </div>
          </form>
        </div>
        <div class="col-4"></div>
      </div>
    </div>
    <script src="../js/join.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  	<script type="text/javascript" src="../js/putFile.js"></script>
  </body>
</html>

