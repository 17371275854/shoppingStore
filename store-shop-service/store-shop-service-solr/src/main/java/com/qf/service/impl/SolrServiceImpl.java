package com.qf.service.impl;

import com.qf.service.SolrService;
import com.qf.vo.ProductVO;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceImpl implements SolrService {
    @Autowired
    private SolrClient solrClient;
    @Override
    public List<ProductVO> selectByKeyWord(String key_word, Integer page) {
        try {
            SolrQuery query = new SolrQuery();
            query.set("df","store_shop_key_word");
            query.setQuery(key_word);
            query.setStart(page - 1);
            query.setRows(15);
            query.setHighlight(true);//开启高亮
            query.addHighlightField("pname");//设置高亮字段
            query.setHighlightSimplePre("<span style='color:red'>");
            query.setHighlightSimplePost("</span>");
            QueryResponse response = solrClient.query(query);
            List<ProductVO> products = new ArrayList<>();
            SolrDocumentList results = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            for (SolrDocument result : results) {
                ProductVO productVO = new ProductVO();
                String id = result.getFieldValue("id").toString();
                productVO.setPid(Long.parseLong(id));
                Map<String, List<String>> stringListMap = highlighting.get(id);
                List<String> pname = stringListMap.get("pname");
                productVO.setPname(pname.get(0));
                productVO.setSalePrice(new BigDecimal(result.getFieldValue("salePrice").toString()));
                productVO.setCname((String) result.getFieldValue("cname"));
                productVO.setTotalSales(Long.parseLong(result.getFieldValue("totalSales").toString()));
                productVO.setAppraisal(Long.parseLong(result.getFieldValue("appraisal").toString()));
                products.add(productVO);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertOne(ProductVO productVO) {
        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id",productVO.getPid());
        doc.setField("pname",productVO.getPname());
        doc.setField("salePrice",productVO.getSalePrice().floatValue());
        doc.setField("cname",productVO.getCname());
        doc.setField("totalSales",productVO.getTotalSales());
        doc.setField("appraisal",productVO.getAppraisal());
        //将doc对象存入到集合中
        try {
            solrClient.add(doc) ;
            return 1;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}