/**
 *  결제 + 결제 취소 함수 담김.
 */

 // getToken 요청을 보내는 함수
function getToken(callback) {
  // 요청할 URL
  var url = 'https://api.iamport.kr/users/getToken';

  // 요청에 필요한 데이터
  var requestData = {
    imp_key: '3130886717734530',
    imp_secret: 'rVnn8cuI8Z8AH90vW5X9KWBZpivPI6sis3DjtQpGRAFpxHA4mNgR1f0RmBiNxZwnWgI5auWuWLCYIhx3'
  };

  // 요청 설정
  var requestConfig = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(requestData)
  };

  // getToken 요청 보내기
  fetch(url, requestConfig)
    .then(response => response.json())
    .then(data => {
      // 요청이 성공적으로 처리된 경우
      if (data.code === 0) {
        var accessToken = data.response.access_token;
        // 콜백 함수 호출하며 토큰 전달
        callback(accessToken);
      } else {
        // 요청이 실패한 경우
        console.log('getToken 요청 실패:', data.message);
      }
    })
    .catch(error => {
      console.error('getToken 요청 에러:', error);
    });
}

// requestPay 함수
function requestPay() {
  // getToken 요청 보내고, 토큰을 받아온 후에 결제 요청을 수행
  getToken(function(accessToken) {
    // 결제 요청에 필요한 데이터
    var paymentData = {
      pg: 'kicc',
      pay_method: 'card',
      merchant_uid: $("#merchant_uid").text(),
      name: $("#name").text(),
      amount: 100,
      buyer_name: $("#userid").text(),
      buyer_tel: $("#phone").text(),
      token: accessToken // 받아온 토큰 추가
    };

    // 결제 요청 설정
    var paymentConfig = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(paymentData)
    };

    // 결제 요청 보내기
    fetch('/verifyIamport/imp86222350', paymentConfig)
      .then(response => response.json())
      .then(data => {
        // 요청이 성공적으로 처리된 경우
        if (data.success) {
          alert('결제 확인 완료!!!');
        } else {
          // 요청이 실패한 경우
          alert('결제 실패!!!!!');
          console.log(data);
        }
      })
      .catch(error => {
        console.error('결제 요청 에러:', error);
      });
  });
}

// cancelPay 함수
function cancelPay() {
  // getToken 요청 보내고, 토큰을 받아온 후에 결제 취소 요청을 수행
  getToken(function(accessToken) {
    // 결제 취소 요청에 필요한 데이터
    var cancelData = {
      imp_uid: "결제 취소할 imp_uid를 입력하세요"
    };

    // 결제 취소 요청 설정
    var cancelConfig = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(cancelData)
    };

    // 결제 취소 요청 보내기
    fetch('/cancelIamport', cancelConfig)
      .then(response => response.json())
      .then(data => {
        // 요청이 성공적으로 처리된 경우
        if (data.success) {
          alert('결제 취소 완료!!!');
        } else {
          // 요청이 실패한 경우
          alert('결제 취소 실패!!!!!');
          console.log(data);
        }
      })
      .catch(error => {
        console.error('결제 취소 요청 에러:', error);
      });
  });
}