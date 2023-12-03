package com.example.thicketpayment.service;

import com.example.thicketpayment.domain.Payment;
import com.example.thicketpayment.dto.response.ResponseKakaopayDto;
import com.example.thicketpayment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;

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

    @Override
    public ResponseKakaopayDto readyKakaopay(String paymentId){

        System.out.println("adminKey = " + adminKey);


        return webClient.post().uri("/v1/payment/ready")
                .header("Authorization", "KakaoAK " + adminKey)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(createReadyForm(paymentRepository.findByUuid(paymentId))))
                .retrieve()
                .bodyToMono(ResponseKakaopayDto.class)
                .block();
    }

    private static MultiValueMap<String, String> createReadyForm(Payment payment) {
        System.out.println(payment.getUuid());
        System.out.println(payment.getMemberUuid());
        System.out.println(payment.getTicketUuid());
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("cid","TC0ONETIME");
        formData.add("partner_order_id",payment.getUuid());
        formData.add("partner_user_id",payment.getMemberUuid());
        formData.add("item_name",payment.getTicketUuid());
        formData.add("quantity","1");
        formData.add("total_amount","200000");
        formData.add("tax_free_amount","5000");
        formData.add("approval_url","http://localhost:3000");
        formData.add("cancel_url","http://localhost:3000");
        formData.add("fail_url","http://localhost:3000");
        return formData;
    }

    @Override
    public void approveKakaopay() {

    }
}
