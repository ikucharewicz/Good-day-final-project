package pl.project.goodday.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.project.goodday.dto.GoldenThoughtDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RestClient {
    @Value("${goldenThoughts.Api}")
    String url;
    private final RestTemplate restTemplate;


    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        initRestTemplate();
    }

    private void initRestTemplate() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
    }

    public GoldenThoughtDto[] fetchGoldenThoughts(){
        return restTemplate.getForObject(url, GoldenThoughtDto[].class);
    }
}


