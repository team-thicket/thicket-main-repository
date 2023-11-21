package com.example.thicketmember.dto.request;

import com.example.thicketmember.enumerate.MemberStatus;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RequestInactiveDto {
    String pswd;
}
