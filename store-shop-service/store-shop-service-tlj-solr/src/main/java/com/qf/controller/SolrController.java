package com.qf.controller;

import com.qf.dto.ResultBean;
import com.qf.service.ISolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("solr")
public class SolrController {

    @Autowired
    private ISolrService solrService;

    @RequestMapping("seek")
    public ResultBean seek(String keyword,Integer page){
        ResultBean resultBean = solrService.seek(keyword, page);
        return resultBean;
    }
}
