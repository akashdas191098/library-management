package com.kane.library.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTResponse {

    private String jwtToken;

    private String userName;
}
