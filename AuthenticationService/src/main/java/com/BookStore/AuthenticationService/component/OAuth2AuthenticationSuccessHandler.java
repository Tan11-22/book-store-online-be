//package com.BookStore.AuthenticationService.component;
//
//import com.BookStore.AuthenticationService.jwt.JwtTokenProvider;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Component
//public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
//        String googleId = oauthUser.getAttribute("sub");
//        String email = oauthUser.getAttribute("email");
//
//        System.out.println(googleId + " " + email);
//
//        String token = tokenProvider.generateToken(authentication);
//
//        // Trả về JSON response
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        new ObjectMapper().writeValue(response.getWriter(), Map.of("token", token));
//    }
//}
