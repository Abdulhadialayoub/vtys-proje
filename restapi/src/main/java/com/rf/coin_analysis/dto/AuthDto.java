package com.rf.coin_analysis.dto;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {
    private String token;
    private UserDto user;
}
