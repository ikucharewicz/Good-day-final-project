package com.example.goodday.controller;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Controller


public class HomeController implements UTFController {
    @RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html; charset=UTF-8")
    public String start() {
        return "index";
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain",
                Charset.forName("UTF-8"))));
        converters.add(stringConverter);
    }
  }



//    Akcja zwraca nazwę widoku wraz z rozszerzeniem.
////   Widok znajduje się w katalogu webapp naszej aplikacji.

//przy produces innym typem jest np:
//produces = "application/json; charset=UTF-8"

// Adnotacja @ResponseBody pod Requestmapping- wskazuje, że nie przekazujemy informacji do widoku tylko zwracamy zawartość bezpośrednio.
//W zawartości możemy umieścić bezpośrednio HTML i zostanie on poprawnie przetworzony.




