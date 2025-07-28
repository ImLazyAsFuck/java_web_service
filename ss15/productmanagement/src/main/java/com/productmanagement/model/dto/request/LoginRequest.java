package com.productmanagement.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest{
    private String username;
    private String password;

    public Object getIpAddress(){
        return null;
    }
}
