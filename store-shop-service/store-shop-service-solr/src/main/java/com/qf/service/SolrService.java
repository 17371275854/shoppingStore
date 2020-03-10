package com.qf.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolrService {
    @Autowired
    private SolrClient solrClient;

    public void selectByKeyWord(String key_word) {
        List<SolrInputDocument> docs = new ArrayList<>();

    }
}
//    List<TProductSearchDTO> products = mapper.selectAll();
//
//    //存放所有的doc的集合
//    List<SolrInputDocument> docs = new ArrayList<>();
//
////遍历products集合，将每一个product对象封装成一个SolrInputDocument对象
//        for (TProductSearchDTO product : products) {
//                SolrInputDocument doc = new SolrInputDocument();
//                doc.setField("id",product.getId());
//                doc.setField("t_product_name",product.gettProductName());
//                doc.setField("t_product_sale_price",product.gettProductSalePrice().floatValue());
//                doc.setField("t_product_pimage",product.gettProductPimage());
//                doc.setField("t_product_pdesc",product.gettProductPdesc());
//                //将doc对象存入到集合中
//                docs.add(doc);
//                }
//                //将该集合添加到solr库中
//                try {
//                solrClient.add(docs);
//                solrClient.commit();//不要忘了
//                } catch (SolrServerException e) {
//                e.printStackTrace();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }