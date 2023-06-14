// index.js

const button = document.getElementById('upload-button');
button.addEventListener('click', () => {
  const input = document.getElementById('input-photo');
  putFile(input.files[0]);
})