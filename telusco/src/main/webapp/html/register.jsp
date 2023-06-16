<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
          <form action="">
            <div class="d-flex flex-column align-items-center pt-2">
              <h3>Sign Up</h3>
              <img src="../images/icon/person-circle.svg" alt="" class="mb-3" style="width: 50px; height: 50px;">
              <button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon03">upload</button>
            </div>
            <div class="">
              <div class="mt-2">
                <span>Gender</span>
                <input type="radio" name="gender" value="남자" id="male">
                <label for="male" class="form-label">Male</label>
                <input type="radio" name="gender" value="여자" id="female">
                <label for="female" class="form-label">Female</label>
              </div>
            </div>
            <div class="input-info">
              <div class="form-floating">
                <input class="form-control" type="text" id="signup-id" placeholder="id"/>
                <label for="signup-id">Username</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="password" id="signup-password" placeholder="password"/>
                <label for="signup-password">Password</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="password" id="signup-password-check" placeholder="password check"/>
                <label for="signup-password-check">Password check</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="text" id="signup-name" placeholder="name"/>
                <label for="signup-name">Name</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="email" id="signup-email" placeholder="email"/>
                <label for="signup-email">Email</label>
              </div><br>
              <div class="form-floating">
                <input class="form-control" type="text" id="signup-phone" placeholder="phone number"/>
                <label for="signup-phone">Phone number</label>
              </div>
              <p>Date of Birth</p><input class="form-control" type="date" id="signup-birth"/><br>
            </div>
          </form>
        </div>
        <div class="col-4"></div>
      </div>
    </div>
    <script src="../js/register.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>
