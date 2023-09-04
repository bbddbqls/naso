// index.js

const imgbutton = document.getElementById('Imageupload-button');
imgbutton.addEventListener('click', () => {
	const inputPhoto = document.getElementById('input-photo');
	putImageFile(inputPhoto.files[0]);
})

const vidbutton = document.getElementById('Videoupload-button');
vidbutton.addEventListener('click', () => {
	const inputVideo = document.getElementById('input-video');
	putVideoFile(inputVideo.files[0]);
})

/*const vidbutton = document.getElementById('Videoupload-button');
vidbutton.addEventListener('click', () => {
	const inputVideo = document.getElementById('input-video');
	putVideoFile(inputVideo.files[0]);
})*/
