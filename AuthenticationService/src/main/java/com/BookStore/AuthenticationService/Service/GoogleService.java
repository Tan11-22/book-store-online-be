package com.BookStore.AuthenticationService.Service;

import com.BookStore.AuthenticationService.dto.BookStoreResponse;

import java.util.Map;

public interface GoogleService {
    BookStoreResponse generateURL();
    BookStoreResponse loginGoogle(Map<String, String> data);
}
