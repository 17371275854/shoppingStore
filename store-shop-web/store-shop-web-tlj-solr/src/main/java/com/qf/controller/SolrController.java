package com.qf.controller;

import com.qf.dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("solr")
public class SolrController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("seek")
    public ResultBean seek(String keyword,Long page){
        //调用服务的提供者并获得结果并返回

        //服务的提供者返回的是一个ResultBean
        String url = "http://STORE-SHOP-SERVICE-TLJ-SOLR/solr/seek?keyword=" + keyword + "&page=" + page;
        return restTemplate.getForObject(url, ResultBean.class);

    }

}
