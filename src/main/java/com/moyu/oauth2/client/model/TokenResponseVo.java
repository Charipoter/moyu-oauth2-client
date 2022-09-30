package com.moyu.oauth2.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponseVo {

    private String tokenValue;
    private String refreshTokenValue;
    private Long expireTime;
    private Object more;

}
