package com.example.thicketticket.service;

import com.example.thicketticket.domain.Payment;
import com.example.thicketticket.dto.request.RequestApproveKakaopayDto;
import com.example.thicketticket.dto.response.ResponseCompletedKakaopayDto;
import com.example.thicketticket.dto.response.ResponseReadyKakaopayDto;
import com.example.thicketticket.enumerate.Method;
import com.example.thicketticket.enumerate.State;
import com.example.thicketticket.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
@PropertySource("classpath:secretKey.properties")
public class KakaoPayServiceImpl implements KakaopayService{
    private final WebClient webClient;
    private final PaymentRepository paymentRepository;

    @Value("${adminKey}")
    private String adminKey;
    
    @Autowired // WebClient객체를 Builder 형태로 주입
    public KakaoPayServiceImpl(WebClient.Builder webClient, PaymentRepository paymentRepository) {
        this.webClient = webClient.baseUrl("https://kapi.kakao.com").build();
        this.paymentRepository = paymentRepository;
    }

    // 카카오 페이 서버로 결제 준비 요청 보내기
    @Override
    public ResponseReadyKakaopayDto readyKakaopay(String paymentId){
            MultiValueMap<String, String> readyForm = createReadyForm(
                    paymentRepository.findByUuid(paymentId));

            return webClient.post().uri("/v1/payment/ready")
                    .header("Authorization", "KakaoAK " + adminKey)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(readyForm))
                    .retrieve()
                    .bodyToMono(ResponseReadyKakaopayDto.class)
                    .block();
    }

    // 카카오 페이 서버로 보낼 결제 준비 api 요청에 담을 form 정보 생성
    private static MultiValueMap<String, String> createReadyForm(Payment payment) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("cid","TC0ONETIME");
        formData.add("partner_order_id",payment.getUuid());
        formData.add("partner_user_id",payment.getMemberUuid());
        formData.add("item_name",payment.getTicketUuid());
        formData.add("quantity","1");
        formData.add("total_amount","200000");
        formData.add("tax_free_amount","195000");
        formData.add("approval_url","http://localhost:3000/paymentCallback");
        formData.add("cancel_url","http://localhost:3000/paymentCallback");
        formData.add("fail_url","http://localhost:3000/paymentCallback");

        return formData;
    }

    // 카카오 페이 서버로 결제 완료 요청 보내기
    @Override
    @Transactional
    public ResponseCompletedKakaopayDto approveKakaopay(RequestApproveKakaopayDto dto) {
        Payment findPayment = paymentRepository.findByUuid(dto.getPaymentId());
        MultiValueMap<String, String> approveForm = createApproveForm(
                findPayment, dto.getTid(), dto.getPgToken());
        return webClient.post().uri("/v1/payment/approve")
                .header("Authorization", "KakaoAK " + adminKey)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(approveForm))
                .retrieve()
                .bodyToMono(ResponseCompletedKakaopayDto.class)
                .block();
    }

    // 카카오 페이 서버로 보낼 결제 완료 api 요청에 담을 form 정보 생성
    private static MultiValueMap<String, String> createApproveForm(Payment payment,
                                                                   String tid,
                                                                   String token) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("cid","TC0ONETIME");
        formData.add("tid",tid);
        formData.add("partner_order_id",payment.getUuid());
        formData.add("partner_user_id",payment.getMemberUuid());
        formData.add("pg_token",token);

        return formData;
    }
}
