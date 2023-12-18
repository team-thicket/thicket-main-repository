package com.example.thicketmember.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestChangePasswordDto {

    @NotBlank(message = "현재 비밀번호는 비워둘 수 없습니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
             message = "비밀번호는 영문, 숫자, 특수 기호가 적어도 1개 이상씩 포함된 8~20자의 문자여야합니다.")
    private String currentPassword;

    @NotBlank(message = "새로운 비밀번호는 비워둘 수 없습니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
             message = "비밀번호는 영문, 숫자, 특수 기호가 적어도 1개 이상씩 포함된 8~20자의 문자여야합니다.")
    private String newPassword;
}
