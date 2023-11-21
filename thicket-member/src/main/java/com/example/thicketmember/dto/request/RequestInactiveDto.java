package com.example.thicketmember.dto.request;

import com.example.thicketmember.enumerate.MemberStatus;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class RequestInactiveDto {
    String pswd;
}
