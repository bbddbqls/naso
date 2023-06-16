// 로그인 정보 저장 스크립트
function saveLoginInfo() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    localStorage.setItem('username', username);
    localStorage.setItem('password', password);
    alert('로그인 정보가 저장되었습니다.');
  }