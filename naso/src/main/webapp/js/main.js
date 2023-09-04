// 검색창 엔터 입력 이벤트
/*var searchInput = document.getElementById("search");
searchInput.addEventListener("keydown", function(event) {
  if (event.keyCode === 13) {
	event.preventDefault();
	alert("You pressed Enter. Search query: " + searchInput.value);
	// 여기에 검색 기능을 추가하면 됩니다.
  }
});*/

// 채팅버튼 클릭시 채팅창 나타나는 효과 + 텍스트 입력
var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
var popoverList = popoverTriggerList.map(function(popoverTriggerEl) {
	return new bootstrap.Popover(popoverTriggerEl, {
		html: true,
		sanitize: false
	})
})
function retrieveInputData() {
	var inputField = document.querySelector('[data-bs-toggle="popover"] + .popover .form-control');
	var inputValue = inputField.value;
	console.log('Input Value:', inputValue);
}

// 모달 기본 스크립트
var myModal = document.getElementById('myModal')
var myInput = document.getElementById('myInput')
/*myModal.addEventListener('shown.bs.modal', function() {
	myInput.focus()
})

// 하단메뉴 스크립트
const menuItems = document.querySelectorAll(".side-menu li");
menuItems.forEach((menuItem) => {
	menuItem.addEventListener("click", () => {
		// 각 메뉴 항목에 대한 동작을 수행하는 코드를 작성하세요
		const text = menuItem.querySelector("a").innerText;
		console.log(`메뉴 항목 "${text}"이(가) 클릭되었습니다.`);
	});
});
const menuToggle = document.getElementById("menu-toggle");
const sideMenu = document.getElementById("side-menu");
menuToggle.addEventListener("click", () => {
	sideMenu.classList.toggle("open");
});*/


// 포토 모달 글쓰기 창에서 사진 업로드하면 업로드한 사진 표시
var selectedFile; // 전역 변수

function previewImage(event) {
	var previewContainer = document.querySelector('.upload-preview');
	var file = event.target.files[0];
	selectedFile = file;
	var reader = new FileReader();

	reader.onloadend = function() {
		previewContainer.textContent = '';
		var image = document.createElement('img');
		image.src = reader.result;
		previewContainer.appendChild(image);

	};
}
function diaryDelete(postId) {

	window.location.href = "/post/diaryDelete.do?dnum=" + postId;
}
function postDelete(postNum) {
	window.location.href = "/post/postDelete.do?pNum=" + postNum;
}
// 

function logout() {
	console.log("실행");
	window.location.href = "/post/logout.do";
}
function goProfile(mNum) {
	console.log("실행");
	window.location.href = "/prfCtrl?mNum=" + mNum;

}


