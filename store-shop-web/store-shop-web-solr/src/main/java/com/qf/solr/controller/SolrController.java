package com.qf.solr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/solr")
public class SolrController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/selectByKeyWord")
    public List selectByKeyWord(String key_word, Long page) {
        String uri = String.format("http://STORE-SHOP-SERVICE-SOLR/solr?key_word=%s&page=%s", key_word, page);
        ArrayList forObject = restTemplate.getForObject(uri, ArrayList.class);
        return forObject;
    }
}
