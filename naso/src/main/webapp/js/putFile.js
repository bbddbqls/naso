const generateRandomString = length => {
	const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
	let result = '';

	for (let i = 0; i < length; i++) {
		const randomIndex = Math.floor(Math.random() * characters.length);
		result += characters.charAt(randomIndex);
	}

	return result;
};

const getFormattedCurrentDateTime = () => {
	const currentDate = new Date();
	const year = currentDate.getFullYear();
	const month = String(currentDate.getMonth() + 1).padStart(2, '0');
	const day = String(currentDate.getDate()).padStart(2, '0');
	const hours = String(currentDate.getHours()).padStart(2, '0');
	const minutes = String(currentDate.getMinutes()).padStart(2, '0');
	const seconds = String(currentDate.getSeconds()).padStart(2, '0');

	return `${year}${month}${day}${hours}${minutes}${seconds}`;
};

const putImageFile = file => {
	const albumBucketName = 'naso-main'; // S3의 버킷 이름
	const region = 'ap-northeast-2'; // 서울
	const accessKeyId = 'AKIATDSP2TEW6YQE57AK'; // IAM에서 생성한 사용자의 accessKeyId
	const secretAccessKey = 's5j6oSWvCAOoDrq8iiaobw+O5X7oRtnwUN2K3kCj'; // IAM에서 생성한 사용자의 secretAccessKey

	AWS.config.update({
		region,
		accessKeyId,
		secretAccessKey
	});

	const currentDateTime = getFormattedCurrentDateTime();
	const randomString = generateRandomString(5);
	const newFileName = `${currentDateTime}-${randomString}.jpg`; // 변경된 파일 이름


	const upload = new AWS.S3.ManagedUpload({
		params: {
			Bucket: albumBucketName,
			Key: newFileName,
			Body: file,
			ACL: 'public-read'
		}
	});

	const promise2 = upload.promise();

	promise2.then(
		data => {
			console.log('Successfully uploaded photo.');

			const form = document.getElementById('upload-image');

			const newInput = document.createElement('input');
			newInput.type = 'hidden';
			newInput.name = 'newFileName';
			newInput.value = newFileName;
			newInput.style.display = 'none';

			const newInput2 = document.createElement('input');
			newInput2.type = 'hidden';
			newInput2.name = 'originalFileName';
			newInput2.value = file.name;
			newInput2.style.display = 'none';
			// 폼에 새로운 input 추가
			form.appendChild(newInput);
			form.appendChild(newInput2);
			form.submit();
		},
		err => {
			console.log('There was an error uploading your photo:', err.message);
		}
	);

};


const putVideoFile = file => {
	const albumBucketName = 'naso-main'; // S3의 버킷 이름
	const region = 'ap-northeast-2'; // 서울
	const accessKeyId = 'AKIATDSP2TEW6YQE57AK'; // IAM에서 생성한 사용자의 accessKeyId
	const secretAccessKey = 's5j6oSWvCAOoDrq8iiaobw+O5X7oRtnwUN2K3kCj'; // IAM에서 생성한 사용자의 secretAccessKey

	AWS.config.update({
		region,
		accessKeyId,
		secretAccessKey
	});

	const currentDateTime = getFormattedCurrentDateTime();
	const randomString = generateRandomString(5);
	const newFileName = `${currentDateTime}-${randomString}.mp4`; // 변경된 파일 이름


	const upload = new AWS.S3.ManagedUpload({
		params: {
			Bucket: albumBucketName,
			Key: newFileName,
			Body: file,
			ACL: 'public-read'
		}
	});

	const promise1 = upload.promise();

	promise1.then(
		data => {
			console.log('Successfully uploaded video.');
			const form = document.getElementById('upload-video');

			const newInput = document.createElement('input');
			newInput.type = 'hidden';
			newInput.name = 'newFileName';
			newInput.value = newFileName;
			newInput.style.display = 'none';

			const newInput2 = document.createElement('input');
			newInput2.type = 'hidden';
			newInput2.name = 'originalFileName';
			newInput2.value = file.name;
			newInput2.style.display = 'none';

			form.appendChild(newInput);
			form.appendChild(newInput2);
			form.submit();
		},
		err => {
			console.log('There was an error uploading your video:', err.message);
		}
	);
};

const putProfileImgFile = file => {
	const albumBucketName = 'naso-main'; // S3의 버킷 이름
	const region = 'ap-northeast-2'; // 서울
	const accessKeyId = 'AKIATDSP2TEW6YQE57AK'; // IAM에서 생성한 사용자의 accessKeyId
	const secretAccessKey = 's5j6oSWvCAOoDrq8iiaobw+O5X7oRtnwUN2K3kCj'; // IAM에서 생성한 사용자의 secretAccessKey

	AWS.config.update({
		region,
		accessKeyId,
		secretAccessKey
	});

	const currentDateTime = getFormattedCurrentDateTime();
	const randomString = generateRandomString(5);
	const newFileName = `${currentDateTime}-${randomString}.jpg`; // 변경된 파일 이름


	const upload = new AWS.S3.ManagedUpload({
		params: {
			Bucket: albumBucketName,
			Key: newFileName,
			Body: file,
			ACL: 'public-read'
		}
	});

	const promise3 = upload.promise();

	promise3.then(
		data => {
			console.log('Successfully uploaded profile.');
			const form = document.getElementById('joinMember');

			const newInput = document.createElement('input');
			newInput.type = 'hidden';
			newInput.name = 'newFileName';
			newInput.value = newFileName;
			newInput.style.display = 'none';


			form.appendChild(newInput);
			form.submit();
		},
		err => {
			console.log('There was an error uploading your video:', err.message);
		}
	);
};

function showPreview(event) {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = function (e) {
    const previewContainer = document.getElementById('previewContainer');
    const previewImage = document.createElement('img');
    previewImage.src = e.target.result;

    previewContainer.innerHTML = '';
    previewContainer.appendChild(previewImage);
  };

  reader.readAsDataURL(file);
}

// 파일 입력(input) 요소에 이벤트 리스너 추가
const fileInput = document.getElementById('input-photo');
fileInput.addEventListener('change', showPreview);
