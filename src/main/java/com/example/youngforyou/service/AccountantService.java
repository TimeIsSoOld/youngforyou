package com.example.youngforyou.service;

import org.springframework.web.multipart.MultipartFile;

public interface AccountantService {

    String importData ( MultipartFile uploadFile);
}
