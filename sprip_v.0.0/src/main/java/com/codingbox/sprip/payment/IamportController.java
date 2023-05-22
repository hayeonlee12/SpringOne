//package com.sprip.paymentPractice2;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.siot.IamportRestClient.IamportClient;
//import com.siot.IamportRestClient.exception.IamportResponseException;
//import com.siot.IamportRestClient.request.CancelData;
//import com.siot.IamportRestClient.response.IamportResponse;
//import com.siot.IamportRestClient.response.Payment;
//
//import lombok.RequiredArgsConstructor;
//import java.util.logging.Logger;
//
//@CrossOrigin(origins = "http://localhost:9090")
//@RequiredArgsConstructor
//@RestController
//public class IamportController {
//
//    private final IamportClient iamportClient;
//    private final MemberService memberService;
//    private final OrderService orderService;
//    
//    private final static Logger LOG = Logger.getGlobal();
//    
//    @Autowired
//    private ObjectMapper objectMapper;
//    
//    @Value("${iamport.api.key}")
//    private String apiKey;
//
//    @Value("${iamport.api.secret}")
//    private String apiSecret;
//
//    @PostMapping("/getToken")
//    @ResponseBody
//    public ResponseEntity<String> getToken() {
//        String apiUrl = "https://api.iamport.kr/users/getToken";
//        
//        RestTemplate restTemplate = new RestTemplate();
//        
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        
//        // 아임포트 API 키와 시크릿 키를 요청 바디에 포함하여 전송
//        Map<String, String> requestData = new HashMap<>();
//        requestData.put("imp_key", apiKey);
//        requestData.put("imp_secret", apiSecret);
//        
//        String requestBody;
//        try {
//            requestBody = objectMapper.writeValueAsString(requestData);
//        } catch (JsonProcessingException e) {
//            // JSON 변환 예외 처리
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
//        }
//
//        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
//        
//        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
//        return response;
//    }
//
//
//
//    //생성자를 통해 REST API 와 REST API secret 입력
//    // 주문ID, 멤버ID 통해서 
//    @Autowired
//    public IamportController(MemberService memberService, OrderService orderService) {
//        this.iamportClient = new IamportClient("imp86222350", 
//        		"rVnn8cuI8Z8AH90vW5X9KWBZpivPI6sis3DjtQpGRAFpxHA4mNgR1f0RmBiNxZwnWgI5auWuWLCYIhx3");
//        this.orderService = orderService;
//        this.memberService = memberService;
//    }
//
//
//    //iamport를 이용하여 결제하기를 완료하였을때
//    @PostMapping("/verifyIamport/imp86222350")
//    public ResponseEntity<IamportResponse<Payment>> paymentByImpUid(HttpServletRequest request, @RequestBody ImpUidRequestDTO requestDTO) throws IamportResponseException, IOException {
//        LOG.info("paymentByImpUid 진입");
//        
//        String imp_uid = requestDTO.getImp_uid();
//        
//        System.out.println(imp_uid);
//        
//        if (!imp_uid.equals("imp86222350")) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//        
//        
//        IamportResponse<Payment> paymentIamportResponse = iamportClient.paymentByImpUid(imp_uid);
//        Payment payment = paymentIamportResponse.getResponse();
//        HttpSession session = request.getSession(false); //로그인이 된 사용자가 세션을 사용하고 있으므로 false 세팅
//        session.setAttribute("payment",payment);
//        session.setMaxInactiveInterval(60); //60초동안 세션을 유지
//        return ResponseEntity.ok(paymentIamportResponse);
//    }
//
//
//    //DB에 값을 넣기 위한 작업
//    @RequestMapping("/item/{orderId}/payment")
//    public ResponseEntity<?> savePayment(@PathVariable Long orderId, HttpServletRequest request, @RequestBody PaymentReqDTO paymentReqDTO) throws IamportResponseException, IOException {
//
//        HashMap<String, String> response = new HashMap<>();
//
//        HttpSession session = request.getSession(false);
//
//        CancelData cancelData = new CancelData(paymentReqDTO.getImp_uid(), true);
//
//        //챌린지 찾아오기
//        Order findOrder = (Order) orderService.findOrder(orderId);
//
//        //verifyIamport에서 세션을 만들어서 여기서 검증한 후 없애줘야함
//        //여긴 결제승인을 제대로 수행된 것이 아닐때 작동한다.
//        Payment payment = (Payment) session.getAttribute("payment");
//        if(payment == null) {
//            response.put("response","잘못된 접근입니다.");
//            return ResponseEntity.ok(response);
//        }
//
//        //성공적으로 작동하면 try 안에 각 프로젝트의 payment table 에 알맞게 값을 넣어주는 로직을 작성하면된다.
//        try {
//            memberService.savePayment(paymentReqDTO.getImp_uid() ,paymentReqDTO.getPrice(), findOrder);
//            response.put("response","1");
//            return ResponseEntity.ok(response);
//        }
//        //Iamport 의 경우 오류가 발생해도 결제가 이루어지므로(돈이 빠져나간다는 소리) cancelPaymentByImpUid 함수를 이용해 꼭 환불이 될 수 있도록 해야한다.
//        //cancelData 는 위에서 imp_uid 를 이용하여 불러왔으므로 그냥 함수의 매개변수값으로 바로 넣어주면 된다.
//        catch (Exception e) {
//            iamportClient.cancelPaymentByImpUid(cancelData);
//            response.put("response","잘못된 접근입니다");
//            return ResponseEntity.ok(response);
//        }
//
//    }
//
//
//}

package com.codingbox.sprip.payment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.codingbox.sprip.member.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:9090")
@RequiredArgsConstructor
@RestController
public class IamportController {

    private final IamportClient iamportClient;
    private final MemberService memberService;
    private final OrderService orderService;
    
    private final static Logger LOG = Logger.getGlobal();
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Value("${iamport.api.key}")
    private String apiKey;

    @Value("${iamport.api.secret}")
    private String apiSecret;
    
    private String accessToken; // 액세스 토큰 저장 변수
    
    @Autowired
  public IamportController(MemberService memberService, OrderService orderService) {
      this.iamportClient = new IamportClient("imp86222350", 
      		"rVnn8cuI8Z8AH90vW5X9KWBZpivPI6sis3DjtQpGRAFpxHA4mNgR1f0RmBiNxZwnWgI5auWuWLCYIhx3");
      this.orderService = orderService;
      this.memberService = memberService;
  }

    @PostMapping("/getToken")
    @ResponseBody
    public ResponseEntity<String> getToken() {
        String apiUrl = "https://api.iamport.kr/users/getToken";
        
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // 아임포트 API 키와 시크릿 키를 요청 바디에 포함하여 전송
        Map<String, String> requestData = new HashMap<>();
        requestData.put("imp_key", apiKey);
        requestData.put("imp_secret", apiSecret);
        
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(requestData);
        } catch (JsonProcessingException e) {
            // JSON 변환 예외 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            // 요청이 성공한 경우 액세스 토큰을 저장
            accessToken = response.getBody();
        }
        return response;
    }

    @PostMapping("/verifyPayment")
    @ResponseBody
    public ResponseEntity<String> verifyPayment(@RequestBody VerifyPaymentRequestDTO requestDTO) {
        try {
            IamportResponse<Payment> paymentIamportResponse = iamportClient.paymentByImpUid(requestDTO.getImpUid());
            Payment payment = paymentIamportResponse.getResponse();
            
            if (payment.getStatus().equals("paid")) {
                // 결제가 이미 완료된 상태인 경우
                return ResponseEntity.ok("Payment verification successful. Payment already completed.");
            } else {
                // 결제 검증 성공한 경우
                // 여기에 결제 취소 로직 추가
                CancelData cancelData = new CancelData(requestDTO.getImpUid(), true);
                HttpHeaders cancelHeaders = new HttpHeaders();
                cancelHeaders.setContentType(MediaType.APPLICATION_JSON);
                cancelHeaders.setBearerAuth(accessToken); // 액세스 토큰 설정
                
                HttpEntity<CancelData> cancelRequest = new HttpEntity<>(cancelData, cancelHeaders);
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> cancelResponse = restTemplate.postForEntity(
                        "https://api.iamport.kr/payments/cancel", cancelRequest, String.class);
                
                if (cancelResponse.getStatusCode() == HttpStatus.OK) {
                    // 결제 취소 요청이 성공한 경우
                    return ResponseEntity.ok("Payment verification successful. Payment cancelled.");
                } else {
                    // 결제 취소 요청이 실패한 경우
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment cancellation failed.");
                }
            }
        } catch (IamportResponseException | IOException e) {
            // 결제 검증 예외 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment verification failed.");
        }
    }

    
}