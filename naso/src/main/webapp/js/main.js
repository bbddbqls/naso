// 검색창 엔터 입력 이벤트
var searchInput = document.getElementById("search");
searchInput.addEventListener("keydown", function(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    alert("You pressed Enter. Search query: " + searchInput.value);
    // 여기에 검색 기능을 추가하면 됩니다.
  }
});

// 채팅버튼 클릭시 채팅창 나타나는 효과 + 텍스트 입력
  var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
  var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
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
  myModal.addEventListener('shown.bs.modal', function () {
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
  });

// 다이어리 글쓰기 관련 스크립트
  var postId = 1;
  function addPost() {
    var title = document.getElementById("title-input").value;
    var content = document.getElementById("content-input").value;
    var date = new Date().toLocaleDateString("ko-KR");
    var postHTML = `
      <div class="diary-post" id="post-${postId}">
        <h2>${title}</h2>
        <p>${content}</p>
        <span class="date">${date}</span>
        <div class="edit-delete-buttons">
          <button onclick="editPost('post-${postId}')" style="border-color: #b4b2b2;">수정</button>
          <button onclick="deletePost('post-${postId}')" style="border-color: #b4b2b2;">삭제</button>
        </div>
      </div>
    `;
    document.getElementById("diary-board").innerHTML += postHTML;
    postId++;
  }
  function deletePost(postId) {
    var postElement = document.getElementById(postId);
    if (postElement) {
      postElement.remove();
    }
  }
  function editPost(postId) {
    var postElement = document.getElementById(postId);
    if (postElement) {
      var h2Element = postElement.querySelector("h2");
      var pElement = postElement.querySelector("p");

      // Create input fields for editing the title and content
      var newTitleInput = document.createElement("input");
      newTitleInput.type = "text";
      newTitleInput.value = h2Element.innerText;

      var newContentInput = document.createElement("textarea");
      newContentInput.value = pElement.innerText;

      // Create a save button
      var saveButton = document.createElement("button");
      saveButton.innerText = "Save";
      saveButton.addEventListener("click", function () {
        // Update the title and content with the new values
        h2Element.innerText = newTitleInput.value;
        pElement.innerText = newContentInput.value;

        // Remove the input fields and save button
        postElement.removeChild(newTitleInput);
        postElement.removeChild(newContentInput);
        postElement.removeChild(saveButton);
      });

      // Append the input fields and save button to the post element
      postElement.appendChild(newTitleInput);
      postElement.appendChild(newContentInput);
      postElement.appendChild(saveButton);
    }
  }
  
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

// 
function uploadImage(event) {
  event.preventDefault();
  var fileInput = document.querySelector('input[type="file"]');
  var file = fileInput.files[0];

  // 이미지 업로드 처리 로직을 추가하세요
  if (file) {
    console.log('파일 업로드:', file);
    // 업로드 처리를 진행하세요
  } else {
    console.log('파일이 선택되지 않았습니다.');
  }
}