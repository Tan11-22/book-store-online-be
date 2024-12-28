//package com.BookStore.BookService.config;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000") // URL của frontend
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức HTTP cho phép
//                .allowedHeaders("Authorization", "Content-Type", "X-Custom-Header", "X-Requested-With") // Headers được phép
//                .allowCredentials(true); // Cho phép gửi cookies và headers bảo mật
//    }
//}