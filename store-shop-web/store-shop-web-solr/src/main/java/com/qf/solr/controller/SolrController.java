package com.qf.solr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solr")
public class SolrController {

    @RequestMapping("/selectByKeyWord")
    public void selectByKeyWord(String key_word) {

    }
}
