// 정보 검사
function updateInfoConfirm() {
    if (document.reg_frm.pw.value == "") {
        alert("비밀번호를 입력하세요.");
        document.reg_frm.pw.focus();
        return;
    }
    if (document.reg_frm.pw.value != document.reg_frm.pw_check.value) {
        alert("비밀번호가 일치하지 않습니다.");
        document.reg_frm.pw.focus();
        return;
    }
    if (document.reg_frm.userName.value.length == 0) {
        alert("이름은 필수 사항입니다.");
        reg_frm.userName.focus();
        return;
    }
    if (document.reg_frm.userEmail.value.length == 0) {
        alert("이메일은 필수 사항입니다.");
        reg_frm.userEmail.focus();
        return;
    }
    document.reg_frm.submit();
}
