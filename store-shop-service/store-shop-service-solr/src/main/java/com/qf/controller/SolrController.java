package com.qf.controller;

import com.qf.service.impl.SolrServiceImpl;
import com.qf.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class SolrController {

    @Autowired
    SolrServiceImpl solrService;

    @RequestMapping("solr")
    public List<ProductVO> selectByKeyWord(String key_word, Integer page) {
        List<ProductVO> productVOS = solrService.selectByKeyWord(key_word, page);
        return productVOS;
    }

    @RequestMapping("insert")
    public int insertOne(ProductVO productVO) {
        //测试数据
        ProductVO productVO1Test = new ProductVO();
        productVO1Test.setPid(111L);
        productVO1Test.setPname("测试手机");
        productVO1Test.setSalePrice(new BigDecimal("987"));
        productVO1Test.setCname("手机");
        productVO1Test.setTotalSales(5L);
        productVO1Test.setAppraisal(0L);
//        doc.setField("id",productVO.getPid());
//        doc.setField("pname",productVO.getPname());
//        doc.setField("salePrice",productVO.getSalePrice().floatValue());
//        doc.setField("cname",productVO.getCname());
//        doc.setField("totalSales",productVO.getTotalSales());
//        doc.setField("appraisal",productVO.getAppraisal());
        return solrService.insertOne(productVO1Test);
    }
}
