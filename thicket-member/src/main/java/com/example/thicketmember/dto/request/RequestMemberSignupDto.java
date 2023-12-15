package com.example.thicketmember.dto.request;

import com.example.thicketmember.enumerate.MemberRole;
import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RequestMemberSignupDto {

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Pattern(regexp="^(?=.[a-zA-Z])(?=.\\d)[a-zA-Z\\d]{8,10}$",
            message = "비밀번호는 최소 하나의 숫자와 하나의 영어를 포함한 8~10자리로 구성해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotNull(message = "생년월일은 필수 입력 항목입니다.")
    private LocalDate birthdate;

    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    @Pattern(regexp="^(01[016789])-([0-9]{3,4})-([0-9]{4,5})$", message="전화번호 형식에 맞지 않습니다.")
    private String phoneNumber;
}