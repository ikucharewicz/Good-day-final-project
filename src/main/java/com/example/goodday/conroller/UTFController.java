package com.example.goodday.conroller;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public interface UTFController {

    //przy wstawianiu do Home Controller metody formatującej:public void configureMessageConverters z @Override
    // IDE mi podpodiedziało, żeby stworzyć taki interfejs, ale nie rozumiem dlaczego

}
