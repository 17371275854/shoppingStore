package com.qf;

import com.qf.mapper.ProductVOMapper;
import com.qf.service.SolrService;
import com.qf.vo.ProductVO;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreShopServiceSolrTestApplication {

    @Autowired
    ProductVOMapper productVOMapper;

    @Autowired
    SolrClient solrClient;
    @Test
    public void content() {
        List<ProductVO> productVOS = productVOMapper.selectAll();
        //存放所有的doc的集合
        List<SolrInputDocument> docs = new ArrayList<>();

        //遍历products集合，将每一个product对象封装成一个SolrInputDocument对象
        for (ProductVO productVO : productVOS) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id",productVO.getPid());
            doc.setField("pname",productVO.getPname());
            doc.setField("salePrice",productVO.getSalePrice().floatValue());
            doc.setField("cname",productVO.getCname());
            doc.setField("totalSales",productVO.getTotalSales());
            doc.setField("appraisal",productVO.getAppraisal());
            //将doc对象存入到集合中
            docs.add(doc);
        }
        try {
            solrClient.add(docs);
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    SolrService solrService;
    @Test
    public void test1() {
        List<ProductVO> productVOS = solrService.selectByKeyWord("手机",1);
        System.out.println();
    }
}