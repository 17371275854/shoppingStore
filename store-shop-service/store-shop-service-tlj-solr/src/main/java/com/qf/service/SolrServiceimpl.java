package com.qf.service;

import com.qf.dto.ResultBean;
import com.qf.vo.SolrProductVO;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceimpl implements ISolrService{

    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultBean seek(String keyword, Integer page) {
        try {
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery(keyword);
            solrQuery.set("df","t_item_keywords");
            solrQuery.setStart(page - 1);
            solrQuery.setRows(15);
            solrQuery.setHighlight(true);//开启高亮
            solrQuery.addHighlightField("t_product_name");//设置高亮字段
            solrQuery.setHighlightSimplePre("<span style='color:red'>");
            solrQuery.setHighlightSimplePost("</span>");
            //执行查询，得到结果集
            QueryResponse response  = solrClient.query(solrQuery);
            ArrayList<SolrProductVO> products = new ArrayList<>();
            SolrDocumentList results = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            for (SolrDocument document  : results) {
                SolrProductVO solrProductVO = new SolrProductVO();
                String id_str = document.getFieldValue("id").toString();
                solrProductVO.setId(Long.parseLong(id_str));
                //===封装高亮数据==
                Map<String, List<String>> stringListMap = highlighting.get(id_str);
                List<String> t_product_names = stringListMap.get("t_product_name");
                String t_product_name = t_product_names.get(0);
                solrProductVO.setTProductName(t_product_name);
                //===end==
                solrProductVO.setTProductType((String) document.getFieldValue("t_product_type"));
                solrProductVO.setTProductSalePrice(new BigDecimal((Double) document.getFieldValue("t_product_sale_price")));
                solrProductVO.setTProductPimage((String) document.getFieldValue("t_product_pimage"));
                solrProductVO.setTProductTotalSales(Long.parseLong((String) document.getFieldValue("t_product_total_sales")));
                products.add(solrProductVO);
            }
            return ResultBean.success(products,"查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("查询失败");
        }
    }
}
