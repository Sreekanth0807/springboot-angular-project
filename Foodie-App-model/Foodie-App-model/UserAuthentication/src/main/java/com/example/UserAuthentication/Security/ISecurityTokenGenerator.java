package com.example.UserAuthentication.Security;

import com.example.UserAuthentication.domain.User;

public interface ISecurityTokenGenerator {
    String createToken(User user);
}
