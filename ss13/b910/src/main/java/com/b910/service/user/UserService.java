package com.b910.service.user;

import com.b910.model.dto.request.UserLogin;
import com.b910.model.dto.request.UserRegister;
import com.b910.model.dto.response.JWTResponse;
import com.b910.model.entity.User;

public interface UserService{
    User registerUser(UserRegister userRegister);

    JWTResponse login(UserLogin userLogin);

    User editUser(User user);

    User getCurrentUser();
}
