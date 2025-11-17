package com.kane.library.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTRequest {

    private String email;

    private String passWord;
}
