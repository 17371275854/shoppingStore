package com.qf.controller;

import com.qf.service.SolrService;
import com.qf.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SolrController {

    @Autowired
    SolrService solrService;

    @RequestMapping("solr")
    public List<ProductVO> selectByKeyWord(String key_word, Integer page) {
        List<ProductVO> productVOS = solrService.selectByKeyWord(key_word, page);
        return productVOS;
    }

}
