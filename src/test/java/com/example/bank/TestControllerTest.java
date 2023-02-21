package com.example.bank;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class TestControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testGetMappingRest(){
        String url = "http://localhost:8080/test/mive/narengi/2?name='Maryam'";
        String str=restTemplate.exchange(url,
                HttpMethod.GET,null,String.class).getBody();
        System.out.println(str);
    }


    @Test
    public void testGetMappingRes3t(){
        String url = "http://localhost:8080/test/mive/narengi/{id}";

        String urlTem = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", "{x}")
                .encode()
                .toUriString();

        Map<String,Object> map = new HashMap();
        map.put("x","Maryam");
        map.put("id",2L);

        String str=restTemplate.exchange(urlTem,
                HttpMethod.GET,null,String.class,map).getBody();
        System.out.println(str);
    }

    @Test
    public void testGetMappingRest2(){

        String url = "http://localhost:8080/test/mive/narengi/{id}?name=Maryam";
        Map<String,Object> map = new HashMap();
        map.put("name","Maryam");
        map.put("id",2L);
      String str=restTemplate.getForObject(url,String.class,2L);//?name='Maryam'

        System.out.println(str);

    }
}
