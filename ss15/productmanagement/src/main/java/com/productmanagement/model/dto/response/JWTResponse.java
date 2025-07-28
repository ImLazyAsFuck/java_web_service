package com.productmanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTResponse {
    private String accessToken;
    
    private String username;
    private String email;
    private String fullName;
    private Boolean status;
    private List<String> roles;
}
