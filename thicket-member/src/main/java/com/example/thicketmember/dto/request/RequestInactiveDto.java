package com.example.thicketmember.dto.request;

import com.example.thicketmember.enumerate.MemberStatus;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class RequestInactiveDto {

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문, 숫자, 특수 기호가 적어도 1개 이상씩 포함된 8~20자의 문자여야합니다.")
    private String pswd;
}
