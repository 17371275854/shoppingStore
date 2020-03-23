package com.qf.init;

import com.qf.mapper.ProductVOMapper;
import com.qf.vo.ProductVO;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SolrInit {

    @Autowired
    private ProductVOMapper productVOMapper;
    private static SolrInit solrInit;
    static {
        solrInit = new SolrInit();
    }

    public static SolrInit getSolrInit() {
        return new SolrInit();
    }

    @Autowired
    SolrClient solrClient;
    private void init() {
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
}
