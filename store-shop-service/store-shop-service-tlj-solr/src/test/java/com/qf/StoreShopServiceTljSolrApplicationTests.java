package com.qf;

import com.qf.dto.ResultBean;
import com.qf.mapper.SolrMapper;
import com.qf.service.ISolrService;
import com.qf.vo.SolrProductVO;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreShopServiceTljSolrApplicationTests {

    @Autowired(required = false)
    private SolrMapper solrMapper;

    @Autowired
    private SolrClient solrClient;

    @Test
    public void test1(){
//        <id column="id" jdbcType="BIGINT" property="id"/>
//        <result column="t_product_name" jdbcType="VARCHAR" property="tProductName"/>
//        <result column="t_product_type" jdbcType="VARCHAR" property="tProductType"/>
//        <result column="t_product_sale_price" jdbcType="DECIMAL" property="tProductSalePrice"/>
//        <result column="t_product_pimage" jdbcType="VARCHAR" property="tProductPimage"/>
//        <result column="t_product_total_sales" jdbcType="BIGINT" property="totalSales"/>
        //从数据库中获取数据
        List<SolrProductVO> solrProducts = solrMapper.selectAll();

        //存放所有的doc的集合
        List<SolrInputDocument> docs = new ArrayList<>();

        //遍历products集合，将每一个product对象封装成一个SolrInputDocument对象
        for (SolrProductVO solrProduct : solrProducts) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id",solrProduct.getId());
            doc.setField("t_product_name",solrProduct.getTProductName());
            doc.setField("t_product_type",solrProduct.getTProductType());
            doc.setField("t_product_sale_price",solrProduct.getTProductSalePrice().floatValue());
            doc.setField("t_product_pimage",solrProduct.getTProductPimage());
            doc.setField("t_product_total_sales",solrProduct.getTProductTotalSales());
            //将doc对象存入到集合中
            docs.add(doc);
        }

        //将该集合添加到solr库中
        try {
            solrClient.add(docs);
            solrClient.commit();//不要忘了
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private ISolrService solrService;
    @Test
    public void Test02(){
        ResultBean resultBean = solrService.seek("手机", 1);
        System.out.println(resultBean);
    }

    @Test
    public void contextLoads() {
    }

}
