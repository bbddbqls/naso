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
  
  const putFile = file => {
    const albumBucketName = 'naso-bucket'; // S3의 버킷 이름
    const region = 'ap-northeast-2'; // 서울
    const accessKeyId = 'AKIA5OUH6UZDHFJERMYH'; // IAM에서 생성한 사용자의 accessKeyId
    const secretAccessKey = 'Bmfk5KfzPt5cV8uPxAr0+6z+KiJqXSN3MpTUZojO'; // IAM에서 생성한 사용자의 secretAccessKey
  
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
  
    const promise = upload.promise();
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
    promise.then(
      function (data) {
        console.log('Successfully uploaded photo.');
      },
      function (err) {
        return console.log('There was an error uploading your photo: ', err.message);
      }
    );
  };

  //file.key == 원본이미지파일
  