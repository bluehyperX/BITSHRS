package com.app.hotelmanagementsystem.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String redirectUrl = request.getContextPath();
        if (userDetails.hasRole("User")) {
            redirectUrl += "/hrms/user_home";
        } else if (userDetails.hasRole("Admin")) {
            redirectUrl += "/hrms/home";
        }

        response.sendRedirect(redirectUrl);
    }
}
